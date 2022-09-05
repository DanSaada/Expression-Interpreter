// 208968560 Dan Saada
/**
 * @author Dan Saada
 * @version ass4
 * @since 2022/04/18
 */

/**
 * This class holds all Unary Expressions-related logical methods.
 */
public abstract class UnaryExpression extends BaseExpression implements Expression {
    private Expression ex1;

    //constructor

    /**
     * This constructor gets an expression, and sets it as this expression.
     *
     * @param ex1
     */
    protected UnaryExpression(Expression ex1) {
        setEx1(ex1);
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
}
