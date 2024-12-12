package br.com.exame.controller;

import br.com.exame.model.Client;
import br.com.exame.model.ConnectionBd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {

    public List<Client> listClient() {
        String sql = "SELECT * FROM tbl_client";
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement ps = ConnectionBd.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Percorrendo os resultados da consulta
            while (rs.next()) {
                Client client = new Client();

                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setAddress(rs.getString("address"));
                client.setNumber(rs.getInt("number"));
                client.setEmail(rs.getString("email"));
                client.setPhone(rs.getString("phone"));

                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }


    public boolean insertClient(Client client) throws SQLException {

        String sql = "INSERT INTO tbl_client (name, address, number, email, phone) values (?, ?, ?, ?, ?)";

        PreparedStatement ps = null;
        try {
            ps = ConnectionBd.getConnection().prepareStatement(sql);
            ps.setString(1, client.getName());
            ps.setString(2, client.getAddress());
            ps.setInt(3, client.getNumber());
            ps.setString(4, client.getEmail());
            ps.setString(5, client.getPhone());
            ps.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean removeClient(int id) {
        String sql = "DELETE FROM tbl_client where id = " + id;
        try {
            PreparedStatement ps = ConnectionBd.getConnection().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
