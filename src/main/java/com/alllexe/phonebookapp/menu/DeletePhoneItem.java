package com.alllexe.phonebookapp.menu;

import com.alllexe.phonebookapp.exception.EmptyValueException;
import com.alllexe.phonebookapp.model.User;
import com.alllexe.phonebookapp.service.PhoneService;
import com.alllexe.phonebookapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

/**
 * Удаление телефона.
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 17.07.2020
 */
@Component
public class DeletePhoneItem implements MenuItem {

    @Value("${delete.phone.item}")
    private String name;
    private final Scanner scanner = new Scanner(System.in);
    @Autowired
    private UserService userService;
    @Autowired
    private PhoneService phoneService;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void action() {

        String str = getInputString();
        if (isPhone(str)) {
            deletePhoneByPhoneNumber(str);
        } else {
            deleteUserByName(str);
        }

    }

    private void deleteUserByName(String userName) {
        Optional<User> userDb = userService.findByName(userName);
        if (userDb.isPresent()) {
            userService.delete(userDb.get());
        } else {
            System.out.println("No phone, no user was founded");
        }
    }

    private void deletePhoneByPhoneNumber(String phoneNumber) {
        phoneService.deletePhoneByPhoneNumber(phoneNumber) ;
    }

    private boolean isPhone(String str) {
        return !phoneService.findByPhoneNumber(str).isEmpty();
    }

    private String getInputString() {
        boolean exit = false;
        String str = null;
        while (!exit) {
            try {
                System.out.println("Enter user phone or user name");
                str = scanner.nextLine();
                if (str.isEmpty()) {
                    throw new EmptyValueException();
                }
                exit = true;
            } catch (EmptyValueException e) {
                System.out.println("You entered an empty value");
            }
        }
        return str;
    }
}
