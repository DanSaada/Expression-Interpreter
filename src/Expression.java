
/**
 * @author Dan Saada
 * @version ass4
 * @since 2022/04/18
 */

import java.util.List;
import java.util.Map;

/**
 * This interface requires other classes that declare that they are implementing it to implement
 * the methods underlying it.
 */
public interface Expression {

    /**
     * This method evaluate the expression using the variable values provided in the assignment,
     * and return the result.
     * <p>
     *     The method will be implemented in any class that uses this interface.
     * </p>
     * @exception Exception If the expression contains a variable which is not in the assignment,
     * an exception is thrown.
     * @param assignment a map of strings and booleans (each string has a true/false value).
     * @return true/false according to the logical result.
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * This method evaluate the expression and return the logic result.
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     * <p>
     *     The method will be implemented in any class that uses this interface.
     * </p>
     * @exception Exception If the expression contains a non valid context an exception is thrown.
     * @return true/false according to the logical result.
     */
    Boolean evaluate() throws Exception;

    /**
     * This method returns a list of the variables in the expression.
     * The implementation of this method for the binary expressions is similar and that is why it is written
     * in the BinaryExpression class once, while all the sub binary expressions are inherit from it.
     * <p>
     *     The method will be implemented in any class that uses this interface.
     * </p>
     * @return a list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * This method returns a nice string representation of the expression,
     * when each logical operator has its own agreed sign.
     * <p>
     *     The method will be implemented in any class that uses this interface.
     * </p>
     * @return a nice string representation of the expression.
     */
    String toString();

    /**
     * This method returns a new expression in which all occurrences of the variable var
     * are replaced with the provided expression (Does not modify the current expression).
     * The new expression that returned depends on the logical operator through which it is being called.
     * <p>
     *     The method will be implemented in any class that uses this interface.
     * </p>
     * @param var
     * @param expression
     * @return a new expression.
     */
    Expression assign(String var, Expression expression);

    /**
     * This method returns the expression tree resulting from converting all the operations to the
     * logical Nand operation.
     * The new expression that returned depends on the logical operator through which it is being called.
     * <p>
     *     The method will be implemented in any class that uses this interface.
     * </p>
     * @return a new expression.
     */
    Expression nandify();

    /**
     * This method returns the expression tree resulting from converting all the operations to the
     * logical Nor operation.
     * The new expression that returned depends on the logical operator through which it is being called.
     * <p>
     *     The method will be implemented in any class that uses this interface.
     * </p>
     * @return a new expression.
     */
    Expression norify();

    /**
     * This method returns a simplified version of the current expression.
     * The new expression that returned depends on the logical operator through which it is being called.
     * <p>
     *     The method will be implemented in any class that uses this interface.
     * </p>
     * @return a new expression.
     */
    Expression simplify();
}
