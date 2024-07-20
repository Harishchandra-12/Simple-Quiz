package com.example.Sample.Quiz.Controller;

import com.example.Sample.Quiz.Model.QuestionWrapper;
import com.example.Sample.Quiz.Model.Responsee;
import com.example.Sample.Quiz.ServiceImpl.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Quiz")
public class QuizController {

    @Autowired
    QuizServiceImpl quizService;

    public ResponseEntity<String> CreateQuiz(@RequestParam String category,
                                             @RequestParam Integer numOfQuestions,
                                             @RequestParam String title) {
        return quizService.createQuiz(category, numOfQuestions, title);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Responsee> responses){
        return quizService.calculateResult(id, responses);
    }

}
