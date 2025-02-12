package org.fdc;

import java.util.Scanner;
import java.util.regex.Pattern;


public class LibraryCLI {

    private Scanner scanner = new Scanner(System.in);
    public void start(){

        Library library = new Library();
        library.loadBooks();
        library.loadLibrarians();
        displayMenu();

    }

    public void displayMenu(){
        Pattern choicePattern = Pattern.compile("^[0-2]$");
        Pattern memberIDPattern = Pattern.compile("^JC\\d{4}$\n");
        Pattern staffIDPattern = Pattern.compile("^LB\\d{4}$\n");
        String choice = "0";
        String exit ="0";


        System.out.printf("+--------------------+%n");
        System.out.printf("|   Library System   |%n");
        System.out.printf("|--------------------|%n");
        System.out.printf("| Librarian.......1  |%n");
        System.out.printf("| Member..........2  |%n");
        System.out.printf("| Exit............0  |%n");
        System.out.printf("+--------------------+%n");


        validateinput(choicePattern,choice,exit,"Enter choice> ");
        if(choice.equals("1")){
            validateinput(staffIDPattern, choice, exit, "Enter ID");
        }



    }

    public void endProgram(){
        System.out.printf("Exiting...");
    }

    public void validateinput(Pattern pattern, String choice, String exit, String userInstruction){
        do{
            System.out.print(userInstruction);
            choice = scanner.next();

            if(!pattern.matcher(choice).matches()){
                System.out.println("Invalid Input! Try again [0 to exit]");
            }else if (choice.equals(exit)){
                endProgram();
            }

        }while (!pattern.matcher(choice).matches());
    }


}
