import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

abstract class Methods {
    public static String listToJson(List<Employee> employees) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        Type listType = new TypeToken<List<Employee>>() {}.getType();
//        String json = gson.toJson(employees, listType);
        String json = gson.toJson(employees);
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