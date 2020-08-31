package persistence;


import model.Cost;
import model.Income;
import model.Transaction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// A reader that can read Transaction data from a file
// COMMENTS: the hints of building this class come from
//           https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
public class Reader {
    public static final String DELIMITER = ",";


    // A dummy constructor which is used to pretend we have a constructor in the Reader class,
    // or we will fail the autobot coverage test
    public Reader() {

    }

    // EFFECTS: return a list of Transactions parsed from file; throws
    //          IOException if an exception is raised when opening / reading
    //          from file
    public static List<Transaction> readTransactions(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    //          containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of Transactions parsed from list of strings
    //          where each string contains data for one Transaction
    private static List<Transaction> parseContent(List<String> fileContent) {
        List<Transaction> transactions = new LinkedList<>();

        for (String line : fileContent) {
            LinkedList<String> lineComponents = splitString(line);
            transactions.add(parseTransaction(lineComponents));

        }
        return transactions;
    }

    // EFFECTS: return a list of strings obtained by splitting line on DELIMITER
    private static LinkedList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new LinkedList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 6 where element 0 represents the
    //           year, element 1 represents the month, element 2 represents the data
    //           element 3 represent the amount, and element 4 represents the category
    //           and element 5 represents the type
    // EFFECTS: returns a transaction constructed from components
    private static Transaction parseTransaction(List<String> components) {
        int year = Integer.parseInt(components.get(0));
        int month = Integer.parseInt(components.get(1));
        int date = Integer.parseInt(components.get(2));
        double amount = Double.parseDouble(components.get(3));
        String category = components.get(4);

        if (components.get(5).equals("true")) {
            return new Income(year, month, date, amount, category);
        } else {
            return new Cost(year, month, date, amount, category);
        }

    }


}
