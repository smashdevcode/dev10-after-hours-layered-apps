package learn.people.ui;

import learn.people.domain.PersonResult;
import learn.people.domain.PersonService;
import learn.people.models.Person;

import java.util.List;

public class Controller {
    private final PersonService service = new PersonService();
    private final View view = new View();

    public void run() {
        view.displayHeader("Welcome to the People app!");
        // Global error handler.
        try {
            runApp();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        view.displayMessage("Goodbye!");
    }

    private void runApp() throws Exception {
        for (int option = view.chooseMenuOption();
             option > 0;
             option = view.chooseMenuOption()) {

            switch (option) {
                case 1:
                    displayPeople();
                    break;
                case 2:
                    createPerson();
                    break;
                case 3:
                    view.displayMessage("Not implemented");
                    break;
                case 4:
                    view.displayMessage("Not implemented");
                    break;
            }
        }
    }

    private void displayPeople() {
        view.displayHeader("People");
        List<Person> people = service.findAll();
        view.displayPeople(people);
    }

    private void createPerson() throws Exception {
        view.displayHeader("Add a Person");
        Person person = view.createPerson();
        PersonResult result = service.create(person);
        if (result.isSuccess()) {
            view.displayMessage(String.format("[Success]%nPerson %s added.", result.getPerson().getId()));
        } else {
            view.displayErrorMessages(result.getMessages());
        }
    }
}
