package am.aca.quiz.software.controller;

import am.aca.quiz.software.service.implementations.HistoryServiceImp;
import am.aca.quiz.software.service.mapper.HistoryMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
public class HistoryController {
    private final HistoryServiceImp historyServiceImp;
    private final HistoryMapper historyMapper;

    public HistoryController(HistoryServiceImp historyServiceImp, HistoryMapper historyMapper) {
        this.historyServiceImp = historyServiceImp;
        this.historyMapper = historyMapper;
    }


}
