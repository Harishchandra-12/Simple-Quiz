package com.example.Sample.Quiz.ServiceImpl;

import com.example.Sample.Quiz.Model.Question;
import com.example.Sample.Quiz.Model.QuestionWrapper;
import com.example.Sample.Quiz.Model.Quiz;
import com.example.Sample.Quiz.Model.Responsee;
import com.example.Sample.Quiz.Repository.QuestionRepo;
import com.example.Sample.Quiz.Repository.QuizRepo;
import com.example.Sample.Quiz.Service.QuizService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService  {

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuestionRepo questionRepo;


    public ResponseEntity<String> createQuiz(String category, Integer numOfQuestions, String title) {

        List<Question> questions = questionRepo.findRandomQuestionsByCategory(category, numOfQuestions);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepo.save(quiz);
        if(questions.size()==numOfQuestions)
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Insufficient Questions",HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionDescription(), q.getChoice1(), q.getChoice2(), q.getChoice3(), q.getChoice4(),q.getMarks());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> calculateResult(Integer id, List<Responsee> responses) {
        Optional<Quiz> optionalQuiz = quizRepo.findById(id);
        if(optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.get();
            List<Question> questions = quiz.getQuestions();
            int right = 0;
            int i = 0;
            for (Responsee response : responses) {
                if (response.getResponse().equals(questions.get(i).getCorrectAnswer()))
                    right+=questions.get(i).getMarks();

                i++;
            }
            return new ResponseEntity<>(right, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(-1,HttpStatus.NOT_FOUND);
    }



}
