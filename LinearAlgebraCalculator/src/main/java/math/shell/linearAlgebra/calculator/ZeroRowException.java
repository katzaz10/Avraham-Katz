package math.shell.linearAlgebra.calculator;

/**
 *	This exception is thrown in case where operation requires a non-zero row
 *	@author Avraham Katz
 *	@version 1.0
 */

public class ZeroRowException extends Exception
{
    public ZeroRowException(String message)
    {
        super(message);
    }
}