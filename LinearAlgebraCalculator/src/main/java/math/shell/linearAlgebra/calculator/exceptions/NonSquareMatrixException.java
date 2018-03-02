package math.shell.linearAlgebra.calculator.exceptions;

/**
 *	This exceptions is thrown in case where user tries to do operation that requires a square matrix on non-square matrix
 *	@author Avraham Katz
 *	@version 1.1
 */

public class NonSquareMatrixException extends Exception
{
    public NonSquareMatrixException(String message)
    {
        super(message);
    }
}