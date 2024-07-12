package com.example.Sample.Quiz.Repository;

import com.example.Sample.Quiz.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {
}
