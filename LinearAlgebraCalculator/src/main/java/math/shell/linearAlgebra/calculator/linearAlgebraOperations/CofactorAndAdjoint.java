package math.shell.linearAlgebra.calculator.linearAlgebraOperations;

import math.shell.linearAlgebra.calculator.exceptions.ErrorMessages;
import math.shell.linearAlgebra.calculator.Matrix;
import math.shell.linearAlgebra.calculator.exceptions.NonSquareMatrixException;

import java.math.BigDecimal;

/**
 *	Operations involving cofactors and adjoints of matrices
 *	@author Avraham Katz
 *	@version 1.1
 */

public class CofactorAndAdjoint
{


    /**
     *	Finds the cofactor of a specific spot i,j in a Matrix
     *	@param matrix Matrix spot i,j in
     *	@param row Row i
     *	@param column Column j
     *	@return BigDecimal Cofactor of i,j
     *	@throws NonSquareMatrixException Thrown if Matrix not a square matrix
     */
    public static BigDecimal cofactor(Matrix matrix, int row, int column) throws NonSquareMatrixException
    {
        matrix.rowNumberTest(row);
        matrix.columnNumberTest(column);

        if (!matrix.getSquareStatus())
        {
            throw new NonSquareMatrixException(ErrorMessages.nonSquare);
        }

        Matrix minor = Minor.minor(matrix, row, column);

        BigDecimal determinantMinor = minor.determinant();

        BigDecimal sign = BigDecimal.ZERO;

        if ((row + column) % 2 == 1)
        {
            sign = BigDecimal.ONE.negate();
        }

        else if ((row + column) % 2 == 0)
        {
            sign = BigDecimal.ONE;
        }

        BigDecimal cofactor = sign.multiply(determinantMinor);

        return cofactor;
    }


    /**
     *	Finds the cofactor matrix of a Matrix
     *	@param matrix Matrix finding cofactor matrix of
     *	@return Matrix Cofactor matrix
     *	@throws NonSquareMatrixException Thrown if Matrix not a square matrix
     */
    public static Matrix cofactorMatrix(Matrix matrix) throws NonSquareMatrixException
    {
        if (!matrix.getSquareStatus())
        {
            throw new NonSquareMatrixException(ErrorMessages.nonSquare);
        }

        BigDecimal[][] cofactorInProgress = matrix.getWrappedMatrix();

        for (int i = 0; i < matrix.getColumnHeight(); i++)
        {
            for (int j = 0; j < matrix.getRowLength(); j++)
            {
                cofactorInProgress[i][j] = cofactor(matrix, i, j);
            }
        }

        Matrix cofactorMatrix = new Matrix(cofactorInProgress);

        return cofactorMatrix;
    }


    /**
     *	Finds the adjoint matrix of a Matrix
     *	@param matrix Matrix finding adjoint matrix of
     *	@return Matrix Adjoint matrix
     *	@throws NonSquareMatrixException Thrown if Matrix not a square matrix
     */
    public static Matrix adjointMatrix(Matrix matrix) throws NonSquareMatrixException
    {
        if (!matrix.getSquareStatus())
        {
            throw new NonSquareMatrixException(ErrorMessages.nonSquare);
        }

        return matrix.cofactorMatrix().transpose();
    }
}
