package service;

import model.Result;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResultService {
    private final List<Result> results = new ArrayList<>();

    public void addResult(Result result) {
        results.add(result);
    }

    public void showMyResults(String username) {
        System.out.println("\n--- My Results ---");
        boolean found = false;

        for (Result result : results) {
            if (result.getUsername().equals(username)) {
                System.out.println(result);
                found = true;
            }
        }

        if (!found) System.out.println("No results found.");
    }

    public void showLeaderboard() {
        System.out.println("\n--- Leaderboard ---");

        List<Result> sorted = new ArrayList<>(results);
        sorted.sort(Comparator.comparingInt(Result::getScore).reversed());

        if (sorted.isEmpty()) {
            System.out.println("No results available.");
            return;
        }

        int rank = 1;
        for (Result result : sorted) {
            System.out.println(rank++ + ". " + result);
        }
    }
}
