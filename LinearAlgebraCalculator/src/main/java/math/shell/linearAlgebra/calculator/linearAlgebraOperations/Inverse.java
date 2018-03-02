package math.shell.linearAlgebra.calculator.linearAlgebraOperations;

import math.shell.linearAlgebra.calculator.exceptions.ErrorMessages;
import math.shell.linearAlgebra.calculator.Matrix;
import math.shell.linearAlgebra.calculator.MultiDimensionalArray;
import math.shell.linearAlgebra.calculator.exceptions.NonSquareMatrixException;
import math.shell.linearAlgebra.calculator.exceptions.NotInvertibleException;
import math.shell.linearAlgebra.calculator.exceptions.NotLeadingOneRowException;
import math.shell.linearAlgebra.calculator.exceptions.ZeroRowException;

import java.math.BigDecimal;

/**
 *	Finds the inverse of a matrix
 *	@author Avraham Katz
 *	@version 1.1
 */

public class Inverse
{


    /**
     *	Tests to see if a Matrix is invertible
     *	@param matrix Matrix testing
     *	@return boolean Whether invertible or not
     */
    private static boolean invertible(Matrix matrix)
    {
        try
        {
            if (matrix.determinant().compareTo(BigDecimal.ZERO) == 0)
            {
                return false;
            }
        }

        catch (NonSquareMatrixException nonSquare)
        {
            return false;
        }

        return true;
    }


    /**
     *	Goes down column x of a matrix, finding the first non-zero value below row z. Once it finds this value, it pushes the row this value is in to a given row. Mirrors operations onto another matrix that will become inverse matrix
     *	@param matrix 2D array row and column contained in
     * 	@param inverseMatrix Matrix that will be converted into the inverse matrix
     *	@param column Number of column going down
     *	@param belowRow Number of row going down from
     *	@param rowMovingTo Number of row moving row with first non-zero value in column to
     *	@see math.shell.linearAlgebra.calculator.linearAlgebraOperations.GaussianElimination#moveFirstRowWithNonZeroValueBelowZForColumnXToRowY(BigDecimal[][], int, int, int)
     */
    private static void moveFirstRowWithNonZeroValueBelowZForColumnXToRowYForInverse(BigDecimal[][] matrix, BigDecimal[][] inverseMatrix, int column, int belowRow, int rowMovingTo)
    {
        MultiDimensionalArray.columnNumberTest(matrix, column);
        MultiDimensionalArray.rowNumberTest(matrix, belowRow);
        MultiDimensionalArray.rowNumberTest(matrix, rowMovingTo);


        for (int i = belowRow; i < MultiDimensionalArray.columnHeight(matrix); i++)
        {
            if (matrix[i][column].compareTo(BigDecimal.ZERO) != 0)
            {
                if (i != rowMovingTo)
                {
                    ElementaryRowOperations.switchRows(matrix, i, rowMovingTo);
                    ElementaryRowOperations.switchRows(inverseMatrix, i, rowMovingTo);
                }

                break;
            }
        }
    }


