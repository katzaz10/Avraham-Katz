package math.shell.linearAlgebra.calculator;

/**
 *	This exception is thrown in case where user tries to do operation that requires a leading one row, on a non-leading one row
 *	@author Avraham Katz
 *	@version 1.0
 */

public class NotLeadingOneRowException extends Exception
{
    public NotLeadingOneRowException(String message)
    {
        super(message);
    }
}