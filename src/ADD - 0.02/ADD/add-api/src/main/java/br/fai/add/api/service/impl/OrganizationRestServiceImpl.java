package br.fai.add.api.service.impl;

import br.fai.add.api.service.OrganizationRestService;
import br.fai.add.db.dao.OrganizationDao;
import br.fai.add.model.entities.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationRestServiceImpl implements OrganizationRestService<Organization> {

    @Autowired
    private OrganizationDao organizationDao;

    @Override
    public List<Organization> find() {
        return organizationDao.find();
    }

    @Override
    public Organization findById(int id) {
        if (id < 0) return null;

        return (Organization) organizationDao.findById(id);
    }

    @Override
    public int create(Organization entity) {
        return organizationDao.create(entity);
    }


    @Override
    public boolean deleteById(int id) {
        return organizationDao.deleteById(id);
    }

}