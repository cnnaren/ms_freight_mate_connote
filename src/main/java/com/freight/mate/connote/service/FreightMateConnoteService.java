package com.freight.mate.connote.service;

import com.freight.mate.connote.api.ConnoteNumberRequest;
import com.freight.mate.connote.api.ConnoteNumberResponse;
import com.freight.mate.connote.api.Status;
import com.freight.mate.connote.exception.MicroserviceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FreightMateConnoteService {

    public ConnoteNumberResponse generateConsignmentNumber(ConnoteNumberRequest connoteNumberRequest, HttpHeaders headers) {
        validateIndexRange(connoteNumberRequest.getLastUsedIndex(), connoteNumberRequest.getRangeStart(), connoteNumberRequest.getRangeEnd());
        Long nextIndex = connoteNumberRequest.getLastUsedIndex() + 1;
        String totalDigits = String.format("%0"+connoteNumberRequest.getDigits()+"d", nextIndex);
        int checkSum = calculateCheckSum(totalDigits);
        String consignmentNumber = "FMCC"+connoteNumberRequest.getAccountNumber()+totalDigits+checkSum;
        return ConnoteNumberResponse.builder().consignmentNumber(consignmentNumber).build();
    }

    private void validateIndexRange(Long index, Long rangeStart, Long rangeEnd) {
        if (!(index >= rangeStart) || !(index <= rangeEnd)) {
            log.info("The last used index in the request is out of range ");
            throw new MicroserviceException("The index provided is out of range", Status.BAD_REQUEST);
        }
    }

    private int calculateCheckSum(String totalDigits) {
        int checkSum = 0;
        int oddSum = 0;
        int evenSum = 0;
        String[] totalDigitsArray = totalDigits.split("");
        for (int i = totalDigitsArray.length -1; i >=0; i--) {
            int intValue = Integer.parseInt(totalDigitsArray[i]);
            if (i % 2 == 0) {
                evenSum += intValue;
            }
            else {
                oddSum += intValue;
            }
        }
        int sum = evenSum*7 + oddSum*3;
        int multiple = sum + (10 - sum % 10);
        checkSum = multiple - sum;
        return checkSum;
    }

}
