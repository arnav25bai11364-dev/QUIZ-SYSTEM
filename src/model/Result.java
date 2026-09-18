package model;

public class Result {
    private String username;
    private String quizTitle;
    private int score;
    private int total;

    public Result(String username, String quizTitle, int score, int total) {
        this.username = username;
        this.quizTitle = quizTitle;
        this.score = score;
        this.total = total;
    }

    public String getUsername() { return username; }
    public String getQuizTitle() { return quizTitle; }
    public int getScore() { return score; }
    public int getTotal() { return total; }

    @Override
    public String toString() {
        return username + " | " + quizTitle + " | " + score + "/" + total;
    }
}
