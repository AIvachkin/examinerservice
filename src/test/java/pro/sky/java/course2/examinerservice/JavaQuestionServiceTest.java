package pro.sky.java.course2.examinerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.QuestionAlreadyExistException;
import pro.sky.java.course2.examinerservice.exception.QuestionNotFoundException;
import pro.sky.java.course2.examinerservice.service.QuestionService;
import pro.sky.java.course2.examinerservice.service.impl.JavaQuestionService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class JavaQuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();

    @BeforeEach
    public void beforeEach() {
        questionService.getAll().forEach(questionService::remove);
    }

//    обнуляем вопросы перед каждым тестом (очищаем SET)

    @Test
    public void addAndRemoveTest() {
        Question question = new Question("Спутник Земли?", "Луна");
        questionService.add(question);

//        case 1 (add)
        assertThat(questionService.getAll()).contains(question);

//        case 2 (negative add)

        assertThatExceptionOfType(QuestionAlreadyExistException.class)
                .isThrownBy(() -> questionService.add("Спутник Земли?", "Луна"));

//        case 3 (remove)

        questionService.remove(question);
        assertThat(questionService.getAll()).isEmpty();

//        case 4 (negative remove)

        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(question));
    }

    @Test
    public void getRandomQuestionTest() {
        Question question1 = new Question("Спутник Земли?", "Луна");
        Question question2 = new Question("Столица России?", "Москва");
        Question question3 = new Question("2+2?", "4");
        questionService.add(question1);
        questionService.add(question2);
        questionService.add(question3);

        assertThat(questionService.getAll()).contains(questionService.getRandomQuestion());

//        случайный вопрос находится в getAll
    }
}
