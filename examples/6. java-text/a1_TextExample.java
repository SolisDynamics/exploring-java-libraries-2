package a6_java.text;

import java.text.*;
import java.util.*;

/** * A comprehensive demonstration of the java.text package capabilities.  
 * This application showcases various aspects of text processing and formatting,  
 * including internationalization (i18n) and localization (l10n).  
 */
public class a1_TextExample {

    /** * Method 1: Text, Number, and Date Formatting  
     * This method demonstrates how to format texts, numbers, and dates based on   
     * user locales, ensuring proper representation for different cultural contexts.  
     */
    public static void performTextNumberDateFormatting() {
        System.out.println("1. Text, Number, and Date Formatting:");

        // Define locales for various regions  
        Locale usLocale = Locale.US;
        Locale germanyLocale = Locale.GERMANY;
        Locale japanLocale = Locale.JAPAN;

        // Set a number to be formatted  
        double number = 1234567.89;

        // Formatting numbers using NumberFormat  
        NumberFormat usNumberFormat = NumberFormat.getInstance(usLocale);
        NumberFormat germanNumberFormat = NumberFormat.getInstance(germanyLocale);

        // Display formatted numbers  
        System.out.println("US Number Format: " + usNumberFormat.format(number));
        System.out.println("German Number Format: " + germanNumberFormat.format(number));

        // Formatting currency  
        NumberFormat usCurrencyFormat = NumberFormat.getCurrencyInstance(usLocale);
        NumberFormat japanCurrencyFormat = NumberFormat.getCurrencyInstance(japanLocale);

        // Display formatted currencies  
        System.out.println("US Currency: " + usCurrencyFormat.format(number));
        System.out.println("Japan Currency: " + japanCurrencyFormat.format(number));

        // Formatting dates  
        Date currentDate = new Date();
        DateFormat usDateFormat = DateFormat.getDateInstance(DateFormat.LONG, usLocale);
        DateFormat germanDateFormat = DateFormat.getDateInstance(DateFormat.LONG, germanyLocale);
        DateFormat japanDateFormat = DateFormat.getDateInstance(DateFormat.LONG, japanLocale);

        System.out.println("US Date Format: " + usDateFormat.format(currentDate));
        System.out.println("German Date Format: " + germanDateFormat.format(currentDate));
        System.out.println("Japan Date Format: " + japanDateFormat.format(currentDate));

        // Formatting time  
        DateFormat usTimeFormat = new SimpleDateFormat("hh:mm:ss a", usLocale); // AM/PM format  
        DateFormat militaryTimeFormat = new SimpleDateFormat("HH:mm:ss", germanyLocale); // 24-hour format  

        System.out.println("US Time Format: " + usTimeFormat.format(currentDate));
        System.out.println("German Military Time Format: " + militaryTimeFormat.format(currentDate));
    }

    /** * Method 2: Text and Number Parsing  
     * Converts formatted strings back into their original data types using   
     * NumberFormat and DateFormat. This is essential for ensuring that user-input   
     * data can be processed correctly.  
     */
    public static void performTextNumberParsing() {
        System.out.println("\n2. Text and Number Parsing:");

        // Parse a number from a formatted string  
        try {
            NumberFormat usNumberFormat = NumberFormat.getInstance(Locale.US);
            String numberString = "1,234,567.89";
            Number parsedNumber = usNumberFormat.parse(numberString);
            System.out.println("Parsed Number: " + parsedNumber);
        } catch (ParseException e) {
            System.err.println("Parsing Error: " + e.getMessage());
        }

        // Parse a date from a formatted string  
        try {
            DateFormat usDateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.US);
            String dateString = "August 25, 2025"; // Example date  
            Date parsedDate = usDateFormat.parse(dateString);
            System.out.println("Parsed Date: " + parsedDate);
        } catch (ParseException e) {
            System.err.println("Date Parsing Error: " + e.getMessage());
        }
    }

    /** * Method 3: Text Comparison and Sorting  
     * This method compares and sorts texts in different languages according to  
     * grammatical rules using Collator, showcasing localized sorting capabilities.  
     */
    public static void performTextComparisonAndSorting() {
        System.out.println("\n3. Text Comparison and Sorting:");
        String[] words = {"äpfel", "Aepfel", "apfel", "banane"};
        Collator germanCollator = Collator.getInstance(Locale.GERMANY);

        // Sort words using the German collator  
        Arrays.sort(words, germanCollator);
        System.out.println("Sorted Words: " + Arrays.toString(words));
    }

    /** * Method 4: Parameterized Text Generation  
     * Creates dynamic texts using String.format, allowing for dynamic formatting of user-specific messages.  
     */
    public static void performParameterizedTextGeneration() {
        System.out.println("\n4. Parameterized Text Generation:");
        String name = "Sarah";
        int age = 28;
        String dynamicMessage = String.format("Hello, %s! You are %d years old.", name, age);

        // Display dynamic message  
        System.out.println("Dynamic Message: " + dynamicMessage);
    }

    /** * Method 5: Null Value Handling  
     * Demonstrates careful handling of null values during formatting and parsing,  
     * ensuring robustness against null reference exceptions.  
     */
    public static void handleNullValues() {
        System.out.println("\n5. Null Value Handling:");
        NumberFormat format = NumberFormat.getInstance();
        Object nullValue = null; // Example of a null value  

        // Check if the value is null  
        if (nullValue == null) {
            System.out.println("Null value cannot be formatted.");
        } else {
            System.out.println("Formatted Null Value: " + format.format(nullValue));
        }
    }

    /** * Method 6: Thread Safety Demonstration  
     * Demonstrates the use of ThreadLocal to ensure that NumberFormat instances are   
     * thread-safe in multi-threaded applications.  
     */
    public static void demonstrateThreadSafety() {
        System.out.println("\n6. Thread Safety Consideration:");
        ThreadLocal<NumberFormat> threadSafeFormat = ThreadLocal.withInitial(() -> 
            NumberFormat.getInstance(Locale.US)
        );

        // Display thread-safe formatted number  
        System.out.println("Thread-safe format: " + threadSafeFormat.get().format(1500.75));
    }

    /* Main method to execute all examples of internationalization and localization. */
    public static void main(String[] args) {
        performTextNumberDateFormatting();
        performTextNumberParsing();
        performTextComparisonAndSorting();
        performParameterizedTextGeneration();
        handleNullValues();
        demonstrateThreadSafety();
    }
}
