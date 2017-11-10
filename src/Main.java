import java.io.*;
import java.sql.*;

import static java.lang.Thread.sleep;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {

        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:KATA5.db");
        Statement statement = connection.createStatement();

        queryPeople(statement);
        createTable(statement);
        sleep(2000);
        insertMailsToNueva(statement);

    }

    private static void insertMailsToNueva(Statement statement) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("emails.txt")));
        String mail;
        String query;
        while((mail = reader.readLine()) != null){
            if(!mail.contains("@")) continue;
            query = "INSERT INTO MAIL (Mail) VALUES ('" + mail + "')";
            System.out.println(query);
            statement.executeUpdate(query);
        }
    }

    private static void createTable(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS 'MAIL' ('ID' INTEGER PRIMARY " +
                "KEY AUTOINCREMENT," + "'MAIL' TEXT NOT NULL);");

    }

    private static void queryPeople(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM PEOPLE;");

        while(resultSet.next()) {
            System.out.print("ID = " + resultSet.getInt("ID") + "\t");
            System.out.println("NAME = " + resultSet.getString("Name"));
        }

    }

}