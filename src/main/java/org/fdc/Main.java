package org.fdc;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Library library = new Library();

        Book book1 = new Book("The Run", "Ellen", "1234");
        Book book2 = new Book("The Run", "Ellen", "12344");
        Book book3= new Book("The Run", "Ellen", "1234");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);




    }
}