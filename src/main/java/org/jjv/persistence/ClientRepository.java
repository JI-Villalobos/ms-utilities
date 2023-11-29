package org.jjv.persistence;

import org.jjv.instances.ConfigInstance;
import org.jjv.models.Client;
import org.jjv.utils.ConfigModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements Repository<Client>{
    ConfigModel configModel = ConfigInstance.getSingle();
    private final String INSERT_ST = "INSERT INTO clients(name, organization) VALUES (?, ?)";
    @Override
    public void save(Client client) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(INSERT_ST);
        ps.setString(1, client.name());
        ps.setInt(2, client.organization());
        ps.executeUpdate();
        connection.close();
    }

    @Override
    public void saveAll(List<Client> clients) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(INSERT_ST);
        for (Client client : clients){
            ps.setString(1, client.name());
            ps.setInt(2, client.organization());
            ps.addBatch();
        }

        ps.executeBatch();
        connection.close();
    }
    @Override
    public List<Client> findAll() throws SQLException{
        Connection connection = getConnection();
        String sql = "SELECT * FROM clients";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        return createClientList(resultSet);

    }

    @Override
    public List<Client> findAllById(Integer id) throws SQLException {
        Connection connection = getConnection();
        String sql = "SELECT * FROM clients WHERE client = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();

        return createClientList(resultSet);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                configModel.getUrl(), configModel.getUsername(), configModel.getPassword()
        );
    }
    
    private List<Client> createClientList(ResultSet resultSet) throws SQLException{
        List<Client> clients = new ArrayList<>();
        while (resultSet.next()){
            clients.add(new Client(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("organization")
            ));
        }
        
        return clients;
    }
}
