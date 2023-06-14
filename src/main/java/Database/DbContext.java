package Database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbContext {
//    private String url="jdbc:postgresql://localhost:5432/db";
//    private String name="postgres";
//    private String password="1234";

    private String url=null;
    private String name;
    private String password;
    public Connection connection(){
        Properties properties = new Properties();
        try{
            FileInputStream fis=new FileInputStream("src/main/resources/projects.properties");
            properties.load(fis);

            url=properties.getProperty("url");
            name=properties.getProperty("name");
            password=properties.getProperty("password");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Connection conn=null;
        try{
            conn= DriverManager.getConnection(url,name,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
