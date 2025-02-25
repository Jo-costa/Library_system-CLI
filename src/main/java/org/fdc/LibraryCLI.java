package org.fdc;

import java.util.Scanner;
import java.util.regex.Pattern;


public class LibraryCLI {
    private static final Library library = new Library();
    private static final Scanner scanner = new Scanner(System.in);

    public void start() {

        Pattern userSelectionPattern = Pattern.compile("^[0-2]$");

        library.loadBooks();
        library.loadLibrarians();


        while (true) {
            displayMainMenu();
            String selection = getUserInput("Enter Selection> ", userSelectionPattern);

            switch (selection){
                case "0":
                    endProgram();
                    break;
                case "1":
                    handleLibrarianActions();
                    break;
                case "2":
                    displayMemberMenu();
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

            System.out.println("Invalid input. Please try again.");
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

    public void handleLibrarianActions(){

        String staffID = getUserInput("Enter staff ID [LBXXXX] [0 to exit]> ", )
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
