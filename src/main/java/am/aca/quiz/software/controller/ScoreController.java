package am.aca.quiz.software.controller;

import am.aca.quiz.software.service.dto.ScoreDto;
import am.aca.quiz.software.service.dto.UserDto;
import am.aca.quiz.software.service.implementations.ScoreServiceImp;
import am.aca.quiz.software.service.implementations.UserServiceImp;
import am.aca.quiz.software.service.mapper.ScoreMapper;
import am.aca.quiz.software.service.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

@RequestMapping("/score")
@RestController
public class ScoreController {

    private final ScoreServiceImp scoreServiceImp;
    private final ScoreMapper scoreMapper;
    private final UserServiceImp userServiceImp;
    private final UserMapper userMapper;


    public ScoreController(ScoreServiceImp scoreServiceImp, ScoreMapper scoreMapper, UserServiceImp userServiceImp, UserMapper userMapper) {
        this.scoreServiceImp = scoreServiceImp;
        this.scoreMapper = scoreMapper;
        this.userServiceImp = userServiceImp;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ModelAndView getScore(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("score");
        List<ScoreDto> scoreDtos = null;
        try {
            UserDto userDto = userMapper.mapEntityToDto(userServiceImp.findByEmail(principal.getName()));
            scoreDtos = scoreMapper.mapEntitiesToDto(scoreServiceImp.getAllByUserId(userDto.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("scoreList", scoreDtos);

        return modelAndView;
    }
}
