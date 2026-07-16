package dao;

import clases.Sede;
import interfaces.ICrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.MySqlConexion;

public class MySqlSedeDAO implements ICrud<Sede, Integer> {

    @Override
    public int save(Sede bean) {
        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;

        try {
            cn = MySqlConexion.getConexion();
            String sql = "INSERT INTO Sede(NombreSede,Direccion,Telefono) VALUES(?,?,?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, bean.getNombreSede());
            pstm.setString(2, bean.getDireccion());
            pstm.setString(3, bean.getTelefono());
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
    public int update(Sede bean) {
        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;

        try {
            cn = MySqlConexion.getConexion();
            String sql = "UPDATE Sede SET NombreSede=?, Direccion=?, Telefono=? WHERE SedeID=?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, bean.getNombreSede());
            pstm.setString(2, bean.getDireccion());
            pstm.setString(3, bean.getTelefono());
            pstm.setInt(4, bean.getSedeID());
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
            String sql = "DELETE FROM Sede WHERE SedeID=?";
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
    public Sede findById(Integer id) {
        Sede bean = null;
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT * FROM Sede WHERE SedeID=?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            if (rs.next()) {
                bean = new Sede();
                bean.setSedeID(rs.getInt("SedeID"));
                bean.setNombreSede(rs.getString("NombreSede"));
                bean.setDireccion(rs.getString("Direccion"));
                bean.setTelefono(rs.getString("Telefono"));
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
    public List<Sede> findAll() {
        List<Sede> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT * FROM Sede";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Sede bean = new Sede();
                bean.setSedeID(rs.getInt("SedeID"));
                bean.setNombreSede(rs.getString("NombreSede"));
                bean.setDireccion(rs.getString("Direccion"));
                bean.setTelefono(rs.getString("Telefono"));
                lista.add(bean);
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
        return lista;

    }

    @Override
    public List<Sede> search(String texto) {
        List<Sede> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT * FROM Sede WHERE NombreSede LIKE ?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, "%" + texto + "%");
            rs = pstm.executeQuery();
          
            while (rs.next()) {
                Sede bean = new Sede();
                bean.setSedeID(rs.getInt("SedeID"));
                bean.setNombreSede(rs.getString("NombreSede"));
                bean.setDireccion(rs.getString("Direccion"));
                bean.setTelefono(rs.getString("Telefono"));
                lista.add(bean);
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
        return lista;
    }
}
