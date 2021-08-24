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
        String csvOuputFileName = "outputCSV.json";
        List<Employee> employees = parseCSV(columnMapping, csvFileName);
//        employees.forEach(System.out::println);
        String json = Methods.listToJson(employees);
        Methods.writeString(json, csvOuputFileName);
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
}
