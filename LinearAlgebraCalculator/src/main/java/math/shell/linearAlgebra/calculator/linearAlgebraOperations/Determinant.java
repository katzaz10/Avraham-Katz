package math.shell.linearAlgebra.calculator.linearAlgebraOperations;

import math.shell.linearAlgebra.calculator.*;
import math.shell.linearAlgebra.calculator.exceptions.ErrorMessages;
import math.shell.linearAlgebra.calculator.exceptions.NonSquareMatrixException;
import math.shell.linearAlgebra.calculator.exceptions.ZeroRowException;

import java.math.BigDecimal;

/**
 *	Operations involving determinants of Matrices
 *	@author Avraham Katz
 *	@version 1.1
 */

public class Determinant
{


    /**
     *	Finds the determinant of a 2x2 2D array, using ad-bc rule
     *	@param matrix 2D array finding determinant of
     *	@return BigDecimal Determinant
     */
    private static BigDecimal determinantTwoByTwoArray(BigDecimal[][] matrix)
    {
        BigDecimal ad = matrix[0][0].multiply(matrix[1][1]);
        BigDecimal bc = matrix[0][1].multiply(matrix[1][0]);

        BigDecimal determinant = ad.subtract(bc);

        return determinant;
    }


    /**
     *	Finds the determinant of a 3x3 2D array
     *	@param matrix 2D array finding determinant of
     *	@return BigDecimal Determinant
     */
    private static BigDecimal determinantThreeByThreeArray(BigDecimal[][] matrix)
    {
        BigDecimal[][] matrixUsing = MultiDimensionalArray.copyMatrix(matrix);

        BigDecimal addition1 = matrixUsing[0][0].multiply(matrixUsing[1][1]).multiply(matrixUsing[2][2]);
        BigDecimal addition2 = matrixUsing[0][1].multiply(matrixUsing[1][2]).multiply(matrixUsing[2][0]);
        BigDecimal addition3 = matrixUsing[0][2].multiply(matrixUsing[1][0]).multiply(matrixUsing[2][1]);
        BigDecimal additionHalf = addition1.add(addition2).add(addition3);

        BigDecimal subtraction1 = matrixUsing[0][2].multiply(matrixUsing[1][1]).multiply(matrixUsing[2][0]);
        BigDecimal subtraction2 = matrixUsing[0][0].multiply(matrixUsing[1][2]).multiply(matrixUsing[2][1]);
        BigDecimal subtraction3 = matrixUsing[0][1].multiply(matrixUsing[1][0]).multiply(matrixUsing[2][2]);
        BigDecimal subtractionHalf = subtraction1.add(subtraction2).add(subtraction3);

        BigDecimal determinant = additionHalf.subtract(subtractionHalf);

        return determinant;
    }


    /**
     *	Goes down column x of a matrix, finding the first non-zero value below row z. Once it finds this value, it pushes the row this value is in to a given row y. Whenever move row, multiply determinant by -1
     *	@param matrix 2D array row and column contained in
     *	@param column Number of column going down
     *	@param belowRow Number of row going down from
     *	@param rowMovingTo Number of row moving row with first non-zero value in column to
     *	@param determinant Value of determinant to be returned at end of determinant operation
     *  @return BigDecimal Current value of the determinant
     *	@see math.shell.linearAlgebra.calculator.linearAlgebraOperations.GaussianElimination#moveFirstRowWithNonZeroValueBelowZForColumnXToRowY(BigDecimal[][], int, int, int)
     */
    private static BigDecimal moveFirstRowWithNonZeroValueBelowZForColumnXToRowYForDeterminant(BigDecimal[][] matrix, int column, int belowRow, int rowMovingTo, BigDecimal determinant)
    {
        MultiDimensionalArray.columnNumberTest(matrix, column);
        MultiDimensionalArray.rowNumberTest(matrix, belowRow);
        MultiDimensionalArray.rowNumberTest(matrix, rowMovingTo);

        for (int i = belowRow; i < MultiDimensionalArray.columnHeight(matrix) - 1; i++)
        {
            if (matrix[i][column].compareTo(BigDecimal.ZERO) != 0)
            {
                ElementaryRowOperations.switchRows(matrix, i, rowMovingTo);

                if (i != rowMovingTo)
                {
                    BigDecimal returnValue = determinant.multiply(BigDecimal.ONE.negate());
                    return returnValue;
                }

                return determinant;
            }
        }

        return determinant;
    }


