package com.atypon.dbproject.exceptions;

import com.atypon.dbproject.entity.Book;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputExceptionsTest {

    @Test
    public void testIsNullEmptyCases(){
        assertTrue(InputExceptions.isNull(""));
    }

    @Test
    public void testIsNullNormalCases(){
        Book obj = new Book.BookBuilder().ID(1).name("test").author("test")
                        .subject("test").publisher("test").year("test").build();
        assertFalse(InputExceptions.isNull(obj));
    }

    @Test
    public void testIsEmptyString(){
        assertFalse(InputExceptions.isEmpty("test"));
        assertTrue(InputExceptions.isEmpty(""));
    }





}