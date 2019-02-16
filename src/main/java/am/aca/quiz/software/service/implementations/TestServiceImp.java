package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.AnswerEntity;
import am.aca.quiz.software.entity.QuestionEntity;
import am.aca.quiz.software.entity.TestEntity;
import am.aca.quiz.software.repository.TestRepository;
import am.aca.quiz.software.service.dto.RandomDto;
import am.aca.quiz.software.service.dto.SubmitQuestionDto;
import am.aca.quiz.software.service.implementations.score.ScorePair;
import am.aca.quiz.software.service.interfaces.TestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TestServiceImp implements TestService {

    private final TestRepository testRepository;
    private final QuestionServiceImp questionServiceImp;
    private final AnswerServiceImp answerServiceImp;
    private final TopicServiceImp topicServiceImp;


    public TestServiceImp(TestRepository testRepository, QuestionServiceImp questionServiceImp, AnswerServiceImp answerServiceImp, TopicServiceImp topicServiceImp) {
        this.testRepository = testRepository;
        this.questionServiceImp = questionServiceImp;
        this.answerServiceImp = answerServiceImp;
        this.topicServiceImp = topicServiceImp;
    }

    @Override
    public void addTest(String testName, String description, long duration, List<QuestionEntity> questionEntities) throws SQLException {
        TestEntity testEntity = new TestEntity(testName, description, duration, questionEntities);
        testRepository.save(testEntity);
    }

    @Override
    public List<TestEntity> getAll() throws SQLException {
        return testRepository.findAll();
    }

    @Transactional
    @Override
    public void update(TestEntity test) throws SQLException {
        testRepository.save(test);
    }


    @Transactional
    @Override
    public void removeById(Long id) throws SQLException {
        if (testRepository.findById(id).isPresent()) {
            testRepository.deleteById(id);
        } else {
            throw new SQLException();
        }
    }

    @Override
    public TestEntity getById(Long id) throws SQLException {
        if (testRepository.findById(id).isPresent()) {
            return testRepository.findById(id).get();
        }
        throw new SQLException();

    }

    @Override
    public Set<BigInteger> findTestIdByTopicId(Long id) {
        return testRepository.findTestByTopicId(id);
    }

    public ScorePair<Double, Double> checkTest(List<SubmitQuestionDto> submitQuestionDtos) {

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
         * Getting all answers from questions
         * Putting in map allAnswersIdsForQuestion where key == Question id(user's submitted question id)
         * and value == Answers Id( for each question).
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

        for (Long key : submissions.keySet()) {

            List<Long> submittedAnswers = submissions.get(key);

            List<Long> correctAnswers = allCorrectAnswersIdsForQuestion.get(key);

            List<Long> allAnswers = allAnswersIdsForQuestion.get(key);

            List<Long> userCorrectAnswers = new ArrayList<>();

            List<Long> userIncorrectAnswerId = new ArrayList<>();

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
                                userIncorrectAnswerId.add(answerServiceImp.getById(i).getId());
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });

            overallScore += points;

            /**
             * Proceed only if user hasn't selected all possible answers
             * Otherwise give 0 points
             */
            if (allAnswers.size() != submittedAnswers.size()) {
                /**
                 * If user selected all correct answers without incorrect ones
                 */
                if (submittedAnswers.equals(correctAnswers)) {

                    score += points;

                } else {
                    /**
                     * If user hasn't selected any correct answers or any answers at all
                     */
                    if (submittedAnswers.size() == 0 || userCorrectAnswers.size() == 0) {
                        score += 0;
                    } else {
                        /**
                         * If user has selected equal amounts of correct and incorrect answers
                         */
                        if (userIncorrectAnswerId.size() == userCorrectAnswers.size()) {
                            score += 0;
                        } else if (userIncorrectAnswerId.size() > userCorrectAnswers.size()) {
                            score += 0;
                        } else {
                            /**
                             * MAGIC Starts Here...
                             */
                            int remainingCorrectAnswers = userCorrectAnswers.size() - userIncorrectAnswerId.size();
                            score += remainingCorrectAnswers * (points / correctAnswers.size());
                            /**
                             * MAGIC Ends Here...
                             */
                        }
                    }
                }
            } else {
                score += 0;
            }
        }
        return new ScorePair<>(score, overallScore);
    }

    public List<QuestionEntity> randomQuestionGenerator(RandomDto randomDto) {
        List<QuestionEntity> testQuestions = new ArrayList<>(randomDto.getQuestionCount().intValue());

        List<QuestionEntity> allQuestions = new ArrayList<>();

        randomDto.getTopicId().forEach(i -> {
            try {
                questionServiceImp.getQuestionsByTopicEntity(topicServiceImp.getById(i))
                        .forEach(j -> {
                            if (j.getLevel().toString().equals(randomDto.getLevel())) {
                                allQuestions.add(j);
                            }
                        });

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        Random random = new Random();
        for (int i = 0; i < randomDto.getQuestionCount(); i++) {
            if (allQuestions.isEmpty()) {
                break;
            }
            if (allQuestions.size() == 1) {
                testQuestions.add(allQuestions.get(0));
                allQuestions.remove(0);
                break;
            } else {
                int randomIndex = random.nextInt(allQuestions.size());
                if (randomIndex == 0) {
                    ++randomIndex;
                }
                testQuestions.add(allQuestions.get(randomIndex - 1));
                allQuestions.remove(randomIndex - 1);
            }
        }
        return testQuestions;
    }
}
