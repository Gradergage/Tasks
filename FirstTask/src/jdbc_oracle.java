import oracle.jdbc.OracleConnection;
import java.sql.*;
import java.util.Properties;

public class jdbc_oracle {
    private OracleConnection connection = null;
    private String driver = null;  // jdbc Driver
    private String url = null;  // Connection url
    private Properties properties = null;


    public jdbc_oracle() {
        driver="oracle.jdbc.OracleDriver";
    }

    // Driver registration
    private void RegisterDriverManager() {
        try {
            Class.forName(driver).newInstance();
        } catch (InstantiationException e) {e.printStackTrace();
        } catch (IllegalAccessException e) {e.printStackTrace();
        } catch (ClassNotFoundException e) {e.printStackTrace();}
    }


    public void setURL(String host, String service, int port) {
        this.url = String.format("jdbc:oracle:thin:@%s:%d:%s", host, port, service);
    }


    public Connection getConnection() {
        return (java.sql.Connection) connection;
    }

    // Connection using URL
    public void Connect(String login, String password) {

        RegisterDriverManager(); // Driver registration

        // Setting connection parameters
        properties = new Properties();
        properties.setProperty("password"         , password);
        properties.setProperty("user"             , login   );
        properties.setProperty("useUnicode"       , "true"  );
        properties.setProperty("characterEncoding", "utf8"  );
        try {
            connection = (OracleConnection) DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            connection = null;
        }
    }

    public void Disconnect (Connection connection) {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {}
    };
}
