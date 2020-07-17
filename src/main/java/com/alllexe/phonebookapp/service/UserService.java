/*
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 17.07.2020
 */
package com.alllexe.phonebookapp.service;

import com.alllexe.phonebookapp.model.User;
import com.alllexe.phonebookapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Optional<User> findByName(String name) {
        return repository.findByUsername(name);
    }

    public User save(User user) {
        return repository.save(user);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public void delete(User user) {
        repository.delete(user);
    }
}
