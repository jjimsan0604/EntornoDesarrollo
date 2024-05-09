package BaseDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MetodosCrud5 {
	
	public  void crearPeliculas(String titulo, String descripcion, int ano, int duracion, int lenguaje) {
		
	       try (Connection con = BDconexion.getConnection()) {
	            System.out.println("Conexión exitosa a la base de datos");
	            con.setAutoCommit(false); // Desactivar el modo autocommit para manejar transacciones

	            // Preparar la inserción en la tabla film
	            String sql = "INSERT INTO film (title, description, release_year, rental_duration, language_id) VALUES (?, ?, ?, ?, ?)";
	            PreparedStatement crearPelicula = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	            crearPelicula.setString(1, titulo);
	            crearPelicula.setString(2, descripcion);
	            crearPelicula.setInt(3, ano);
	            crearPelicula.setInt(4, duracion);
	            crearPelicula.setInt(5, lenguaje);

	            // Ejecutar la inserción en la tabla film
	            int filasAfectadasPeliculas = crearPelicula.executeUpdate();

	            // Obtener el ID generado para la película insertada en film
	            ResultSet pelicula = crearPelicula.getGeneratedKeys();
	            int filmId = 0;
	            if (pelicula.next()) {
	                filmId = pelicula.getInt(1);
	            }

	            // Preparar la inserción en la tabla film_text
	            String insertFilmText = "INSERT INTO film_text (film_id, title, description) VALUES (?, ?, ?)";
	            PreparedStatement filmText = con.prepareStatement(insertFilmText);
	            filmText.setInt(1, filmId);
	            filmText.setString(2, titulo);
	            filmText.setString(3, descripcion);

	            // Ejecutar la inserción en la tabla film_text
	            int filasAfectadasFilmText = filmText.executeUpdate();

	            // Comprobar si ambas inserciones fueron exitosas para confirmar la transacción
	            if (filasAfectadasPeliculas > 0 && filasAfectadasFilmText > 0) {
	                // Confirmar la transacción si todo fue exitoso
	                con.commit();
	                System.out.println("Película agregada correctamente.");
	            } else {
	                // Si algo falla, hacer un rollback para deshacer los cambios
	                con.rollback();
	                System.out.println("Error al agregar la película. Se realizó un rollback.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
