package br.fai.add.db.dao.impl;

import br.fai.add.db.connection.ConnectionFactory;
import br.fai.add.db.dao.RespondentDao;
import br.fai.add.model.entities.Respondent;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


        final String sql = "SELECT * FROM quem_responde QR INNER JOIN colaborador C ON" +
                " C.id = QR.colaborador_id;";


        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Respondent respondent = new Respondent();
                respondent.setId(resultSet.getInt("id"));
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
    public Respondent findById(int id) {
        return null;
    }

    @Override
    public int create(Respondent entity) {
        return 0;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
