package clases;

public class Sede {

    private int sedeID;
    private String nombreSede;
    private String direccion;
    private String telefono;

    public Sede() {
    }

    public Sede(int sedeID, String nombreSede, String direccion, String telefono) {
        this.sedeID = sedeID;
        this.nombreSede = nombreSede;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getSedeID() {
        return sedeID;
    }

    public void setSedeID(int sedeID) {
        this.sedeID = sedeID;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombreSede;
    }

}
