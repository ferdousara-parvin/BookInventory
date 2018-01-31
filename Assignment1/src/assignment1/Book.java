// Assignment 1
// Part 1 (Book class)
// Written by: Viveka Anban (40063308) and Ferdousara Parvin (40062738)
package assignment1;

/**
 * <h1>Book class</h1>
 * COMP249<br>
 * Assignment 1 <br>
 * Due date: Thursday, January 31st, 2018</br>
 *
 * @author Viveka Anban(40063308) and Ferdousara Parvin(40062738) COMP249
 */


public class Book {

    // Attributes of a book
    private String title;
    private String author;
    private long isbn;
    private double price;
    private static int counterBook;

    //Constructors
    /**
     * Constructs a new Book object with the default values. By
     * default, the title of the book is set to "The Alchemist", the author is
     * set to "Paulo Coelho", the ISBN is set to 061122416 and the price is set
     * to $11.99.
     */
    public Book() {
        title = "The Alchemist";
        author = "Paulo Coelho";
        isbn = 061122416;
        price = 11.99;
        counterBook++; // Increment static variable counterBook every time a Book object is created
    }

    /**
     * Constructs a new Book object, whose title, author, isbn and price are specified by the arguments of the same name
     * parameters.
     *
     * @param title title of the book
     * @param author author of the book
     * @param isbn ISBN of the book
     * @param price price of the book
     */
    public Book(String title, String author, long isbn, double price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        counterBook++; // Increment static variable counterBook every time a Book object is created
    }

    //Getters and Setters 
    /**
     * Gets the current value of the Title attribute
     * <p>
     * The title is a String value that is usually written on the cover of the
     * book.
     *
     * @return String - title of the Book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the Title
     * <p>
     * The title is a String value that is usually written on the cover of the
     * book.
     *
     * @param title title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the current value of the Author attribute
     * <p>
     * The author is the name of the person that wrote the Book.
     *
     * @return String - author of the Book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the value of the Author attribute.
     * <p>
     * The author is the name of the person that wrote the Book.
     *
     * @param author author of the book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the current value of the ISBN attribute
     * <p>
     * The ISBN is a 10 or 13 digit number sequence that is unique to the Book.
     *
     * @return long - ISBN of the Book
     */
    public long getIsbn() {
        return isbn;
    }

    /**
     * Sets the value of the ISBN attribute.
     * <p>
     * The ISBN is a 10 or 13 digit number sequence that is unique to the Book.
     *
     * @param isbn ISBN of the Book
     */
    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    /**
     * Gets the current value of the price attribute
     * <p>
     * The price is the currency (in CAN$) that is needed to buy the book.
     *
     * @return double - price of the Book
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price attribute.
     * <p>
     * The price is the currency (in CAN$) that is needed to buy the book.
     *
     * @param price price of the book
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the current value of static variable counterBook
     * <p>
     * The variable counterBook is incremented every time a Book is created. It
     * is used to know how many Books have been created at any point in time.
     *
     * @return int - total number of created Books
     */
    public static int getNumberOfCreatedBooks() {
        return counterBook;
    }

    /**
     * Checks whether two Books are equal
     * @param other the Book to compare with this Book
     * @return Boolean - true if both Books have the same ISBN and the same price; false otherwise
     */
    public Boolean equals(Book other) {
        return (isbn == other.isbn) && (price == other.price);
    }
    
    /**
     * Returns a String representing this Book and its values.
     * @Override toString in class Object
     * @return String - returns all the value of all the attributes of
     * a Book to make it recognizable.
     */
    public String toString() {
        return "\nTitle: " + title + "\nAuthor: " + author + "\nISBN: " + isbn + "\nPrice: " + price + "\n";
    }

}