    /**
     *	Goes to the first non-zero slot in a row, and divides the entire row by that value to get a leading 1 row. Mirrors operations onto another matrix that will become inverse matrix
     *	@param matrix 2D array row contained in
     * 	@param inverseMatrix Matrix that will be converted into the inverse matrix
     *	@param row Number of row
     *	@see  math.shell.linearAlgebra.calculator.linearAlgebraOperations.GaussianElimination#reduceRowToGetLeadingOne(BigDecimal[][], int)
     */
    private static void reduceRowToGetLeadingOneForInverse(BigDecimal[][] matrix, BigDecimal[][] inverseMatrix, int row)
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
                //won't get here because of initial if statement, ensuring only a non-zero row is passed through
            }

            BigDecimal divideRow = matrix[row][firstNonZeroSlot];
            ElementaryRowOperations.divideRowByX(matrix, row, divideRow);
            ElementaryRowOperations.divideRowByX(inverseMatrix, row, divideRow);
        }
    }


    /**
     *	Takes row with leading 1, performing elementary row operations to clear all values below a leading 1 in all subsequent rows. Mirrors operations onto another matrix that will become inverse matrix
     *	@param matrix 2D array row contained in
     * 	@param inverseMatrix Matrix that will be converted into the inverse matrix
     *	@param row Number of row
     *	@see  math.shell.linearAlgebra.calculator.linearAlgebraOperations.GaussianElimination#clearValuesBelowLeadingOne(BigDecimal[][], int)
     */
    private static void clearValuesBelowLeadingOneForInverse(BigDecimal[][] matrix, BigDecimal[][] inverseMatrix, int row)
    {
        MultiDimensionalArray.rowNumberTest(matrix, row);

        if (GaussianElimination.isLeadingOneRow(matrix, row))
        {
            for (int i = row + 1; i < MultiDimensionalArray.columnHeight(matrix); i++)
            {
                try
                {
                    BigDecimal rowMultiply = BigDecimal.ONE.negate().multiply(matrix[i][GaussianElimination.leadingOneSlot(matrix, row)]);

                    ElementaryRowOperations.addRowToRow(matrix, row, i, rowMultiply);
                    ElementaryRowOperations.addRowToRow(inverseMatrix, row, i, rowMultiply);
                }

                catch (NotLeadingOneRowException needLeadingOne)
                {
                    //won't get here because of initial if statement, ensuring only a leading one row is passed through
                }
            }
        }
    }


    /**
     *	If row has leading 1, makes all values above zero by row addition. Mirrors operations onto another matrix that will become inverse matrix
     *	@param matrix 2D array clearing values in
     * 	@param inverseMatrix Matrix that will be converted into the inverse matrix
     *	@param row Row clearing above
     *	@see math.shell.linearAlgebra.calculator.linearAlgebraOperations.GaussianElimination#clearValuesAboveLeadingOne(BigDecimal[][] matrix, int row)
     */
    private static void clearValuesAboveLeadingOneForInverse(BigDecimal[][] matrix, BigDecimal[][] inverseMatrix, int row)
    {
        MultiDimensionalArray.rowNumberTest(matrix, row);

        if (GaussianElimination.isLeadingOneRow(matrix, row))
        {
            for (int i = row - 1; i > -1; i--)
            {
                try
                {
                    BigDecimal rowMultiply = BigDecimal.ONE.negate().multiply(matrix[i][GaussianElimination.leadingOneSlot(matrix, row)]);

                    ElementaryRowOperations.addRowToRow(matrix, row, i, rowMultiply);
                    ElementaryRowOperations.addRowToRow(inverseMatrix, row, i, rowMultiply);
                }

                catch(NotLeadingOneRowException needLeadingOne)
                {
                    //won't get here because of initial if statement, ensuring only a leading one row is passed through
                }
            }
        }
    }


    /**
     *	Returns a matrix in row echelon form. Mirrors operations onto another matrix that will become inverse matrix
     *	@param matrix Matrix reducing
     * 	@param inverseMatrix Matrix that will be converted into the inverse matrix
     *	@return Matrix New matrix in row echelon form
     *	@see  math.shell.linearAlgebra.calculator.linearAlgebraOperations.GaussianElimination#rowEchelonForm(Matrix)
     */
    private static Matrix rowEchelonFormForInverse(Matrix matrix, Matrix inverseMatrix)
    {
        BigDecimal[][] rowEchelon = matrix.getWrappedMatrix();
        BigDecimal[][] inverse = inverseMatrix.getWrappedMatrix();

        int rowToPutNextLeadingOne = 0; 	//this keeps track of how many rows have been moved up so far, so that can properly move up subsequent rows

        for (int i = 0; i < MultiDimensionalArray.rowLength(rowEchelon); i++)
        {
            if (GaussianElimination.columnNonEmptyBelowAndIncludingRowX(rowEchelon, i, rowToPutNextLeadingOne))
            {
                moveFirstRowWithNonZeroValueBelowZForColumnXToRowYForInverse(rowEchelon, inverse, i, rowToPutNextLeadingOne, rowToPutNextLeadingOne);
                reduceRowToGetLeadingOneForInverse(rowEchelon, inverse, rowToPutNextLeadingOne);
                clearValuesBelowLeadingOneForInverse(rowEchelon, inverse, rowToPutNextLeadingOne);
                rowToPutNextLeadingOne++;

                if (rowToPutNextLeadingOne == matrix.getColumnHeight())
                {
                    break;
                }

            }
        }

        Matrix inverseMatrixInProgress = new Matrix(inverse);

        return inverseMatrixInProgress;
    }


    /**
     *	Returns a matrix in reduced row echelon form. Mirrors operations onto another matrix that will become inverse matrix
     *	@param matrix Matrix reducing
     *	@param inverseMatrix Matrix that will be converted into the inverse matrix
     *	@return Matrix New matrix in reduced row echelon form
     *	@see math.shell.linearAlgebra.calculator.linearAlgebraOperations.GaussianElimination#reducedRowEchelonForm(Matrix)
     */
    private static Matrix reducedRowEchelonFormForInverse(Matrix matrix, Matrix inverseMatrix)
    {
        BigDecimal[][] reducedRowEchelon = GaussianElimination.rowEchelonForm(matrix).getWrappedMatrix();
        BigDecimal[][] inverseMatrixInProgress = rowEchelonFormForInverse(matrix, Matrix.identity(matrix.getColumnHeight())).getWrappedMatrix();

        for (int i = MultiDimensionalArray.columnHeight(reducedRowEchelon) - 1; i > -1; i--)
        {
            clearValuesAboveLeadingOneForInverse(reducedRowEchelon, inverseMatrixInProgress, i);
        }

        Matrix inverseAfterRref = new Matrix(inverseMatrixInProgress);

        return inverseAfterRref;
    }


    /**
     *	Inverts a Matrix, returning the inverse. If the Matrix is not invertible, throws a NotInvertibleException
     *	@param matrix Matrix inverting
     *	@return Matrix Inverse matrix
     */
    public static Matrix inverse(Matrix matrix)
    {
        if (!invertible(matrix))
        {
            throw new NotInvertibleException(ErrorMessages.notInvertible);
        }

        Matrix finalInverseMatrix = Matrix.identity(matrix.getColumnHeight());

        return reducedRowEchelonFormForInverse(matrix, finalInverseMatrix);
    }


}