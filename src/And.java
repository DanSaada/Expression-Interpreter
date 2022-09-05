
/**
 * @author Dan Saada
 * @version ass4
 * @since 2022/04/18
 */

import java.util.Map;

/**
 * This class holds all And-related logical methods.
 */
public class And extends BinaryExpression implements Expression {

    //constructor

    /**
     * This constructor gets two expression, and sets a new expression.
     *
     * @param ex1
     * @param ex2
     */
    public And(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    /**
     * This method evaluate the expression using the variable values provided in the assignment,
     * and return the result.
     * <p>
     * The logic behind this method is "And" logic:
     * T And T = T
     * T And F = F
     * F And T = F
     * F And F = F
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
            return this.getExpression1().evaluate(assignment) && this.getExpression2().evaluate(assignment);
        } catch (Exception e) {
            System.out.println("error in And evaluation");
        }
        return null;
    }

    /**
     * This method evaluate the expression and return the logic result.
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     * @exception Exception If the expression contains a non valid context an exception is thrown.
     * @return true/false according to the logical result according to the "And" logic
     */
    @Override
    public Boolean evaluate() throws Exception {
        try {
            return this.getExpression1().evaluate() && this.getExpression2().evaluate();
        } catch (Exception e) {
            System.out.println("error in And evaluation");
        }
        return null;
    }

    /**
     * This method converts the expression to a nice string representation of the expression.
     * <p>
     * The "And" logical operator agreed sign is "&".
     * </p>
     *
     * @return a nice string representation of the expression.
     */
    @Override
    public String toString() {
        return "(" + this.getExpression1().toString() + " & " + this.getExpression2().toString() + ")";
    }


    /**
     * This method returns a new expression in which all occurrences of the variable var
     * are replaced with the provided expression (Does not modify the current expression).
     * <p>
     * The new expression that returned depends on the "And" logical operator.
     * </p>
     *
     * @param var
     * @param expression
     * @return a new expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new And(getExpression1().assign(var, expression), getExpression2().assign(var, expression));
    }

    /**
     * This method returns the expression tree resulting from converting all the operations to the
     * logical Nand operation.
     * <p>
     * The new expression that returned depends and displays the "And" logical operator with the "Nand" one.
     * </p>
     *
     * @return a new expression.
     */
    @Override
    public Expression nandify() {
        return new Nand(new Nand(getExpression1().nandify(), getExpression2().nandify()),
                new Nand(getExpression1().nandify(), getExpression2().nandify()));
    }

    /**
     * This method returns the expression tree resulting from converting all the operations to the
     * logical Nor operation.
     * <p>
     * The new expression that returned depends and displays the "And" logical operator with the "Nor" one.
     * </p>
     *
     * @return a new expression.
     */
    @Override
    public Expression norify() {
        return new Nor(new Nor(getExpression1().norify(), getExpression1().norify()),
                new Nor(getExpression2().norify(), getExpression2().norify()));
    }

    /**
     * This method returns a simplified version of the current expression.
     * <p>
     * The new expression that returned depends on the "And" logical operator.
     * Among the simplifications made in the method:
     * * x & 1 = x
     * * x & 0 = 0
     * * x & x = x
     * </p>
     *
     * @return a new expression.
     */
    @Override
    public Expression simplify() {
        //x & 1 = x
        if (getExpression2().simplify().toString().equals("T")) {
            return getExpression1().simplify();
        }
        //1 & x = x
        if (getExpression1().simplify().toString().equals("T")) {
            return getExpression2().simplify();
        }
        //x & 0 = 0
        if (getExpression1().simplify().toString().equals("F") || getExpression2().simplify().toString().equals("F")) {
            return new Val(false).simplify();
        }
        //x & x = x
        if (getExpression1().simplify().toString().equals(getExpression2().simplify().toString())) {
            return getExpression1().simplify();
        }
        //can't simplify
        return new And(getExpression1().simplify(), getExpression2().simplify());
    }
}
