package br.fai.add.db.dao.impl;

import br.fai.add.db.connection.ConnectionFactory;
import br.fai.add.db.dao.QuestionDao;
import br.fai.add.model.entities.Question;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao<Question> {

    @Override
    public List<Question> find() {
        List<Question> items = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT * FROM pergunta ;";


        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Question question = new Question();
                question.setId(resultSet.getInt("id"));
                question.setDescription(resultSet.getString("descricao_pergunta"));
                question.setAlternativesQuestion(resultSet.getBoolean("pergunta_fechada"));


                items.add(question);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return items;
    }

    @Override
    public List<Question> findQuestionsByForm(int id) {
        List<Question> items = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT P.id as id_p, * FROM pergunta P INNER JOIN avaliacao A ON A.id = P.avaliacao_id " +
                " WHERE A.id = ?;";


        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Question question = new Question();
                question.setId(resultSet.getInt("id_p"));
                question.setDescription(resultSet.getString("descricao_pergunta"));
                question.setAlternativesQuestion(resultSet.getBoolean("pergunta_fechada"));


                items.add(question);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return items;
    }

    @Override
    public Question findById(int id) {
        Question item = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        final String sql = "SELECT * from pergunta where id = ? ;";

        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                item = new Question();

                item.setId(resultSet.getInt("id"));
                item.setDescription(resultSet.getString("descricao_pergunta"));
                item.setAlternativesQuestion(resultSet.getBoolean("pergunta_fechada"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return item;
    }

    @Override
    public int create(Question entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int id = -1;

        String sql = "INSERT INTO pergunta(avaliacao_id, descricao_pergunta, " +
                " pergunta_fechada) VALUES(?, ?, ?) ; ";

        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, entity.getForm().getId());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setBoolean(3, entity.isAlternativesQuestion());

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
        String sql = "DELETE FROM pergunta WHERE id = ?; ";

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
