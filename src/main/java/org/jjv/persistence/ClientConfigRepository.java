package org.jjv.persistence;

import org.jjv.instances.ConfigInstance;
import org.jjv.models.ClientConfig;
import org.jjv.utils.ConfigModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientConfigRepository implements Repository<ClientConfig> {
    ConfigModel configModel = ConfigInstance.getSingle();

    @Override
    public void save(ClientConfig clientConfig) throws SQLException {
        String sql;
        if (clientConfig.id() != null && clientConfig.id() > 0){
            sql = "UPDATE clients_config SET client=?, organization=?, seller_main=?, seller_sat=?, buyer_main=?, buyer_sat=?, expense_main=?, minimum_amount=?, income_main=? WHERE id=?";
        } else {
            sql = "INSERT INTO clients_config(client, organization, seller_main, seller_sat, buyer_main, buyer_sat, expense_main, minimum_amount, income_main) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        }
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, clientConfig.clientId());
        ps.setInt(2, clientConfig.organization());
        ps.setString(3, clientConfig.sellerMainAccount());
        ps.setString(4, clientConfig.sellerSATIdentifier());
        ps.setString(5, clientConfig.buyerMainAccount());
        ps.setString(6, clientConfig.buyerSATIdentifier());
        ps.setString(7, clientConfig.expenseMainAccount());
        ps.setDouble(8, clientConfig.minimumAmountToApply());
        ps.setString(9, clientConfig.incomeMainAccount());
        if (clientConfig.id() != null && clientConfig.id() > 0){
            ps.setInt(10, clientConfig.id());
        }
        ps.executeUpdate();

        connection.close();
    }

    @Override
    public void saveAll(List<ClientConfig> configList) throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        String SQL = "INSERT INTO clients_config(client, organization, seller_main, seller_sat, buyer_main, buyer_sat, expense_main, minimum_amount, income_main) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(SQL);
        for (ClientConfig clientConfig : configList){
            ps.setInt(1, clientConfig.clientId());
            ps.setInt(2, clientConfig.organization());
            ps.setString(3, clientConfig.sellerMainAccount());
            ps.setString(4, clientConfig.sellerSATIdentifier());
            ps.setString(5, clientConfig.buyerMainAccount());
            ps.setString(6, clientConfig.buyerSATIdentifier());
            ps.setString(7, clientConfig.expenseMainAccount());
            ps.setDouble(8, clientConfig.minimumAmountToApply());
            ps.setString(9, clientConfig.incomeMainAccount());

            ps.addBatch();
        }

        ps.executeBatch();
        connection.commit();
    }

    @Override
    public List<ClientConfig> findAll() throws SQLException {
        Connection connection = getConnection();
        String sql = "SELECT * FROM clients_config";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        return createClientConfigList(resultSet);
    }

    @Override
    public List<ClientConfig> findAllById(Integer id) throws SQLException {
        Connection connection = getConnection();
        String sql = "SELECT * FROM clients_config WHERE organization = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();

        return createClientConfigList(resultSet);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                configModel.getUrl(), configModel.getUsername(), configModel.getPassword()
        );
    }

    private List<ClientConfig> createClientConfigList(ResultSet resultSet) throws SQLException {
        List<ClientConfig> configList = new ArrayList<>();
        while (resultSet.next()){
            configList.add(new ClientConfig(
                    resultSet.getInt("id"),
                    resultSet.getInt("client"),
                    resultSet.getInt("organization"),
                    resultSet.getString("seller_main"),
                    resultSet.getString("seller_sat"),
                    resultSet.getString("buyer_main"),
                    resultSet.getString("buyer_sat"),
                    resultSet.getString("expense_main"),
                    resultSet.getDouble("minimum_amount"),
                    resultSet.getString("income_main")
            ));
        }
        return configList;
    }
}
