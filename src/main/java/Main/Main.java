package Main;

import Person.Person;
import Repository.BaseOperation;
import Repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BaseOperation option = new Repository();
        Scanner scanner = new Scanner(System.in);

        int operationNumber = 0;
        while (operationNumber != 5) {
            System.out.println("------------------------------Menu-------------------------------------------------");
            System.out.print("Connection: ");
            option.connDb();
            System.out.println("[1] - get all person, [2] - get by id, [3] - save person, [4] - update person, [5] - exit");
            System.out.println("-----------------------------------------------------------------------------------");
            operationNumber = scanner.nextInt();
            switch (operationNumber) {
                case 1:
                    System.out.println("-----------------------------------------------------------------------------------");
                    List<Person> person = new ArrayList<>();
                    person.addAll(option.getAll());
                    person.stream().forEach(System.out::println);
                    System.out.println("-----------------------------------------------------------------------------------");
                    break;
                case 2:
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.print("enter id of person: ");
                    System.out.println(option.getById(scanner.nextInt()).toString());
                    System.out.println("-----------------------------------------------------------------------------------");
                    break;
                case 3:
                    System.out.println("-----------------------------------------------------------------------------------");
                    if (option.save(addPerson()) == true) {
                        System.out.println("Save succesfull");
                    } else {
                        System.out.println("Save Error");
                    }
                    System.out.println("-----------------------------------------------------------------------------------");
                    break;
                case 4:
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.print("Write id person:");
                    int id = scanner.nextInt();
                    if (option.update(id, addPerson()) == true) {
                        System.out.println("Succesfull update");
                    } else {
                        System.out.println("Update error");
                    }
                    System.out.println("-----------------------------------------------------------------------------------");
                    break;
            }
        }
    }

    public static Person addPerson() {
        Person person = new Person();
        System.out.print("Write name: ");
        Scanner scanner = new Scanner(System.in);
        person.setName(String.valueOf(scanner.nextLine()));
        System.out.print("Write surname: ");
        person.setSurname(String.valueOf(scanner.nextLine()));
        System.out.print("Write age: ");
        person.setAge(scanner.nextInt());


        return person;
    }
}
