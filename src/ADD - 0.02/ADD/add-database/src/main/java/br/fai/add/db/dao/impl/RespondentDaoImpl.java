package br.fai.add.db.dao.impl;

import br.fai.add.db.connection.ConnectionFactory;
import br.fai.add.db.dao.RespondentDao;
import br.fai.add.model.entities.Collaborator;
import br.fai.add.model.entities.Form;
import br.fai.add.model.entities.Respondent;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RespondentDaoImpl implements RespondentDao<Respondent> {


    @Override
    public List<Respondent> find() {
        List<Respondent> items = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = " SELECT * FROM quem_responde QR INNER JOIN colaborador C ON " +
                " C.id = QR.colaborador_id;";


        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Respondent respondent = new Respondent();
                respondent.setId(resultSet.getInt("id"));
                respondent.setAnswered(resultSet.getBoolean("foi_respondido"));
                respondent.setName(resultSet.getString("nome_respondente"));


                items.add(respondent);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return items;
    }

    @Override
    public List<Respondent> findRespondentsByForm(int id) {
        List<Respondent> items = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = " SELECT * FROM quem_responde QR INNER JOIN colaborador C ON " +
                " C.id = QR.colaborador_id INNER JOIN avaliacao A ON QR.avaliacao_id = A.id " +
                " WHERE A.id = ?";


        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Respondent respondent = new Respondent();
                respondent.setId(resultSet.getInt("id"));
                respondent.setAnswered(resultSet.getBoolean("foi_respondido"));
                respondent.setName(resultSet.getString("nome_respondente"));


                items.add(respondent);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return items;
    }

    @Override
    public List<Respondent> findRespondentsByOrg(int id) {
        List<Respondent> items = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = " SELECT A.id as id_a, * FROM quem_responde QR " +
                " INNER JOIN colaborador C ON C.id = QR.colaborador_id " +
                " INNER JOIN avaliacao A ON A.id = QR.avaliacao_id " +
                " WHERE organizacao_id = ? AND foi_respondido = true;";


        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Respondent respondent = new Respondent();
                respondent.setId(resultSet.getInt("id"));
                respondent.setAnswered(resultSet.getBoolean("foi_respondido"));
                respondent.setName(resultSet.getString("nome_respondente"));

                Form form = new Form();
                form.setId(resultSet.getInt("id_a"));
                form.setTitle(resultSet.getString("titulo"));
                respondent.setForm(form);


                items.add(respondent);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return items;
    }

    @Override
    public Respondent findRespondentByCollaboratorAndForm(int id, int id2) {
        Respondent item = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT * FROM quem_responde QR INNER JOIN colaborador C ON QR.colaborador_id = C.id " +
                " INNER JOIN avaliacao A ON QR.avaliacao_id = A.id " +
                " WHERE QR.colaborador_id = ? AND A.id = ?;";

        //o nome usuario não precisar igual no dao e no bd

        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id2);


            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                item = new Respondent();


                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("nome_respondente"));
                item.setAnswered(resultSet.getBoolean("foi_respondido"));

                Form form = new Form();
                form.setId(resultSet.getInt("avalia" +
                        "cao_id"));
                item.setForm(form);

                Collaborator collaborator = new Collaborator();
                collaborator.setId(resultSet.getInt("colaborador_id"));
                item.setCollaborator(collaborator);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return item;
    }

    @Override
    public boolean update(Respondent entity, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE quem_responde QR SET foi_respondido = true WHERE QR.id = ?;";


        try {

            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            preparedStatement.execute();
            connection.commit();
            //grava informações permanentemente no bd qnd o commit acontece
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

    @Override
    public Respondent findById(int id) {

        Respondent item = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT * FROM quem_responde WHERE id = ?;";

        //o nome usuario não precisar igual no dao e no bd

        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                item = new Respondent();


                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("nome_respondente"));
                item.setAnswered(resultSet.getBoolean("foi_respondido"));

                Form form = new Form();
                form.setId(resultSet.getInt("avaliacao_id"));
                item.setForm(form);

                Collaborator collaborator = new Collaborator();
                collaborator.setId(resultSet.getInt("colaborador_id"));
                item.setCollaborator(collaborator);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return item;
    }

    @Override
    public int create(Respondent entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int id = -1;

        String sql = "INSERT INTO quem_responde(foi_respondido, nome_respondente, colaborador_id, " +
                " avaliacao_id) VALUES(?, ?, ?, ?) ; ";

        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setBoolean(1, entity.isAnswered());
            preparedStatement.setString(2, entity.getCollaborator().getFullName());
//            preparedStatement.setString(2, entity.getName();
            // Decidir oq vamos fazer nessa linha, considerando que talvez utilizemos o session
            preparedStatement.setInt(3, entity.getCollaborator().getId());
            preparedStatement.setInt(4, entity.getForm().getId());

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
        String sql = "DELETE FROM quem_responde WHERE id = ?; ";

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
