package br.fai.add.db.dao.impl;


import br.fai.add.db.connection.ConnectionFactory;
import br.fai.add.db.dao.CollaboratorDao;
import br.fai.add.model.entities.Collaborator;
import br.fai.add.model.entities.Organization;
import br.fai.add.model.enums.UserType;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CollaboratorDaoImpl implements CollaboratorDao<Collaborator> {

    @Override
    public List<Collaborator> find() {

        List<Collaborator> items = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT * FROM colaborador ;";

        //o nome usuario não precisar igual no dao e no bd

        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Collaborator collaborator = new Collaborator();
                collaborator.setId(resultSet.getInt("id"));
                collaborator.setFullName(resultSet.getString("nome"));
                collaborator.setEmail(resultSet.getString("email"));
                collaborator.setCpf(resultSet.getString("cpf"));

                String userType = resultSet.getString("tipo");
                collaborator.setType(UserType.valueOf(userType));

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
    public List<Collaborator> findCollaboratorsByForm(int id) {
        List<Collaborator> items = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT C.id as id_c, * FROM colaborador C INNER JOIN avaliacao A ON C.id = A.colaborador_id " +
                " WHERE A.id = ?;";

        //o nome usuario não precisar igual no dao e no bd

        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Collaborator collaborator = new Collaborator();
                collaborator.setId(resultSet.getInt("id_c"));
                collaborator.setFullName(resultSet.getString("nome"));
                collaborator.setEmail(resultSet.getString("email"));
                collaborator.setCpf(resultSet.getString("cpf"));

                String userType = resultSet.getString("tipo");
                collaborator.setType(UserType.valueOf(userType));

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
    public List<Collaborator> findCollaboratorsByOrganization(int id) {
        List<Collaborator> items = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT C.id as id_c, * FROM colaborador C INNER JOIN organizacao O ON C.organizacao_id = O.id " +
                " WHERE O.id = ?;";

        //o nome usuario não precisar igual no dao e no bd

        try {

            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Collaborator collaborator = new Collaborator();
                collaborator.setId(resultSet.getInt("id_c"));
                collaborator.setFullName(resultSet.getString("nome"));
                collaborator.setEmail(resultSet.getString("email"));
                collaborator.setCpf(resultSet.getString("cpf"));

                String userType = resultSet.getString("tipo");
                collaborator.setType(UserType.valueOf(userType));

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

                String userType = resultSet.getString("tipo");
                item.setType(UserType.valueOf(userType));

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

        String sql;
        if (entity.getJobTitle() != null) {
            sql = "INSERT INTO colaborador(organizacao_id, ";
            sql += " tipo, nome, sexo, ";
            sql += " cpf, telefone, email, ";
            sql += " cargo, senha, ";
            sql += " nome_usuario) ";
            sql += " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ;";
        } else {
            sql = "INSERT INTO colaborador(organizacao_id, ";
            sql += " tipo, nome, sexo, ";
            sql += " cpf, telefone, email, ";
            sql += " setor, senha, ";
            sql += " nome_usuario) ";
            sql += " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ;";
        }

        //o nome usuario não precisar igual no dao e no bd

        try {


            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //setInt para mandar o id, o BD recebe diversos tipos de dados nos inserts, por ex datas, etc


            if (entity.getJobTitle() != null) {
                preparedStatement.setInt(1, entity.getOrganization().getId());
                preparedStatement.setString(2, UserType.EMPLOYEE.toString());
                preparedStatement.setString(3, entity.getFullName());
                preparedStatement.setString(4, entity.getGender());
                preparedStatement.setString(5, entity.getCpf());
                preparedStatement.setString(6, entity.getPhoneNumber());
                preparedStatement.setString(7, entity.getEmail());
                preparedStatement.setString(8, entity.getJobTitle());
                preparedStatement.setString(9, entity.getPassword());
                preparedStatement.setString(10, entity.getUsername());

            }
            if (entity.getCompanyBranch() != null) {
                preparedStatement.setInt(1, entity.getOrganization().getId());
                preparedStatement.setString(2, UserType.REVIEWER.toString());
                preparedStatement.setString(3, entity.getFullName());
                preparedStatement.setString(4, entity.getGender());
                preparedStatement.setString(5, entity.getCpf());
                preparedStatement.setString(6, entity.getPhoneNumber());
                preparedStatement.setString(7, entity.getEmail());
                preparedStatement.setString(8, entity.getCompanyBranch());
                preparedStatement.setString(9, entity.getPassword());
                preparedStatement.setString(10, entity.getUsername());
            }


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
    public boolean deleteById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM colaborador WHERE id = ?; ";

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

    @Override
    public Collaborator validateUsernameAndPassword(String username, String password) {

        Collaborator collaborator = null;
        Organization organization = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        final String sql = "SELECT * FROM colaborador WHERE nome_usuario = ? AND senha = ?;";

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
            organization = new Organization();

            collaborator.setId(resultSet.getInt("id"));
            collaborator.setFullName(resultSet.getString("nome"));
            collaborator.setCpf(resultSet.getString("cpf"));
            collaborator.setEmail(resultSet.getString("email"));

            organization.setId(resultSet.getInt("organizacao_id"));
            collaborator.setOrganization(organization);

            String userType = resultSet.getString("tipo");
            collaborator.setType(UserType.valueOf(userType));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }

        return collaborator;
    }

}
