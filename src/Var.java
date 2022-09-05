// 208968560 Dan Saada
/**
 * @author Dan Saada
 * @version ass4
 * @since 2022/04/18
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class holds all Var-related methods.
 */
public class Var implements Expression {
    private String string;

    //constructor

    /**
     * This constructor gets a string, and.
     *
     * @param string
     */
    public Var(String string) {
        this.string = string;
    }

    /**
     * This method evaluate the expression using the variable values provided in the assignment,
     * and return the result.
     *
     * @param assignment a map of strings and booleans (each string has a true/false value).
     * @return true if the variable exists in the Map, otherwise return false.
     * @throws Exception If the expression contains a variable which is not in the assignment,
     *                   an exception is thrown.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            if (assignment.get(this.string)) {
                return true;
            } else if (!assignment.get(this.string)) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("error in Var evaluation");
        }
        return null;
    }

    @Override
    public Boolean evaluate() throws Exception {
        return null;
    }

    @Override
    public String toString() {
        return this.string;
    }

    @Override
    public List<String> getVariables() {
        List<String> variables = new ArrayList<>();
        variables.add(this.string);
        return variables;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.string.equals(var)) {
            return expression;
        }
        return this;
    }

    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
        return this;
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
