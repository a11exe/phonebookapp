/*
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 17.07.2020
 */
package com.alllexe.phonebookapp.menu;

import com.alllexe.phonebookapp.Application;
import com.alllexe.phonebookapp.model.Users;
import com.alllexe.phonebookapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Сохранить телефоны в XML.
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 17.07.2020
 */
@Component
public class SavePhoneItems implements MenuItem {

    @Value("${save.phone.items}")
    private String name;
    @Autowired
    private UserService userService;
    private final Logger logger =
            LoggerFactory.getLogger(Application.class);

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void action() {
        try {
            Users users = new Users();
            users.setUsers(userService.findAll());
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(users, new File("userPhones.xml"));
            System.out.println("Saved to: userPhones.xml");
        } catch (JAXBException e) {
            System.out.println("Can't save to XML");
            logger.error(e.getMessage());
        }

    }
}
