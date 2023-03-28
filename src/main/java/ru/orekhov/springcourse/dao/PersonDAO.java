package ru.orekhov.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.orekhov.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT; // через PEOPLE_COUNT инкриментируем наш id по ++(+1) автоматически каждому
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom",24,"tom24@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Bob",43,"bob43@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Mike",19,"mike@yandex.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Katy",18,"katy@yahoo.com"));
    }

    public List<Person> index() {// представление возвращает список людей people
        return people;
    }

    public Person show(int id) {// через лямбду возвращаем id человека
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
    public void save(Person person){// ничего ен возвращает ,а только принимает объект класса персон
        person.setId(++PEOPLE_COUNT);// генерим новое id автоматически при добавлении нового чела в БД
        people.add(person);

    }
    public void update(int id,Person updatedPerson){
        Person personToBeUpdated = show(id);// передаем шоу id,находим чела из БД и помещаем в переменную персонтобеапдейт

        personToBeUpdated.setName(updatedPerson.getName());// обновляем имя челу
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }
    public void delete(int id){
        people.removeIf(p -> p.getId() == id);// удаляем по предикату из нашего листа. опишем через лямбду
// получаем у персон id если оно true то удаляем из нашего списка
    }
}