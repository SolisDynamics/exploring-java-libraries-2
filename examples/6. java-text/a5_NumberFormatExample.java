package a6_java.text;

import java.text.*;
import java.util.*;

public class a5_NumberFormatExample {

    public static void main(String[] args) {
        try {
            // Method 9: getAvailableLocales  
            // Retrieve and display all available locales for number formatting  
            Locale[] availableLocales = NumberFormat.getAvailableLocales();
            System.out.println("Total Available Locales: " + availableLocales.length);

            // Method 10: getInstance  
            // Get the default number format for the current locale  
            NumberFormat defaultFormat = NumberFormat.getInstance();
            System.out.println("Default Format: " + defaultFormat.format(12345.678));

            // Method 11: getInstance(Locale)  
            // Get the number format for a specified locale (French)  
            NumberFormat frenchFormat = NumberFormat.getInstance(Locale.FRENCH);
            System.out.println("French Format: " + frenchFormat.format(12345.678));

            // Method 12: getIntegerInstance  
            // Get the integer format for the current default locale  
            NumberFormat intFormat = NumberFormat.getIntegerInstance();
            System.out.println("Integer Format: " + intFormat.format(12345));

            // Method 13: getIntegerInstance(Locale)  
            // Get the integer format for a specified locale (German)  
            NumberFormat germanIntFormat = NumberFormat.getIntegerInstance(Locale.GERMAN);
            System.out.println("German Integer Format: " + germanIntFormat.format(12345));

            // Method 14: getCurrencyInstance  
            // Get the currency format for the current default locale  
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
            System.out.println("Currency Format: " + currencyFormat.format(12345.678));

            // Method 15: getCurrencyInstance(Locale)  
            // Get the currency format for a specified locale (Japanese)  
            NumberFormat japanCurrencyFormat = NumberFormat.getCurrencyInstance(Locale.JAPAN);
            System.out.println("Japanese Currency Format: " + japanCurrencyFormat.format(12345.678));

            // Method 16: getPercentInstance  
            // Get the percentage format for the current default locale  
            NumberFormat percentFormat = NumberFormat.getPercentInstance();
            System.out.println("Percent Format: " + percentFormat.format(0.25));

            // Method 17: getPercentInstance(Locale)  
            // Get the percentage format for a specified locale (Italian)  
            NumberFormat italyPercentFormat = NumberFormat.getPercentInstance(Locale.ITALY);
            System.out.println("Italian Percent Format: " + italyPercentFormat.format(0.25));

            // Method 18: getCompactNumberInstance  
            // Get compact number format for the default locale  
            NumberFormat compactFormat = NumberFormat.getCompactNumberInstance();
            System.out.println("Compact Number Format: " + compactFormat.format(1500));

            // Method 19: getCompactNumberInstance(Locale, Style)  
            // Get compact number format with a specified style for the French locale  
            NumberFormat compactFrenchFormat = NumberFormat.getCompactNumberInstance(Locale.FRENCH, NumberFormat.Style.SHORT);
            System.out.println("Compact French Format: " + compactFrenchFormat.format(1500));

            // Formatting a double number using Method 5: format(double number)  
            System.out.println("Formatted Double: " + defaultFormat.format(12345.678));

            // Method 20: parse(String)  
            // Parsing a formatted string back to a number  
            String numberString = "12,345.67";
            Number parsedNumber = defaultFormat.parse(numberString);
            System.out.println("Parsed Number: " + parsedNumber);

            // Using Method 35 to set currency  
            currencyFormat.setCurrency(Currency.getInstance("USD"));
            System.out.println("Formatted Currency (USD): " + currencyFormat.format(12345.678));

            // Method 23: getMaximumFractionDigits  
            System.out.println("Max Fraction Digits: " + defaultFormat.getMaximumFractionDigits());

            // Method 27: setMaximumFractionDigits  
            defaultFormat.setMaximumFractionDigits(2);
            System.out.println("Format after setting max fraction digits to 2: " + defaultFormat.format(12345.6789));

            // Method 31: isGroupingUsed  
            System.out.println("Is Grouping Used: " + defaultFormat.isGroupingUsed());

            // Method 33: setGroupingUsed  
            defaultFormat.setGroupingUsed(false);
            System.out.println("Format after disabling grouping: " + defaultFormat.format(123456789));

            // Checking decimal separator visibility with Method 39  
            System.out.println("Is Decimal Separator Always Shown? " + ((DecimalFormat) currencyFormat).isDecimalSeparatorAlwaysShown());

            // Method 38: getCurrency  
            System.out.println("Currency in use: " + currencyFormat.getCurrency().getSymbol());

            // Method 1: clone  
            NumberFormat clonedFormat = (NumberFormat) defaultFormat.clone();
            System.out.println("Clone Equality: " + defaultFormat.equals(clonedFormat));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
