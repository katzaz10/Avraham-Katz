package math.shell.linearAlgebra.calculator.linearAlgebraOperations;

import math.shell.linearAlgebra.calculator.exceptions.ErrorMessages;
import math.shell.linearAlgebra.calculator.Matrix;
import math.shell.linearAlgebra.calculator.MultiDimensionalArray;
import math.shell.linearAlgebra.calculator.exceptions.NonSquareMatrixException;

import java.math.BigDecimal;

/**
 *	Operations involving minor of matrices
 *	@author Avraham Katz
 *	@version 1.1
 */

public class Minor
{


    /**
     *	Returns the minor of a specific spot in a 2D array
     *	@param matrix 2D array looking at
     *	@param row Value of i
     *	@param column Value of j
     *	@return BigDecimal[][] Minor as 2D array
     */
    private static BigDecimal[][] minor(BigDecimal[][] matrix, int row, int column)
    {
        MultiDimensionalArray.rowNumberTest(matrix, row);
        MultiDimensionalArray.columnNumberTest(matrix, column);

        int nextSlot = 0;

        BigDecimal[][] minor = new BigDecimal[MultiDimensionalArray.columnHeight(matrix) - 1][MultiDimensionalArray.columnHeight(matrix) - 1];

        BigDecimal[] minorValues = new BigDecimal[((MultiDimensionalArray.columnHeight(matrix) - 1) * (MultiDimensionalArray.columnHeight(matrix) - 1))];

        for (int i = 0; i < MultiDimensionalArray.columnHeight(matrix); i++)
        {
            for (int j = 0; j < MultiDimensionalArray.rowLength(matrix); j++)
            {
                if (i == row || j == column)
                {
                    continue;
                }

                else
                {
                    minorValues[nextSlot] = matrix[i][j];
                    nextSlot++;
                }
            }
        }

        nextSlot = 0;

        for (int a = 0; a < MultiDimensionalArray.columnHeight(minor); a++)
        {
            for (int b = 0; b < MultiDimensionalArray.rowLength(minor); b++)
            {
                minor[a][b] = minorValues[nextSlot];
                nextSlot++;
            }
        }

        return minor;
    }


    /**
     *	Finds the minor of a specific spot i,j in a Matrix
     *	@param matrix Matrix looking at
     *	@param row Value of i
     *	@param column Value of j
     *	@return Matrix Minor of spot i,j
     *	@throws NonSquareMatrixException Thrown if Matrix not a square matrix
     */
    public static Matrix minor(Matrix matrix, int row, int column) throws NonSquareMatrixException
    {
        if (!matrix.getSquareStatus())
        {
            throw new NonSquareMatrixException(ErrorMessages.nonSquare);
        }

        Matrix returnMinor = new Matrix(minor(matrix.getWrappedMatrix(), row, column));

        return returnMinor;
    }
}
