package pro.sky.java.course2.examinerservice.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/javaMath")
public class MathQuestionController {

    private final QuestionService questionServiceMath;

    public MathQuestionController(@Qualifier("mathQuestionService") QuestionService questionServiceMath) {
        this.questionServiceMath = questionServiceMath;
    }

    @GetMapping("/addMath")
    public Question addQuestion(String question, String answer) {
        return questionServiceMath.add(question, answer);
    }

    @GetMapping("/removeMath")
    public Question removeQuestion(String question, String answer) {
        return questionServiceMath.remove(new Question(question, answer));
    }

    @GetMapping()
    public Collection<Question> getQuestions() {
        return questionServiceMath.getAll();
    }

}
