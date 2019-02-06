package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.AnswerEntity;
import am.aca.quiz.software.entity.QuestionEntity;
import am.aca.quiz.software.entity.TestEntity;
import am.aca.quiz.software.repository.TestRepository;
import am.aca.quiz.software.service.dto.AnswerDto;
import am.aca.quiz.software.service.dto.QuestionDto;
import am.aca.quiz.software.service.dto.SubmitQuestionDto;
import am.aca.quiz.software.service.interfaces.TestService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TestServiceImp implements TestService {

    private final TestRepository testRepository;
    private final QuestionServiceImp questionServiceImp;

    public TestServiceImp(TestRepository testRepository, QuestionServiceImp questionServiceImp) {
        this.testRepository = testRepository;
        this.questionServiceImp = questionServiceImp;
    }


    @Override
    public void addTest(String testName, String description, long duration, List<QuestionEntity> questionEntities) throws SQLException {
        TestEntity testEntity = new TestEntity(testName, description, duration, questionEntities);
        testRepository.saveAndFlush(testEntity);
    }

    @Override
    public List<TestEntity> getAll() throws SQLException {
        return testRepository.findAll();
    }

    @Override
    public void update(TestEntity test) throws SQLException {
        testRepository.save(test);
    }


    @Override
    public void removeById(Long id) throws SQLException {
        if (testRepository.findById(id) != null) {
            testRepository.deleteById(id);
        }
    }

    @Override
    public TestEntity getById(Long id) throws SQLException {
        return testRepository.findById(id).get();
    }

    @Override
    public Set<BigInteger> findTestIdByTopicId(Long id) {
        return testRepository.findTestByTopicId(id);

    }


    public void checkTest(List<SubmitQuestionDto> submitQuestionDtos) {

        double score=0;
        double overallScore=0;

        /**
         * Key = Submitted question ID
         * Value = User's submitted question's answers' IDs
         */
        Map<Long, List<Long>> submissions = new TreeMap<>();

        /**
         * Key = Question ID
         * Value = Question's correct answers' IDs
         */
        Map<Long, List<Long>> allCorrectAnswerIdsForQuestion = new TreeMap<>();
        /**
         * Filling user's submitted answers in submissions map
         * Getting from questions answers only Corrects
         * Putting in map allCorrectAnswerIdsForQuestion where key=Question id( user's submitted question id)
         * and value=Correct answers Id( for each question).
         */
        submitQuestionDtos
                .stream()
                .forEach(i -> {

                            submissions.put(i.getQuestionId(), i.getChosenAnswerList());

                            long id = i.getQuestionId();

                            List<AnswerEntity> allCorrectAnswers = null;

                            List<Long> correctAnswerIds = new ArrayList<>();

                            try {
                                allCorrectAnswers = questionServiceImp
                                        .getById(id)
                                        .getAnswerEntities()
                                        .stream()
                                        .filter(j -> j.isIs_correct())
                                        .collect(Collectors.toList());


                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            allCorrectAnswers.forEach(k -> correctAnswerIds.add(k.getId()));

                            allCorrectAnswerIdsForQuestion.put(id, correctAnswerIds);
                        }
                );


        /*
        Prints Map's Key and Value by lambda expression.
        Testing Purposes.
         */
        //  allCorrectAnswerIdsForQuestion.forEach((k, v) -> System.out.println("Question Id is " + k + " , Answer id is " + v));
        //  submissions.forEach((k, v) -> System.out.println("Question Id is " + k + " , Answer id is " + v));



        for(Long key :submissions.keySet()){

            List<Long> submitted=submissions.get(key);
            List<Long> correct=allCorrectAnswerIdsForQuestion.get(key);

            if(submitted.equals(correct)){
                try {
                    score+=questionServiceImp.getById(key).getPoints();
                    overallScore+=score;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println(score);


    }
}
