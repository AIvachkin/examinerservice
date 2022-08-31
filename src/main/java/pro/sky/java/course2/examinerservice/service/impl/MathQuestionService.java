package pro.sky.java.course2.examinerservice.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.QuestionAlreadyExistException;
import pro.sky.java.course2.examinerservice.service.QuestionService;

import java.util.*;

@Service
public class MathQuestionService implements QuestionService {

    private final Set<Question> questionsMath;
    private final Random randomMath;

    public MathQuestionService() {
        this.questionsMath = new HashSet<>();
        this.randomMath = new Random();
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if(questionsMath.contains(question)){
            throw new QuestionAlreadyExistException();
        }
        questionsMath.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if(!questionsMath.contains(question)){
            throw new QuestionAlreadyExistException();
        }
        questionsMath.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questionsMath);
    }

    @Override
    public Question getRandomQuestion() {
        return new ArrayList<>(questionsMath).get(randomMath.nextInt(questionsMath.size())) ;
    }
}
