import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
   An example demonstrating multiple catch blocks, this time with
   resources specified in the try.

   Early versions based on similar code in Horstmann's Big Java.

   @author Jim Teresco
   @version Spring 2020
 */
public class CatchExampleWithResources {

    /**
       Main method to demonstrate try-catch-finally.

       @param args[0] a file to try to open for reading.
    */
    public static void main(String[] args) {
        
        // let's try to open a file and read in a string then convert
        // it to an int, stored in this variable
        int value = 0;

	// this time, we "try with resources", meaning that this object's
	// close method will automatically be called if the construction
	// is successful, including if the block ends when exception is
	// thrown and possibly handled.
        try (Scanner s = new Scanner(new File(args[0]))) {
                        
            // this line might generate a NoSuchElementException
            String word = s.next();
            
            // this line might generate a NumberFormatException
            value = Integer.parseInt(word);
	
	    System.out.println("I found the number " + value + " in your file.");
        }
        catch (FileNotFoundException e) {
            
            // if our file is not found, we'll call this exception handler,
            // with the appropriate message as encapsulated in the exception
            System.err.println(e.getMessage());
            System.err.println("Please try again, but with a file that exists next time.");
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println("Your file existed, but some other IOException happened...");
        }
        catch (NoSuchElementException e) {
            System.err.println("I expected your file to have something in it...");
        }
        catch (NumberFormatException e) {
            System.err.println("I expected your file to have an int in it...");
        }
    }
}
