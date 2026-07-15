/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;

public interface ICrud<T, ID> {
    //T-->Clase
    //ID->String,Integer(tipo de dato de la llave primaria)
    int save(T bean);
    int update(T bean);
    int delete(ID id);
    
    T findById(ID id);

    List<T> findAll();
    
    List<T> search(String texto);
}
