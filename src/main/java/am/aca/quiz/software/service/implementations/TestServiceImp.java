package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.AnswerEntity;
import am.aca.quiz.software.entity.QuestionEntity;
import am.aca.quiz.software.entity.TestEntity;
import am.aca.quiz.software.repository.TestRepository;
import am.aca.quiz.software.service.dto.SubmitQuestionDto;
import am.aca.quiz.software.service.interfaces.TestService;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TestServiceImp implements TestService {

    private final TestRepository testRepository;
    private final QuestionServiceImp questionServiceImp;
    private final AnswerServiceImp answerServiceImp;

    public TestServiceImp(TestRepository testRepository, QuestionServiceImp questionServiceImp, AnswerServiceImp answerServiceImp) {
        this.testRepository = testRepository;
        this.questionServiceImp = questionServiceImp;
        this.answerServiceImp = answerServiceImp;
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


    public Pair<Double,Double> checkTest(List<SubmitQuestionDto> submitQuestionDtos) {

        double score = 0;
        double overallScore = 0;

        /**
         * Key = Submitted question ID
         * Value = User's submitted question's answers' IDs
         */
        Map<Long, List<Long>> submissions = new TreeMap<>();

        /**
         * Key = Question ID
         * Value = Question's correct answers' IDs
         */
        Map<Long, List<Long>> allCorrectAnswersIdsForQuestion = new TreeMap<>();
        Map<Long, List<Long>> allAnswersIdsForQuestion = new TreeMap<>();
        /**
         * Filling user's submitted answers in submissions map
         * Getting from questions all answers
         * Putting in map allAnswersIdsForQuestion where key=Question id( user's submitted question id)
         * and value=Answers Id( for each question).
         * Getting from questions all correct answers
         * Putting in map allCorrectAnswerIdsForQuestion where key=Question id( user's submitted question id)
         * and value=Correct answers Id( for each question).
         */
        submitQuestionDtos
                .stream()
                .forEach(i -> {
                            submissions.put(i.getQuestionId(), i.getChosenAnswerList());

                            long id = i.getQuestionId();

                            List<AnswerEntity> allCorrectAnswers = null;

                            List<Long> correctAnswersIds = new ArrayList<>();

                            List<Long> allAnswersIds = null;

                            try {
                                allAnswersIds = questionServiceImp
                                        .getById(id)
                                        .getAnswerEntities()
                                        .stream()
                                        .map(AnswerEntity::getId)
                                        .collect(Collectors.toList());

                                allCorrectAnswers = questionServiceImp
                                        .getById(id)
                                        .getAnswerEntities()
                                        .stream()
                                        .filter(AnswerEntity::isIs_correct)
                                        .collect(Collectors.toList());


                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            /**
                             * Checks whether allCorrectAnswers is null.
                             */

                            Objects.requireNonNull(allCorrectAnswers).forEach(k -> correctAnswersIds.add(k.getId()));

                            allCorrectAnswersIdsForQuestion.put(id, correctAnswersIds);

                            allAnswersIdsForQuestion.put(id, allAnswersIds);
                        }
                );

        /**
         Prints Map's Key and Value by lambda expression.
         Testing Purposes.
         */
        //  allCorrectAnswerIdsForQuestion.forEach((k, v) -> System.out.println("Question Id is " + k + " , Answer id is " + v));
        //  submissions.forEach((k, v) -> System.out.println("Question Id is " + k + " , Answer id is " + v));
       // allAnswersIdsForQuestion.forEach((k, v) -> System.out.println("Question Id is " + k + " , Answer id is " + v));


        for (Long key : submissions.keySet()) {

            List<Long> submittedAnswers = submissions.get(key);
            List<Long> correctAnswers = allCorrectAnswersIdsForQuestion.get(key);
            List<Long> allAnswers = allAnswersIdsForQuestion.get(key);
            List<Long> userCorrectAnswers = new ArrayList<>();
            List<Long> userIncorrectAnswerd = new ArrayList<>();
            double points;
            try {
                points = questionServiceImp.getById(key).getPoints();
            } catch (SQLException e) {
                e.printStackTrace();
                points = 0;
            }
            submittedAnswers
                    .forEach(i -> {
                        try {
                            if (answerServiceImp.getById(i).isIs_correct()) {
                                userCorrectAnswers.add(answerServiceImp.getById(i).getId());
                            } else {
                                userIncorrectAnswerd.add(answerServiceImp.getById(i).getId());
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });

            overallScore += points;

            if (allAnswers.size() != submittedAnswers.size()) {

                if (submittedAnswers.equals(correctAnswers)) {

                    score += points;

                } else {
                    if (submittedAnswers.size() == 0 || userCorrectAnswers.size() == 0) {
                        score += 0;
                    } else {
                        if (userIncorrectAnswerd.size() == userCorrectAnswers.size()) {
                            score += 0;
                        } else if (userIncorrectAnswerd.size() > userCorrectAnswers.size()) {
                            score += 0;
                        } else {
                            int x = userCorrectAnswers.size() - userIncorrectAnswerd.size();
                            score += x * (points / correctAnswers.size());
                        }
                    }
                }

            } else {
                score += 0;
            }
        }
//        System.out.println("User Score : " + score);
//        System.out.println("Test Overall Score : " + overallScore);
        return new Pair<>(score,overallScore);

    }
}
