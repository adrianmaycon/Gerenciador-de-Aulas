/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModelUsuario;
import util.ConexaoSQLite;

/**
 *
 * @author mayco
 */
public class DAOUsuario extends ConexaoSQLite {

    /**
     * Salvar um novo usuario no banco de dados
     *
     * @param pModelUsuario
     * @return
     */
    public boolean salvarUsuarioDAO(ModelUsuario pModelUsuario) {
        conectar();
        // executar sql
        String sql = "INSERT INTO tbl_usuario("
                + "usu_nome, "
                + "usu_login, "
                + "usu_senha) "
                + "VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            preparedStatement.setString(1, pModelUsuario.getUsuNome());
            preparedStatement.setString(2, pModelUsuario.getUsuLogin());
            preparedStatement.setString(3, pModelUsuario.getUsuSenha());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        desconectar();
        return true;
    }

    /**
     * Retornar lista de objetos do banco
     *
     * @return
     */
    public List<ModelUsuario> getListaUsuarioDAO() {
        List<ModelUsuario> listaUsuario = new ArrayList<>();
        ModelUsuario modelUsuario = new ModelUsuario();
        conectar();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT pk_usu_id, "
                + "usu_nome, "
                + "usu_login, "
                + "usu_senha "
                + " FROM tbl_usuario";

        try {
            preparedStatement = criarPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                modelUsuario = new ModelUsuario();
                modelUsuario.setUsuId(resultSet.getInt(1));
                modelUsuario.setUsuNome(resultSet.getString(2));
                modelUsuario.setUsuLogin(resultSet.getString(3));
                modelUsuario.setUsuSenha(resultSet.getString(4));
                listaUsuario.add(modelUsuario);
            }
        } catch (Exception e) {
            System.err.println("Deu Erro!");
            System.err.println(e);
        }

        desconectar();
        return listaUsuario;
    }

    /**
     * excluir um usuario pelo codigo
     *
     * @param pCodigo
     * @return bollean
     */
    public boolean excluirUsuarioDAO(int pCodigo) {
        conectar();
        PreparedStatement preparedStatement;
        String sql = "DELETE FROM tbl_usuario WHERE pk_usu_id = '" + pCodigo + "'";
        preparedStatement = this.criarPreparedStatement(sql);
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        this.desconectar();
        return true;
    }

    /**
     *
     * @param pCodigoUsuario
     * @return
     */
    public ModelUsuario getUsuarioDAO(int pCodigoUsuario) {
        ModelUsuario modelUsuario = new ModelUsuario();
        conectar();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT pk_usu_id, "
                + "usu_nome, "
                + "usu_login, "
                + "usu_senha "
                + " FROM tbl_usuario WHERE pk_usu_id = '" + pCodigoUsuario + "'";

        preparedStatement = criarPreparedStatement(sql);
        try {
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                modelUsuario = new ModelUsuario();
                modelUsuario.setUsuId(resultSet.getInt("pk_usu_id"));
                modelUsuario.setUsuNome(resultSet.getString("usu_nome"));
                modelUsuario.setUsuLogin(resultSet.getString("usu_login"));
                modelUsuario.setUsuSenha(resultSet.getString("usu_senha"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.err.println(e);
        }
        desconectar();
        return modelUsuario;
    }

    /**
     * Atualizar usuario
     *
     * @param modelUsuario
     * @return
     */
    public boolean atualizarUsuario(ModelUsuario modelUsuario) {
        this.conectar();
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE tbl_usuario SET "
                + "usu_nome =?, "
                + "usu_login=?, "
                + "usu_senha=? "
                + "WHERE pk_usu_id = '" + modelUsuario.getUsuId() + "'";

        preparedStatement = criarPreparedStatement(sql);
        try {
            preparedStatement.setString(1, modelUsuario.getUsuNome());
            preparedStatement.setString(2, modelUsuario.getUsuLogin());
            preparedStatement.setString(3, modelUsuario.getUsuSenha());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        this.desconectar();
        return true;
    }

    /**
     * Valida usuário e senha no banco de dados
     *
     * @param modelUsuario
     * @return
     */
    public boolean validarUsuarioDAO(ModelUsuario modelUsuario) {
        conectar();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT "
                + "pk_usu_id, "
                + "usu_nome, "
                + "usu_login, "
                + "usu_senha "
                + " FROM tbl_usuario "
                + "WHERE pk_usu_id = '" + modelUsuario.getUsuLogin() + "' AND "
                + "usu_senha = '" + modelUsuario.getUsuSenha() + "'";

        preparedStatement = criarPreparedStatement(sql);
        try {
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                desconectar();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
