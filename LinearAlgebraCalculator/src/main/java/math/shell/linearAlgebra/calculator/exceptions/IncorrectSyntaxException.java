package math.shell.linearAlgebra.calculator.exceptions;

/**
 *	This runtime exceptions is thrown in case where user inputs equation that parser cannot handle
 *	@author Avraham Katz
 *	@version 1.1
 */

public class IncorrectSyntaxException extends RuntimeException
{
    public IncorrectSyntaxException(String message)
    {
        super(message);
    }
}