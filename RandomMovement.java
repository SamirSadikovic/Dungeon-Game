import java.util.Random;
import java.util.HashSet;

public class RandomMovement {
    
    public static Character get(HashSet<Character> legalMoves) {
        Random r = new Random();
        return (Character) legalMoves.toArray()[r.nextInt(legalMoves.size())];
    }
}