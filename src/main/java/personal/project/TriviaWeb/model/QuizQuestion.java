package personal.project.TriviaWeb.model;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestion {
    
    private String question;

    private String correctAnswer;

    private List<String> allAnswers = new ArrayList<>();

    public QuizQuestion() {
    }

    public QuizQuestion(String question, String correctAnswer, List<String> allAnswers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.allAnswers = allAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getAllAnswers() {
        return allAnswers;
    }

    public void setAllAnswers(List<String> allAnswers) {
        this.allAnswers = allAnswers;
    }

    @Override
    public String toString() {
        return "QuizQuestion [question=" + question + ", correctAnswer=" + correctAnswer + ", allAnswers=" + allAnswers
                + "]";
    }
}
