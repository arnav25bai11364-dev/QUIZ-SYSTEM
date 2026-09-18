import model.Question;
import model.Quiz;
import model.Result;
import model.User;
import service.AuthService;
import service.QuizService;
import service.ResultService;
import util.TimerThread;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AuthService authService = new AuthService();
    private static final QuizService quizService = new QuizService();
    private static final ResultService resultService = new ResultService();

    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("     ONLINE QUIZ MANAGEMENT SYSTEM");
        System.out.println("======================================");

        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Register Student");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> login();
                case "2" -> register();
                case "3" -> {
                    System.out.println("Thank you for using the system.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = authService.login(username, password);

        if (user == null) {
            System.out.println("Invalid username or password.");
            return;
        }

        System.out.println("Login successful. Role: " + user.getRole());

        if (user.getRole().equals("ADMIN")) {
            adminMenu(user);
        } else {
            studentMenu(user);
        }
    }

    private static void register() {
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (authService.registerStudent(username, password)) {
            System.out.println("Registration successful.");
        } else {
            System.out.println("Registration failed. Username may already exist or fields are empty.");
        }
    }

    private static void adminMenu(User user) {
        while (true) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. View quizzes");
            System.out.println("2. View leaderboard");
            System.out.println("3. Logout");
            System.out.print("Choose: ");

            switch (scanner.nextLine()) {
                case "1" -> showQuizzes();
                case "2" -> resultService.showLeaderboard();
                case "3" -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void studentMenu(User user) {
        while (true) {
            System.out.println("\n--- STUDENT MENU ---");
            System.out.println("1. View quizzes");
            System.out.println("2. Take quiz");
            System.out.println("3. My results");
            System.out.println("4. Leaderboard");
            System.out.println("5. Logout");
            System.out.print("Choose: ");

            switch (scanner.nextLine()) {
                case "1" -> showQuizzes();
                case "2" -> takeQuiz(user);
                case "3" -> resultService.showMyResults(user.getUsername());
                case "4" -> resultService.showLeaderboard();
                case "5" -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void showQuizzes() {
        System.out.println("\n--- AVAILABLE QUIZZES ---");
        for (Quiz quiz : quizService.getQuizzes()) {
            System.out.println(
                    quiz.getId() + ". " + quiz.getTitle()
                    + " | Questions: " + quiz.getQuestions().size()
                    + " | Time: " + quiz.getTimeLimitSeconds() + " sec"
            );
        }
    }

    private static void takeQuiz(User user) {
        showQuizzes();

        System.out.print("Enter quiz ID: ");
        int id;

        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return;
        }

        Quiz quiz = quizService.findQuiz(id);

        if (quiz == null) {
            System.out.println("Quiz not found.");
            return;
        }

        System.out.println("\nStarting: " + quiz.getTitle());
        System.out.println("You have " + quiz.getTimeLimitSeconds() + " seconds.");

        TimerThread timer = new TimerThread(quiz.getTimeLimitSeconds());
        timer.start();

        int score = 0;

        for (Question question : quiz.getQuestions()) {
            if (timer.isTimeUp()) {
                System.out.println("\nTime is up!");
                break;
            }

            question.display();
            System.out.print("Your answer (A/B/C/D): ");
            String answer = scanner.nextLine().trim();

            if (answer.length() == 1 &&
                    question.isCorrect(answer.charAt(0))) {
                score++;
            }
        }

        timer.stopTimer();

        Result result = new Result(
                user.getUsername(),
                quiz.getTitle(),
                score,
                quiz.getQuestions().size()
        );

        resultService.addResult(result);

        System.out.println("\n========== RESULT ==========");
        System.out.println("Student : " + user.getUsername());
        System.out.println("Quiz    : " + quiz.getTitle());
        System.out.println("Score   : " + score + "/" + quiz.getQuestions().size());
        System.out.println("============================");
    }
}
