package org.fdc;

import java.util.Objects;

public class Book {

    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    Book(String tile, String author, String isbn, boolean isAvailable){
        this.title  = tile;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}

    public String getAuthor(){return author;}
    public void setAuthor(String author){this.author = author;}

    public String getIsbn(){return isbn;}
    public void setIsbn(String isbn){this.isbn = isbn;}

    public boolean getAvailability(){return isAvailable;}
    public void setAvailability(boolean isAvailable){this.isAvailable = isAvailable;}

    @Override
    public String toString(){
        return title+","+author+","+isbn+","+isAvailable;
    }

    //Override equals() method to compare books objects based on books attributes
    @Override
    public boolean equals(Object obj){

        //check if both objects are the same in memory
        //"this" would refer to book object --> book.equals(book)
        if(this == obj) return true;

        //getClass() != obj.getClass() -- if current object and the object passed as parameter
        //are of different types, it will return false
        if(obj == null || getClass() != obj.getClass()) return false;

        //obj is an Object type, java does not know that it's specifically a Book object
        //by using explicit casting, Java is aware that obj is actually a Book object
        //allowing access its properties.
        Book book = (Book) obj;

        return this.title.equals(book.getTitle()) && this.author.equals(book.getAuthor()) && this.isbn.equals(book.getIsbn());

    }

    @Override
    public int hashCode(){
        return Objects.hash(title, author, isbn);
    }



}
