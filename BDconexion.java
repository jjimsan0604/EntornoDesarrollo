package BaseDeDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BDconexion {
	
	   public static Connection getConnection() throws SQLException {
	        try {
	            Properties properties = BDconfiguracion.loadDatabaseConfig();
	            String dbUrl = properties.getProperty("db_url");
	            String user = properties.getProperty("user");
	            String password = properties.getProperty("password");
	            
	            return DriverManager.getConnection(dbUrl, user, password);
	        } catch (IOException e) {
	            throw new SQLException("Error al cargar la configuración de la base de datos.", e);
	        }
	    }
	}
