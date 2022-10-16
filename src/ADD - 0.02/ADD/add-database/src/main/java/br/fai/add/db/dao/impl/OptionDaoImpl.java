package br.fai.add.db.dao.impl;

import br.fai.add.db.connection.ConnectionFactory;
import br.fai.add.db.dao.OptionDao;
import br.fai.add.model.entities.Option;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OptionDaoImpl implements OptionDao<Option> {

    @Override
    public List<Option> find() {
        List<Option> items = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT * FROM alternativa ;";


        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Option option = new Option();
                option.setId(resultSet.getInt("id"));
                option.setOption_label(resultSet.getString("letra_rotulo"));
                option.setDescription(resultSet.getString("descricao_da_alternativa"));
                option.setCorrectAnswer(resultSet.getBoolean("correta"));


                items.add(option);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return items;
    }

    @Override
    public List<Option> findOptionsByQuestion(int id) {
        List<Option> items = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT * FROM alternativa A INNER JOIN pergunta P ON " +
                " P.id = A.pergunta_id WHERE P.id = ?;";


        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Option option = new Option();
                option.setId(resultSet.getInt("id"));
                option.setOption_label(resultSet.getString("letra_rotulo"));
                option.setDescription(resultSet.getString("descricao_da_alternativa"));
                option.setCorrectAnswer(resultSet.getBoolean("correta"));


                items.add(option);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return items;
    }

    @Override
    public Option findOptionByQuestion(int id, int id2) {
        Option item = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT * FROM alternativa WHERE id = ?;";

        //o nome usuario não precisar igual no dao e no bd

        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                item = new Option();

                item.setId(resultSet.getInt("id"));
                item.setOption_label(resultSet.getString("letra_rotulo"));
                item.setDescription(resultSet.getString("descricao_da_alternativa"));
                item.setCorrectAnswer(resultSet.getBoolean("correta"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return item;
    }

    @Override
    public Option findById(int id) {
        Option item = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT * FROM alternativa WHERE id = ?;";

        //o nome usuario não precisar igual no dao e no bd

        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                item = new Option();

                item.setId(resultSet.getInt("id"));
                item.setOption_label(resultSet.getString("letra_rotulo"));
                item.setDescription(resultSet.getString("descricao_da_alternativa"));
                item.setCorrectAnswer(resultSet.getBoolean("correta"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return item;
    }

    @Override
    public int create(Option entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int id = -1;

        String sql = "INSERT INTO alternativa(descricao_da_alternativa, letra_rotulo, correta, " +
                " pergunta_id) VALUES(?, ?, ?, ?) ; ";

        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getDescription());
            preparedStatement.setString(2, entity.getOption_label());
            preparedStatement.setBoolean(3, entity.isCorrectAnswer());
            preparedStatement.setInt(4, entity.getQuestion().getId());


            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                id = resultSet.getInt(1);

            }
            connection.commit();
            return id;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return id;
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);

        }
    }

    @Override
    public boolean deleteById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM alternativa WHERE id = ?; ";

        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            connection.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            ConnectionFactory.close(preparedStatement, connection);

        }
    }


}