package personal.project.TriviaWeb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import personal.project.TriviaWeb.model.QuizQuestion;

@Service
public class TriviaService {

    private Integer currentIndex = 0;
    private Integer score = 0;
    
    public String getCategories(String[] categories) {

        String categoryList = "";
        
        for (String category : categories) {
            if (categoryList == "") {
                categoryList += category;
            } else {
                categoryList += ",";
                categoryList += category;
            }
        }

        return categoryList;
    }

    public QuizQuestion getCurrentQuestion(List<QuizQuestion> quizList) {

        return quizList.get(currentIndex);
    }

    public Boolean hasNextQuestion(List<QuizQuestion> quizList) {

        return currentIndex < quizList.size();
    }

    public void moveToNextQuestion() {

        currentIndex++;
    }

    public void resetIndex() {

        currentIndex = 0;
        score = 0;
    }

    public Integer getScore() {
        return score;
    }
    
    public void correctAnswer() {
        score++;
    }
}
