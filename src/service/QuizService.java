package service;

import model.Question;
import model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizService {
    private final List<Quiz> quizzes = new ArrayList<>();

    public QuizService() {
        loadSampleQuiz();
    }

    private void loadSampleQuiz() {
        Quiz quiz = new Quiz(1, "Java Fundamentals", 60);

        quiz.addQuestion(new Question(
                1, "Which keyword is used to inherit a class in Java?",
                "implements", "extends", "inherits", "super", 'B'));

        quiz.addQuestion(new Question(
                2, "Which collection does not allow duplicate elements?",
                "ArrayList", "LinkedList", "HashSet", "Vector", 'C'));

        quiz.addQuestion(new Question(
                3, "Which method is the entry point of a Java application?",
                "start()", "run()", "main()", "init()", 'C'));

        quiz.addQuestion(new Question(
                4, "Which concept allows the same method name with different parameters?",
                "Inheritance", "Encapsulation", "Overloading", "Abstraction", 'C'));

        quiz.addQuestion(new Question(
                5, "Which keyword is used to handle an exception?",
                "catch", "final", "static", "this", 'A'));

        quizzes.add(quiz);
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public Quiz findQuiz(int id) {
        for (Quiz quiz : quizzes) {
            if (quiz.getId() == id) return quiz;
        }
        return null;
    }
}
