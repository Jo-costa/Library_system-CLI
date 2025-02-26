package org.fdc;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


public class LibraryCLI {
    public Library library = new Library();
    public Scanner scanner = new Scanner(System.in);

    public void start() {
        Pattern userSelectionPattern = Pattern.compile("^[0-2]$");

        library.loadBooks();
        library.loadLibrarians();
        library.loadMembers();


        while (true) {
            displayMainMenu();
            String selection = getUserInput("Enter Selection> ", userSelectionPattern);

            switch (selection) {
                case "0":
                    System.out.println("Exiting...");
                    ;
                    break;
                case "1":
                    handleLibrarianActions();
                    break;
                case "2":
                    handleMemberActions();
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }

        }
    }


    public void displayMainMenu() {
        System.out.printf("+--------------------+%n");
        System.out.printf("|   Library System   |%n");
        System.out.printf("|--------------------|%n");
        System.out.printf("| Librarian.......1  |%n");
        System.out.printf("| Member..........2  |%n");
        System.out.printf("| Exit............0  |%n");
        System.out.printf("+--------------------+%n");
    }

    public String getUserInput(String prompt, Pattern regexpattern) {
        while (true) {
            System.out.print(prompt);
            String userInput = scanner.nextLine();
            if (regexpattern.matcher(userInput).matches()) return userInput;
            System.out.println("Invalid input. Please try again. ");

        }
    }


    public void handleLibrarianActions() {
        Pattern selectionPattern = Pattern.compile("^[0-4]$");
        Pattern memberNamePattern = Pattern.compile("^[A-Za-z0-9\\s]+$|0");
        Pattern staffIDPattern = Pattern.compile("^(?i)lb\\d{4}$|0");

        String staffID = getUserInput("Enter staff ID [LBXXXX] [0 to exit]> ", staffIDPattern);

        if (staffID.equals("0")) return;

        Librarian librarian = findLibrarian(staffID);
        if (librarian == null) {
            System.out.println("User not found");
            return;
        }


        while (true) {
            displayLibrarianMenu();
            String selection = getUserInput("Enter Selection> ", selectionPattern);

            switch (selection) {
                case "0":
                    return;
                case "1":
                    addBook(librarian, library);
                    break;
                case "2":
                    removeBook(librarian, library);
                    break;
                case "3":
                    addMember(librarian, library);
                    break;
                case "4":
                    removeMember(librarian, library);
                    break;
            }
        }
    }

    public void handleMemberActions() {
        Pattern selectionPattern = Pattern.compile("^[0-4]$");
        Pattern memberNamePattern = Pattern.compile("^[A-Za-z0-9\\s]+$|0");
        Pattern memberIDPattern = Pattern.compile("^(?i)mb\\d{4}$|0");

        String memberID = getUserInput("Enter member ID [MBXXXX] [0 to exit]> ", memberIDPattern);

        if (memberID.equals("0")) return;

        Member member = findMember(memberID);
        if (member == null) {
            System.out.println("User not found");
            return;
        }


        while (true) {
            displayMemberMenu();
            String selection = getUserInput("Enter Selection> ", selectionPattern);

            switch (selection) {
                case "0":
                    return;
                case "1":
                    borrowBook(member);
                    break;
                case "2":
                    returnBook(member);
                    break;
                case "3":
                    searchBook();
                    break;
                case "4":
                    viewAllBooks();
                    break;
            }
        }
    }


    public void displayLibrarianMenu() {
        System.out.printf("+----------------------------+%n");
        System.out.printf("|         Staff Menu         |%n");
        System.out.printf("|----------------------------|%n");
        System.out.printf("| Add book................1  |%n");
        System.out.printf("| Remove book.............2  |%n");
        System.out.printf("| Add member..............3  |%n");
        System.out.printf("| Remove member...........4  |%n");
        System.out.printf("| Back to main menu.......0  |%n");
        System.out.printf("+----------------------------+%n");
    }

