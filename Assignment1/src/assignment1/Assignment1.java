//TODO: Do we HAVE to use methods for choice 3 and 4? 
// Assignment 1
// Part 2 (Driver class)
// Written by: Viveka Anban (40063308) and Ferdousara Parvin (40062738)
/**
 * @author Viveka Anban(40063308) and Ferdousara Parvin(40062738)
 * COMP249
 * @version 2.0
 */

package assignment1;

import java.util.Scanner;

public class Assignment1 {

    // Create scanner
    static Scanner keyboard = new Scanner(System.in);

    // Final variable
    static final String PASSWORD = "password"; // the password cannot be changed (final)
    static final int ZERO = 0;
    static final int ONE = 1;
    static final int THREE = 3;
    static final int FIVE = 5;

    // Variables
    static int chosenNumberFromMenu = ZERO; // to check for valid input for number choice in menu
    static String inputPassword;
    static int numberOfStrikes = ZERO; // counter for how many failed attempts for the passwrd
    static int numberOfBooksToEnter;
    static Book[] inventory;
    static boolean hasPassedVerification;

    public static void main(String[] args) {

        // Print a welcome message
        System.out.println("--------------------------------------\n"
                + " Welcome to Viveka and Ferdousara's program\n"
                + "--------------------------------------------");

        // Promt user to enter maximum number of books
        System.out.print("Please enter the maximum number of books: ");
        final int MAX_BOOKS = keyboard.nextInt(); // Retrieve data and store it in final variable MAX_BOOKS
        inventory = new Book[MAX_BOOKS]; // Set the size of array inventory
        int numberOfSpotsRemainingInArray = MAX_BOOKS; // Initialize the integer variable to MAX_BOOKS whic means that all spots are free at the beginning of the program 

        int numberOfStrikes2 = 0; // counter for how many times option 1 has been clicked
        boolean endOfProgram = false;

        while (!endOfProgram) {

            // Promt the user to enter a number between 1 and 5 inclusive
            displayMenu();

            // The processus of each option
            switch (chosenNumberFromMenu) {

                case 1: // Enter new books

                    numberOfStrikes = 0; // reset counter
                    hasPassedVerification = isValidPassword();

                    //TODO (FERDOU): I feel like the three if statments can be combined into a if(number strikes == 3), else if(passedVerification), and an else. -> Maybe im wrong but just check in case
                    // Wrong password entered
                    if (!hasPassedVerification && numberOfStrikes2 < THREE) { // < 3 because the user has a first try before entering the switch statement
                        numberOfStrikes2++; // since we can enter this if statement if hasPassedVerification is false meaning numberOfStrikes = 3, we will display the main menu again and hence, numberOfStrikes2 will increment
                        break; // display main menu again

                    }

                    if (numberOfStrikes2 == THREE) { // 12 attemps
                        System.out.print("\nProgram detected suspicious activities and will terminate immediately!");
                        System.exit(0); // Close the program
                    }

                    // Correct password entered
                    if (hasPassedVerification) {
                        // Promt the user for the number of books user wants to enter
                        System.out.print("Please enter the number of books you would like to enter: ");
                        numberOfBooksToEnter = keyboard.nextInt();

                        // Check if there is enough space in the array inventory
                        while ((numberOfSpotsRemainingInArray - numberOfBooksToEnter) < ZERO) {
                            System.out.print("You can add no more than " + numberOfSpotsRemainingInArray + " in your inventory of books. Please enter a new number: ");
                            numberOfBooksToEnter = keyboard.nextInt();
                        }

                        numberOfSpotsRemainingInArray -= numberOfBooksToEnter;
                    }

                    // Add books to inventory
                    //TODO (FERDOU): i cannot always start at 0. What if they try adding books another time and the entries 0 to 5 are occupied so you want to start adding books from where its going to end
                    for (int i = ZERO; i < numberOfBooksToEnter; i++) {
                        System.out.print("Please enter the title of the book: ");
                        String title = keyboard.next();

                        System.out.print("Please enter the author of the book: ");
                        String author = keyboard.next();

                        System.out.print("Please enter the ISBN of the book: ");
                        long isbn = keyboard.nextLong();

                        System.out.print("Please enter the price of the book: ");
                        double price = keyboard.nextDouble();

                        System.out.println();

                        inventory[i] = new Book(title, author, isbn, price);
                    }

                    break;

                case 2: // Change information of a book

                    if (isValidPassword()) { //Only run the following, if the password is valid. Else, display the main menu.
                        int userInput = 0;

                        do {
                            //Prompt user for index number
                            System.out.print("Which book do you wish to update? ");
                            int index = keyboard.nextInt();

                            if (inventory[index] == null) { //Verify whether or not there is a book at said index in the array
                                do {
                                    System.out.println("Cannot find book. What do you want to do ?"
                                            + "\n\t1. Re-enter another book "
                                            + "\n\t2. Go to main menu ");

                                    userInput = keyboard.nextInt();
                                } while (userInput != 1 && userInput != 2); //Validate user input. Repeat until a valid userInput is given.
                            } else {
                                System.out.println("Book #" + index + inventory[index]);

                                do {
                                    do {
                                        System.out.print("\nWhat information would you like to change? "
                                                + "\n\t1. Author "
                                                + "\n\t2. Title "
                                                + "\n\t3. ISBN "
                                                + "\n\t4. price "
                                                + "\n\t5. Quit "
                                                + "\nPlease enter your choice > ");
                                        userInput = keyboard.nextInt();
                                        keyboard.nextLine(); // Catches the /n that was not read by the nextInt()
                                    } while (userInput < ONE || userInput > FIVE); //Validate user input

                                    changeBookInfo(userInput, index); //Call this method to start the process of changing the selected information
                                } while (userInput != 5); //Repeat these options until user decides to quit this operation

                            }

                        } while (userInput == 1); // Repeat this while the user still wants to try changing another book.

                    }

                    break;

                case THREE: // Display all books by a specific author
                    boolean authorExists = false; // Create a control variable to check if the author the user is asking for has written a book from our inventory.
                    do {
                        System.out.print("\nPlease enter the author's name: "); //Prompt user
                        String inputName = keyboard.next();
                        for (int i = 0; i < inventory.length; i++) { //Iterate through the entire array
                            if (inventory[i] == null) {
                                continue; // If said element is null, authorExists stays false.
                            }
                            if (inventory[i].getAuthor().equals(inputName)) {
                                System.out.println(inventory[i]); //Print said book info
                                authorExists = true; //If the compared authors are equal, it means that there exists a book with that author.
                            }
                        }

                        if (!authorExists) { //If the user provided an author that has not been found, inform the user and prompt them to try again.
                            System.out.println("No book written by this author has been found. Try again.");
                        }

                    } while (!authorExists); //Repeat this until the user provides the program with valid author names that have written books from out inventory.

                    break;

                case 4: // Display all books under a certain price
                    boolean cheaperThanExists = false; //Create a control variable that will let us know whether or not the userInput yields results.

                    do {
                        System.out.print("\nPlease enter the max price: "); //Prompt user to enter the compared price
                        double inputPrice = keyboard.nextDouble();

                        for (int i = 0; i < inventory.length; i++) { //Iterate through the entire array
                            if (inventory[i] == null) {
                                continue; // If said element is null, cheaperThanExists stays false.
                            }
                            if (inputPrice > inventory[i].getPrice()) { //Verify if the compared book has a lower price than the user input
                                System.out.println(inventory[i]);
                                cheaperThanExists = true;
                            }
                        }

                        if (!cheaperThanExists) {
                            System.out.println("No book cheaper than that price has been found. Try again.");
                        }
                    } while (!cheaperThanExists);//Repeat this until the user provides the program with price that will yield cheaper books in the incentory.

                    break;
                case FIVE: // Quit
                    endOfProgram = true; //Enables user to end the program.
                    break;

            }
        }

        // Print closing message
        System.out.println(
                "\n--------------------------------------------\n"
                + " Thank you for using our program. Goodbye!\n"
                + "--------------------------------------------\n");

    }

