package dao;

import clases.Usuario;
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

public class MySqlUsuarioDAO implements ICrud<Usuario, Integer> {

    // 1. MÉTODO DE INICIO DE SESIÓN 
    public Usuario iniciarSesion(String correo, String contrasena) {
        Usuario usu = null;
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT * FROM Usuario WHERE Correo = ? AND Contrasena = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, correo);
            pstm.setString(2, contrasena);
            
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                usu = new Usuario();
                usu.setUsuarioID(rs.getInt("UsuarioID"));
                usu.setNombre(rs.getString("Nombre"));
                usu.setApellido(rs.getString("Apellido"));
                usu.setCorreo(rs.getString("Correo"));
                usu.setContrasena(rs.getString("Contrasena"));
                usu.setRolID(rs.getInt("RolID"));
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
        return usu;
    }

    // 2. MÉTODOS DEL ICRUD 
    @Override
    public int save(Usuario bean) {
        return 0;
    }

    @Override
    public int update(Usuario bean) {
        return 0;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public Usuario findById(Integer id) {
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        return new ArrayList<>();
    }

    @Override
    public List<Usuario> search(String texto) {
        return new ArrayList<>();
    }
}
