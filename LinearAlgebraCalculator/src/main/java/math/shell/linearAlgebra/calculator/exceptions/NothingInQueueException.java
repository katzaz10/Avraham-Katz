package math.shell.linearAlgebra.calculator.exceptions;

/**
 *	This runtime exceptions is thrown in case where user tries to do operation on last output, but no operations have been performed and nothing in calculator queue
 *	@author Avraham Katz
 *	@version 1.1
 */


public class NothingInQueueException extends Exception
{
    public NothingInQueueException(String message)
    {
        super(message);
    }
}