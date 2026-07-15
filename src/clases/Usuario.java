package clases;

/**
 * @author MendozaAnahi
 */

public class Usuario {
    //Atributos de la tabla
    private int usuarioID, rolID;
    private String nombre, apellido, correo, contrasena;
    
    //Constructores
    //1. Vacío por defecto
    public Usuario() {
    }

    //2. Parametrizado completo
    public Usuario(int usuarioID, int rolID, String nombre, String apellido, String correo, String contrasena) {
        this.usuarioID = usuarioID;
        this.rolID = rolID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    //getters y setters
    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public int getRolID() {
        return rolID;
    }

    public void setRolID(int rolID) {
        this.rolID = rolID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}