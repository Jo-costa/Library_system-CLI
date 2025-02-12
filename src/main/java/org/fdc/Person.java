package org.fdc;

abstract public class Person {
    private String name;
    private String id;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName(){return this.name;}
    public void setName(String name) { this.name = name;}

    public String getId() { return id;}
    public void setId(String id) {this.id = id ;}

    public abstract boolean equals(Object obj);
    public abstract int hashCode();
}
