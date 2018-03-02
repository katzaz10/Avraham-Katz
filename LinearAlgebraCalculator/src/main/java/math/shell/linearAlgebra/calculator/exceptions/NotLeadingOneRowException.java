package math.shell.linearAlgebra.calculator.exceptions;

/**
 *	This exceptions is thrown in case where user tries to do operation that requires a leading one row, on a non-leading one row
 *	@author Avraham Katz
 *	@version 1.1
 */

public class NotLeadingOneRowException extends Exception
{
    public NotLeadingOneRowException(String message)
    {
        super(message);
    }
}