package com.geektrust.ledgerapp.entities;

import com.geektrust.ledgerapp.definitions.EmiResponse;
import com.geektrust.ledgerapp.definitions.Lumpsum;

import java.util.ArrayList;

public class Borrower {
    private final String bankName;
    private final String borrowerName;
    ArrayList<Lumpsum> lumpsums;
    private final int totalRepayAmount;
    private final int emiAmount;

    public Borrower(String bankName, String borrowerName, int principalAmount,
                                   int numYears, int rateOfInterest) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.totalRepayAmount = principalAmount + Math.ceilDiv((principalAmount * rateOfInterest * numYears), 100);
        this.emiAmount = Math.ceilDiv(this.totalRepayAmount, (12 * numYears));
        lumpsums = new ArrayList<>();
    }

    public void addPayment(int emiNumber, int lumpsumAmount) {
        lumpsums.add(new Lumpsum(emiNumber, lumpsumAmount));
    }

    public EmiResponse getEmi(int emiNumber) {
        int totalAmountGiven = (emiNumber * emiAmount);
        for(Lumpsum lumpsum: lumpsums) {
            if(lumpsum.timestamp <= emiNumber) totalAmountGiven += lumpsum.amount;
        }
        totalAmountGiven = Math.min(totalAmountGiven, totalRepayAmount);
        int remainingAmount = totalRepayAmount - totalAmountGiven;
        int emisRemaining = Math.ceilDiv(remainingAmount, emiAmount);
        return new EmiResponse(totalAmountGiven, emisRemaining, bankName, borrowerName);
    }
}
