package org.fdc;

import java.util.ArrayList;

public class Library {

    private final ArrayList<Book>books;
    private final ArrayList<Member> members;


    Library(){
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }



    public void addBook(Book book){
        if(books.contains(book)){
            System.out.println("Book already in the library!");
        }else {
            books.add(book);
        }
    }

    public void addMember(Member member){

        if(members.contains(member)){
            System.out.println("Already a member");
        }else {
            members.add(member);
        }
    }

    public ArrayList<Book> getBooks(){
        return books;
    }

//    public Member getMembers(){
//
//    }

//   public void librarianAddBook(Book book, Librarian librarian){
//
//    }
//
//       public void librarianRemoveBook(Book book, Librarian librarian){
//
//    }
//
//      public void librarianAddMember(Member member, Librarian librarian){
//
//    }
//
//    public void librarianRemoveMember(Member member, Librarian librarian){
//
//    }
}
