package com.alllexe.phonebookapp.menu;

import com.alllexe.phonebookapp.exception.MenuOutException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

/**
 * Основное меню.
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 17.07.2020
 */
@Component
public class MainMenu implements MenuItem {

    private List<MenuItem> menuItems;
    private boolean exit;
    private final Scanner scanner = new Scanner(System.in);

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    private String getMenuRange() {
        return "from 0 to " + (menuItems.size() - 1);
    }

    @Override
    public String getName() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < menuItems.size(); i++) {
            stringBuilder
                    .append(i)
                    .append(": ")
                    .append(menuItems.get(i).getName())
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    @Override
    public void action() {

        while (!exit) {
            System.out.println("Please select menu item:");
            System.out.println(getName());
            String str = scanner.nextLine();
            try {
                int key = Integer.parseInt(str);
                if ((key < 0) || (key >= menuItems.size())) {
                    throw new MenuOutException();
                }

                menuItems.get(key).action();

            } catch (NumberFormatException e) {
                System.out.println("Please enter number!");
            } catch (MenuOutException e) {
                System.out.println("No such menu item. Please enter number from " + getMenuRange());
            }
        }
    }
}
