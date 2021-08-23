import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.util.List;

public class CsvParser {
    public static void main() {
        System.out.println("\n\tДомашнее задание к занятию 1.5: Работа с файлами CSV, XML, JSON\n" +
                "\tЗадача 1: CSV - JSON парсер\n");

        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String csvFileName = "data.csv";
        String csvOuputFileName = "outputCSV.dat";
        List<Employee> employees = parseCSV(columnMapping, csvFileName);
//        employees.forEach(System.out::println);
        String json = listToJson(employees);
        writeString(json, csvOuputFileName);
    }

    private static List<Employee> parseCSV(String[] fields, String fileName) {
        List<Employee> employees = null;
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(fields);
            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(reader)
                    .withMappingStrategy(strategy)
                    .build();
            employees = csv.parse();
            System.out.println("\t>Список сотрудников получен из файла " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private static String listToJson(List<Employee> employees) {
        String json = new String();
        Gson gson = new GsonBuilder().create();
        String[] tempArray = gson.toJson(employees).split(",");
        for (String s : tempArray) {
            json += s + ",\r\n";
        }
        System.out.println("\t>Список сотрудников переведен в строку JSON");
        return json;
    }

    private static boolean writeString(String s, String filePath) {
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
