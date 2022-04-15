package com.geektrust.ledgerapp.utils;

import org.junit.Assert;
import org.junit.Test;

public class RequestParserTest {
    @Test
    public void parseRequestCorrectly(){
        RequestParser requestParser = new RequestParser();
        String query = "LOAN IDIDI Dale 5000 1 6";
        requestParser.parseRequest(query.split(" "));

        // assertions
        Assert.assertEquals("IDIDI", requestParser.bankName);
        Assert.assertEquals("Dale", requestParser.borrowerName);
        Assert.assertEquals(5000, requestParser.principalAmount);
        Assert.assertEquals(1, requestParser.numYears);
        Assert.assertEquals(6, requestParser.rateOfInterest);
    }
}
