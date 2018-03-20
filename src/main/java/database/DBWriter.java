package database;

import java.sql.*;
import java.util.Date;

public class DBWriter {

    private Double degrese;
    private int pressure;
    private Date date;
    private static final String URL = "jdbc:mysql://localhost:3306/weatherdb?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String QUERY = "INSERT INTO weather VALUES (?, ?, ?, ?)";

    public DBWriter(Double degrese, int pressure, Date date){
        this.degrese = degrese;
        this.pressure = pressure;
        this.date = date;
        writer();
    }

    public void writer(){
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(QUERY)) {
            System.out.println(connection.isClosed());
            System.out.println(pressure);
            statement.setInt(1, 0);
            statement.setDate(2, new java.sql.Date(date.getTime()));
            statement.setDouble(3, degrese);
            statement.setInt(4, pressure);
            statement.execute();

        } catch (SQLException e){
            System.err.println(e);
        }
    }

}