    public void displayMemberMenu() {
        System.out.printf("+----------------------------+%n");
        System.out.printf("|        Member Menu         |%n");
        System.out.printf("|----------------------------|%n");
        System.out.printf("| Borrow book.............1  |%n");
        System.out.printf("| Return book.............2  |%n");
        System.out.printf("| Find a book.............3  |%n");
        System.out.printf("| View all books..........4  |%n");
        System.out.printf("| Back to main menu.......0  |%n");
        System.out.printf("+----------------------------+%n");
    }

    public void addBook(Librarian librarian, Library library) {
        Pattern titlePattern = Pattern.compile("^[A-Za-z0-9\\s']+$|0");
        Pattern authorPattern = Pattern.compile("^[A-Za-z\\s]+$|0");
        Pattern isbnPattern = Pattern.compile("^\\d{6}|0$");

        System.out.println("Adding book!");
        String title = getUserInput("Enter book title [0 to exit]> ", titlePattern);
        if (title.equals("0")) return;
        String author = getUserInput("Enter author name [0 to exit]> ", authorPattern);
        if (author.equals("0")) return;
        String isbn = getUserInput("Enter isbn [0 to exit]> ", isbnPattern);
        if (isbn.equals("0")) return;
        boolean availability = true;
        Book book = new Book(title, author, isbn, availability);

        librarian.addBook(book, library);

        System.out.println("Book Added!");

    }

