package org.jjv.persistence;

import org.jjv.instances.ConfigInstance;
import org.jjv.models.Operator;
import org.jjv.utils.ConfigModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperatorRepository implements Repository<Operator> {
    ConfigModel configModel = ConfigInstance.getSingle();
    private final String INSERT_ST = "INSERT INTO operators(name, rfc, client) VALUES (?, ?, ?)";
    @Override
    public void save(Operator operator) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(INSERT_ST);
        ps.setString(1, operator.name());
        ps.setString(2, operator.rfc());
        ps.setInt(3, operator.client());
        ps.executeUpdate();
        connection.close();
    }

    @Override
    public void saveAll(List<Operator> operators) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(INSERT_ST);
        for (Operator operator : operators){
            ps.setString(1, operator.name());
            ps.setString(2, operator.rfc());
            ps.setInt(3, operator.client());
            ps.addBatch();
        }

        ps.executeBatch();
        ps.close();
    }

    @Override
    public List<Operator> findAll() throws SQLException {
        Connection connection = getConnection();
        String sql = "SELECT * FROM operators";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        return createOperatorList(resultSet);
    }

    @Override
    public List<Operator> findAllById(Integer id) throws SQLException {
        Connection connection = getConnection();
        String sql = "SELECT * FROM operators WHERE client = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();

        return createOperatorList(resultSet);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                configModel.getUrl(), configModel.getUsername(), configModel.getPassword()
        );
    }

    private List<Operator> createOperatorList(ResultSet resultSet) throws SQLException {
        List<Operator> operators = new ArrayList<>();
        while (resultSet.next()){
            operators.add(new Operator(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("rfc"),
                    resultSet.getInt("client")
            ));
        }

        return operators;
    }
}
