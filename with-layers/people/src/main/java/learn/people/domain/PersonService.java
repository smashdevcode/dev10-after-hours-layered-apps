package learn.people.domain;

import learn.people.data.PersonRepository;
import learn.people.models.Person;

import java.util.List;

public class PersonService {
    private final PersonRepository repository = new PersonRepository();

    // find
    public List<Person> findAll() {
        return repository.findAll();
    }

    // create
    public PersonResult create(Person person) throws Exception {
        PersonResult result = validate(person);
        if (!result.isSuccess()) {
            return result; // short circuit... or bail!
        }

        person = repository.create(person);
        result.setPerson(person);

        return result;
    }

    // update

    // delete

    private PersonResult validate(Person person) {
        PersonResult result = new PersonResult();

        if (person == null) {
            result.addMessage("Person must not be null.");
            return result; // bail!
        }

        if (person.getFirstName() == null || person.getFirstName().isBlank()) {
            result.addMessage("Person first name is required.");
        }

        if (person.getLastName() == null || person.getLastName().isBlank()) {
            result.addMessage("Person last name is required.");
        }

        return result;
    }
}
