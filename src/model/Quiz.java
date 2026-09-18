package model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private int id;
    private String title;
    private int timeLimitSeconds;
    private final List<Question> questions = new ArrayList<>();

    public Quiz(int id, String title, int timeLimitSeconds) {
        this.id = id;
        this.title = title;
        this.timeLimitSeconds = timeLimitSeconds;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getTimeLimitSeconds() { return timeLimitSeconds; }
    public List<Question> getQuestions() { return questions; }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}
