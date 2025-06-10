import org.mindrot.jbcrypt.BCrypt;

public class HashPasswords {
    public static void main(String[] args) {
        String[] passwords = {"admin", "bs1", "lt1"};

        for (String plain : passwords) {
            String hashed = BCrypt.hashpw(plain, BCrypt.gensalt());
            System.out.println("Plain: " + plain);
            System.out.println("Hashed: " + hashed);
            System.out.println();
        }
    }
}
