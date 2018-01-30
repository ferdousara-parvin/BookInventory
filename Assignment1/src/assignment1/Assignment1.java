// Assignment 1
// Part 2 (Driver class)
// Written by: Viveka Anban (40063308) and Ferdousara Parvin (40062738)
package assignment1;

import java.util.Scanner;

/**
 * <h1>Driver class</h1>
 * The purpose of this program is to review some concepts that we learnt previously concerning classes, loops, array of objects, static methods and static attributes. 
 * For this assignment, we had to create a Book class which possessed 4 attributes (price, author, ISB, and title). 
 * We then implemented this class in the driver class called Assignment1 (which simulated a book store) to keep track of the books. 
 * We were able to add new Books to the "store", change information of a book, display all books by a specific author and display all books under a certain price <br>
 * COMP249<br>
 * Assignment 1 <br>
 * Due date: Thursday, January 31st, 2018</br>
 *
 * @author Viveka Anban(40063308) and Ferdousara Parvin(40062738) COMP249
 */
public class Assignment1 {

    // Create scanner
    static Scanner keyboard = new Scanner(System.in);

    // Final variable
    static final String PASSWORD = "password"; // the password cannot be changed (final)
    static final int ZERO = 0;
    static final int ONE = 1;
    static final int TWO = 2;
    static final int THREE = 3;
    static final int FOUR = 4;
    static final int FIVE = 5;

    // Variables
    static int chosenNumberFromMenu = ZERO; // varible is used to check for validity of the input number from user (must only be the numbers fro mthe menu)
    static int numberOfStrikes; // counter for how many failed attempts for the password
    static String inputPassword;
    static Book[] inventory; // array to store all the books

