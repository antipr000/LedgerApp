package com.geektrust.ledgerapp;

import com.geektrust.ledgerapp.definitions.EmiResponse;
import com.geektrust.ledgerapp.entities.Borrower;

import java.util.HashMap;

public class LedgerStorage {
    /*
    * We cannot have more loans for a user from a bank as lump sum would not support that, to support
    * it we need to know for a lump sum against which loan we are paying for
    * */
    HashMap<String, HashMap<String, Borrower>> ledger;

    public LedgerStorage() {
        ledger = new HashMap<>();
    }

    public void addLoan(String bankName, String borrowerName, int principal, int numYears, int rateOfInterest) {
        Borrower borrower = new Borrower(bankName, borrowerName, principal, numYears, rateOfInterest);
        HashMap<String, Borrower> borrowerNameToDataMap = new HashMap<>();
        borrowerNameToDataMap.put(borrowerName, borrower);
        ledger.put(bankName, borrowerNameToDataMap);
    }

    public void payment(String bankName, String borrowerName, int lumpSum, int emiNumber) throws Exception {
        Borrower borrower = ledger.get(bankName).get(borrowerName);
        borrower.addPayment(emiNumber, lumpSum);
    }

    public EmiResponse balance(String bankName, String borrowerName, int emiNumber) {
        Borrower borrower = ledger.get(bankName).get(borrowerName);
        return borrower.getEmi(emiNumber);
    }

}
