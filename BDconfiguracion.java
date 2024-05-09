package BaseDeDatos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BDconfiguracion {

	    private static String archivo = "C:\\basura\\configuracion_bd.txt";
	    
	    public static Properties loadDatabaseConfig() throws IOException {
	        Properties properties = new Properties();
	        
	        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
	            properties.load(reader);
	        }
	        
	        return properties;
	    }
	}
