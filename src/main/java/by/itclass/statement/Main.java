package by.itclass.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection cn= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/po_2211","root","");
            // Statement - создает объект, который потом превращает строки в запросы
             Statement st = cn.createStatement()){
            //делаем запрос к БД
            String query = "INSERT INTO staff(name,age,department,project) VALUE ('employee11',27,5,3)";
           // на объекте вызываем метод, который  превращает строку в запрос
            st.execute(query);//возвращает boolean
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
