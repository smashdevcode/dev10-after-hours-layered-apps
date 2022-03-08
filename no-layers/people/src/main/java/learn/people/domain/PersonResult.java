package learn.people.domain;

import learn.people.models.Person;

import java.util.ArrayList;

public class PersonResult {
    private final ArrayList<String> messages = new ArrayList<>();
    private Person person;

    public boolean isSuccess() {
        return messages.size() == 0;
    }

    public ArrayList<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