    /**
     * This methods displays the main menu of this program, providing the user
     * with 5 choices
     * <p>
     * 1. Enter new books <br>2. Change the information of a book. <br>3. Display all
     * books by a specific author. <br>4. Display all books under a certain price.
     * <br>5. End the program
     * </p>
     */
    public static void displayMenu() {
        // do-while loop to verify if user input is correct
        do {
            System.out.print("\nWhat do you want to do? "
                    + "\n\t1. Enter new books (password required) "
                    + "\n\t2. Change information of a book (password required) "
                    + "\n\t3. Display all books by a specific author "
                    + "\n\t4. Display all books under a certain price "
                    + "\n\t5. Quit "
                    + "\nPlease enter your choice > ");
            chosenNumberFromMenu = keyboard.nextInt();
        } while (chosenNumberFromMenu < ONE || chosenNumberFromMenu > FIVE); //Validate user input

    }

    /**
     * This method checks if the user input matches the password.
     *
     * The user has 3 chances to enter the correct input. Once that limit is
     * reached without the correct password, a false value is returned.
     *
     * @return true if the user has successfully entered the valid password ;
     * false otherwise
     */
    public static boolean isValidPassword() {

        numberOfStrikes = ZERO;
        do {
            // Prompt the user to enter his/her password
            System.out.print("Please enter your password: ");
            inputPassword = keyboard.next();
            numberOfStrikes++;
        } while (!inputPassword.equals(PASSWORD) && numberOfStrikes < THREE);

        // When the program gets out of the do-while loop, we want to know why if got out. There's two choices: correct password entered or numberOfStrikes = 3
        if (inputPassword.equals(PASSWORD)) {
            return true;
        } else { // numberOfStrikes = 3
            return false;
        }
    }

    /**
     * This method is used to change an information from an existing book in the
     * inventory. This method is called when the user decides which information
     * they want to change from the book they chose. Depending of their choice,
     * a message will prompt the user to enter the new value of said
     * property/information of the book.
     *
     * @param choice the choice (1-Author, 2-Title, 3-ISBN, 4-Price, 5-Quit
     * Operation) corresponding to the information the user wants to change from
     * an existing book in the inventory.
     * @param index the index number of the book in the inventory(an array).
     */
    public static void changeBookInfo(int choice, int index) {

        switch (choice) {
            case 1:
                System.out.print("Change author to -> ");
                inventory[index].setAuthor(keyboard.nextLine());
                System.out.println("Book #" + index + "\n" + inventory[index]);
                break;
            case 2:
                System.out.print("Change title to -> ");
                inventory[index].setTitle(keyboard.nextLine());
                System.out.println("Book #" + index + "\n" + inventory[index]);
                break;
            case 3:
                System.out.print("Change ISBN to -> ");
                inventory[index].setIsbn(keyboard.nextLong());
                System.out.println("Book #" + index + "\n" + inventory[index]);
                break;
            case 4:
                System.out.print("Change price to -> ");
                inventory[index].setPrice(keyboard.nextDouble());
                System.out.println("Book #" + index + "\n" + inventory[index]);
                break;

        }

    }

}
