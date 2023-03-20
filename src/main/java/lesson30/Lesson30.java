package lesson30;

import java.net.Socket;
import java.sql.*;

public class Lesson30 {
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        connect("jdbc:postgresql://localhost:5432/telran", "postgres", "yupostgres" );
        System.out.println("Postgresql server connected.");

        //use statement
        Statement stmt = connection.createStatement();

       // dropTable(stmt);
       // createTable(stmt);
       // System.out.println("Table created.");

        // insertInto(stmt);
        // System.out.println("Record(s) added.");

        select(stmt);
        findById(3);

        disconnect();
        System.out.println("Postgresql server disconnected.");

    }

    static void dropTable(Statement stmt) throws SQLException {
        stmt.executeUpdate("DROP TABLE IF EXISTS students");
    }

    static void insertInto(Statement stmt) throws SQLException {
        stmt.executeUpdate("insert into students (name, group_name) values ('Ivan', '38a'), ('Petr', '38a');");
    }

    static void select(Statement stmt) throws SQLException {
        ResultSet res = stmt.executeQuery("SELECT * FROM students");
        while (res.next()) {
            System.out.printf("id: %d, name: %s, group: %s", res.getInt(1),
                    res.getString(2), res.getString(3));
        }
    }

    static void findById(Integer id) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM students WHERE id = ?");
        ps.setInt(1, id);
        ResultSet res = ps.executeQuery();
        while (res.next()) {
            System.out.println("id: %d name: %s");
        }
    }

    static void connect(String url, String user, String passwd) {
        try {
            connection = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void createTable(Statement stmt) throws SQLException {
        stmt.executeUpdate("create table students (\n" +
                "\tid\t\tSERIAL PRIMARY KEY, \n" +
                "\tname \tTEXT NOT NULL, \n" +
                "\tgroup_name\tTEXT NOT NULL\n" +
                ");");
    }

    static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}