package org.fdc;

public class Book {

    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    Book(String tile, String author, String isbn){
        this.title  = tile;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}

    public String getAuthor(){return author;}
    public void setAuthor(String author){this.author = author;}

    public String getIsbn(){return isbn;}
    public void setIsbn(String isbn){this.isbn = isbn;}

    public boolean getIsAvailable(){return isAvailable;}
    public void setIsAvailable(boolean isAvailable){this.isAvailable = isAvailable;}

    @Override
    public String toString(){
        return title+","+author+","+isbn+","+isAvailable;
    }



}
