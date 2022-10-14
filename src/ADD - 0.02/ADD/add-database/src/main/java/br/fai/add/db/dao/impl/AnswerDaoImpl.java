package br.fai.add.db.dao.impl;

import br.fai.add.db.connection.ConnectionFactory;
import br.fai.add.db.dao.AnswerDao;
import br.fai.add.model.entities.Answer;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AnswerDaoImpl implements AnswerDao<Answer> {

    @Override
    public List<Answer> find() {

        List<Answer> items = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT * FROM resposta ;";


        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Answer answer = new Answer();
                answer.setId(resultSet.getInt("id"));
                answer.setAnswerText(resultSet.getString("texto_resposta"));

                items.add(answer);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return items;
    }

    @Override
    public Answer findById(int id) {

        Answer item = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

//        final String sql = "SELECT * from resposta where id = ? ;";

        final String sql = "SELECT R.id, R.texto_resposta, A.descricao_da_alternativa FROM resposta R " +
                " INNER JOIN pergunta P ON R.pergunta_id = P.id FULL OUTER JOIN alternativa A on R.alternativa_resposta_id = A.id" +
                " WHERE R.id = ?;";

        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                item = new Answer();

//                if (resultSet.getBoolean("pergunta_fechada") == true) {
//                item.setId(resultSet.getInt("id"));
//                item.setAnswerText(resultSet.getString("descricao_da_alternativa"));
//                } else {
                item.setId(resultSet.getInt("id"));
                item.setAnswerText(resultSet.getString("texto_resposta"));
//                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return item;

    }

    @Override
    public Answer findAnswerByQuestion(int id) {

        Answer item = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

//        final String sql = "SELECT * from resposta where id = ? ;";

        final String sql = "SELECT R.id, R.texto_resposta, A.descricao_da_alternativa FROM resposta R " +
                " INNER JOIN pergunta P ON R.pergunta_id = P.id FULL OUTER JOIN alternativa A on R.alternativa_resposta_id = A.id" +
                " WHERE P.id = ?;";

        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                item = new Answer();

//                if (resultSet.getBoolean("pergunta_fechada") == true) {
//                item.setId(resultSet.getInt("id"));
//                item.setAnswerText(resultSet.getString("descricao_da_alternativa"));
//                } else {
                item.setId(resultSet.getInt("id"));
                item.setAnswerText(resultSet.getString("texto_resposta"));
//                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return item;

    }

    @Override
    public int create(Answer entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int id = -1;

        String sql = "INSERT INTO resposta(texto_resposta, colaborador_id, pergunta_id, " +
                " alternativa_resposta_id) VALUES(?, ?, ?, ?) ; ";

        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getAnswerText());
            preparedStatement.setInt(2, entity.getCollaborator().getId());
            preparedStatement.setInt(3, entity.getQuestion().getId());
            preparedStatement.setInt(4, entity.getOption().getId());

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
        String sql = "DELETE FROM resposta WHERE id = ?; ";

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
