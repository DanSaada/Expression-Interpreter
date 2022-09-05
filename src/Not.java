
/**
 * @author Dan Saada
 * @version ass4
 * @since 2022/04/18
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class holds all Not-related logical methods.
 */
public class Not extends UnaryExpression implements Expression {

    //constructor

    /**
     * This constructor gets an expression, and sets a new expression.
     *
     * @param ex1
     */
    public Not(Expression ex1) {
        super(ex1);
    }

    /**
     * This method evaluate the expression using the variable value provided in the assignment,
     * and return the result.
     * <p>
     * The logic behind this method is "Not" logic:
     * Not T = F
     * Not F = T
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
            return !this.getExpression1().evaluate(assignment);
        } catch (Exception e) {
            System.out.println("error in Not evaluation");
        }
        return null;
    }

    /**
     * This method evaluate the expression and return the logic result.
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     * @exception Exception If the expression contains a non valid context an exception is thrown.
     * @return true/false according to the logical result according to the "Not" logic
     */
    @Override
    public Boolean evaluate() throws Exception {
        try {
            return !this.getExpression1().evaluate();
        } catch (Exception e) {
            System.out.println("error in Not evaluation");
        }
        return null;
    }

    /**
     * This method converts the expression to a nice string representation of the expression.
     * <p>
     * The "Not" logical operator agreed sign is "~".
     * </p>
     *
     * @return a nice string representation of the expression.
     */
    @Override
    public String toString() {
        return "~(" + this.getExpression1().toString() + ")";
    }

    /**
     * This method returns a list of the variables in the expression.
     * <p>
     *     The implementation of this method for the unary expressions is different from the binary
     *     one and that is why it is written in this class.
     * </p>
     * @return a list of the variables in the expression.
     */
    @Override
    public List<String> getVariables() {
        List<String> variables = new ArrayList<>();
        if (this.getExpression1() != null) {
            variables.addAll(this.getExpression1().getVariables());
        }
        return variables;
    }

    /**
     * This method returns a new expression in which all occurrences of the variable var
     * are replaced with the provided expression (Does not modify the current expression).
     * <p>
     * The new expression that returned depends on the "Not" logical operator.
     * </p>
     *
     * @param var
     * @param expression
     * @return a new expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Not(getExpression1().assign(var, expression));
    }

    /**
     * This method returns the expression tree resulting from converting all the operations to the
     * logical Nand operation.
     * <p>
     * The new expression that returned depends and displays the "Not" logical operator with the "Nand" one.
     * </p>
     *
     * @return a new expression.
     */
    @Override
    public Expression nandify() {
        return new Nand(getExpression1().nandify(), getExpression1().nandify());
    }

    /**
     * This method returns the expression tree resulting from converting all the operations to the
     * logical Nor operation.
     * <p>
     * The new expression that returned depends and displays the "Not" logical operator with the "Nor" one.
     * </p>
     *
     * @return a new expression.
     */
    @Override
    public Expression norify() {
        return new Nor(getExpression1().norify(), getExpression1().norify());
    }

    /**
     * This method returns a simplified version of the current expression.
     * <p>
     * The new expression that returned depends on the "Not" logical operator.
     * Among the simplifications made in the method:
     * * ~1 = 0
     * * ~0 = 1
     * </p>
     *
     * @return a new expression.
     */
    @Override
    public Expression simplify() {
        //~1 = 0
        if (getExpression1().simplify().toString().equals("T")) {
            return new Val(false).simplify();
        }
        //~0 = 1
        if (getExpression1().simplify().toString().equals("F")) {
            return new Val(true).simplify();
        }
        //can't simplify
        return new Not(getExpression1().simplify());
    }
}
