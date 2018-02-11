package math.shell.linearAlgebra.calculator;

/**
 *	This runtime exception is thrown in case where user tries to do operation that requires a matrix on last output, but last output not of type OutputType.MATRIX
 *	@author Avraham Katz
 *	@version 1.0
 */

public class NoMatrixInQueueException extends Exception
{
    public NoMatrixInQueueException(String message)
    {
        super(message);
    }
}