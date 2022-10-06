package com.freight.mate.connote.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConnoteNumberRequest {

    @NotNull
    private String carrierName;

    @NotNull
    private String accountNumber;

    private int digits;

    @NotNull
    private Long lastUsedIndex;

    @NotNull
    private Long rangeStart;

    @NotNull
    private Long rangeEnd;
}
