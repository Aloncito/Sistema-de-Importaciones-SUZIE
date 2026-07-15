package clases;

/**
 * @author MendozaAnahi
 */

public class Producto {
    //Atributos de la tabla
    private int codigo, stock, categoriaID, proveedorID, sedeID;
    private String nombre, descripcion; 
    private double precio;
    private boolean estado;
    
    //Constructores
    //1. Vacío por defecto
    public Producto() {
    }
    
    //2. Parametrizado completo
    public Producto(int codigo, int stock, int categoriaID, int proveedorID, int sedeID, 
                    String nombre, String descripcion, double precio, boolean estado) {
        this.codigo = codigo;
        this.stock = stock;
        this.categoriaID = categoriaID;
        this.proveedorID = proveedorID;
        this.sedeID = sedeID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
    }
    
    //getters y setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCategoriaID() {
        return categoriaID;
    }

    public void setCategoriaID(int categoriaID) {
        this.categoriaID = categoriaID;
    }

    public int getProveedorID() {
        return proveedorID;
    }

    public void setProveedorID(int proveedorID) {
        this.proveedorID = proveedorID;
    }

    public int getSedeID() {
        return sedeID;
    }

    public void setSedeID(int sedeID) {
        this.sedeID = sedeID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
