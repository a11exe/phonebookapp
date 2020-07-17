/*
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 17.07.2020
 */
package com.alllexe.phonebookapp.service;

import com.alllexe.phonebookapp.model.Phone;
import com.alllexe.phonebookapp.model.User;
import com.alllexe.phonebookapp.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository repository;

    public Phone save(Phone phone) {
        return repository.save(phone);
    }

    public Optional<Phone> findByPhoneNumberAndUser(String phoneNumber, User user) {
        return repository.findByPhoneNumberAndUser(phoneNumber, user);
    }

    public List<Phone> findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    public void deletePhoneByPhoneNumber(String phoneNumber) {
        repository.deletePhoneByPhoneNumber(phoneNumber);
    }
}
