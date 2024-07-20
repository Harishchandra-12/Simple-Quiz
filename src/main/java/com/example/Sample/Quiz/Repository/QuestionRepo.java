package com.example.Sample.Quiz.Repository;

import com.example.Sample.Quiz.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {


    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :noOfQuestions", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int noOfQuestions);

    Optional<Question> getQuestionById(Integer id);

}
