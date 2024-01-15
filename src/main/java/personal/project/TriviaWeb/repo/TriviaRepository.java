package personal.project.TriviaWeb.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import personal.project.TriviaWeb.model.Highscore;

@Repository
public class TriviaRepository {

    @Autowired
    private JdbcTemplate template;

    public void saveHighscore(String name, Integer highscore, String difficulty, Integer questionNo) {

        if (difficulty.equals("easy")) {
            if (questionNo == 10) {
                template.update(Queries.SQL_INSERT_EASY10, name, highscore);
            } else if (questionNo == 20) {
                template.update(Queries.SQL_INSERT_EASY20, name, highscore);
            } else if (questionNo == 30) {
                template.update(Queries.SQL_INSERT_EASY30, name, highscore);
            }
        } else if (difficulty.equals("medium")) {
            if (questionNo == 10) {
                template.update(Queries.SQL_INSERT_MEDIUM10, name, highscore);
            } else if (questionNo == 20) {
                template.update(Queries.SQL_INSERT_MEDIUM20, name, highscore);
            } else if (questionNo == 30) {
                template.update(Queries.SQL_INSERT_MEDIUM30, name, highscore);
            }
        } else if (difficulty.equals("hard")) {
            if (questionNo == 10) {
                template.update(Queries.SQL_INSERT_HARD10, name, highscore);
            } else if (questionNo == 20) {
                template.update(Queries.SQL_INSERT_HARD20, name, highscore);
            } else if (questionNo == 30) {
                template.update(Queries.SQL_INSERT_HARD30, name, highscore);
            }
        } else {
            System.err.print("Error");
            System.exit(1);
        }
    }
    
    public List<Highscore> getAllHighScore(String difficulty, Integer questionNo) {

        List<Highscore> highscoreList = new ArrayList<>();
        SqlRowSet rs = null;

        if (difficulty.equals("easy")) {
            if (questionNo == 10) {
                rs = template.queryForRowSet(Queries.SQL_SELECT_EASY10);
            } else if (questionNo == 20) {
                rs = template.queryForRowSet(Queries.SQL_SELECT_EASY20);
            } else if (questionNo == 30) {
                rs = template.queryForRowSet(Queries.SQL_SELECT_EASY30);
            }
        } else if (difficulty.equals("medium")) {
            if (questionNo == 10) {
                rs = template.queryForRowSet(Queries.SQL_SELECT_MEDIUM10);
            } else if (questionNo == 20) {
                rs = template.queryForRowSet(Queries.SQL_SELECT_MEDIUM20);
            } else if (questionNo == 30) {
                rs = template.queryForRowSet(Queries.SQL_SELECT_MEDIUM30);
            }
        } else if(difficulty.equals("hard")) {
            if (questionNo == 10) {
                rs = template.queryForRowSet(Queries.SQL_SELECT_HARD10);
            } else if (questionNo == 20) {
                rs = template.queryForRowSet(Queries.SQL_SELECT_HARD20);
            } else if (questionNo == 30) {
                rs = template.queryForRowSet(Queries.SQL_SELECT_HARD30);
            }        
        } else {
            System.err.print("Error");
            System.exit(1);
        }

        while (rs.next()) {
            String name = rs.getString("name");
            Integer score = rs.getInt("score");
            
            Highscore highscore = new Highscore(name, score);
            highscoreList.add(highscore);
        }

        return highscoreList;
    }
}
