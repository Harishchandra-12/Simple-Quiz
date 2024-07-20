package com.example.Sample.Quiz.Service;

import org.springframework.http.ResponseEntity;
import com.example.Sample.Quiz.Model.QuestionWrapper;
import com.example.Sample.Quiz.Model.Responsee;

import java.util.List;

public interface QuizService {

    public ResponseEntity<String> createQuiz(String category,Integer numOfQuestions, String title);

    ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id);
    public ResponseEntity<Integer> calculateResult(Integer id, List<Responsee> responses);
}
