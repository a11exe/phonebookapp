package com.alllexe.phonebookapp.menu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void action() {

    }
}
