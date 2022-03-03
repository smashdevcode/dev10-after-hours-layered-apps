package learn.people;

import learn.people.models.Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*

Pros

* See everything in one place
* Easy to learn in the beginning
* Easy to share global variables across methods
* Quicker to develop

Cons

* How well can we support more than one developer?
* While it's helpful to see everything in one place... there's a tipping to the dark side
* Difficult to unit test
* Difficult to reuse code

Things to consider...

* Maintainability
* Proper encapsulation
* Single responsibility principle
* Loose coupling between components

 */

public class App {
    private static final Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        displayHeader("Welcome to the People app!");
        // Global error handler.
        try {
            runApp();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        displayMessage("Goodbye!");
    }

    private static void runApp() {
        for (int option = chooseMenuOption();
             option > 0;
             option = chooseMenuOption()) {

            switch (option) {
                case 1:
                    displayPeople();
                    break;
                case 2:
                    addPerson();
                    break;
                case 3:
                    displayMessage("Not implemented");
                    break;
                case 4:
                    displayMessage("Not implemented");
                    break;
            }
        }
    }

    private static int chooseMenuOption() {
        displayHeader("Main Menu");
        System.out.println("0. Exit");
        System.out.println("1. Display People");
        System.out.println("2. Add a Person");
        System.out.println("3. Update a Person");
        System.out.println("4. Remove a Person");
        return readInt("Choose [0-4]", 0, 4);
    }

    private static void displayPeople() {
        ArrayList<Person> people = findAll();

        // display that data
        for (Person person : people) {
            System.out.printf("%s: %s %s%n", person.getId(), person.getFirstName(), person.getLastName());
        }
    }

    private static void addPerson() {

    /*
     * Prompt the user for first name and last name
     *
     * Add readRequiredString() helper method
     *
     * Create an instance of the Person model
     *
     * Validate the user data
     *   Validate that the first name was provided
     *   Validate that the last name was provided
     *
     * If there are validation errors, display the errors to the user and don't persist the person to the CSV file
     *
     * Persist the person to the CSV file
     *   Get the list of people
     *   Determine the next available ID
     *   Set the person's ID
     *   Add the person to the list
     *   Write the list to the file
     *
     * Display success message
     */

    }

    //////////////////////////////////////////////////////////////////////
    // FILE ACCESS
    //////////////////////////////////////////////////////////////////////

    private static ArrayList<Person> findAll() {
        // read the data from the CSV file
        ArrayList<Person> people = new ArrayList<>();
        String filePath = "./data/people.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(",");

                if (fields.length == 3) {
                    Person person = new Person(
                            Integer.parseInt(fields[0]),
                            fields[1],
                            fields[2]
                    );
                    people.add(person);
                }

            }
        } catch (FileNotFoundException ex) {
            // If the file doesn't exist, no big deal.
        } catch (IOException ex) {
            System.out.println("Could not open the file path: " + filePath);
        }
        return people;
    }

    //////////////////////////////////////////////////////////////////////
    // HELPER METHODS
    //////////////////////////////////////////////////////////////////////

    private static void displayHeader(String message) {
        int length = message.length();
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(length));
        System.out.println();
    }

    private static void displayMessage(String message) {
        System.out.println();
        System.out.println(message);
    }

    private static String readString(String prompt) {
        System.out.print(prompt + ": ");
        return console.nextLine();
    }

    private static int readInt(String prompt) {
        while (true) {
            String value = readString(prompt);
            try {
                // Whew! I only have one early return :)
                return Integer.parseInt(value);
            } catch (NumberFormatException ex) {
                System.out.printf("'%s' is not a valid number.%n", value);
            }
        }

//        boolean isValid = false;
//        int intValue = -1;
//        while (!isValid) {
//            String value = readString(prompt);
//            try {
//                intValue = Integer.parseInt(value);
//                isValid = true;
//            } catch (NumberFormatException ex) {
//                System.out.printf("'%s' is not a valid number.%n", value);
//            }
//        }
//        return intValue;
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.printf("[Error]%nValue must be between %s and %s.%n", min, max);
        }
    }
}
