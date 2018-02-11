package math.shell.linearAlgebra.calculator;

/**
 *	This runtime exception is thrown in case where user inputs equation that parser cannot handle
 *	@author Avraham Katz
 *	@version 1.0
 */

public class IncorrectSyntaxException extends RuntimeException
{
    public IncorrectSyntaxException(String message)
    {
        super(message);
    }
}