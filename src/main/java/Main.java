import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.util.List;

public class Main {
    private static final String SEP = File.separator;
    public static void main(String[] args) {
        System.out.println("\tДомашнее задание к занятию 1.5: Работа с файлами CSV, XML, JSON\n" +
                "\tЗадача 1: CSV - JSON парсер\n");

        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "src" + SEP + "main" + SEP + "resources" + SEP + "data.csv";
        String ouputFileName = "output.dat";
        List<Employee> employees = parseCSV(columnMapping, fileName);
        employees.forEach(System.out::println);
        String json = listToJson(employees);
        System.out.println(json);
        writeString(json, ouputFileName);
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
        return json;
    }

    private static boolean writeString(String s, String filePath) {
        boolean isWrited = false;
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write(s);
            writer.flush();
            isWrited = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isWrited;
    }
}
