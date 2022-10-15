package br.fai.add.api.service;

import java.util.List;

public interface OptionRestService<T> extends BaseRestService<T> {
    List<T> findOptionsByQuestion(int id);

}
