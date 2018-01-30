// Assignment 1
// Part 1 (Book class)
// Written by: Viveka Anban (40063308) and Ferdousara Parvin (40062738)

//TODO (FERDOU): should we maybe change teh equals method into the real way of making it ? equals(Object object)? I feel like that would be beneficial to the javadoc

package assignment1;

/**
 *
 * @author Viveka Anban anf Ferdousara Parvin
 */
//TODO: For the equals method, shoud we compare the price en tenant compte du margin d'erreur
//TODO: Why is there 2 directives on how to write the toString();
public class Book {
    
    private String title;
    private String author;
    private long isbn;
    private double price;
    private static int counterBook;

    //ctors
    /**
     * Constructs a newly allocated Book object with the default values.
     * By default, title is set to "The Alchemist", author is set to "Paulo Coelho", idbn is set to 061122416 and the price is set to 11.99. 
     */
    public Book() {
        title = "The Alchemist";
        author = "Paulo Coelho";
        isbn = 061122416;
        price = 11.99;
        counterBook++;
    }
    
    
/**
 * Constructs a newly allocated Book object with the its attributes set to the parameters.
 * 
 * @param title Title of the book
 * @param author Author of the book
 * @param isbn ISBN of the book
 * @param price Price of the book
 */
    public Book(String title, String author, long isbn, double price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        counterBook++;
    }


    //Getters and Setters 
    /**Get the current value of the Title attribute
     * <p> 
     * The title is a String value that is usually written on the cover of the book.
     * 
     * @return title of the Book
     */
    public String getTitle() {
        return title;
    }
     /**Set the value of the Title 
     * <p> 
     * The title is a String value that is usually written on the cover of the book.
     * @param title must be a String
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**Get the current value of the Author attribute
     * <p> 
     * The author is the name of the person that wrote the Book.
     * 
     * @return author of the Book
     */
    public String getAuthor() {
        return author;
    }

    /**Set the value of the Author attribute. 
     * <p> 
     * The author is the name of the person that wrote the Book.
     * @param author must be a String
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**Get the current value of the ISBN attribute
     * <p> 
     * The ISBN is a 10 or 13 digit number sequence that is unique to the Book.
     * 
     * @return ISBN of the Book
     */
    public long getIsbn() {
        return isbn;
    }

    /**Set the value  of the ISBN attribute. 
     * <p> 
     * The ISBN is a 10 or 13 digit number sequence that is unique to the Book.
     * @param isbn must be a long
     */
    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    /**Get the current value of the price attribute
     * <p> 
     * The price is the currency (in CAN$) that is needed to buy the book.
     * 
     * @return Price of the Book
     */
    public double getPrice() {
        return price;
    }

    /**Set the value  of the price attribute. 
     * <p> 
     * The price is the currency (in CAN$) that is needed to buy the book.
     * @param price must be a double
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**Get the current value of static variable counterBook
     * <p> 
     * counterBook is a variable that is incremented every time a Book is created. It is used to know how many Books have been created at any point in time.
     * 
     * @return the total number of created Books
     */
    public static int findNumberOfCreatedBooks() {
        return counterBook;
    }

    //TODO: Put it as the real equals method?
    public Boolean equals(Book other) {
        return (isbn == other.isbn) && (price == other.price);
    }

    @Override
    /**
     * @return a String object representing the Book instance. Its contains all the attributes/properties of a Book to make it recognizable.
     */
    public String toString() {
        return "\nTitle: " + title + "\nAuthor: " + author + "\nISBN: " + isbn + "\nPrice: " + price + "\n";
    }

}
