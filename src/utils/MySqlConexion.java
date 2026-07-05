package utils;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 * @author MendozaAnahi
 */
public class MySqlConexion {
    public static Connection getConexion(){
        Connection cn=null;
        try {
            // 1. Acceder a la clase "Driver" dentro del jar
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. Declarar las variables
            String user="root";
            // Modifica la contraseña si tienes otra en tu MySql
            String pass="mysql";
            String url="jdbc:mysql://localhost:3306/importaciones_suzie?serverTimezone=UTC";
            // 3. Crear una instancia de Connection "cn"
            cn=DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return cn;
    }
}
