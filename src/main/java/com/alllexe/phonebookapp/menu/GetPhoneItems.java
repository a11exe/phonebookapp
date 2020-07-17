package com.alllexe.phonebookapp.menu;

import com.alllexe.phonebookapp.model.Phone;
import com.alllexe.phonebookapp.model.User;
import com.alllexe.phonebookapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.StringJoiner;

/**
 * Список телефонов.
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 17.07.2020
 */
@Component
public class GetPhoneItems implements MenuItem {

    @Value("${get.phone.items}")
    private String name;

    @Autowired
    private UserService userService;

    @Override
    public String getName() {
        return name;
    }

    private List<User> getAllUsersPhones() {
        return userService.findAll();
    }

    private void printAllUsersPhones(List<User> users) {
        users.forEach(u -> {
            StringBuilder sb = new StringBuilder();
            sb
                    .append(u.getId())
                    .append(" ")
                    .append(u.getUsername())
                    .append(": ");
            StringJoiner sj = new StringJoiner("; ");
            for (Phone phone: u.getPhones()
                    ) {
                sj.add(phone.getPhoneNumber());

            }
            sb.append(sj.toString());
            System.out.println(sb.toString());
        });
    }

    @Override
    public void action() {
        printAllUsersPhones(getAllUsersPhones());
    }
}
