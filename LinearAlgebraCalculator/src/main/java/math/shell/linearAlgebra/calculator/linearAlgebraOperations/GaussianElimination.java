package math.shell.linearAlgebra.calculator.linearAlgebraOperations;

import math.shell.linearAlgebra.calculator.exceptions.ErrorMessages;
import math.shell.linearAlgebra.calculator.Matrix;
import math.shell.linearAlgebra.calculator.MultiDimensionalArray;
import math.shell.linearAlgebra.calculator.exceptions.NotLeadingOneRowException;
import math.shell.linearAlgebra.calculator.exceptions.ZeroRowException;
import java.math.BigDecimal;

/**
 *	Reduce matrices to row echelon form and reduced row echelon form
 *	@author Avraham Katz
 *	@version 1.1
 */

public class GaussianElimination
{

    /**
     *	Determines if a row contains only zeros (a zero row)
     *	@param matrix 2D array row contained in
     *	@param row Number of row
     *	@return boolean Whether the row is a zero row or not
     */
    public static boolean isZeroRow(BigDecimal[][] matrix, int row)
    {
        MultiDimensionalArray.rowNumberTest(matrix, row);

        BigDecimal[] rowTesting = MultiDimensionalArray.getRow(matrix, row);

        for (int i = 0; i < rowTesting.length; i++)
        {
            if (rowTesting[i].compareTo(BigDecimal.ZERO) != 0)
            {
                return false;
            }
        }

        return true;
    }


    /**
     *	Determines if a row has a 1 as its first non-zero value, either in the first slot or after a series of zeros
     *	@param matrix 2D array row contained in
     *	@param row Number of row
     *	@return boolean Whether the row is a leading 1 row or not
     */
    public static boolean isLeadingOneRow(BigDecimal[][] matrix, int row)
    {
        MultiDimensionalArray.rowNumberTest(matrix, row);

        BigDecimal[] rowChecking = MultiDimensionalArray.getRow(matrix, row);

        for (int i = 0; i < rowChecking.length; i++)
        {
            if (rowChecking[i].compareTo(BigDecimal.ONE) == 0)
            {
                return true;
            }

            else if (rowChecking[i].compareTo(BigDecimal.ZERO) != 0)
            {
                return false;
            }
        }

        return false;
    }


    /**
     *	Finds slot of first value of 1 in a leading one row
     *	@param matrix 2D array row contained in
     *	@param row Number of row
     *	@return int Number of slot in row leading 1 contained in
     *	@throws NotLeadingOneRowException Thrown when row is not a leading one row
     */
    public static int leadingOneSlot(BigDecimal[][] matrix, int row) throws NotLeadingOneRowException
    {
        MultiDimensionalArray.rowNumberTest(matrix, row);

        if (!isLeadingOneRow(matrix, row))
        {
            throw new NotLeadingOneRowException(ErrorMessages.needLeadingOne);
        }

        BigDecimal[] rowSearching = MultiDimensionalArray.getRow(matrix, row);

        for (int i = 0; i < rowSearching.length; i++)
        {
            if (rowSearching[i].compareTo(BigDecimal.ONE) == 0)
            {
                return i;
            }
        }

        return 0; //will never get here as isLeadingOneSlot method ensures that there is in fact a 1 in the row
    }


