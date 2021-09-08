package com.atypon.dbproject.entity;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {


    private int ID;
    private final String name;
    private final String author;
    private final String subject;
    private final String publisher;
    private final String year;


    private Book(BookBuilder builder){
        this.ID=builder.ID;
        this.name=builder.name;
        this.author=builder.author;
        this.subject=builder.subject;
        this.publisher=builder.publisher;
        this.year=builder.year;
    }



    public static class BookBuilder {

        private int ID;
        private String name;
        private String author;
        private String subject;
        private String publisher;
        private String year;


        public BookBuilder ID (int ID){
            this.ID=ID;
            return this;
        }
        public BookBuilder name (String name){
            this.name=name;
            return this;
        }

        public BookBuilder author (String author){
            this.author=author;
            return this;
        }



        public BookBuilder subject (String subject){
            this.subject=subject;
            return this;
        }

        public BookBuilder publisher (String publisher){
            this.publisher=publisher;
            return this;
        }

        public BookBuilder year (String year){
            this.year=year;
            return this;
        }

        public  Book build(){
            return new Book(this);
        }


    }



    @Override
    public String toString() {
        return ID + "," + name + "," + subject + "," + author + ","
                + publisher + "," + year +"\n";
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }


    public String getSubject() {
        return subject;
    }


    public String getPublisher() {
        return publisher;
    }


    public String getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return ID == book.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}