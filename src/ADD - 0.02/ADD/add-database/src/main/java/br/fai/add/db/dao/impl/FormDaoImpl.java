package br.fai.add.db.dao.impl;

import br.fai.add.db.connection.ConnectionFactory;
import br.fai.add.db.dao.FormDao;
import br.fai.add.model.entities.Form;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FormDaoImpl implements FormDao<Form> {


    @Override
    public List<Form> findAllForms(int id) {
        List<Form> items = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT distinct ON(titulo) A.titulo, A.id as id_a, * FROM avaliacao A INNER JOIN colaborador C " +
                " ON C.id = A.colaborador_id WHERE C.id = ?;";


        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Form form = new Form();
                form.setId(resultSet.getInt("id_a"));
                form.setDatetime(resultSet.getTimestamp("data_criacao"));
                form.setDatelimit(resultSet.getDate("data_limite"));

                form.setTitle(resultSet.getString("titulo"));

                items.add(form);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return items;
    }

    @Override
    public List<Form> findAnsweredForms(int id) {
        List<Form> items = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT distinct ON(titulo) A.titulo, A.id as id_a, * FROM avaliacao A INNER JOIN colaborador C " +
                " ON C.id = A.colaborador_id " +
                " INNER JOIN quem_responde QR ON qr.avaliacao_id = A.id " +
                " WHERE qr.foi_respondido = true AND C.id = ?;";


        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Form form = new Form();
                form.setId(resultSet.getInt("id_a"));
                form.setDatetime(resultSet.getTimestamp("data_criacao"));
                form.setDatelimit(resultSet.getDate("data_limite"));

                form.setTitle(resultSet.getString("titulo"));

                items.add(form);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return items;
    }

    @Override
    public List<Form> findUnansweredForms(int id) {
        List<Form> items = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT distinct ON(titulo) A.titulo, A.id as id_a, * FROM avaliacao A INNER JOIN colaborador C " +
                " ON C.id = A.colaborador_id " +
                " INNER JOIN quem_responde QR ON qr.avaliacao_id = A.id " +
                " WHERE qr.foi_respondido = false AND QR.colaborador_id = ?;";


        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Form form = new Form();
                form.setId(resultSet.getInt("id_a"));
                form.setDatetime(resultSet.getTimestamp("data_criacao"));
                form.setDatelimit(resultSet.getDate("data_limite"));

                form.setTitle(resultSet.getString("titulo"));

                items.add(form);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return items;
    }


    @Override
    public Form findById(int id) {
        Form item = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        final String sql = "SELECT * from avaliacao where id = ? ;";

        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                item = new Form();

                item.setId(resultSet.getInt("id"));
                item.setDatetime(resultSet.getTimestamp("data_criacao"));
                item.setDatelimit(resultSet.getDate("data_limite"));
                item.setTitle(resultSet.getString("titulo"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return item;
    }

    @Override
    public int create(Form entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int id = -1;

        String sql = "INSERT INTO avaliacao(data_criacao, data_limite, titulo, " +
                " colaborador_id) VALUES(?, ?, ?, ?) ; ";

        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setDate(2, (Date) entity.getDatelimit());
            preparedStatement.setString(3, entity.getTitle());
            preparedStatement.setInt(4, entity.getCollaborator().getId());

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
        String sql = "DELETE FROM avaliacao WHERE id = ?; ";

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
