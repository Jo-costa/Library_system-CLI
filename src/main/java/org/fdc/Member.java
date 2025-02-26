package org.fdc;

import java.util.ArrayList;
import java.util.Objects;

public class Member extends Person{
    private final ArrayList<Book> borrowedBooks;

    Member(String name, String id){

        //assigns the name and age parameters to the
        // corresponding fields in the Person class
        super(name, id);
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book, Library library){

         if( book != null && book.getAvailability()){
             Book bookToBorrow = library.memberBorrowBook(this, book);
             borrowedBooks.add(bookToBorrow);
         } else if(book != null && !book.getAvailability()) {
             System.out.println("Book not available!");
         } else {
             System.out.println("Book not found!");
         }
    }
    public void returnBook(Book book, Library library){

    }
    public ArrayList<Book> getBorrowedBooks(){return borrowedBooks;}


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
