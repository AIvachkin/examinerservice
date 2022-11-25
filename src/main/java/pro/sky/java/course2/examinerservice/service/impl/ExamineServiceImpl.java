package pro.sky.java.course2.examinerservice.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.IncorrectAmountOfQuestionsException;
import pro.sky.java.course2.examinerservice.service.ExaminerService;
import pro.sky.java.course2.examinerservice.service.QuestionService;

import java.util.*;

@Service
public class ExamineServiceImpl implements ExaminerService {

    private final QuestionService questionService;
    private final QuestionService questionServiceMath;

    public ExamineServiceImpl(@Qualifier("javaQuestionService") QuestionService questionService,
                              @Qualifier("mathQuestionService") QuestionService questionServiceMath) {
        this.questionService = questionService;
        this.questionServiceMath = questionServiceMath;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
//        if (amount < 0 || amount > questionService.getAll().size() + questionServiceMath.getAll().size()) {
//            throw new IncorrectAmountOfQuestionsException();
//        }
        Set<Question> result = new HashSet<>();

        Set <Question> allQuestionsSet = new HashSet<>();
        allQuestionsSet.addAll(questionService.getAll());
        allQuestionsSet.addAll(questionServiceMath.getAll());

        if (amount < 0 || amount > allQuestionsSet.size()) {
            throw new IncorrectAmountOfQuestionsException();
        }

        List <Question> allQuestionsList = new ArrayList<>(allQuestionsSet);

        Random r = new Random();
        while (result.size() < amount) {
            result.add(allQuestionsList.get(r.nextInt(allQuestionsList.size())));
        }
        return result;
    }
}
