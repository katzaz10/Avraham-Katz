package math.shell.linearAlgebra.calculator.exceptions;

/**
 *	This exceptions is thrown in case where matrix operation is undefined
 *	@author Avraham Katz
 *	@version 1.1
 */

public class UndefinedSolutionException extends Exception
{
    public UndefinedSolutionException(String message)
    {
        super(message);
    }
}