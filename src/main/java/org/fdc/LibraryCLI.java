package org.fdc;

import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;


public class LibraryCLI {
    Library library = new Library();

    private Scanner scanner = new Scanner(System.in);

    public void start() {

        library.loadBooks();
        library.loadLibrarians();
        displayMenu();

    }

    public void displayMenu() {
        Pattern userSelectionPattern = Pattern.compile("^[0-2]$");
        Pattern staffSelectionPattern = Pattern.compile("^[0-4]$");
        Pattern memberIDPattern = Pattern.compile("^MB\\d{4}$");
        Pattern staffIDPattern = Pattern.compile("^LB\\d{4}$");
        Pattern titlePattern = Pattern.compile("[A-Za-z0-9\\s\\p{P}]+|0$");
        Pattern isbnPattern = Pattern.compile("^\\d{6}|0$");
        String userSelection;
        String title;
        String author;
        String isbn;
        boolean available;

        while (true) {
            System.out.printf("+--------------------+%n");
            System.out.printf("|   Library System   |%n");
            System.out.printf("|--------------------|%n");
            System.out.printf("| Librarian.......1  |%n");
            System.out.printf("| Member..........2  |%n");
            System.out.printf("| Exit............0  |%n");
            System.out.printf("+--------------------+%n");
            System.out.print("Enter selection> ");
            userSelection = scanner.next();
            while (!userSelectionPattern.matcher(userSelection).matches()) {
                System.out.println("Invalid input! ");
                System.out.print("Enter selection> ");
                userSelection = scanner.next();
            }
            switch (Integer.parseInt(userSelection)) {
                case 0:
                    endProgram();
                    break;
                case 1:
                    Librarian librarian = null;
                    System.out.print("Enter staff ID [LBXXXX]> ");
                    userSelection = scanner.next().toUpperCase();
                    do {
                        if (userSelection.equals("0")) {
                            continue;
                        } else if (inputIsInvalid(userSelection, staffIDPattern)) {
                            System.out.println("Invalid Input");
                            System.out.print("Enter staff ID [0 to go back]> ");
                            userSelection = scanner.next();
                        }
                        if (validateStaffMember(userSelection)) {
                            displayStaffMenu();
                            for (Librarian staff : library.getStaff()) {
                                if (staff.getId().equals(userSelection)) {
                                    librarian = staff;
                                }
                            }
                            break;
                        } else {
                            System.out.println("User not found");
                            System.out.print("Enter staff ID [0 to go back]> ");
                            userSelection = scanner.next().toUpperCase();
                        }
                    } while (!inputIsInvalid(userSelection, staffIDPattern));

                    System.out.print("Enter selection> ");
                    userSelection = scanner.next();
                    scanner.nextLine();
                    while (!staffSelectionPattern.matcher(userSelection).matches()) {
                        System.out.println("Invalid input! ");
                        System.out.print("Enter selection> ");
                        userSelection = scanner.next();
                        scanner.nextLine();
                    }
                    switch (Integer.parseInt(userSelection)) {
                        case 1:
                            System.out.printf("Adding book!");
                            System.out.print("Enter book title> ");
                            title = scanner.nextLine();

                            System.out.print("Enter author name>");
                            author = scanner.nextLine();

                            System.out.print("Enter isbn [000000]> ");
                            isbn = scanner.nextLine();
                            while (!isbnPattern.matcher(isbn).matches()) {
                                System.out.println("Invalid input! ");
                                System.out.print("Enter isbn[000000] [0 to exit]> ");
                                userSelection = scanner.nextLine();
                            }
                            if (userSelection.equals("0")) continue;

                            available = true;

                            Book book = new Book(title, author, isbn, available);
                            librarian.addBook(book, library);
                            break;
                        case 2:
                            System.out.println("Removing book!");
                            System.out.print("Enter book title> ");
                            title = scanner.nextLine();

                            System.out.print("Enter author name> ");
                            author = scanner.nextLine();

                            System.out.print("Enter isbn [000000]> ");
                            isbn = scanner.nextLine();
                            while (!isbnPattern.matcher(isbn).matches()) {
                                System.out.println("Invalid input! ");
                                System.out.print("Enter isbn[000000] [0 to exit]> ");
                                userSelection = scanner.nextLine();
                            }
                            if (userSelection.equals("0")) continue;

                            ArrayList<Book> bookToRemove = new ArrayList<>();
                            for (Book b : library.getBooks()) {
                                if (b.getTitle().equals(title)&& b.getAuthor().equals(author)
                                        && b.getIsbn().equals(isbn)) {

                                    bookToRemove.add(b);// add book to the bookToRemove arraylist,
                                    // to prevent ConcurrentModificationException and then remove
                                    // it once the for each loop has ended
                                    break;
                                }
                            }
                            if (!bookToRemove.isEmpty()) {
                                librarian.removeBook(bookToRemove.get(0), library);
                            } else {
                                System.out.println("Book not found!");
                            }
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Member!");

            }

        }

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

    public void displayStaffMenu() {
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
        System.out.printf("|         Member Menu         |%n");
        System.out.printf("|----------------------------|%n");
        System.out.printf("| Borrow book.............1  |%n");
        System.out.printf("| Return book.............2  |%n");
        System.out.printf("| View all books..........3  |%n");
        System.out.printf("| Back to main menu.......0  |%n");
        System.out.printf("+----------------------------+%n");
    }

    public void endProgram() {
        System.out.println("Exiting...");
    }

    public void backToMainMenu() {

    }


}
