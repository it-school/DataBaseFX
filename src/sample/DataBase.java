package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;

public class DataBase {
    private Connection conn;
    private PreparedStatement st;
    private Connection con;
    private String url;
    private String DBname;

    public DataBase(String DBname) {
        this.DBname = DBname;
        url = "jdbc:mysql://192.168.1.152:3309/"+ DBname +"?serverTimezone=UTC";
        con=null;
    }

    public boolean Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try {
            // JDBC URL для базы данных на Localhost

            // здесь осуществляется соединение c login и password
            Properties properties = new Properties();
            properties.setProperty("user", "itschool");
            properties.setProperty("password", "");
            properties.setProperty("useSSL", "false");
            properties.setProperty("autoReconnect", "true");
            System.out.println(properties);
            con = getConnection(url, properties);



            //  Формирование запросов к БД
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("use " + this.DBname + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> Search (String word) throws SQLException {
        String query = "SELECT word FROM `words` WHERE word LIKE ?;";
        PreparedStatement st = this.con.prepareStatement(query);
        st.setString(1, word + "%");
        ArrayList<String> result= new ArrayList<>();
        ResultSet rs =  st.executeQuery();
        while(rs.next())
        {
            result.add(rs.getString(1));
        }
        return result;
    }
}


