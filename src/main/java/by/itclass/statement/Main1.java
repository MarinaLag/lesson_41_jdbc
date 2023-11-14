package by.itclass.statement;

import by.itclass.model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main1 {
    public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL ="jdbc:mysql://localhost:3306/po_2211";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection cn = DriverManager.getConnection(DB_URL, DB_USER,DB_PASSWORD);
             // ОБЫЧНЫЙ ЗАПРОС
          Statement st = cn.createStatement()) {
            List<Staff> staff =new ArrayList<>();
            // пишем запрос данные через запрос
            String query = "SELECT s.name, s.age, d.name FROM staff s LEFT JOIN department d ON s.department = d.id";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){ // берем первую строку
                String name = rs.getString(1);
                int age = rs.getInt("age"); //для примера
                String department = rs.getString(3); // т.к. имя полей одинаковые то по индексу
                //или в запросе указать имена полей и потом на их новые имена ссылаться
                //создаем объект
                staff.add(new Staff(name,age,department));
            }
            staff.forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
