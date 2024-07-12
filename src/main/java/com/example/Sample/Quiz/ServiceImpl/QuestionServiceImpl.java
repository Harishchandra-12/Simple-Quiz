package com.example.Sample.Quiz.ServiceImpl;


import com.example.Sample.Quiz.Model.Question;
import com.example.Sample.Quiz.Repository.QuestionRepo;
import com.example.Sample.Quiz.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepo questionRepo;


    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> list = questionRepo.findAll();
        if(list.isEmpty())
            return new ResponseEntity<>(list,HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(list,HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepo.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
 
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionRepo.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateQuestion(Question question, Integer id) {
        //Integer id = question.getId();
        Optional<Question> existingQuestion = questionRepo.getQuestionById(id);
        if(existingQuestion.isPresent()) {
            Question existingQuestion1 = existingQuestion.get();
//            existingQuestion1.setCategory(question.getCategory());
//            existingQuestion1.setQuestionDescription(question.getQuestionDescription());
//            existingQuestion1.setChoice1(question.getChoice1());
//            existingQuestion1.setChoice2(question.getChoice2());
//            existingQuestion1.setChoice3(question.getChoice3());
//            existingQuestion1.setChoice4(question.getChoice4());
//            existingQuestion1.setMarks(question.getMarks());
//            existingQuestion1.setCorrectAnswer(question.getCorrectAnswer());
            questionRepo.save(existingQuestion1);
            return new ResponseEntity<>("updated",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        }

    }


    public ResponseEntity<String> deleteQuestionById(Integer id) {

        Optional<Question> existingQuestion = questionRepo.getQuestionById(id);

        if(existingQuestion.isPresent()){
            questionRepo.deleteById(id);
            return new ResponseEntity<>("deleted",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        }

    }
}
