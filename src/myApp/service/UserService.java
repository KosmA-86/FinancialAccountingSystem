package myApp.service;

import myApp.domain.User;
import myApp.repository.OtherWorkWithFiles;
import myApp.repository.UserHolderSingleton;
import myApp.repository.UsersWorkWithFiles;

import java.io.IOException;
import java.util.ArrayList;
/**
 * Класс перезентор пользователей: добавление, загрузка списка, ФИО пользователя
 */
public class UserService {

    private UsersWorkWithFiles usersWorkWithFiles = new UsersWorkWithFiles();
    private User user = new User();

    public ArrayList<User> loadAllUsers() throws IOException {

        return usersWorkWithFiles.readAllUsers(user.getPath());
    }

    public String getUserFio() {
        UserHolderSingleton currentUser = UserHolderSingleton.getFindUser();
        user = currentUser.getUser();
        return user.getSurname() + " " + user.getName();
    }

    public void addNewUser(String name, String surname) throws IOException {
        OtherWorkWithFiles otherWorkWithFiles = new OtherWorkWithFiles();
        usersWorkWithFiles.addInFileNewUser(otherWorkWithFiles.findId(user.getPath(), user.getPref()), name, surname);
    }
}
