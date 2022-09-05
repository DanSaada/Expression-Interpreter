// 208968560 Dan Saada
/**
 * @author Dan Saada
 * @version ass4
 * @since 2022/04/18
 */

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds all Binary Expressions-related logical methods.
 */
public abstract class BinaryExpression extends BaseExpression implements Expression {
    private Expression ex1;
    private Expression ex2;

    //constructor
    /**
     * This constructor gets two expression, and sets them as the expression vars.
     *
     * @param ex1
     * @param ex2
     */
    protected BinaryExpression(Expression ex1, Expression ex2) {
        setEx1(ex1);
        setEx2(ex2);
    }

    /**
     * This method is a "get method".
     *
     * @return expression 1
     */
    protected Expression getExpression1() {
        return this.ex1;
    }

    /**
     * This method gets an expression and sets it.
     *
     * @param ex1
     */
    public void setEx1(Expression ex1) {
        this.ex1 = ex1;
    }

    /**
     * This method gets an expression and sets it.
     *
     * @param ex2
     */
    public void setEx2(Expression ex2) {
        this.ex2 = ex2;
    }

    /**
     * This method is a "get method".
     *
     * @return expression 2
     */
    protected Expression getExpression2() {
        return this.ex2;
    }

    /**
     * This method returns a list of the variables in the expression.
     * <p>
     *     The implementation of this method for the binary expressions is similar and that is why
     *     it is written in this, while all the sub binary expressions are inherit from it.
     * </p>
     * @return a list of the variables in the expression.
     */
    @Override
    public List<String> getVariables() {
        List<String> variables = new ArrayList<>();
        //adding the variables to the list.
        if (this.getExpression1() != null) {
            variables.addAll(this.getExpression1().getVariables());
        }
        if (this.getExpression2() != null) {
            variables.addAll(this.getExpression2().getVariables());
        }
        //Make a copy of variables before iterating over them.
        List<String> tempVariables = new ArrayList<>();
        for (int i = 0; i < variables.size(); i++) {
            if (!tempVariables.contains(variables.get(i))) {
                tempVariables.add(variables.get(i));
            }
        }
        return tempVariables;
    }
}
