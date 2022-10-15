package br.fai.add.client.service;

import java.util.List;

public interface OptionService<T> extends BaseService<T> {

    List<T> findOptionsByQuestion(int id);

}
