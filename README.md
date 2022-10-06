# Freightmate Restful Services!

This project provides the following API and is developed using spring boot, gradle.

1. An API that allows clients to generate consignment number using the specified rules.

## Project Repo URL
https://github.com/cnnaren/ms_freight_mate_connote.git

# Follow the below steps to build and run this application:

1. Clone the project and import the same in an IDE (IntelliJ/STS).
2. Build the project from terminal using the command **./gradlew clean build** from the terminal
3. After build completes, run the jar file in the target folder using the command

   #### java -jar build/libs/ms_freight_mate_connote-1.0.jar

4. The microservices will run on the port 8090 using the context path **/freight-mate **
7. Refer to the below curl commands. This curl request can be imported into postman and click send button

## Testing RESTful Services

1. Generate consigment number:
   $ curl --location --request POST 'http://localhost:8090/freight-mate/v1/consignment/generate' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "carrierName":"FreightmateCourierCo",
   "accountNumber":"123ABC",
   "digits":10,
   "lastUsedIndex":19605,
   "rangeStart":19000,
   "rangeEnd":20000
   }'


