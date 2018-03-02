package math.shell.linearAlgebra.calculator.containers;

import math.shell.linearAlgebra.calculator.exceptions.ErrorMessages;
import math.shell.linearAlgebra.calculator.exceptions.NoMatrixInQueueException;
import math.shell.linearAlgebra.calculator.exceptions.NothingInQueueException;

/**
 *	A containers with one slot that keeps track of the last output type of the calculator
 *	@author Avraham Katz
 *	@version 1.1
 */

public class LastOutputType
{
    /**
     *	Last type outputted by the calculator, starts at NULL, and switches off between MATRIX and BIGDECIMAL
     */
    public enum OutputType
    {
        NULL, MATRIX, BIGDECIMAL
    }

    private OutputType lastOutput = OutputType.NULL;

    /**
     *	A singleton pattern that acts as the only containers of last output of type MATRIX
     */
    public static final LastOutputType Singleton = new LastOutputType();


    /**
     * Gets the last output type
     * @return  Last output type
     */
    public OutputType getLastOutputType()
    {
        return this.lastOutput;
    }


    /**
     *	Checks to see if any operations that store a Matrix or BigDecimal in the queue have been performed yet
     *	@throws NothingInQueueException Thrown if containers are empty and attempting to use last output
     */
    public void nullStateCheck() throws NothingInQueueException
    {
        if (this.lastOutput == OutputType.NULL)
        {
            throw new NothingInQueueException(ErrorMessages.nullState);
        }
    }


    /**
     *	Checks to see if the last output type is of type MATRIX
     *	@throws NoMatrixInQueueException Thrown if matrix was not last output and attempting to use last output
     */
    public void matrixTypeCheck() throws NoMatrixInQueueException
    {
        if (this.lastOutput != OutputType.MATRIX)
        {
            throw new NoMatrixInQueueException(ErrorMessages.nonMatrix);
        }
    }


    /**
     * Sets the last output type to MATRIX status
     */
    public void setLastOutputMATRIX()
    {
        this.lastOutput = OutputType.MATRIX;
    }


    /**
     * Sets the last output type to BIGDECIMAL status
     */
    public void setLastOutputBIGDECIMAL()
    {
        this.lastOutput = OutputType.BIGDECIMAL;
    }
}
