package learn.people;

import learn.people.data.PersonRepository;
import learn.people.models.Person;

import java.util.ArrayList;
import java.util.List;
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
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.run();
    }
}