    /**
     *	Goes to the first non-zero slot in a row, and divides the entire row by that value to get a leading 1 row. Whatever value divide row by, multiply determinant by that value
     *	@param matrix 2D array row contained in
     *	@param row Number of row
     *	@param determinant Value of determinant to be returned at end of determinant operation
     *  @return BigDecimal Current value of the determinant
     *	@see math.shell.linearAlgebra.calculator.linearAlgebraOperations.GaussianElimination#reduceRowToGetLeadingOne(BigDecimal[][], int)
     */
    private static BigDecimal reduceRowToGetLeadingOneForDeterminant(BigDecimal[][] matrix, int row, BigDecimal determinant)
    {
        MultiDimensionalArray.rowNumberTest(matrix, row);

        int firstNonZeroSlot = 0;

        if (!GaussianElimination.isZeroRow(matrix, row))
        {
            try
            {
                firstNonZeroSlot = GaussianElimination.findFirstNonZeroSlotInRow(matrix, row);
            }

            catch (ZeroRowException zeroRow)
            {
                //will never get here because of initial if statement..."if (!GaussianElimination.isZeroRow(matrix, row))" ensuring a non-zero row is passed in"
            }

            BigDecimal divideRow = matrix[row][firstNonZeroSlot];
            ElementaryRowOperations.divideRowByX(matrix, row, divideRow);

            BigDecimal returnValue = determinant.multiply(divideRow)/*.round(new MathContext(10, RoundingMode.HALF_EVEN))*/;    //want to get rid of this round model
            return returnValue;
        }

        return determinant;
    }


    /**
     *	Finds the determinant of a matrix by row reduction
     *	@param  matrix Matrix finding determinant of
     *	@return BigDecimal Determinant
     *	@throws NonSquareMatrixException Thrown if matrix not a square matrix
     */
    private static BigDecimal determinantByRowReduction(Matrix matrix) throws NonSquareMatrixException
    {
        BigDecimal determinant = BigDecimal.ONE;

        BigDecimal[][] rowEchelon = matrix.getWrappedMatrix();

        int rowToPutNextLeadingOne = 0; 	//this keeps track of how many rows have been moved up so far, so that can properly move up subsequent rows

        for (int i = 0; i < MultiDimensionalArray.rowLength(rowEchelon); i++)
        {
            if (GaussianElimination.columnNonEmptyBelowAndIncludingRowX(rowEchelon, i, rowToPutNextLeadingOne))
            {
                determinant = moveFirstRowWithNonZeroValueBelowZForColumnXToRowYForDeterminant(rowEchelon, i, rowToPutNextLeadingOne, rowToPutNextLeadingOne, determinant);
                determinant = reduceRowToGetLeadingOneForDeterminant(rowEchelon, rowToPutNextLeadingOne, determinant);
                GaussianElimination.clearValuesBelowLeadingOne(rowEchelon, rowToPutNextLeadingOne);
                rowToPutNextLeadingOne++;

                if (rowToPutNextLeadingOne == matrix.getColumnHeight())
                {
                    break;
                }
            }
        }

        Matrix det = new Matrix(rowEchelon);

        BigDecimal returnValue = determinantTriangularOrDiagonalMatrix(det).multiply(determinant);

        return returnValue;
    }


    /**
     *	Finds the determinant of a 2x2 matrix
     *	@param matrix Matrix finding determinant of
     *	@return BigDecimal Determinant
     */
    private static BigDecimal determinantTwoByTwoMatrix(Matrix matrix)
    {
        return determinantTwoByTwoArray(matrix.getWrappedMatrix());
    }


    /**
     *	Finds the determinant of a 3x3 matrix
     *	@param matrix Matrix finding determinant of
     *	@return BigDecimal Determinant
     */
    private static BigDecimal determinantThreeByThreeMatrix(Matrix matrix)
    {
        return determinantThreeByThreeArray(matrix.getWrappedMatrix());
    }


    /**
     *	Finds the determinant of a triangular or diagonal matrix
     *	@param matrix Matrix finding determinant of
     *	@return BigDecimal Determinant
     *	@throws NonSquareMatrixException Thrown if matrix not a square matrix
     */
    private static BigDecimal determinantTriangularOrDiagonalMatrix(Matrix matrix) throws NonSquareMatrixException
    {
        BigDecimal determinant = BigDecimal.ONE;

        for (int i = 0; i < matrix.getColumnHeight(); i++)
        {
            determinant = determinant.multiply(matrix.getWrappedMatrix()[i][i]);
        }

        return determinant;
    }



    /**
     *	Finds the determinant of a Matrix
     *	@param matrix Matrix finding determinant of
     *	@return BigDecimal Determinant
     *	@throws NonSquareMatrixException Thrown if Matrix not a square matrix
     */
    public static BigDecimal determinant(Matrix matrix) throws NonSquareMatrixException
    {
        if (!matrix.getSquareStatus())
        {
            throw new NonSquareMatrixException(ErrorMessages.nonSquare);
        }

        else if (matrix.getColumnHeight() == 1)
        {
            return matrix.getWrappedMatrix()[0][0];
        }

        else if (matrix.getTriangularStatus() || matrix.getDiagonalStatus())
        {
            return determinantTriangularOrDiagonalMatrix(matrix);
        }

        else if (matrix.getTwoByTwoStatus())
        {
            return determinantTwoByTwoMatrix(matrix);
        }

        else if (matrix.getThreeByThreeStatus())
        {
            return determinantThreeByThreeMatrix(matrix);
        }

        else
        {
            return determinantByRowReduction(matrix);
        }
    }
}