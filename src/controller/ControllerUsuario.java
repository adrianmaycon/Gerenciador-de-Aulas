/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.DAOUsuario;
import java.util.List;
import model.ModelUsuario;

/**
 *
 * @author Adrian Maycon
 */
public class ControllerUsuario {
    DAOUsuario dAOUsuario = new DAOUsuario();
    
    /** 
     * Salvar um novo usuario no banco
     * @param modelUsuario 
     * @return boolean
     */

    public boolean salvarUsuarioController(ModelUsuario modelUsuario) {
          return this.dAOUsuario.salvarUsuarioDAO(modelUsuario);
    }

    public List<ModelUsuario> getListaUsuariosController() {
       return this.dAOUsuario.getListaUsuarioDAO();
    }

    public boolean excluirUsuarioController(int pCodigo) {
        return this.dAOUsuario.excluirUsuarioDAO(pCodigo);
    }

    public ModelUsuario getUsuarioController(int pCodigo) {
     return this.dAOUsuario.getUsuarioDAO(pCodigo);
    }

    public boolean atualizarUsuarioController(ModelUsuario modelUsuario) {
        return this.dAOUsuario.atualizarUsuario(modelUsuario);
    }

    public boolean validarUsuarioController(ModelUsuario modelUsuario) {
      return this.dAOUsuario.validarUsuarioDAO(modelUsuario);
    }
}
