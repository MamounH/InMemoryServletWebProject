package com.atypon.dbproject.database;

public class IDGenerator {

    private int bookId;
    private int quoteId;


    public IDGenerator(int bookId , int quoteId) {
        this.bookId=bookId;
        this.quoteId=quoteId;
    }


    protected synchronized int getNewBookId(){
        bookId = bookId +1;
        return bookId;
    }

    protected synchronized int getNewQuoteId(){
        quoteId = quoteId + 1;
        return quoteId;
    }

    protected synchronized int getLatestBookId(){
        return bookId;
    }

    protected synchronized int getLatestQuoteId(){
        return quoteId;
    }



}
