package personal.project.TriviaWeb.model;

import jakarta.validation.constraints.NotEmpty;

public class Highscore {
    
    @NotEmpty(message = "Name is mandatory")
    private String name;

    private Integer highscore;

    private Integer ranking;

    public Highscore() {
    }

    public Highscore(@NotEmpty(message = "Name is mandatory") String name, Integer highscore) {
        this.name = name;
        this.highscore = highscore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHighscore() {
        return highscore;
    }

    public void setHighscore(Integer highscore) {
        this.highscore = highscore;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "Highscore [name=" + name + ", highscore=" + highscore + ", ranking=" + ranking + "]";
    }
}
