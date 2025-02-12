package org.fdc;

import java.util.ArrayList;
import java.util.Objects;

public class Member extends Person{
    private ArrayList<Book> borrowedBooks;

    Member(String name, String id){

        //assigns the name and age parameters to the
        // corresponding fields in the Person class
        super(name, id);
        this.borrowedBooks = new ArrayList<>();
    }

//    public void borrowBook(Book book, Library library, Librarian librarian){
//
//        if(book.getAvailability()){
//            book.setAvailability(false);
//            borrowedBooks.add(book);
//            //library.librarianRemoveBook()
//        }
//
//    }
    public void returnBook(Book book){}
//    public ArrayList<Book> getBorrowedBooks(){}


    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;

        if(obj == null || getClass() != obj.getClass()) return false;

        Member member = (Member) obj;

        return this.getName().equals(member.getName()) && this.getId().equals(member.getId());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId());
    }
}
