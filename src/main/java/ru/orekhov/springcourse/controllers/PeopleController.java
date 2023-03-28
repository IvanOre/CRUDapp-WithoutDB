package ru.orekhov.springcourse.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.orekhov.springcourse.dao.PersonDAO;
import ru.orekhov.springcourse.models.Person;

import javax.validation.Valid;

/**
 * @author Neil Alishev
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;// внедрили персон дао

    @Autowired // указали спрингу что нужно внедрить зависимость
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) { // получим всех людей из DAO и передадим на отображение в представление
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")   // получим одного человека из DAO по id и передадим на отображение тоже
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }
@GetMapping("/new") // /people/new будет форма для добавления нового человека
   // public String newPerson(Model model){// не забываем модель внедрить для таймлифа что бы передать объект для которого форма нужна
        public String newPerson(@ModelAttribute("person") Person person){// можно так через модел аттрибьют
       // model.addAttribute("person",new Person());

        return "people/new";
    }
    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){// получим чела с данными из формы которые реализуем в new
        //добавили аннотацию @Valid для самого класса person в аргументе и BandingResult(ошибка помещается в него) он должен всегда идти после модели которая валидируется
        if (bindingResult.hasErrors())// если есть ошибки то сразу возврат на страницу пипл/нью.ошибки покажем в таймлфи коде
            return "people/new";
        personDAO.save(person);// объект класса персон добавим в БД с помощью ДАО
        return "redirect:/people";// перенаправляем на /people

    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("person",personDAO.show(id));// ключ person значение что вернется из personDAO по id
        return "people/edit";

    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,BindingResult bindingResult,
                         @PathVariable("id") int id){// принимает персон из формы
if (bindingResult.hasErrors())
    return "people/edit";
        personDAO.update(id,person);
        return "redirect:/people";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){// удалям человека по id из дао
        personDAO.delete(id);
        return "redirect:/people";

    }
}