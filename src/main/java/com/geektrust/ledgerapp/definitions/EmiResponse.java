package com.geektrust.ledgerapp.definitions;

public class EmiResponse {
    int totalAmountPaid;
    int numEmiRemaining;
    String bankName;
    String borrowerName;

    public EmiResponse(int totalAmountPaid, int numEmiRemaining, String bankName, String borrowerName) {
        this.totalAmountPaid = totalAmountPaid;
        this.numEmiRemaining = numEmiRemaining;
        this.bankName = bankName;
        this.borrowerName = borrowerName;
    }

    public void printEmi() {
        System.out.println(bankName + " " + borrowerName + " " + totalAmountPaid + " " + numEmiRemaining);
    }

    public String getString() {
        return bankName + " " + borrowerName + " " + totalAmountPaid + " " + numEmiRemaining;
    }
}
