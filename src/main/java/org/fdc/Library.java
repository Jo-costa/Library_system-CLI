package org.fdc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Library {

    private final ArrayList<Book>books;
    private final ArrayList<Member> members;
    private final ArrayList<Librarian> staff;

    Library(){
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.staff = new ArrayList<>();
    }

    private void addBook(Book book){
        if(books.contains(book)){
            System.out.println("Book already in the library!");
        }else {
            books.add(book);
        }
    }

    private void removeBook(Book book){
        if(books.contains(book)){
            books.remove(book);
        }else {
            System.out.println("Not found!");
        }
    }



    public void addMember(Member member){

        if(members.contains(member)){
            System.out.println("Already a member");
        }else {
            members.add(member);
        }
    }

    private void removeMember(Member member){
        if(books.contains(member)){
            books.remove(member);
        }else {
            System.out.println("Not found!");
        }
    }


    public ArrayList<Book> getBooks(){
        return books;
    }

    public ArrayList<Member> getMembers(){
        return members;
    }

   public void librarianAddBook(Book book, Librarian librarian){

        if(!books.contains(book)){
            librarian.addBook(book, this);
        }

    }

    public void librarianRemoveBook(Book book, Librarian librarian){

        if(members.contains(book)){
            librarian.removeBook(book, this);
        }
    }

      public void librarianAddMember(Member member, Librarian librarian){

        if(!members.contains(member)){
            librarian.addMember(member, this);
        }else {
            System.out.println("Already a member!");
        }
    }
    public void librarianRemoveMember(Member member, Librarian librarian){
        if(members.contains(member)){
            librarian.removeMember(member, this);
        }else {
            System.out.println("Not found!");
        }
    }

    public void loadBooks(){
        try {

            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/books.csv"));
            String line;

            while((line = reader.readLine()) != null){
                String[] lines = line.split(",");
                String title =lines[0];
                String author =lines[1];
                String isbn =lines[2];
                boolean isAvailable =Boolean.parseBoolean(lines[3]);

                Book book = new Book(title, author, isbn, isAvailable);
                books.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadLibrarians(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/librarians.csv"));

            String line;

            while((line = reader.readLine()) != null){
                String[] lines = line.split(",");
                String name = lines[0];
                String id = lines[1];

                Librarian librarian = new Librarian(name, id);
                staff.add(librarian);

            }

            } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