    public void removeBook(Librarian librarian, Library library) {
        Pattern titlePattern = Pattern.compile("^[A-Za-z0-9\\s']+$|0");
        Pattern authorPattern = Pattern.compile("^[A-Za-z\\s]+$|0");
        Pattern isbnPattern = Pattern.compile("^\\d{6}|0$");

        System.out.println("Adding book!");
        String title = getUserInput("Enter book title [0 to exit]> ", titlePattern);
        if (title.equals("0")) return;
        String author = getUserInput("Enter author name [0 to exit]> ", authorPattern);
        if (author.equals("0")) return;
        String isbn = getUserInput("Enter isbn [0 to exit]> ", isbnPattern);
        if (isbn.equals("0")) return;

        ArrayList<Book> bookToRemove = new ArrayList<>();
        for (Book book : library.getBooks()) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author) && book.getIsbn().equals(isbn)) {
                bookToRemove.add(book);//add book to the bookToRemove arraylist,
                // to prevent ConcurrentModificationException
                // then grab the book and remove from library
                // once the for each loop has ended
            }
        }
        if (!bookToRemove.isEmpty()) {
            librarian.removeBook(bookToRemove.getFirst(), library);
        } else {
            System.out.println("Book not found!");
        }
    }

    public void addMember(Librarian librarian, Library library) {
        Pattern namePattern = Pattern.compile("^[A-Za-z0-9\\s]+$|0");
        Pattern memberIDPattern = Pattern.compile("^(?i)mb\\d{4}$");

        System.out.println("Adding book!");
        String name = getUserInput("Enter member name [0 to exit]> ", namePattern);
        if (name.equals("0")) return;
        String id = getUserInput("Enter member id [0 to exit]> ", memberIDPattern);
        if (id.equals("0")) return;

        for (Member m : library.getMembers()) {
            if (m.getId().equals(id)) {
                System.out.println("Already a member!");
                return;
            }
        }
        Member member = new Member(name, id);
        librarian.addMember(member, library);
    }

    public void removeMember(Librarian librarian, Library library) {
        Pattern namePattern = Pattern.compile("^[A-Za-z0-9\\s]+$|0");
        Pattern memberIDPattern = Pattern.compile("^(?i)mb\\d{4}$");

        System.out.println("Adding book!");
        String name = getUserInput("Enter member name [0 to exit]> ", namePattern);
        if (name.equals("0")) return;
        String id = getUserInput("Enter member id [0 to exit]> ", memberIDPattern);
        if (id.equals("0")) return;

        ArrayList<Member> memberToRemove = new ArrayList<>();
        for (Member member : library.getMembers()) {

            if (member.getName().equals(name) && member.getId().equals(id)) {
                memberToRemove.add(member);
            }
        }
        if (!memberToRemove.isEmpty()) {
            librarian.removeMember(memberToRemove.getFirst(), library);

        } else {
            System.out.println("Member not found!");
        }
    }

    public void borrowBook(Member member) {
        Pattern titlePattern = Pattern.compile("^[A-Za-z0-9\\s']+$|0");
        Pattern authorPattern = Pattern.compile("^[A-Za-z\\s]+$|0");

        String bookTitle = getUserInput("Enter book name> ", titlePattern);
        if (bookTitle.equals("0")) return;
        String authorName = getUserInput("Enter author name> ", authorPattern);
        if (authorName.equals("0")) return;
        Book book = findBook(bookTitle, authorName);
        member.borrowBook(book, library);
    }

    public void returnBook(Member member) {

        int i = 0;
        if (!member.getBorrowedBooks().isEmpty()) {
            for (Book book : member.getBorrowedBooks()) {
                System.out.println(i+1 + "-> " +"Title: " +book.getTitle()+ ", Author: "+book.getAuthor());
                i++;
            }
        } else {
            System.out.println("Empty");
            return;
        }

        String pattern = "^[0-"+i+"]$";
        Pattern selectionPattern = Pattern.compile(pattern);
        String selection = getUserInput("Choose book to return> ", selectionPattern);
        if(selection.equals("0")) return;

        int converSelection = Integer.parseInt(selection);
        member.getBorrowedBooks().remove(converSelection -1);

    }

    public void searchBook(){
        Pattern titlePattern = Pattern.compile("^[A-Za-z0-9\\s']+$|0");
        Pattern authorPattern = Pattern.compile("^[A-Za-z\\s]+$|0");
        Pattern isbnPattern = Pattern.compile("^\\d{6}|0$");

        String title = getUserInput("Enter book title> ", titlePattern);
        if(title.equals("0")) return;
        String author = getUserInput("Enter author name> ", authorPattern);
        if(author.equals("0")) return;
        Book book = findBook(title, author);


        if(book != null){
            String availability = book.getAvailability() ? "Yes" : "No";

            System.out.printf("+%-48s+%n","-".repeat(48));
            System.out.printf("| %-15s %31s|%n","Book  ",book.getTitle());
            System.out.printf("|%-48s|%n","-".repeat(48));
            System.out.printf("| %-15s %30s |%n", "Author   ", book.getAuthor());
            System.out.printf("| %-15s %30s |%n", "ISBN   ", book.getIsbn());
            System.out.printf("| %-15s %30s |%n", "Available   ", availability);
            System.out.printf("+%-48s+%n","-".repeat(48));
        }else {
            System.out.println("Book Found!");
        }



    }

    public void viewAllBooks(){
        for (Book b : library.getBooks()){
            String availability = b.getAvailability() ? "Yes" : "No";

            System.out.printf("+%-48s+%n","-".repeat(48));
            System.out.printf("| %-15s %31s|%n","Book  ",b.getTitle());
            System.out.printf("|%-48s|%n","-".repeat(48));
            System.out.printf("| %-15s %30s |%n", "Author   ", b.getAuthor());
            System.out.printf("| %-15s %30s |%n", "ISBN   ", b.getIsbn());
            System.out.printf("| %-15s %30s |%n", "Available   ", availability);
            System.out.printf("+%-48s+%n","-".repeat(48));

        }
    }

    public Librarian findLibrarian(String staffID) {
        for (Librarian librarian : library.getStaff()) {
            if (librarian.getId().equalsIgnoreCase(staffID)) return librarian;
        }
        return null;
    }

    public Member findMember(String memberID) {
        for (Member member : library.getMembers()) {
            if (member.getId().equalsIgnoreCase(memberID)) return member;
        }
        return null;
    }

    public Book findBook(String title, String author) {
        for (Book book : library.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase((author))) return book;
        }
        return null;
    }

    public boolean validateStaffMember(String id) {
        for (Librarian librarian : library.getStaff()) {
            if (librarian.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean inputIsInvalid(String userInput, Pattern pattern) {
        if (!pattern.matcher(userInput).matches()) {
            return true;
        }
        return false;
    }


}
