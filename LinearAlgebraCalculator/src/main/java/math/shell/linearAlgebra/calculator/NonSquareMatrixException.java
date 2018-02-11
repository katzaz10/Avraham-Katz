package math.shell.linearAlgebra.calculator;

/**
 *	This exception is thrown in case where user tries to do operation that requires a square matrix on non-square matrix
 *	@author Avraham Katz
 *	@version 1.0
 */

public class NonSquareMatrixException extends Exception
{
    public NonSquareMatrixException(String message)
    {
        super(message);
    }
}