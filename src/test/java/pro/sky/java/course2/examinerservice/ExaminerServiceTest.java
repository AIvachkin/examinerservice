package pro.sky.java.course2.examinerservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.IncorrectAmountOfQuestionsException;
import pro.sky.java.course2.examinerservice.service.impl.ExamineServiceImpl;
import pro.sky.java.course2.examinerservice.service.impl.JavaQuestionService;

import java.util.Collections;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceTest {

    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExamineServiceImpl examineService;

    @Test
    public void getQuestionsNegativeTest() {
        when(javaQuestionService.getAll()).thenReturn(Collections.emptyList());

        assertThatExceptionOfType(IncorrectAmountOfQuestionsException.class)
                .isThrownBy(() -> examineService.getQuestions(1));

        assertThatExceptionOfType(IncorrectAmountOfQuestionsException.class)
                .isThrownBy(() -> examineService.getQuestions(-1));
    }

    @Test
    public void getQuestionsPositiveTest() {
        Question question1 = new Question("Спутник Земли?", "Луна");
        Question question2 = new Question("Столица России?", "Москва");
        Question question3 = new Question("2+2?", "4");
        Question question4 = new Question("H2O?", "Вода");
        Question question5 = new Question("Положительное число - квадратный корень из 100?", "10");

        Set<Question> allQuestions = Set.of(question1, question2, question3, question4, question5);

        when(javaQuestionService.getAll()).thenReturn(allQuestions);

        when(javaQuestionService.getRandomQuestion()).thenReturn(question1,
                question3,
                question1,
                question2,
                question5,
                question4,
                question1,
                question2);
//возвращает разные вопросы в зависимости от номера вызова метода - эмуляция рандома

        assertThat(examineService.getQuestions(3)).containsExactlyInAnyOrder(question1, question3, question2);


        when(javaQuestionService.getRandomQuestion()).thenReturn(question1,
                question3,
                question1,
                question2,
                question5,
                question4,
                question1,
                question2);
        assertThat(examineService.getQuestions(5)).containsExactlyInAnyOrder(question1, question3, question2, question5, question4);

    }

}
