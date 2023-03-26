import java.util.*;
//import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        List<Person> minor = persons.stream()
             .filter(person -> person.getAge() < 18)
                .collect(Collectors.toList());
        long count = minor.stream()
                .count();
        System.out.println("Перепись населения города Лондон");
        System.out.println("Количестово неосвершеннолетних граждан:");
        System.out.println(count);
        System.out.println("_______________________________________________________________________________________________________________________________________________________________________________________________________________________");

        List<String> recruits = persons.stream()
                .filter((person) -> person.getSex() == Sex.MAN)
                .filter((person) -> (person.getAge() >= 18 & (person.getAge()<=27)))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Фамилии призывников:");
        System.out.println(recruits);
        System.out.println("_____________________________________________________________________________________________________________________________________________________________________________________________________________________________");

        List<Person> worker = persons.stream()
                .filter( (person) -> person.getEducation() == Education.HIGHER)
                .filter((person) -> (person.getAge() >= 18))
                .filter((person) -> (person.getSex() == Sex.MAN) & (person.getAge() < 65) ||(person.getSex() == Sex.WOMAN) & (person.getAge() < 60))
//                .filter((person) -> (person.getSex() == Sex.WOMAN) & (person.getAge() < 60))
                .sorted (Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Фамилии потенциально работоспособных людей с высшим образованием:");
        System.out.println(worker);
        System.out.println("________________________________________________________________________________________________________________________________________________________________________________________________________________________________");


    }
}