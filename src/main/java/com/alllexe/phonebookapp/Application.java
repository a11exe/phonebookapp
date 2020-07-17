package com.alllexe.phonebookapp;

import com.alllexe.phonebookapp.menu.AddPhoneItem;
import com.alllexe.phonebookapp.menu.DeletePhoneItem;
import com.alllexe.phonebookapp.menu.ExitPhoneItems;
import com.alllexe.phonebookapp.menu.GetPhoneItems;
import com.alllexe.phonebookapp.menu.MainMenu;
import com.alllexe.phonebookapp.menu.MenuItem;
import com.alllexe.phonebookapp.menu.SavePhoneItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Application {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(Application.class);

    private ApplicationContext context;

    public static void main(String[] args) {

        ApplicationContext applicationContext;

        // Load Spring application context
        try {
            applicationContext =
                    new ClassPathXmlApplicationContext("spring/spring-app.xml");

            List<MenuItem> menuItemList = new ArrayList<>();
            menuItemList.add(applicationContext.getBean(AddPhoneItem.class));
            menuItemList.add(applicationContext.getBean(DeletePhoneItem.class));
            menuItemList.add(applicationContext.getBean(GetPhoneItems.class));
            menuItemList.add(applicationContext.getBean(SavePhoneItems.class));
            menuItemList.add(applicationContext.getBean(ExitPhoneItems.class));

            MainMenu mainMenu = applicationContext.getBean(MainMenu.class);
            mainMenu.setMenuItems(menuItemList);
            mainMenu.action();

        } catch (Exception e) {
            LOGGER.error(
                    "Error loading Spring application context: "
                            + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

}
