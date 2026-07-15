package dao;

import clases.Categoria;
import interfaces.ICrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.MySqlConexion;

public class MySqlCategoriaDAO implements ICrud<Categoria, Integer> {
    @Override
    public int save(Categoria bean) {

        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;

        try {
            cn = MySqlConexion.getConexion();

            String sql = "INSERT INTO Categoria (NombreCategoria, Descripcion) VALUES (?,?)";

            pstm = cn.prepareStatement(sql);

            pstm.setString(1, bean.getNombreCategoria());
            pstm.setString(2, bean.getDescripcion());

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
    public int update(Categoria bean) {

        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;

        try {
            cn = MySqlConexion.getConexion();

            String sql = "UPDATE Categoria SET NombreCategoria=?, Descripcion=? WHERE CategoriaID=?";

            pstm = cn.prepareStatement(sql);

            pstm.setString(1, bean.getNombreCategoria());
            pstm.setString(2, bean.getDescripcion());
            pstm.setInt(3, bean.getCategoriaID());

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

            String sql = "DELETE FROM Categoria WHERE CategoriaID=?";

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
    public Categoria findById(Integer id) {
        Categoria bean = null;

        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            cn = MySqlConexion.getConexion();
            String sql = "SELECT * FROM Categoria WHERE CategoriaID=?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                
                bean = new Categoria();

                bean.setCategoriaID(rs.getInt("CategoriaID"));
                bean.setNombreCategoria(rs.getString("NombreCategoria"));
                bean.setDescripcion(rs.getString("Descripcion"));

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
    public List<Categoria> findAll() {

        List<Categoria> lista = new ArrayList<>();

        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT * FROM Categoria";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {

                Categoria bean = new Categoria();

                bean.setCategoriaID(rs.getInt("CategoriaID"));
                bean.setNombreCategoria(rs.getString("NombreCategoria"));
                bean.setDescripcion(rs.getString("Descripcion"));

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
    public List<Categoria> search(String texto) {

        List<Categoria> lista = new ArrayList<>();

        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            cn = MySqlConexion.getConexion();

            String sql = "SELECT * FROM Categoria WHERE NombreCategoria LIKE ?";

            pstm = cn.prepareStatement(sql);

            pstm.setString(1, "%" + texto + "%");

            rs = pstm.executeQuery();

            while (rs.next()) {

                Categoria bean = new Categoria();

                bean.setCategoriaID(rs.getInt("CategoriaID"));
                bean.setNombreCategoria(rs.getString("NombreCategoria"));
                bean.setDescripcion(rs.getString("Descripcion"));

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
