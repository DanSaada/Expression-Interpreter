// 208968560 Dan Saada
/**
 * @author Dan Saada
 * @version ass4
 * @since 2022/04/18
 */

import java.util.Map;

/**
 * This class holds all Xor-related logical methods.
 */
public class Xor extends BinaryExpression implements Expression {

    //constructor

    /**
     * This constructor gets two expression, and sets a new expression.
     *
     * @param ex1
     * @param ex2
     */
    public Xor(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    /**
     * This method evaluate the expression using the variable values provided in the assignment,
     * and return the result.
     * <p>
     * The logic behind this method is "Xor" logic:
     * T Xor T = F
     * T Xor F = T
     * F Xor T = T
     * F Xor F = F
     * </p>
     *
     * @param assignment a map of strings and booleans (each string has a true/false value).
     * @return true or false according to the table of truth shown above, and null in case of exception.
     * @throws Exception If the expression contains a variable which is not in the assignment,
     *                   an exception is thrown.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            return this.getExpression1().evaluate(assignment) && !this.getExpression2().evaluate(assignment)
                    || !this.getExpression1().evaluate(assignment) && this.getExpression2().evaluate(assignment);
        } catch (Exception e) {
            System.out.println("error in Xor evaluation");
        }
        return null;
    }

    /**
     * This method evaluate the expression and return the logic result.
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     * @exception Exception If the expression contains a non valid context an exception is thrown.
     * @return true/false according to the logical result according to the "Xor" logic
     */
    @Override
    public Boolean evaluate() throws Exception {
        try {
            return this.getExpression1().evaluate() && !this.getExpression2().evaluate()
                    || !this.getExpression1().evaluate() && this.getExpression2().evaluate();
        } catch (Exception e) {
            System.out.println("error in Xor evaluation");
        }
        return null;
    }

    /**
     * This method converts the expression to a nice string representation of the expression.
     * <p>
     * The "Xor" logical operator agreed sign is "^".
     * </p>
     *
     * @return a nice string representation of the expression.
     */
    @Override
    public String toString() {
        return "(" + this.getExpression1().toString() + " ^ " + this.getExpression2().toString() + ")";
    }

    /**
     * This method returns a new expression in which all occurrences of the variable var
     * are replaced with the provided expression (Does not modify the current expression).
     * <p>
     * The new expression that returned depends on the "Xor" logical operator.
     * </p>
     *
     * @param var
     * @param expression
     * @return a new expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Xor(getExpression1().assign(var, expression), getExpression2().assign(var, expression));
    }

    /**
     * This method returns the expression tree resulting from converting all the operations to the
     * logical Nand operation.
     * <p>
     * The new expression that returned depends and displays the "Xor" logical operator with the "Nand" one.
     * </p>
     *
     * @return a new expression.
     */
    @Override
    public Expression nandify() {
        return new Nand(new Nand(getExpression1().nandify(),
                new Nand(getExpression1().nandify(), getExpression2().nandify())),
                new Nand(getExpression2().nandify(), new Nand(getExpression1().nandify(), getExpression2().nandify())));
    }

    /**
     * This method returns the expression tree resulting from converting all the operations to the
     * logical Nor operation.
     * <p>
     * The new expression that returned depends and displays the "Xor" logical operator with the "Nor" one.
     * </p>
     *
     * @return a new expression.
     */
    @Override
    public Expression norify() {
        return new Nor(new Nor(new Nor(getExpression1().norify(), getExpression1().norify()),
                new Nor(getExpression2().norify(), getExpression2().norify())),
                new Nor(getExpression1().norify(), getExpression2().norify()));
    }

    /**
     * This method returns a simplified version of the current expression.
     * <p>
     * The new expression that returned depends on the "Xor" logical operator.
     * Among the simplifications made in the method:
     * * x ^ 1 = ~(x)
     * * x ^ 0 = x
     * * x ^ x = 0
     * </p>
     *
     * @return a new expression.
     */
    @Override
    public Expression simplify() {
        //x ^ 1 = ~(x)
        if (getExpression2().simplify().toString().equals("T")) {
            return new Not(getExpression1()).simplify();
        }
        //1 ^ x = ~(x)
        if (getExpression1().simplify().toString().equals("T")) {
            return new Not(getExpression2()).simplify();
        }
        //x ^ 0 = x
        if (getExpression2().simplify().toString().equals("F")) {
            return getExpression1().simplify();
        }
        //0 ^ x = x
        if (getExpression1().simplify().toString().equals("F")) {
            return getExpression2().simplify();
        }
        //x ^ x = 0
        if (getExpression1().simplify().toString().equals(getExpression2().simplify().toString())) {
            return new Val(false).simplify();
        }
        //can't simplify
        return new Xor(getExpression1().simplify(), getExpression2().simplify());
    }
}
