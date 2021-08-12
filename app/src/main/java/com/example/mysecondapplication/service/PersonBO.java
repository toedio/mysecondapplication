package com.example.mysecondapplication.service;

import android.app.Application;

import com.example.mysecondapplication.config.AppDatabase;
import com.example.mysecondapplication.helper.SimpleCallback;
import com.example.mysecondapplication.repository.Person;
import com.example.mysecondapplication.repository.PersonDAO;

import java.util.List;

public class PersonBO {

    private PersonDAO personDAO;

    public PersonBO(Application application) {
        personDAO = AppDatabase.getDatabase(application).personDAO();
    }

    public Long count() {
        return personDAO.count();
    }

    public Long countSync() {
        return personDAO.countBySync(true);
    }

    public Long countUnsync() {
        return personDAO.countBySync(false);
    }

    public List<Person> getAll() { return personDAO.getAll(); }

    public List<Person> getUnsync() { return personDAO.getBySync(false); }

    public void save(Person person, SimpleCallback simpleCallback) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            try {
                Long personId = personDAO.save(person);
                person.setId(personId);
                simpleCallback.object = person;
                simpleCallback.success = true;
            } catch (Exception e) {
                simpleCallback.success = false;
            }
            simpleCallback.call();

        });
    }

    public void update(Person person, SimpleCallback simpleCallback) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            try {
                personDAO.update(person);
                simpleCallback.object = person;
                simpleCallback.success = true;
            } catch (Exception e) {
                simpleCallback.success = false;
            }
            simpleCallback.call();
        });
    }

    public void update(Person person) {
        AppDatabase.databaseWriteExecutor.execute(() -> personDAO.update(person));
    }
}
