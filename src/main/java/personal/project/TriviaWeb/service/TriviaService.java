package personal.project.TriviaWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import personal.project.TriviaWeb.model.Highscore;
import personal.project.TriviaWeb.model.QuizQuestion;
import personal.project.TriviaWeb.repo.TriviaRepository;

@Service
public class TriviaService {

    @Autowired
    private TriviaRepository triviaRepo;

    private Integer currentIndex = 0;
    private Integer score = 0;
    private Integer questionNo = 1;
    
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
        questionNo++;
    }

    public void resetIndex() {

        currentIndex = 0;
        score = 0;
        questionNo = 1;
    }

    public Integer getScore() {
        return score;
    }
    
    public void correctAnswer() {
        score++;
    }

    public Integer getQuestionNo() {
        return questionNo;
    }

    public void saveHighscore(String name, Integer highscore, String difficulty, Integer questionNo) {
        triviaRepo.saveHighscore(name, highscore, difficulty, questionNo);
    }

    public List<Highscore> getAllHighscore(String difficulty, Integer questionNo) {
        
        List<Highscore> highscoreList = triviaRepo.getAllHighScore(difficulty, questionNo);

        highscoreList = highscoreList.stream()
            .sorted((s1, s2) -> s2.getHighscore().compareTo(s1.getHighscore()))
            .toList();

        Integer rank = 1;    
        for (Highscore highscore : highscoreList) {
            highscore.setRanking(rank);
            rank++;
        }

        return highscoreList;
    }
}
