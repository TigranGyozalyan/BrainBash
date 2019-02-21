package am.aca.quiz.software.service;


import am.aca.quiz.software.entity.HistoryEntity;
import am.aca.quiz.software.entity.enums.Status;
import am.aca.quiz.software.service.implementations.HistoryServiceImp;
import am.aca.quiz.software.service.implementations.TestServiceImp;
import am.aca.quiz.software.service.mapper.HistoryMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class ThreadService {

    private HistoryServiceImp historyServiceImp;

    private HistoryMapper historyMapper;

    private TestServiceImp testServiceImp;

    public ThreadService(HistoryServiceImp historyServiceImp, HistoryMapper historyMapper, TestServiceImp testServiceImp) {
        this.historyServiceImp = historyServiceImp;
        this.historyMapper = historyMapper;
        this.testServiceImp = testServiceImp;
    }

    @Async("threadPoolTaskExecutor")
    @Scheduled(cron = "*/30 * * * * ?")
    public void findUser() throws InterruptedException {

        System.out.println("START : " + LocalTime.now());
        Set<HistoryEntity> threadTestList = new HashSet<>();

        List<HistoryEntity> userHistoriesUpcoming = historyServiceImp.findAllByStatus(Status.UPCOMING);

        List<HistoryEntity> userHistoriesIngoing = historyServiceImp.findAllByStatus(Status.INPROGRESS);

        threadTestList.addAll(userHistoriesUpcoming);
        threadTestList.addAll(userHistoriesIngoing);

        if (!threadTestList.isEmpty()) {

            LocalDateTime now = LocalDateTime.now();

            System.out.println(now);
            System.out.println("SET SIZE " + threadTestList.size());

            Iterator<HistoryEntity> iterator = threadTestList.iterator();
            while (iterator.hasNext()) {
                HistoryEntity i = iterator.next();
                LocalDateTime userStartTime = i.getStartTime();
                if (i.getStatus().equals(Status.INPROGRESS)) {
                    try {
                        if (i.getSessionId() != null) {
                            if (now.isAfter(userStartTime.plusMinutes
                                (testServiceImp.getById(i.getTestEntity().getId()).getDuration())
                                .plusSeconds(3))) {

                                threadUpdate(now, i);
                                iterator.remove();
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        if (now.isAfter(userStartTime.plusMinutes(testServiceImp
                            .getById(i.getTestEntity().getId()).getDuration()))) {

                            threadUpdate(now, i);
                            iterator.remove();
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("END : " + LocalTime.now());
    }

    private void threadUpdate(LocalDateTime now, HistoryEntity i) throws SQLException {
        am.aca.quiz.software.entity.HistoryEntity historyEntity = historyServiceImp.getById(i.getId());
        historyEntity.setScore(0);
        historyEntity.setStatus(Status.COMPLETED);
        historyEntity.setEndTime(now);
        historyEntity.setSessionId(null);
        historyServiceImp.addHistory(historyEntity);
    }
}
