package com.example.Sample.Quiz.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionWrapper {

    private Integer id;
    private String questionDescription;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private Integer marks; //Max marks
}