    /**
     * This is the main method where the Book class is being used.
     *
     */
    public static void main(String[] args) {

        // Print a welcome message
        System.out.println("--------------------------------------\n"
                + " Welcome to Viveka and Ferdousara's program\n"
                + "--------------------------------------------");

        System.out.print("Please enter the maximum number of books: "); // Promt user to enter maximum number of books
        final int MAX_BOOKS = keyboard.nextInt(); // Retrieve data and store it in final variable MAX_BOOKS
        inventory = new Book[MAX_BOOKS]; // Set the size of array inventory
        int numberOfSpotsRemainingInInventory = MAX_BOOKS; // Initialize to MAX_BOOKS which means that all spots are free in the inventory array at the beginning of the program 

        int numberOfStrikes2 = ZERO; // counter for how many times option 1 has been clicked
        boolean endOfProgram = false; // true = exit the menu, quit the program
        int numberOfBooksToEnter = ZERO;
        boolean hasPassedVerification = false; // true = correct password
        int startIndex = ZERO; // from which index books must be added

        // While loop to navigate trough the meny and its different options
        while (!endOfProgram) {

            // Promt the user to enter a number between 1 and 5 inclusive
            displayMenu();

            // The processus of each option
            switch (chosenNumberFromMenu) {

                case ONE: // Enter new books

                    numberOfStrikes = ZERO; // reset counter every time the user enters option 1
                    hasPassedVerification = isValidPassword();

                    // Wrong password entered
                    if (!hasPassedVerification && numberOfStrikes2 < THREE) { // < 3 because the user has a first try before entering the switch statement
                        numberOfStrikes2++; // since we can enter this if statement if hasPassedVerification is false meaning numberOfStrikes = 3, we will display the main menu again and hence, numberOfStrikes2 will increment
                        break; // display main menu again

                    }

                    // After 12 attempts
                    if (numberOfStrikes2 == THREE) {
                        System.out.print("\nProgram detected suspicious activities and will terminate immediately!"); // Warning message
                        System.exit(ZERO); // Close the program
                    }

                    // Correct password entered
                    if (hasPassedVerification) {
                        System.out.print("Please enter the number of books you would like to enter: "); // Promt the user to enter the number of books he/she wanst to enter
                        numberOfBooksToEnter = keyboard.nextInt();

                        // Check if there is enough space in the array inventory
                        while ((numberOfSpotsRemainingInInventory - numberOfBooksToEnter) < ZERO) {
                            System.out.print("You can add no more than " + numberOfSpotsRemainingInInventory + " in your inventory of books. Please enter a new number: ");
                            numberOfBooksToEnter = keyboard.nextInt();
                        }

                        numberOfSpotsRemainingInInventory -= numberOfBooksToEnter; // Update numberOfSpotsRemainingInInventory
                    }

                    // Add books to inventory
                    for (int i = startIndex, counter = ZERO; counter < numberOfBooksToEnter; i++, counter++) {
                        System.out.print("Please enter the title of the book: ");
                        String title = keyboard.next();

                        System.out.print("Please enter the author of the book: ");
                        String author = keyboard.next();

                        System.out.print("Please enter the ISBN of the book: ");
                        long isbn = keyboard.nextLong();

                        System.out.print("Please enter the price of the book: ");
                        double price = keyboard.nextDouble();

                        System.out.println();

                        inventory[i] = new Book(title, author, isbn, price); // Create new Book object and add it in inventory array

                        startIndex++; // the next time the program will enter this for loop, books will be added starting from the empty spot after the last Book that was added
                    }

                    break;

                case TWO: // Change information of a book

                    if (isValidPassword()) { //Only run the following, if the password is valid. Else, display the main menu.
                        int userInput = ZERO;

                        do {
                            System.out.print("Which book do you wish to update? "); // Prompt user for index number
                            int index = keyboard.nextInt();

                            if (inventory[index] == null) { // Verify whether or not there is a book at said index in the array
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
                                    } while (userInput < ONE || userInput > FIVE); // Validate user input

                                    changeBookInfo(userInput, index); // Call this method to start the process of changing the selected information
                                } while (userInput != FIVE); // Repeat these options until user decides to quit this operation

                            }

                        } while (userInput == ONE); // Repeat this while the user still wants to try changing another book.

                    }

                    break; // userInput == 2 (wants to go back to main menu)

                case THREE: // Display all books by a specific author
                    boolean authorExists = false; // Create a control variable to check if the author the user is asking for has written a book from our inventory.
                    do {
                        System.out.print("\nPlease enter the author's name: "); // Prompt user t oenter name of author he/she is looking for
                        String inputName = keyboard.next();
                        for (int i = ZERO; i < inventory.length; i++) { //Iterate through the entire array
                            if (inventory[i] == null) {
                                continue; // If said element is null, authorExists stays false.
                            }
                            if (inventory[i].getAuthor().equalsIgnoreCase(inputName)) {
                                System.out.println(inventory[i]); // Print said book info
                                authorExists = true; //If the compared authors are equal, it means that there exists a book with that author.
                            }
                        }

                        if (!authorExists) { // If the user provided an author that has not been found, inform the user and prompt them to try again.
                            System.out.println("No book written by this author has been found. Try again.");
                        }

                    } while (!authorExists); // Repeat this until the user provides the program with valid author names that have written books from out inventory.

                    break;

                case FOUR: // Display all books under a certain price
                    boolean cheaperThanExists = false; // Create a control variable that will let us know whether or not the userInput yields results.

                    do {
                        System.out.print("\nPlease enter the max price: "); // Prompt user to enter the compared price
                        double inputPrice = keyboard.nextDouble();

                        for (int i = ZERO; i < inventory.length; i++) { // Iterate through the entire array
                            if (inventory[i] == null) {
                                continue; // If said element is null, cheaperThanExists stays false.
                            }
                            if (inputPrice > inventory[i].getPrice()) { // Verify if the compared book has a lower price than the user input
                                System.out.println(inventory[i]);
                                cheaperThanExists = true;
                            }
                        }

                        if (!cheaperThanExists) {
                            System.out.println("No book cheaper than that price has been found. Try again.");
                        }
                    } while (!cheaperThanExists);// Repeat this until the user provides the program with price that will yield cheaper books in the incentory.

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
     * 1. Enter new books <br>2. Change the information of a book. <br>3.
     * Display all books by a specific author. <br>4. Display all books under a
     * certain price.
     * <br>5. End the program</br>
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
        } while (chosenNumberFromMenu < ONE || chosenNumberFromMenu > FIVE); // Validate user input

    }

    /**
     * This method checks if the user input matches the password.
     *
     * The user has 3 chances to enter the correct input. Once that limit is
     * reached without the correct password, a false value is returned.
     *
     * @return Boolean - true if the user has successfully entered the valid
     * password; false otherwise
     */
    public static boolean isValidPassword() {

        numberOfStrikes = ZERO;
        do {
            System.out.print("Please enter your password: "); // Prompt the user to enter his/her password
            inputPassword = keyboard.next(); // Retrieve user input and store it in variable inputPassword
            numberOfStrikes++;
        } while (!inputPassword.equals(PASSWORD) && numberOfStrikes < THREE);

        // When the program gets out of the do-while loop, we want to know why it got out. There's two choices: correct password entered or numberOfStrikes = 3
        if (inputPassword.equals(PASSWORD)) {
            return true;
        } else { // numberOfStrikes = 3
            return false;
        }
    }

    /**
     * This method is used to change an information from an existing book in the
     * inventory. This method is called when the user decides which information
     * they want to change from the book they chose. Depending on their choice,
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
            case ONE:
                System.out.print("Change author to -> ");
                inventory[index].setAuthor(keyboard.nextLine()); // set the author of the book at the specified index to the specified value
                System.out.println("Book #" + index + "\n" + inventory[index]);
                break;
            case TWO:
                System.out.print("Change title to -> ");
                inventory[index].setTitle(keyboard.nextLine()); // set the title of the book at the specified index to the specified value
                System.out.println("Book #" + index + "\n" + inventory[index]);
                break;
            case THREE:
                System.out.print("Change ISBN to -> ");
                inventory[index].setIsbn(keyboard.nextLong()); // set the ISBN of the book at the specified index to the specified value
                System.out.println("Book #" + index + "\n" + inventory[index]);
                break;
            case FOUR:
                System.out.print("Change price to -> ");
                inventory[index].setPrice(keyboard.nextDouble()); // set the price of the book at the specified index to the specified value
                System.out.println("Book #" + index + "\n" + inventory[index]);
                break;

        }

    }

}
