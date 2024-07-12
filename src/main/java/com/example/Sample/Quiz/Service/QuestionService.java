package com.example.Sample.Quiz.Service;

import com.example.Sample.Quiz.Model.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {

    public ResponseEntity<List<Question>> getAllQuestions();
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category);
    public ResponseEntity<String> addQuestion(Question question);
    public ResponseEntity<String> updateQuestion(Question question, Integer id);
    public ResponseEntity<String> deleteQuestionById(Integer id);
}
