/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import util.ConexaoSQLite;
import model.ModelUsuario;

/**
 *
 * @author mayco
 */
public class DAOUsuario extends ConexaoSQLite{
    
    public boolean salvarUsuarioDAO(ModelUsuario pModelUsuario) {
        conectar();
        // executar sql
        desconectar();
        return true;
    }
}
