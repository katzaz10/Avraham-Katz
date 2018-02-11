package math.shell.linearAlgebra.calculator;

/**
 *	This exception is thrown in case where matrix operation is undefined
 *	@author Avraham Katz
 *	@version 1.0
 */

public class UndefinedSolutionException extends Exception
{
    public UndefinedSolutionException(String message)
    {
        super(message);
    }
}