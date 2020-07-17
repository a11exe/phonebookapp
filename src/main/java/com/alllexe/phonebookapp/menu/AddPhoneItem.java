package com.alllexe.phonebookapp.menu;

import com.alllexe.phonebookapp.exception.EmptyValueException;
import com.alllexe.phonebookapp.exception.PhoneValueException;
import com.alllexe.phonebookapp.model.Phone;
import com.alllexe.phonebookapp.model.User;
import com.alllexe.phonebookapp.service.PhoneService;
import com.alllexe.phonebookapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

/**
 * Добавление телефона.
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 17.07.2020
 */
@Component
public class AddPhoneItem implements MenuItem {

    @Value("${add.phone.item}")
    private String name;
    private final Scanner scanner = new Scanner(System.in);
    @Autowired
    private UserService userService;
    @Autowired
    private PhoneService phoneService;

    private String readUserPhone() {
        boolean exit = false;
        String userPhone = null;
        while (!exit) {
            try {
                System.out.println("Enter user phone");
                userPhone = scanner.nextLine();
                if (userPhone.isEmpty()) {
                    throw new EmptyValueException();
                }
                if (!userPhone.matches("[0-9]+") || userPhone.length() <= 2) {
                    throw new PhoneValueException();
                }
                exit = true;
            } catch (EmptyValueException e) {
                System.out.println("You entered an empty value");
            } catch (PhoneValueException e) {
                System.out.println("Phone format only digits min 3");
            }
        }
        return userPhone;
    }

    private String readUserName() {
        boolean exit = false;
        String userName = null;
        while (!exit) {
            try {
                System.out.println("Enter user name");
                userName = scanner.nextLine();
                if (userName.isEmpty()) {
                    throw new EmptyValueException();
                }
                exit = true;
            } catch (EmptyValueException e) {
                System.out.println("You entered an empty value");
            }
        }
        return userName;
    }

    private User saveOrFindUser(String userName) {

        User user;
        Optional<User> userDb = userService.findByName(userName);
        if (!userDb.isPresent()) {
            user = new User();
            user.setUsername(userName);
            user = userService.save(user);
            System.out.println("New user was added");
        } else {
            user = userDb.get();
            System.out.println("User was finded");
        }

        return user;

    }

    private void savePhone(User user, String userPhone) {
        Phone phone;
        Optional<Phone> phoneDb = phoneService.findByPhoneNumberAndUser(userPhone, user);
        if (!phoneDb.isPresent()) {
            phone = new Phone();
            phone.setUser(user);
            phone.setPhoneNumber(userPhone);
            phoneService.save(phone);
            System.out.println("Phone was added");
        } else {
            System.out.println("This phone already exist");
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void action() {
        String userName = readUserName();
        String userPhone = readUserPhone();
        User user = saveOrFindUser(userName);
        savePhone(user, userPhone);
    }


}
