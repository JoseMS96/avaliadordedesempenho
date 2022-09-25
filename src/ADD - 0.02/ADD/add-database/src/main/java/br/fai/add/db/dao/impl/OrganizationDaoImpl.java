package br.fai.add.db.dao.impl;

import br.fai.add.db.dao.OrganizationDao;
import br.fai.add.model.entities.Organization;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizationDaoImpl implements OrganizationDao<Organization> {

    @Override
    public List<Organization> find() {
        return null;
    }

    @Override
    public Organization findById(int id) {
        return null;
    }

    @Override
    public int create(Organization entity) {
        return -1;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
