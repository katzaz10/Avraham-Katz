package math.shell.linearAlgebra.calculator;

import math.shell.linearAlgebra.calculator.exceptions.ErrorMessages;

import java.math.BigDecimal;

/**
 *	Methods that involve operations on 2D arrays
 *	@author Avraham Katz
 *	@version 1.1
 */

public class MultiDimensionalArray
{
    /**
     *	Gets the column height of a 2D array
     *	@param matrix 2D array getting height of
     *	@return int Height of matrix
     */
    public static int columnHeight(BigDecimal[][] matrix)
    {
        return matrix.length;
    }


    /**
     *	Gets the row length of a 2D array
     *	@param matrix 2D array getting length
     *	@return int Length of matrix
     */
    public static int rowLength(BigDecimal[][] matrix)
    {
        return matrix[0].length;
    }


    /**
     *	Test to see if the row attempting to use in a method is less than 0 or greater than the largest row number. Throws an exceptions if not
     *	@param matrix 2D array testing
     *	@param rowNumber Number of row testing
     */
    public static void rowNumberTest(BigDecimal[][] matrix, int rowNumber)
    {
        if (rowNumber < 0)
        {
            throw new IllegalArgumentException(ErrorMessages.row0);
        }

        if (rowNumber > columnHeight(matrix) - 1) //important to not, output message uses 0 to n model of matrix, not 1 to n model of matrix. Consider going back to regular badRowNumber error message
        {
            throw new IllegalArgumentException("row " + rowNumber + " does not exist in matrix...largest row = " + (columnHeight(matrix) - 1));
        }
    }


    /**
     *	Test to see if the column attempting to use in a method is less than 0 or greater than the largest column number. Throws an exceptions if not
     *	@param matrix 2D array testing
     *	@param columnNumber Number of column testing
     */
    public static void columnNumberTest(BigDecimal[][] matrix, int columnNumber)
    {
        if (columnNumber < 0)
        {
            throw new IllegalArgumentException(ErrorMessages.column0);
        }

        if (columnNumber > rowLength(matrix) - 1) //important to note, output message uses 0 to n model of matrix, not 1 to n model of matrix. Consider going back to regular badColumnNumber error message
        {
            throw new IllegalArgumentException("column " + columnNumber + " does not exist in matrix...largest row = " + (rowLength(matrix) - 1));
        }
    }


    /**
     *	Returns an array of the values of a given column in a 2D array
     *	@param matrix Matrix getting column from
     *	@param columnGetting Number of column getting values from
     *	@return BigDecimal[] Array of values in column
     */
    public static BigDecimal[] getColumn(BigDecimal[][] matrix, int columnGetting)
    {
        columnNumberTest(matrix, columnGetting);

        BigDecimal[] returnColumn = new BigDecimal[columnHeight(matrix)];

        for (int i = 0; i < columnHeight(matrix); i++)
        {
            returnColumn[i] = matrix[i][columnGetting];
        }

        return returnColumn;
    }


    /**
     *	Returns an array of the values of a given row in a 2D array
     *	@param matrix 2D array getting row from
     *	@param rowGetting Number of row getting values from
     *	@return BigDecimal[] Array of values in row
     */
    public static BigDecimal[] getRow(BigDecimal[][] matrix, int rowGetting)
    {
        rowNumberTest(matrix, rowGetting);

        return matrix[rowGetting];
    }


    /**
     *	Makes a copy of a 2D array
     *	@param matrixCopying 2D array copying
     *	@return BigDecimal[][] Copy of 2D array inputting
     */
    public static BigDecimal[][] copyMatrix(BigDecimal[][] matrixCopying)
    {
        BigDecimal[][] returnMatrix = new BigDecimal[columnHeight(matrixCopying)][rowLength(matrixCopying)];

        for (int i = 0; i < columnHeight(matrixCopying); i++)
        {
            for (int j = 0; j < rowLength(matrixCopying); j++)
            {
                returnMatrix[i][j] = matrixCopying[i][j];
            }
        }

        return returnMatrix;
    }
}