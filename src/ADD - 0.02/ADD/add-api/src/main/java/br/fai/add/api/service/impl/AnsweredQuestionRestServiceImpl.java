package br.fai.add.api.service.impl;

import br.fai.add.api.service.AnswerRestService;
import br.fai.add.api.service.QuestionRestService;
import br.fai.add.model.entities.Answer;
import br.fai.add.model.entities.AnsweredQuestion;
import br.fai.add.model.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AnsweredQuestionRestServiceImpl {

    @Autowired
    private AnswerRestService answerRestService;

    @Autowired
    private QuestionRestService questionRestService;

    public List<AnsweredQuestion> find() {

        List<AnsweredQuestion> answeredQuestions = new ArrayList<AnsweredQuestion>();

        List<Question> questions = questionRestService.find();

        for (Question question : questions) {
            AnsweredQuestion answeredQuestion = new AnsweredQuestion();
            answeredQuestion.setAnswer((Answer) answerRestService.findAnswerByQuestion(question.getId()));
            answeredQuestion.setQuestion(question);
            answeredQuestions.add(answeredQuestion);
        }


        return answeredQuestions;
    }


}
