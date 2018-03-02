package math.shell.linearAlgebra.calculator;

import java.math.BigDecimal;

/**
 *	A series of tests on 2D arrays, used for finding attributes of matrices when making them
 *	@author Avraham Katz
 *	@version 1.1
 */

public class MatrixConstructorTests
{


    /**
     *	Ensures that all rows in a 2D array have same sized rows, matrix cannot have different sized rows
     *	@param matrix 2D array to be wrapped in Matrix object
     */
    public static void matrixTest(BigDecimal[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = i + 1; j < matrix.length; j++)
            {
                if (matrix[i].length != matrix[j].length)
                {
                    throw new IllegalArgumentException("matrix cannot have different sized rows");
                }
            }
        }
    }


    /**
     *	Tests to see if a 2D array is a square matrix
     *	@param matrix 2D array to be wrapped in Matrix object
     *	@return boolean Whether square matrix or not
     */
    public static boolean squareMatrix(BigDecimal[][] matrix)
    {
        if (MultiDimensionalArray.columnHeight(matrix) == MultiDimensionalArray.rowLength(matrix))
        {
            return true;
        }

        return false;
    }


    /**
     *	Tests to see if a 2D array is an identity matrix
     *	@param matrix 2D array to be wrapped in Matrix object
     *	@return boolean Whether identity matrix or not
     */
    public static boolean identityMatrix(BigDecimal[][] matrix)
    {
        if (!squareMatrix(matrix))
        {
            return false;
        }

        for (int i = 0; i < MultiDimensionalArray.columnHeight(matrix); i++)
        {
            for (int j = 0; j < MultiDimensionalArray.rowLength(matrix); j++)
            {
                if (i == j && matrix[i][j].compareTo(BigDecimal.ONE) != 0)
                {
                    return false;
                }

                if (i != j && matrix[i][j].compareTo(BigDecimal.ZERO) != 0)
                {
                    return false;
                }
            }
        }

        return true;
    }


    /**
     *	Tests to see if a 2D array is a 2x2 matrix
     *	@param matrix 2D array to be wrapped in Matrix object
     *	@return boolean Whether 2x2 matrix or not
     */
    public static boolean twoByTwoMatrix(BigDecimal[][] matrix)
    {
        if (MultiDimensionalArray.columnHeight(matrix) == 2 && MultiDimensionalArray.rowLength(matrix) == 2)
        {
            return true;
        }

        return false;
    }


    /**
     *	Tests to see if a 2D array is a 3x3 matrix
     *	@param matrix 2D array to be wrapped in Matrix object
     *	@return boolean Whether 3x3 matrix or not
     */
    public static boolean threeByThreeMatrix(BigDecimal[][] matrix)
    {
        if (MultiDimensionalArray.columnHeight(matrix) == 3 && MultiDimensionalArray.rowLength(matrix) == 3)
        {
            return true;
        }

        return false;
    }



    /**
     *	Tests to see if a 2D array is a diagonal
     *	@param matrix 2D array to be wrapped in Matrix object
     *	@return boolean Whether diagonal matrix or not
     */
    public static boolean diagonalMatrix(BigDecimal[][] matrix)
    {
        if (!squareMatrix(matrix))
        {
            return false;
        }

        if (!upperTriangularMatrix(matrix) || !lowerTriangularMatrix(matrix))
        {
            return false;
        }

        return true;
    }


    /**
     *	Tests to see if a 2D array is an upper triangular matrix
     *	@param matrix 2D array to be wrapped in Matrix object
     *	@return boolean Whether upper triangular matrix or not
     */
    public static boolean upperTriangularMatrix(BigDecimal[][] matrix)
    {
        if (!squareMatrix(matrix))
        {
            return false;
        }

        for (int i = 1; i < MultiDimensionalArray.columnHeight(matrix); i++)
        {
            for (int j = 0; j < i; j++)
            {
                if (matrix[i][j].compareTo(BigDecimal.ZERO) != 0)
                {
                    return false;
                }
            }
        }

        return true;
    }


    /**
     *	Tests to see if a 2D array is a lower triangular matrix
     *	@param matrix 2D array to be wrapped in Matrix object
     *	@return boolean Whether lower triangular matrix or not
     */
    public static boolean lowerTriangularMatrix(BigDecimal[][] matrix)
    {
        if (!squareMatrix(matrix))
        {
            return false;
        }

        for (int i = 0; i < MultiDimensionalArray.columnHeight(matrix); i++)
        {
            for (int j = i + 1; j < MultiDimensionalArray.rowLength(matrix); j++)
            {
                if (matrix[i][j].compareTo(BigDecimal.ZERO) != 0)
                {
                    return false;
                }
            }
        }

        return true;
    }


    /**
     *	Tests to see if a 2D array is a triangular matrix
     *	@param matrix 2D array to be wrapped in Matrix object
     *	@return boolean Whether triangular matrix or not
     */
    public static boolean triangularMatrix(BigDecimal[][] matrix)
    {
        if (upperTriangularMatrix(matrix))
        {
            return true;
        }

        else if (lowerTriangularMatrix(matrix))
        {
            return true;
        }

        return false;
    }
}