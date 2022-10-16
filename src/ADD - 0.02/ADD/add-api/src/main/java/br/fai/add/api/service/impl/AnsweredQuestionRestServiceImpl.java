package br.fai.add.api.service.impl;

import br.fai.add.api.service.AnswerRestService;
import br.fai.add.api.service.OptionRestService;
import br.fai.add.api.service.QuestionRestService;
import br.fai.add.model.entities.Answer;
import br.fai.add.model.entities.AnsweredQuestion;
import br.fai.add.model.entities.Option;
import br.fai.add.model.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AnsweredQuestionRestServiceImpl {

    @Autowired
    private AnswerRestService answerRestService;

    @Autowired
    private QuestionRestService questionRestService;

    @Autowired
    private OptionRestService optionRestService;

    public List<AnsweredQuestion> find(int formId, int collaboratorId) { // SUGAR FORM ID

        List<AnsweredQuestion> answeredQuestions = new ArrayList<AnsweredQuestion>();

        List<Question> questions = questionRestService.findQuestionsByForm(formId);
        for (Question question : questions) {

            AnsweredQuestion answeredQuestion = new AnsweredQuestion();

            Answer answer = (Answer) answerRestService.findAnswerByQuestionAndCollaborator(question.getId(), collaboratorId);

            if (answer != null && question.isAlternativesQuestion() == true) {
                answer.setOption((Option) optionRestService.findById(answer.getOption().getId()));
            }


            answeredQuestion.setAnswer(answer);
            answeredQuestion.setQuestion(question);
            answeredQuestions.add(answeredQuestion);

        }


        return answeredQuestions;
    }


}
