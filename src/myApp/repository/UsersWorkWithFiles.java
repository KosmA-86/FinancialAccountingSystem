package myApp.repository;

import myApp.domain.User;

import java.io.*;
import java.util.ArrayList;
/**
* Класс работы с файлами по пользователям
 */
public class UsersWorkWithFiles {

    public ArrayList<User> readAllUsers(String path) throws IOException {

        File f = new File(path);
        String str;
        ArrayList<User> allRecords = new ArrayList<>();
        if (f.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((str = br.readLine()) != null) {
                String id = str.substring((str.indexOf("id:") + 3), str.indexOf(";"));
                String name = str.substring((str.indexOf("name:") + 5), str.indexOf(";", (str.indexOf("name:") + 5)));
                String surname = str.substring((str.indexOf("surname:") + 8), str.indexOf(";", (str.indexOf("surname:") + 8)));
                User user = new User();
                user.setIdUser(id);
                user.setName(name);
                user.setSurname(surname);
                allRecords.add(user);
            }
        }

        return allRecords;
    }

    public void addInFileNewUser(String idUser, String name, String surname) throws IOException {

        String userData = "id:" + idUser + ";name:" + name + ";surname:" + surname + "; ";

        FileWriter fw = new FileWriter(new User().getPath(), true);
        fw.write(userData);
        fw.append("\n");
        fw.close();
    }

    public void delUserFile(String idUser) throws IOException {

        FileWriter fwU = new FileWriter(new User().getPath(), true);


    }


}
