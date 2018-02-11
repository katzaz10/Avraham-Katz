package math.shell.linearAlgebra.calculator;

/**
 *	This runtime exception is thrown in case where user tries to invert a non-invertible matrix
 *	@author Avraham Katz
 *	@version 1.0
 */

public class NotInvertibleException extends RuntimeException
{
    public NotInvertibleException(String message)
    {
        super(message);
    }
}