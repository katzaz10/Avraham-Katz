package math.shell.linearAlgebra.calculator.exceptions;

/**
 *	This runtime exceptions is thrown in case where user tries to invert a non-invertible matrix
 *	@author Avraham Katz
 *	@version 1.1
 */

public class NotInvertibleException extends RuntimeException
{
    public NotInvertibleException(String message)
    {
        super(message);
    }
}