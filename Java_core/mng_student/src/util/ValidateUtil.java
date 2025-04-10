package util;

public class ValidateUtil {
    public static boolean isValidAge(int age) {
        return age >= 18 && age <= 100;
    }

    public static boolean isValidScore(int score) {
        return score >= 0 && score <= 10;
    } 
}
