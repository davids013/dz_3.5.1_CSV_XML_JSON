import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.List;

abstract class Methods {
    public static String listToJson(List<Employee> employees) {
        String json = new String();
        Gson gson = new GsonBuilder().create();
        String[] tempArray = gson.toJson(employees).split(",");
        for (String s : tempArray) {
            json += s + ",\r\n";
        }
        json = json.substring(0, json.length() - 3);
        System.out.println("\t>Список сотрудников переведен в строку JSON");
        return json;
    }

    public static boolean writeString(String s, String filePath) {
        boolean isWrited = false;
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write(s);
            writer.flush();
            isWrited = true;
            System.out.println("\t>Данные записаны в файл " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isWrited;
    }
}