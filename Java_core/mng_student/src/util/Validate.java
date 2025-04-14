package util;

public class Validate {
    public static boolean isValidAge(int age) {
        return age >= 5 && age <= 50;
    }

    public static boolean isValidScore(int score) {
        return score >= 0 && score <= 100;
    }
}
