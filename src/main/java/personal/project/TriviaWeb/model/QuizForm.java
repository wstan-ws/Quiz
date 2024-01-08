package personal.project.TriviaWeb.model;

import java.util.Arrays;

import jakarta.validation.constraints.NotEmpty;

public class QuizForm {
    
    private Integer numberOfQuestions;
    
    private String difficulty;

    @NotEmpty(message = "Must select at least 1 category")
    private String[] categories;

    public QuizForm() {
    }

    public QuizForm(Integer numberOfQuestions, String difficulty, String[] categories) {
        this.numberOfQuestions = numberOfQuestions;
        this.difficulty = difficulty;
        this.categories = categories;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "QuizForm [numberOfQuestions=" + numberOfQuestions + ", difficulty=" + difficulty + ", categories="
                + Arrays.toString(categories) + "]";
    }
}
