package math.shell.linearAlgebra.calculator;

import java.math.BigDecimal;

/**
 *	A container with one slot that holds the last calculator output of type BIGDECIMAL
 *	@author Avraham Katz
 *	@version 1.0
 */

public class LastBigDecimalOutput
{
    /**
     *	A singleton pattern that acts as the only container of last calculator output of type BIGDECIMAL
     */
    public static final LastBigDecimalOutput Singleton = new LastBigDecimalOutput();

    BigDecimal[] lastOutput = new BigDecimal[1];

    private LastBigDecimalOutput()
    {

    }


    /**
     *	Adds a BigDecimal to the container
     *	@param bigDecimalAdding BigDecimal adding to container
     */
    public void add(BigDecimal bigDecimalAdding)
    {
        this.lastOutput[0] = bigDecimalAdding;
    }


    /**
     *	Gets the BigDecimal stored in the container
     *	@return BigDecimal The BigDecimal that was stored
     */
    public BigDecimal getLastOutput()
    {
        return this.lastOutput[0];
    }
}