    /**
     *	Goes down column x of a matrix, finding the first non-zero value below row z. Once it finds this value, it pushes the row this value is in up to a given row
     *	(created method, because when reducing to row echelon form, find first non-zero value in a column, and move it up to row x)
     *	@param matrix 2D array row and column contained in
     *	@param column Number of column going down
     *	@param belowRow Number of row going down from
     *	@param rowMovingTo Number of row moving row with first non-zero value in column to
     */
    private static void moveFirstRowWithNonZeroValueBelowZForColumnXToRowY (BigDecimal[][] matrix, int column, int belowRow, int rowMovingTo)
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
                }

                break;
            }
        }
    }


    /**
     *	Finds the first slot in a row in which there is a non-zero value
     *	@param matrix 2D array row contained in
     *	@param row Number of row
     *	@return int Number of slot first non zero value in
     *	@throws ZeroRowException Thrown if the row has no non-zero values in it
     */
    public static int findFirstNonZeroSlotInRow (BigDecimal[][] matrix, int row) throws ZeroRowException
    {
        MultiDimensionalArray.rowNumberTest(matrix, row);

        if (isZeroRow(matrix, row))
        {
            throw new ZeroRowException(ErrorMessages.zeroRow);
        }

        BigDecimal[] rowTesting = MultiDimensionalArray.getRow(matrix, row);

        for (int i = 0; i < rowTesting.length; i++)
        {
            if (rowTesting[i].compareTo(BigDecimal.ZERO) != 0)
            {
                return i;
            }
        }

        return 0;
    }


    /**
     *	Goes to the first non-zero slot in a row, and divides the entire row by that value to get a leading 1 row
     *	@param matrix 2D array row contained in
     *	@param row Number of row
     */
    private static void reduceRowToGetLeadingOne (BigDecimal[][] matrix, int row)
    {
        MultiDimensionalArray.rowNumberTest(matrix, row);

        int firstNonZeroSlot = 0;

        if (!isZeroRow(matrix, row))
        {
            try
            {
                firstNonZeroSlot = findFirstNonZeroSlotInRow(matrix, row);
            }

            catch (ZeroRowException zeroRow)
            {
                //will never get here due to prior isZeroRow method
            }

            ElementaryRowOperations.divideRowByX(matrix, row, matrix[row][firstNonZeroSlot]);
        }
    }


    /**
     *	Takes row with leading 1, performing elementary row operations to clear all values below a leading 1 in all subsequent rows
     *	@param matrix 2D array row contained in
     *	@param row Number of row
     */
    public static void clearValuesBelowLeadingOne(BigDecimal[][] matrix, int row)
    {
        MultiDimensionalArray.rowNumberTest(matrix, row);

        if (isLeadingOneRow(matrix, row))
        {
            for (int i = row + 1; i < MultiDimensionalArray.columnHeight(matrix); i++)
            {
                try
                {
                    ElementaryRowOperations.addRowToRow(matrix, row, i, matrix[i][leadingOneSlot(matrix, row)].multiply(BigDecimal.ONE.negate()));
                }

                catch (NotLeadingOneRowException needLeadingOne)
                {
                    //will never get here due to initial if statement, ensuring a leading one row is passed in
                }
            }
        }
    }


    /**
     *	Tests to see if a column is non-empty besides for zeros from row x and down
     *	@param matrix 2D array row and column contained in
     *	@param column Column going down
     *	@param row Row testing below
     *	@return boolean Whether specified column is non-empty besides for zeros below specified row
     */
    public static boolean columnNonEmptyBelowAndIncludingRowX(BigDecimal[][] matrix, int column, int row)
    {
        MultiDimensionalArray.columnNumberTest(matrix, column);
        MultiDimensionalArray.rowNumberTest(matrix, row);

        BigDecimal[] columnTesting = MultiDimensionalArray.getColumn(matrix, column);

        for (int i = row; i < columnTesting.length; i++)
        {
            if (columnTesting[i].compareTo(BigDecimal.ZERO) != 0)
            {
                return true;
            }
        }

        return false;
    }


    /**
     *	Returns a Matrix in row echelon form
     *	@param matrix Matrix reducing
     *	@return Matrix New Matrix in row echelon form
     */
    public static Matrix rowEchelonForm(Matrix matrix)
    {
        BigDecimal[][] rowEchelon = matrix.getWrappedMatrix();

        int rowToPutNextLeadingOne = 0; 	//this keeps track of how many rows have been moved up so far, so that can properly move up subsequent rows

        for (int i = 0; i < MultiDimensionalArray.rowLength(rowEchelon); i++)
        {
            if (columnNonEmptyBelowAndIncludingRowX(rowEchelon, i, rowToPutNextLeadingOne))
            {
                moveFirstRowWithNonZeroValueBelowZForColumnXToRowY(rowEchelon, i, rowToPutNextLeadingOne, rowToPutNextLeadingOne);
                reduceRowToGetLeadingOne (rowEchelon, rowToPutNextLeadingOne);
                clearValuesBelowLeadingOne(rowEchelon, rowToPutNextLeadingOne);
                rowToPutNextLeadingOne++;

                if (rowToPutNextLeadingOne == matrix.getColumnHeight())
                {
                    break;
                }
            }
        }

        Matrix rowEchelonMatrix = new Matrix(rowEchelon);

        rowEchelonMatrix.unNegateAllZeros();

        return rowEchelonMatrix;
    }


    /**
     *	If row has leading 1, makes all values above zero by row addition
     *	@param matrix 2D array clearing values in
     *	@param row Row clearing above
     */
    private static void clearValuesAboveLeadingOne(BigDecimal[][] matrix, int row)
    {
        MultiDimensionalArray.rowNumberTest(matrix, row);

        if (isLeadingOneRow(matrix, row))
        {
            for (int i = row - 1; i > -1; i--)
            {
                try
                {
                    ElementaryRowOperations.addRowToRow(matrix, row, i, matrix[i][leadingOneSlot(matrix, row)].multiply(BigDecimal.ONE.negate()));
                }

                catch(NotLeadingOneRowException needLeadingOne)
                {
                    //won't ever get here becuse of initial if statement, ensuring a leading one row is passed in
                }
            }
        }
    }


    /**
     *	Returns a Matrix in reduced row echelon form
     *	@param matrix Matrix reducing
     *	@return Matrix New Matrix in reduced row echelon form
     */
    public static Matrix reducedRowEchelonForm(Matrix matrix)
    {
        BigDecimal[][] reducedRowEchelon = matrix.rowEchelonForm().getWrappedMatrix();

        for (int i = MultiDimensionalArray.columnHeight(reducedRowEchelon) - 1; i > -1; i--)
        {
            clearValuesAboveLeadingOne(reducedRowEchelon, i);
        }

        Matrix reducedRowEchelonMatrix = new Matrix(reducedRowEchelon);

        reducedRowEchelonMatrix.unNegateAllZeros();

        return reducedRowEchelonMatrix;
    }


}