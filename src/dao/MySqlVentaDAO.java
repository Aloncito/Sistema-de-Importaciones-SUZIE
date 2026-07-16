package dao;

import clases.Venta;
import clases.Cliente;
import clases.Usuario;
import clases.MetodoPago;
import interfaces.ICrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.MySqlConexion;

public class MySqlVentaDAO implements ICrud<Venta, Integer> {

    @Override
    public int save(Venta bean) {
        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            cn = MySqlConexion.getConexion();
            String sql = "INSERT INTO Venta(FechaVenta, Total, ClienteID, UsuarioID, MetodoPagoID, Estado) "
                       + "VALUES(?, ?, ?, ?, ?, ?)";
            
            pstm = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            pstm.setTimestamp(1, Timestamp.valueOf(bean.getFechaVenta()));
            pstm.setDouble(2, bean.getTotal());
            pstm.setInt(3, bean.getCliente().getClienteID());
            pstm.setInt(4, bean.getUsuario().getUsuarioID());
            pstm.setInt(5, bean.getMetodoPago().getMetodoPagoID());
            pstm.setString(6, bean.getEstado() != null ? bean.getEstado() : "Pendiente");

            salida = pstm.executeUpdate();
            
            if (salida > 0) {
                rs = pstm.getGeneratedKeys();
                if (rs.next()) {
                    bean.setVentaID(rs.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return salida;
    }

    @Override
    public int update(Venta bean) {
        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;
        
        try {
            cn = MySqlConexion.getConexion();
            String sql = "UPDATE Venta SET Total=?, MetodoPagoID=?, Estado=? WHERE VentaID=?";
            
            pstm = cn.prepareStatement(sql);
            pstm.setDouble(1, bean.getTotal());
            pstm.setInt(2, bean.getMetodoPago().getMetodoPagoID());
            pstm.setString(3, bean.getEstado());
            pstm.setInt(4, bean.getVentaID());
            
            salida = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
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
            String sql = "DELETE FROM Venta WHERE VentaID=?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            salida = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return salida;
    }

    @Override
    public Venta findById(Integer id) {
        Venta bean = null;
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT * FROM Venta WHERE VentaID = ?";
            
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                bean = new Venta();
                bean.setVentaID(rs.getInt("VentaID"));
                bean.setFechaVenta(rs.getTimestamp("FechaVenta").toLocalDateTime());
                bean.setTotal(rs.getDouble("Total"));
                bean.setEstado(rs.getString("Estado"));
                
                // Cliente básico
                Cliente c = new Cliente();
                c.setClienteID(rs.getInt("ClienteID"));
                bean.setCliente(c);
                
                // Usuario básico
                Usuario u = new Usuario();
                u.setUsuarioID(rs.getInt("UsuarioID"));
                bean.setUsuario(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bean;
    }

    @Override
    public List<Venta> findAll() {
        List<Venta> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT * FROM Venta ORDER BY FechaVenta DESC";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                Venta bean = new Venta();
                bean.setVentaID(rs.getInt("VentaID"));
                bean.setFechaVenta(rs.getTimestamp("FechaVenta").toLocalDateTime());
                bean.setTotal(rs.getDouble("Total"));
                bean.setEstado(rs.getString("Estado"));
                lista.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    @Override
    public List<Venta> search(String texto) {
        List<Venta> lista = new ArrayList<>();
        // Por ahora retorna todos, puedes mejorarlo después
        return findAll();
    }
}
