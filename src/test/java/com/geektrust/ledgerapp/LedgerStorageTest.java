package com.geektrust.ledgerapp;

import com.geektrust.ledgerapp.definitions.EmiResponse;
import org.junit.Test;
import org.junit.Assert;

import java.util.Set;

public class LedgerStorageTest {

    @Test
    public void addsLoan_expectLedgerToBeUpdatedWithLoan() {
        LedgerStorage ledgerStorage = new LedgerStorage();
        ledgerStorage.addLoan("MBI", "Dale", 1000, 5, 4);

        // assertions
        Assert.assertEquals(1, ledgerStorage.ledger.size());
        Assert.assertEquals(Set.of("MBI"), ledgerStorage.ledger.keySet());
    }

    @Test
    public void allOperationsWorksCorrectly(){
        LedgerStorage ledgerStorage = new LedgerStorage();
        ledgerStorage.addLoan("IDIDI", "Dale", 5000, 1, 6);
        ledgerStorage.addLoan("MBI", "Harry", 10000, 3, 7);
        ledgerStorage.addLoan("UON", "Shelly", 15000, 2, 9);
        ledgerStorage.payment("IDIDI", "Dale", 1000, 5);
        ledgerStorage.payment("MBI", "Harry", 5000, 10);
        ledgerStorage.payment("UON", "Shelly", 7000, 12);

        EmiResponse expectedFirstResponse = new EmiResponse(1326, 9, "IDIDI", "Dale");
        EmiResponse firstResponse = ledgerStorage.balance("IDIDI", "Dale", 3);

        EmiResponse expectedSecondResponse = new EmiResponse(3652, 4, "IDIDI", "Dale");
        EmiResponse secondResponse = ledgerStorage.balance("IDIDI", "Dale", 6);

        EmiResponse expectedThirdResponse = new EmiResponse(15856, 3, "UON", "Shelly");
        EmiResponse thirdResponse = ledgerStorage.balance("UON", "Shelly", 12);

        EmiResponse expectedFourthResponse = new EmiResponse(9044, 10, "MBI", "Harry");
        EmiResponse fourthResponse = ledgerStorage.balance("MBI", "Harry", 12);

        // assertions
        Assert.assertEquals(expectedFirstResponse.getString(), firstResponse.getString());
        Assert.assertEquals(expectedSecondResponse.getString(), secondResponse.getString());
        Assert.assertEquals(expectedThirdResponse.getString(), thirdResponse.getString());
        Assert.assertEquals(expectedFourthResponse.getString(), fourthResponse.getString());
    }

}
