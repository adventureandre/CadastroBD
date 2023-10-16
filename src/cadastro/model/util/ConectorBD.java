package cadastro.model.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConectorBD {
    private static  Connection conn = null;

    public static Connection getConnection(){
        if(conn == null){

            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static Properties loadProperties(){
        try(FileInputStream fs  = new FileInputStream("db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return  props;
        }
        catch (IOException e){
            throw new DbException(e.getMessage());
        }
    }
}


