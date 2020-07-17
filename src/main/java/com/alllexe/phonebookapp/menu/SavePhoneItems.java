/*
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 17.07.2020
 */
package com.alllexe.phonebookapp.menu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Добавить телефон.
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 17.07.2020
 */
@Component
public class SavePhoneItems implements MenuItem {

    @Value("${save.phone.items}")
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void action() {

    }
}
