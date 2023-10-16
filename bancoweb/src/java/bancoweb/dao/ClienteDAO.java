package bancoweb.dao;

import bancoweb.model.Cliente;
import bancoweb.utils.DBConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lopes
 */
public class ClienteDAO {

    Connection conexao;

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        conexao = DBConnection.getConnection();

        String sql = "SELECT * FROM clientes";
        PreparedStatement stmt;

        try {

            stmt = this.conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("sexo").charAt(0), rs.getString("email"), rs.getString("civil"), rs.getString("regiao"));
                cliente.setId(rs.getInt("codigo"));

                clientes.add(cliente);
            }
            rs.close();
            stmt.close();
            conexao.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clientes;
    }

    public int inserirCliente(Cliente cliente) {

        int status = 0;
        String sql = "INSERT INTO clientes (nome, sexo, email, civil, regiao) VALUES (?, ?, ?, ?, ?)";
        conexao = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, String.valueOf(cliente.getSexo()));
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getCivil());
            stmt.setString(5, cliente.getRegiao());

            status = stmt.executeUpdate();

            stmt.close();
            conexao.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public int alterarCliente(Cliente cliente) {
        int status = 0;
        String sql = "UPDATE clientes SET nome = ?, sexo = ?, email = ?, civil = ?, regiao = ? WHERE codigo = ?";
        conexao = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, String.valueOf(cliente.getSexo()));
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getCivil());
            stmt.setString(5, cliente.getRegiao());
             stmt.setInt(6, cliente.getId());

            status = stmt.executeUpdate();

            stmt.close();
            conexao.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public int excluirCliente(int id) {
        int status = 0;
        String sql = "DELETE FROM clientes WHERE codigo = ?";

        conexao = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            status = stmt.executeUpdate();

            stmt.close();
            conexao.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public Cliente buscarCliente(int id) {
        Cliente cliente = null;
        conexao = DBConnection.getConnection();

        String sql = "SELECT * FROM clientes WHERE codigo = ?";
        PreparedStatement stmt;
        try {

            stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cliente = new Cliente(rs.getString("nome"), rs.getString("sexo").charAt(0), rs.getString("email"), rs.getString("civil"), rs.getString("regiao"));
                cliente.setId(rs.getInt("codigo"));

            }
            rs.close();
            stmt.close();
            conexao.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cliente;
    }
}
