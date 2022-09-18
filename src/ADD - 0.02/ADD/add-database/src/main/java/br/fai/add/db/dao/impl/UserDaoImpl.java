package br.fai.add.db.dao.impl;


import br.fai.add.db.connection.ConnectionFactory;
import br.fai.add.db.dao.UserDao;
import br.fai.add.model.entities.Collaborator;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao<Collaborator> {

    @Override
    public List<Collaborator> find() {

        List<Collaborator> items = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT * FROM colaborador";

        //o nome usuario não precisar igual no dao e no bd

        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Collaborator collaborator = new Collaborator();
                collaborator.setId(resultSet.getInt("id"));
                //user.setType(UserType.valueOf(resultSet.getString("nome_usuario")));
                collaborator.setFullName(resultSet.getString("nome"));
                collaborator.setEmail(resultSet.getString("email"));
                collaborator.setJobTitle(resultSet.getString("cargo"));
                collaborator.setCpf(resultSet.getString("cpf"));


                items.add(collaborator);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return items;

    }

    @Override
    public Collaborator findById(int id) {

        Collaborator item = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT * FROM colaborador WHERE id = ?;";

        //o nome usuario não precisar igual no dao e no bd

        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                item = new Collaborator();

                item.setId(resultSet.getInt("id"));
                //user.setType(UserType.valueOf(resultSet.getString("nome_usuario")));
                item.setFullName(resultSet.getString("nome"));
                item.setEmail(resultSet.getString("email"));
                item.setJobTitle(resultSet.getString("cargo"));
                item.setCpf(resultSet.getString("cpf"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return item;

    }

    @Override
    public int create(Collaborator entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int id = -1;

        String sql = "INSERT INTO colaborador(nomecompleto, ";
        sql += " senha, nome_usuario, email, tipo, ";
        sql += " esta_ativo, criado_em, criado_por, ";
        sql += " ultima_modificacao ";
        sql += " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";


        //o nome usuario não precisar igual no dao e no bd

        try {

            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

//            preparedStatement.setString(1, entity.getFullName());
//            preparedStatement.setString(2, entity.getPassword());
//            preparedStatement.setString(3, entity.getUsername());
//            preparedStatement.setString(4, entity.getEmail());
//            preparedStatement.setString(5, UserType.CLIENT.toString());
//            preparedStatement.setBoolean(6, true);
//            preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
//            preparedStatement.setString(8, entity.getUsername());
//            preparedStatement.setTimestamp(9, new Timestamp(System.currentTimeMillis()));

            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

            connection.commit();
            //grava informações permanentemente no bd qnd o commit acontece
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
    public boolean update(Collaborator entity) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE usuario SET nome_completo = ?, ";
        sql += " ultimate_modificacao = ?, email = ? ";
        sql += " WHERE ";
        sql += " id = ? ;";


        //o nome usuario não precisar igual no dao e no bd

        try {

            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, entity.getFullName());
            preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setInt(4, entity.getId());

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
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public Collaborator validateUsernameAndPassword(String username, String password) {

        Collaborator collaborator = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT * FROM usuario WHERE nome_usuario = ? AND senha = ?;";

        //o nome usuario não precisar igual no dao e no bd

        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return collaborator;
            }

            collaborator = new Collaborator();
            collaborator.setId(resultSet.getInt("id"));
            collaborator.setFullName(resultSet.getString("nome"));
            collaborator.setEmail(resultSet.getString("email"));


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }


        return collaborator;
    }
}
