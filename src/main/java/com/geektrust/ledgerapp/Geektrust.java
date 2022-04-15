package com.geektrust.ledgerapp;

import com.geektrust.ledgerapp.definitions.EmiResponse;
import com.geektrust.ledgerapp.utils.RequestParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Geektrust {
    public static void main(String[] args) {
        String filepath = args[0];
        LedgerStorage ledgerStorage = new LedgerStorage();
        RequestParser requestParser = new RequestParser();
        try{
            File file = new File(filepath);
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] lineItems = line.split(" ");

                requestParser.parseRequest(lineItems);

                switch (lineItems[0]) {
                    case "LOAN" -> ledgerStorage.addLoan(
                            requestParser.bankName,
                            requestParser.borrowerName,
                            requestParser.principalAmount,
                            requestParser.numYears,
                            requestParser.rateOfInterest);
                    case "PAYMENT" ->
                            ledgerStorage.payment(requestParser.bankName, requestParser.borrowerName, requestParser.lumpSumAmount, requestParser.numEmis);
                    case "BALANCE" -> {
                        EmiResponse response = ledgerStorage.balance(requestParser.bankName,
                                requestParser.borrowerName,
                                requestParser.numEmis);
                        response.printEmi();
                    }
                }
            }
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
}
