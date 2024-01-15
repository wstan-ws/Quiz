package personal.project.TriviaWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import personal.project.TriviaWeb.model.Highscore;
import personal.project.TriviaWeb.model.QuizForm;
import personal.project.TriviaWeb.model.QuizQuestion;
import personal.project.TriviaWeb.service.TriviaService;

@Controller
@RequestMapping
public class TriviaController {

    @Autowired
    TriviaService triviaSvc;

    @Autowired
    TriviaAPIController triviaAPIController;

    @GetMapping(path = {"/", "/home"})
    public String getHome() {

        return "home";
    }

    @GetMapping(path = "/selection")
    public String getSelection(Model model) {

        model.addAttribute("quizForm", new QuizForm());

        return "selection";
    }

    @PostMapping(path = "/quiz")
    public String postQuiz(@Valid @ModelAttribute("quizForm") QuizForm quizForm, BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            return "selection";
        }

        Integer limit = quizForm.getNumberOfQuestions();
        String difficulty = quizForm.getDifficulty();
        String[] categories = quizForm.getCategories();
        String categoryList = triviaSvc.getCategories(categories);

        List<QuizQuestion> quizList = triviaAPIController.getQuestions(limit, difficulty, categoryList);

        model.addAttribute("currentQuestion", triviaSvc.getCurrentQuestion(quizList));
        model.addAttribute("score", triviaSvc.getScore());

        Integer questionNo = triviaSvc.getQuestionNo();

        model.addAttribute("questionNo", questionNo);

        session.setAttribute("quizList", quizList);
        session.setAttribute("difficulty", difficulty);
        session.setAttribute("questionNo", limit);
        
        return "quiz";
    }

    @PostMapping(path = "/next")
    public String checkAnswer(@RequestParam String answer, HttpSession session) {

        List<QuizQuestion> quizList = (List<QuizQuestion>) session.getAttribute("quizList");
        QuizQuestion currentQuestion = triviaSvc.getCurrentQuestion(quizList);
        if (answer.equals(currentQuestion.getCorrectAnswer())) {
            triviaSvc.correctAnswer();
        }

        triviaSvc.moveToNextQuestion();

        return "redirect:/quiz";
    }

    @GetMapping(path = "/quiz")
    public String getQuiz(HttpSession session, Model model) {

        model.addAttribute("score", triviaSvc.getScore());
        model.addAttribute("questionNo", triviaSvc.getQuestionNo());

        List<QuizQuestion> quizList = (List<QuizQuestion>) session.getAttribute("quizList");

        if (triviaSvc.hasNextQuestion(quizList)) {
            model.addAttribute("currentQuestion", triviaSvc.getCurrentQuestion(quizList));
            return "quiz";
        } else {
            model.addAttribute("highscoreForm", new Highscore());
            model.addAttribute("score", triviaSvc.getScore());
            session.setAttribute("highscore", triviaSvc.getScore());

            return "highscore";
        }
    }

    @PostMapping(path = "/highscore")
    public String postHighscore(@Valid @ModelAttribute("highscoreForm") Highscore highscore, BindingResult result, HttpSession session, Model model) {

        if (result.hasErrors()) {
            Integer score = (Integer)session.getAttribute("highscore");
            model.addAttribute("score", score);
            return "highscore";
        }

        String name = highscore.getName();
        Integer score = highscore.getHighscore();
        String difficulty = session.getAttribute("difficulty").toString().toLowerCase();
        Integer questionNo = (Integer) session.getAttribute("questionNo");
        triviaSvc.saveHighscore(name, score, difficulty, questionNo);

        List<Highscore> highscoreList = triviaSvc.getAllHighscore(difficulty, questionNo);
        model.addAttribute("leaderboard", highscoreList);

        difficulty = difficulty.substring(0, 1).toUpperCase() + difficulty.substring(1);
        String title = String.format("Leaderboard for %s (%d Questions)", difficulty, questionNo);
        model.addAttribute("title", title);

        triviaSvc.resetIndex();
        session.invalidate();

        return "/leaderboard";
    }

    @GetMapping(path = "/highscore")
    public String getLeaderboard(Model model) {

        String difficulty = "easy";
        Integer questionNo = 10;

        List<Highscore> highscoreList = triviaSvc.getAllHighscore(difficulty, questionNo);
        model.addAttribute("leaderboard", highscoreList);

        difficulty = difficulty.substring(0, 1).toUpperCase() + difficulty.substring(1);
        String title = String.format("Leaderboard for %s (%d Questions)", difficulty, questionNo);
        model.addAttribute("title", title);

        return "/leaderboard";
    }
}
