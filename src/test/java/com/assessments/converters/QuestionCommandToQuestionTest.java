package converters;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

import com.assessments.commands.QuestionCommand;
import com.assessments.converters.QuestionCommandToQuestion;
import com.assessments.domain.Answer;
import com.assessments.domain.Question;
import com.assessments.domain.QuestionType;

public class QuestionCommandToQuestionTest {

    QuestionCommandToQuestion converter;
    QuestionCommand questionCommand;

    @Before
    public void setUp() throws Exception {
        converter = new QuestionCommandToQuestion();
        questionCommand = new QuestionCommand();
        questionCommand.setQuestion("It is mandatory that the product increment be released to production at the end of each Sprint.");
        List<Answer> answers = new ArrayList<>();
        Answer answer1 = new Answer();
        answer1.setAnswer("True");
        answer1.setValue("value");
        answer1.setCorrect(false);
        answers.add(answer1);

        Answer answer2 = new Answer();
        answer2.setAnswer("False");
        answer2.setValue("value");
        answer2.setCorrect(true);
        answers.add(answer2);

        questionCommand.setAnswers(answers);
        questionCommand.setType(QuestionType.SIMPLE);
        questionCommand.setMessage("The product increment should be usable and releasable at the end of every Sprint, but it does not have to be released.");

    }

    @Test
    public void test() {
        Question question = converter.convert(questionCommand);
        assertTrue(Objects.equals(question.getMessage(), questionCommand.getMessage()) && 
                Objects.equals(question.getAnswers(), questionCommand.getAnswers()) &&
                Objects.equals(question.getQuestion(), questionCommand.getQuestion()) &&
                Objects.equals(question.getType(), questionCommand.getType()));
    }

}
