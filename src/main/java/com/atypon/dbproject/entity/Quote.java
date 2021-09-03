package com.atypon.dbproject.entity;

import java.util.Objects;

public class Quote {


    private int id;
    private final int bookId;
    private final String quote;

    private String bookName;


    private Quote(QuoteBuilder builder){
        this.id=builder.id;
        this.bookId=builder.bookId;
        this.quote=builder.quote;
        this.bookName=builder.bookName;
    }

    @Override
    public String toString() {
        return id + "," + bookId + "," + quote +"\n";
    }

    public static class QuoteBuilder{
        private int id;
        private int bookId;
        private String quote;
        private String bookName;


        public QuoteBuilder id (int id){
            this.id=id;
            return this;
        }

        public QuoteBuilder bookId (int bookId){
            this.bookId=bookId;
            return this;
        }

        public QuoteBuilder quote (String quote){
            this.quote=quote;
            return this;
        }

        public QuoteBuilder bookName (String bookName){
            this.bookName=bookName;
            return this;
        }

        public Quote build(){
            return new Quote(this);
        }

    }

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public String getQuote() {
        return quote;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName=bookName;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return id == quote.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
