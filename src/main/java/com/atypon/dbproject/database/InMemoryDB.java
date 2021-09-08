package com.atypon.dbproject.database;

import com.atypon.dbproject.dao.LibraryDao;
import com.atypon.dbproject.dao.daoImp.BooksDaoImp;
import com.atypon.dbproject.dao.daoImp.QuotesDaoImp;
import com.atypon.dbproject.entity.Book;
import com.atypon.dbproject.entity.Quote;
import com.atypon.dbproject.exceptions.InputExceptions;

import java.util.Set;
import java.util.TreeMap;

public class InMemoryDB {

    LibraryDao<Integer,Book> bookLibraryDao = new BooksDaoImp<>();
    LibraryDao<Integer,Quote> QuoteLibraryDao = new QuotesDaoImp<>();

    private final Table<Integer,Book> booksTable = new Table<>(bookLibraryDao);
    private final Table<Integer,Quote> quotesTable = new Table<>(QuoteLibraryDao);

    private final IDGenerator idGenerator = new IDGenerator(booksTable.getAll().lastEntry().getKey(),
            quotesTable.getAll().lastEntry().getKey());


    private static InMemoryDB instance;


    public static synchronized InMemoryDB getInstance(){
        if (instance == null) {
            instance = new InMemoryDB();
        }

        return instance;
    }


    public TreeMap<Integer,Book> getAllBooks(){
        return booksTable.getAll();
    }

    public TreeMap<Integer,Quote> getAllQuotes(){
        assignBookNames();
        return quotesTable.getAll();

    }

    private void assignBookNames() {
        Set<Integer> keys = quotesTable.getAll().keySet();
        for(Integer k:keys) {
            quotesTable.getAll().get(k).setBookName(booksTable.get(quotesTable.get(k).getBookId()).getName());
        }
    }

    public Book getBook(int key){
        return booksTable.get(key);
    }

    public Quote getQuote(int key){
        return quotesTable.get(key);
    }

    public TreeMap<Integer,Quote> getBookQuotes(int id) {

        TreeMap<Integer,Quote> quotes = new TreeMap<>();


        Set<Integer> keys = quotesTable.getAll().keySet();
        for(Integer k:keys) {
            if (quotesTable.get(k).getBookId()==id){
                quotes.put(quotesTable.get(k).getId(),quotesTable.get(k));
            }

        }
        return quotes;
    }

    public boolean bookIsAdded(Book book){

        if (InputExceptions.isNull(book)){
            return false;
        } else {
            book.setID(idGenerator.getNewBookId());
            booksTable.add(book,book.getID());
        }
        return booksTable.recordExists(book.getID());
    }

    public boolean quoteIsAdded(Quote quote){

        if (InputExceptions.isNull(quote)){
            return false;
        } else {
            quote.setId(idGenerator.getNewQuoteId());
            quotesTable.add(quote,quote.getId());
        }
        return quotesTable.recordExists(quote.getId());
    }


    public boolean bookIsUpdated(Book book){

        if (InputExceptions.isNull(book)){
            return false;
        } else {
            booksTable.update(book,book.getID());
        }
        return booksTable.recordExists(book.getID());
    }

    public boolean quoteIsUpdated(Quote quote){

        if (InputExceptions.isNull(quote)){
            return false;
        } else {
            quotesTable.update(quote,quote.getId());
        }
        return quotesTable.recordExists(quote.getId());
    }

    public void removeBook(int key) {
        booksTable.remove(key);
        if (QuoteLibraryDao.RecordsAreDeleted(key)){
                quotesTable.getAll().entrySet().removeIf(entry -> entry.getValue().getBookId() == key);
        }
    }

    public void removeQuote(int key) {
        quotesTable.remove(key);
    }


}
