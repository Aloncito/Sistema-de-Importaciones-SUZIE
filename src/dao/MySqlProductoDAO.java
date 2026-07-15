package dao;

import clases.Producto;
import interfaces.ICrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.MySqlConexion;

/**
 * @author MendozaAnahi
 */

public class MySqlProductoDAO implements ICrud<Producto, Integer> {

    @Override
    public int save(Producto bean) {
        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;
        try {
            cn = MySqlConexion.getConexion();
            // ProductoID es AUTO_INCREMENT, no se envía
            String sql = "INSERT INTO Producto (NombreProducto, Descripcion, Precio, Stock, Estado, "
                        + "CategoriaID, ProveedorID, SedeID) VALUES (?,?,?,?,?,?,?,?)";
            pstm = cn.prepareStatement(sql);
            
            pstm.setString(1, bean.getNombre());
            pstm.setString(2, bean.getDescripcion());
            pstm.setDouble(3, bean.getPrecio());
            pstm.setInt(4, bean.getStock());
            pstm.setBoolean(5, bean.isEstado()); 
            pstm.setInt(6, bean.getCategoriaID());
            pstm.setInt(7, bean.getProveedorID());
            pstm.setInt(8, bean.getSedeID());
            
            salida = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) cn.close();
                if (pstm != null) pstm.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return salida;
    }

    @Override
    public int update(Producto bean) {
        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "UPDATE Producto SET NombreProducto=?, Descripcion=?, Precio=?, Stock=?, "
                        + "Estado=?, CategoriaID=?, ProveedorID=?, SedeID=? WHERE ProductoID=?";
            pstm = cn.prepareStatement(sql);
            
            pstm.setString(1, bean.getNombre());
            pstm.setString(2, bean.getDescripcion());
            pstm.setDouble(3, bean.getPrecio());
            pstm.setInt(4, bean.getStock());
            pstm.setBoolean(5, bean.isEstado());
            pstm.setInt(6, bean.getCategoriaID());
            pstm.setInt(7, bean.getProveedorID());
            pstm.setInt(8, bean.getSedeID());
            pstm.setInt(9, bean.getCodigo()); // llave primaria al final
            
            salida = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) cn.close();
                if (pstm != null) pstm.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return salida;
    }

    @Override
    public int delete(Integer id) {
        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;
        try {
            cn = MySqlConexion.getConexion();
            // Borrado Lógico: Estado=0 (Inactivo)
            String sql = "UPDATE Producto SET Estado=0 WHERE ProductoID=?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            
            salida = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) cn.close();
                if (pstm != null) pstm.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return salida;
    }

    @Override
    public Producto findById(Integer id) {
        Producto bean = null;
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT * FROM Producto WHERE ProductoID=?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                bean = new Producto();
                bean.setCodigo(rs.getInt(1));
                bean.setNombre(rs.getString(2));
                bean.setDescripcion(rs.getString(3));
                bean.setPrecio(rs.getDouble(4));
                bean.setStock(rs.getInt(5));
                bean.setEstado(rs.getBoolean(6));
                bean.setCategoriaID(rs.getInt(7));
                bean.setProveedorID(rs.getInt(8));
                bean.setSedeID(rs.getInt(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) cn.close();
                if (pstm != null) pstm.close();
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bean;
    }

    @Override
    public List<Producto> findAll() {
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Producto> lista = new ArrayList<Producto>();
        try {
            cn = MySqlConexion.getConexion();
            // Solo listamos los productos activos
            String sql = "SELECT * FROM Producto WHERE Estado=1";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                Producto bean = new Producto();
                bean.setCodigo(rs.getInt(1));
                bean.setNombre(rs.getString(2));
                bean.setDescripcion(rs.getString(3));
                bean.setPrecio(rs.getDouble(4));
                bean.setStock(rs.getInt(5));
                bean.setEstado(rs.getBoolean(6));
                bean.setCategoriaID(rs.getInt(7));
                bean.setProveedorID(rs.getInt(8));
                bean.setSedeID(rs.getInt(9));
                
                lista.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) cn.close();
                if (pstm != null) pstm.close();
                if (rs != null) rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return lista;
    }

    @Override
    public List<Producto> search(String texto) {
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Producto> lista = new ArrayList<Producto>();
        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT * FROM Producto WHERE NombreProducto LIKE ? AND Estado=1";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, "%" + texto + "%"); 
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                Producto bean = new Producto();
                bean.setCodigo(rs.getInt(1));
                bean.setNombre(rs.getString(2));
                bean.setDescripcion(rs.getString(3));
                bean.setPrecio(rs.getDouble(4));
                bean.setStock(rs.getInt(5));
                bean.setEstado(rs.getBoolean(6));
                bean.setCategoriaID(rs.getInt(7));
                bean.setProveedorID(rs.getInt(8));
                bean.setSedeID(rs.getInt(9));
                
                lista.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) cn.close();
                if (pstm != null) pstm.close();
                if (rs != null) rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return lista;
    }
}
