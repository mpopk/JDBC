package com.kodilla.hibernate.manytomany.facade;

public class OrderProcessingException extends Exception{

    public static String ERR_NOT_MATCH_RESULTS = "Query doesn't retrieve any results";

    public OrderProcessingException(String message) {
        super(message);
    }
}


