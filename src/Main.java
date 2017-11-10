import java.sql.*;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:KATA5.db");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM PEOPLE;");

        while(resultSet.next()) {
            System.out.print("ID = " + resultSet.getInt("ID") + "\t");
            System.out.println("NAME = " + resultSet.getString("Name"));
        }
    }
}
