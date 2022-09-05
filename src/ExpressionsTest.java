// 208968560 Dan Saada
/**
 * @author Dan Saada
 * @version ass4
 * @since 2022/04/18
 */

import java.util.Map;
import java.util.TreeMap;

/**
 * This class tests the program's functionality.
 */
public class ExpressionsTest {

    /**
     * This is the main method which creates a three variables expression and uses the program's functionality on it.
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Expression expression = new And(new Or(new And(new Var("x"), new Var("y")), new Val(true)),
                new Var("z"));
        Map<String, Boolean> assignment = new TreeMap<>();
        assignment.put("x", true);
        assignment.put("y", false);
        assignment.put("z", false);
        System.out.println(expression);
        System.out.println(expression.evaluate(assignment));
        System.out.println(expression.nandify());
        System.out.println(expression.norify());
        System.out.println(expression.simplify());
    }
}