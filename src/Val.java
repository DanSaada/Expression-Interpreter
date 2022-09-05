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
 * This class holds all Val-related methods.
 */
public class Val implements Expression {
    private Boolean bool;

    //constructor

    /**
     * This constructor gets a boolean, and.
     *
     * @param bool
     */
    public Val(Boolean bool) {
        this.bool = bool;
    }

    //return this val
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.bool;
    }

    //same
    @Override
    public Boolean evaluate() throws Exception {
        return this.bool;
    }

    /**
     * This method converts the expression to a nice string representation of the expression.
     * <p>
     * The True logical operator agreed sign is "T".
     * The False logical operator agreed sign is "F".
     * </p>
     *
     * @return a nice string representation of the expression.
     */
    @Override
    public String toString() {
        if (this.bool) {
            return "T";
        } else {
            return "F";
        }
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public Expression assign(String var, Expression expression) {
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
