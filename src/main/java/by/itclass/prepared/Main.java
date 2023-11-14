package by.itclass.prepared;

import by.itclass.model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL ="jdbc:mysql://localhost:3306/po_2211";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        try {
            Class.forName(DRIVER_CLASS); //загружаем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // пишем запрос
        String query ="SELECT s.name, s.age, d.name FROM staff s LEFT JOIN department d ON s.department = d.id " +
                "WHERE s.age>? AND d.name LIKE ?";
        // получаем объекты для работы
        try (Connection cn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
             // ПОДГОТОВЛЕННЫЙ ЗАПРОС - мы сразу передаем объект запроса query
             PreparedStatement ps = cn.prepareStatement(query)){
            List<Staff> staff =new ArrayList<>();
            ps.setInt(1,20); // вместо первого ? = 20
            ps.setString(2,"Dev%"); //  вместо второго ? = Dev%

            ResultSet rs = ps.executeQuery();// без параметров т,к мы его запустили раньше строка 30
            while (rs.next()) { // берем первую строку
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
