package math.shell.linearAlgebra.calculator.containers;

import java.math.BigDecimal;

/**
 *	A containers with one slot that holds the last calculator output of type BIGDECIMAL
 *	@author Avraham Katz
 *	@version 1.1
 */

public class LastBigDecimalOutput
{
    /**
     *	A singleton pattern that acts as the only containers of last calculator output of type BIGDECIMAL
     */
    public static final LastBigDecimalOutput Singleton = new LastBigDecimalOutput();

    BigDecimal[] lastOutput = new BigDecimal[1];

    private LastBigDecimalOutput()
    {

    }


    /**
     *	Adds a BigDecimal to the containers
     *	@param bigDecimalAdding BigDecimal adding to containers
     */
    public void add(BigDecimal bigDecimalAdding)
    {
        this.lastOutput[0] = bigDecimalAdding;
    }


    /**
     *	Gets the BigDecimal stored in the containers
     *	@return BigDecimal The BigDecimal that was stored
     */
    public BigDecimal getLastOutput()
    {
        return this.lastOutput[0];
    }
}

