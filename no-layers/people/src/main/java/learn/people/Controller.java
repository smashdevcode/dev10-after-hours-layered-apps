package learn.people;

import learn.people.data.PersonRepository;
import learn.people.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private static final Scanner console = new Scanner(System.in);
    private PersonRepository repository = new PersonRepository();

    public void run() {
        displayHeader("Welcome to the People app!");
        // Global error handler.
        try {
            runApp();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        displayMessage("Goodbye!");
    }

    private void runApp() throws Exception {
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

    private int chooseMenuOption() {
        displayHeader("Main Menu");
        System.out.println("0. Exit");
        System.out.println("1. Display People");
        System.out.println("2. Add a Person");
        System.out.println("3. Update a Person");
        System.out.println("4. Remove a Person");
        return readInt("Choose [0-4]", 0, 4);
    }

    private void displayPeople() {
        displayHeader("People");

        ArrayList<Person> people = repository.findAll();

        // display that data
        for (Person person : people) {
            System.out.printf("%s: %s %s%n", person.getId(), person.getFirstName(), person.getLastName());
        }
    }

    private void addPerson() throws Exception {
        displayHeader("Add a Person");

        // Interacting with the user to get the person.
        Person person = createPerson();

        List<String> errorMessages = validatePerson(person);

        if (errorMessages.size() > 0) {
            displayErrorMessages(errorMessages);
            return; // bail!
        }

        person = repository.create(person);

        displayMessage(String.format("[Success]%nPerson %s added.", person.getId()));
    }

    private List<String> validatePerson(Person person) {
        List<String> errorMessages = new ArrayList<>();

        if (person.getFirstName() == null || person.getFirstName().isBlank()) {
            errorMessages.add("Person first name is required.");
        }

        if (person.getLastName() == null || person.getLastName().isBlank()) {
            errorMessages.add("Person last name is required.");
        }
        return errorMessages;
    }

    //////////////////////////////////////////////////////////////////////
    // HELPER METHODS
    //////////////////////////////////////////////////////////////////////

    private void displayHeader(String message) {
        int length = message.length();
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(length));
        System.out.println();
    }

    private void displayMessage(String message) {
        System.out.println();
        System.out.println(message);
    }

    private void displayErrorMessages(List<String> errorMessages) {
        displayHeader("[Errors]");
        for (String errorMessage : errorMessages) {
            System.out.println(errorMessage);
        }
    }

    private Person createPerson() {
        // #1 "unwinding"... "decomposing"
//        String firstName = readString("First Name");
//        String lastName = readString("Last Name");
//
//        Person person = new Person(0, firstName, lastName);

        // #2
        Person person = new Person();
        person.setFirstName(readString("First Name"));
        person.setLastName(readString("Last Name"));
        return person;
    }

    private String readString(String prompt) {
        System.out.print(prompt + ": ");
        return console.nextLine();
    }

    private int readInt(String prompt) {
        while (true) {
            String value = readString(prompt);
            try {
                // Whew! I only have one early return :)
                return Integer.parseInt(value);
            } catch (NumberFormatException ex) {
                System.out.printf("'%s' is not a valid number.%n", value);
            }
        }

        // In the real world, you wouldn't leave commented out code in your files.
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

    private int readInt(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.printf("[Error]%nValue must be between %s and %s.%n", min, max);
        }
    }
}
