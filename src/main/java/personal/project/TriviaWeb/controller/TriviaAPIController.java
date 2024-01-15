package personal.project.TriviaWeb.controller;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import personal.project.TriviaWeb.model.QuizQuestion;

@RestController
@RequestMapping
public class TriviaAPIController {
    
    public List<QuizQuestion> getQuestions(Integer limit, String difficulty, String categoryList) {

        List<QuizQuestion> quizList = new ArrayList<>();

        String url = UriComponentsBuilder
            .fromUriString("https://the-trivia-api.com/v2/questions")
            .queryParam("limit", 50)
            .queryParam("difficulties", difficulty)
            .queryParam("categories", categoryList)
            .toUriString();

        RequestEntity<Void> request = RequestEntity.get(url).build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> response = template.exchange(request, String.class);
        String payload = response.getBody();

        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonArray array = reader.readArray();

        for (JsonValue item : array) {
            JsonObject object = item.asJsonObject();

            String question = object.getJsonObject("question").getString("text");
            String correctAnswer = object.getString("correctAnswer");

            List<String> allAnswers = new ArrayList<>();
           
            JsonArray arrayAnswers = object.getJsonArray("incorrectAnswers");
            for (JsonValue answer : arrayAnswers) {
                String incorrectAnswer = answer.toString().replaceAll("^\"|\"$", "").replaceAll("\\\\", "");
                allAnswers.add(incorrectAnswer);
            }
            allAnswers.add(correctAnswer);
            Collections.shuffle(allAnswers);

            QuizQuestion quizQuestion = new QuizQuestion(question, correctAnswer, allAnswers);
            quizList.add(quizQuestion);
            Collections.shuffle(quizList);

            if (quizList.size() == limit) {
                break;
            }
        }

        return quizList;
    }

}
