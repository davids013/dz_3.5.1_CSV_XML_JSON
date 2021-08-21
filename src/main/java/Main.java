import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("\tДомашнее задание к занятию 1.5: Работа с файлами CSV, XML, JSON\n" +
                "\tЗадача 1: CSV - JSON парсер\n");

        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> employees = parseCSV(columnMapping, fileName);
        employees.forEach(System.out::println);
        String json = listToJson(employees);
        System.out.println(json);

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
        String json = null;
        return json;
    }
}
