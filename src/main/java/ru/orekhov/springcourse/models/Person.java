package ru.orekhov.springcourse.models;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Person {
    private int id;

    @NotEmpty(message = "Name should not be empty")// добавили валидацию,что бы не было пустых значений
    @Size(min = 2,max = 30,message = "Name should between 2 and 30 characters")// размер имени
    private String name;
    @Min(value = 0,message ="Age should be grater than 0")// границы возраста
    private int age;
    @NotEmpty(message = "Email should be not empty")// не пустой
    @Email(message = "Email should be valid")// валидный должен быть
    private String email;


    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Person() {// создали пустой конструктор для метода в пипл контроллер для newPerson

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
