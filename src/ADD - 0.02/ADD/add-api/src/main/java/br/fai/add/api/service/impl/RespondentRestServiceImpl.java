package br.fai.add.api.service.impl;

import br.fai.add.api.service.RespondentRestService;
import br.fai.add.db.dao.RespondentDao;
import br.fai.add.model.entities.Respondent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespondentRestServiceImpl implements RespondentRestService<Respondent> {

    @Autowired
    private RespondentDao respondentDao;

    @Override
    public List<Respondent> find() {
        return respondentDao.find();
    }

    @Override
    public List<Respondent> findRespondentsByForm(int id) {
        return respondentDao.findRespondentsByForm(id);
    }

    @Override
    public List<Respondent> findRespondentsByOrg(int id) {
        return respondentDao.findRespondentsByOrg(id);
    }

    @Override
    public Respondent findRespondentByCollaboratorAndForm(int id, int id2) {
        if (id < 0) return null;

        return (Respondent) respondentDao.findRespondentByCollaboratorAndForm(id, id2);
    }

    @Override
    public Respondent findById(int id) {
        if (id < 0) return null;

        return (Respondent) respondentDao.findById(id);
    }

    @Override
    public int create(Respondent entity) {
        return respondentDao.create(entity);
    }


    @Override
    public boolean update(final int id, Respondent entity) {

        Respondent respondent = (Respondent) respondentDao.findById(id);
        if (respondent == null) return false;


        respondent.setAnswered(true);

        return respondentDao.update(respondent, id);

    }

    @Override
    public boolean deleteById(int id) {
        return respondentDao.deleteById(id);
    }


}
