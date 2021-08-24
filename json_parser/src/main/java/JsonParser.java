import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public static void main() {
        System.out.println("________________________________________________\n" +
                "\t\tДомашнее задание к занятию 1.5: Работа с файлами CSV, XML, JSON\n" +
                "\t\tЗадача 3: JSON парсер");
        String inputFileName = "outputXML.json";
        if (new File(inputFileName).isFile()) {
            String json = readString(inputFileName);
            List<Employee> list = jsonToList(json);
            for (Employee emp : list) {
                System.out.println("\t" + emp);
            }
        } else System.out.println("\tФайл " + inputFileName + " не найден");
    }


    private static String readString(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                sb.append(temp.replace(" ", ""));
            }
            System.out.println("\tФайл " + fileName + " прочитан");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static List<Employee> jsonToList(String json) {
        List<Employee> employees = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            JSONArray array = (JSONArray) parser.parse(json);
            for (Object obj : array) {
                JSONObject jsonObj = (JSONObject) obj;
                Employee emp = gson.fromJson(String.valueOf(jsonObj), Employee.class);
                employees.add(emp);
            }
            if (employees.size() > 0)
                System.out.println("\tНайдено работников: " + employees.size());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
