package org.fdc;

import java.util.Objects;

public class Librarian extends Person{

    Librarian(String name, String id){
        super(name, id);
    }

    public void addBook(Book book, Library library){

    }

    public void removeBook(Book book, Library library){

    }

    public void addMember(Member member, Library library){

    }

    public void removeMember(Member member, Library library){

    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;

        if(obj == null || getClass() != obj.getClass()) return false;

       Librarian librarian = (Librarian) obj;

       return this.getName().equals(librarian.getName()) && this.getId().equals(librarian.getId());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), getName());
    }
}
