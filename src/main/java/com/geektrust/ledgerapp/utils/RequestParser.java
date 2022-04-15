package com.geektrust.ledgerapp.utils;

public class RequestParser {
    public String bankName;
    public String borrowerName;
    public int principalAmount;
    public int numYears;
    public int rateOfInterest;
    public int numEmis;
    public int lumpSumAmount;

    public RequestParser() {
        bankName = "";
        borrowerName = "";
        principalAmount = 0;
        numYears = 0;
        rateOfInterest = 0;
        numEmis = 0;
        lumpSumAmount = 0;
    }

    public void parseRequest(String []data) {
        switch (data[0]) {
            case "LOAN" -> {
                bankName = data[1];
                borrowerName = data[2];
                principalAmount = Integer.parseInt(data[3]);
                numYears = Integer.parseInt(data[4]);
                rateOfInterest = Integer.parseInt(data[5]);
            }
            case "PAYMENT" -> {
                bankName = data[1];
                borrowerName = data[2];
                lumpSumAmount = Integer.parseInt(data[3]);
                numEmis = Integer.parseInt(data[4]);
            }
            case "BALANCE" -> {
                bankName = data[1];
                borrowerName = data[2];
                numEmis = Integer.parseInt(data[3]);
            }
            default -> {
            }
        }
    }
}
