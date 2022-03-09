package learn.people.data;

import learn.people.models.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {
    private static final String filePath = "./data/people.csv";

    public List<Person> findAll() {
        // read the data from the CSV file
        ArrayList<Person> people = new ArrayList<>();

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

    public Person create(Person person) throws Exception {
        List<Person> people = findAll();
        int nextId = getNextId(people);
        person.setId(nextId);
        people.add(person);
        writeToFile(people);
        return person;
    }

    // update

    // delete

    private int getNextId(List<Person> people) {
        int nextId = 0;
        for (Person p : people) {
            if (nextId < p.getId()) {
                nextId = p.getId();
            }
        }
        nextId = nextId + 1;
        return nextId;
    }

    private void writeToFile(List<Person> people) throws Exception {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Person person : people) {
                writer.printf("%s,%s,%s%n", person.getId(), person.getFirstName(), person.getLastName());
            }
        } catch (IOException ex) {
            throw new Exception("Could not write to file path: " + filePath, ex);
        }
    }
}
