package math.shell.linearAlgebra.calculator.exceptions;

/**
 *	This exceptions is thrown in case where operation requires a non-zero row
 *	@author Avraham Katz
 *	@version 1.1
 */

public class ZeroRowException extends Exception
{
    public ZeroRowException(String message)
    {
        super(message);
    }
}