package math.shell.linearAlgebra.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *	A series of elementary row operations that are the basis of linear algebra
 *	@author Avraham Katz
 *	@version 1.0
 */

public class ElementaryRowOperations
{


    /**
     *	Divides each value in a row in a 2D array by a constant
     *	@param matrix 2D array the row is contained in
     *	@param rowDividing Row dividing by constant
     *	@param divisor Value dividing row by
     */
    public static void divideRowByX(BigDecimal[][] matrix, int rowDividing, BigDecimal divisor)
    {
        MultiDimensionalArray.rowNumberTest(matrix, rowDividing);

        if (divisor.compareTo(BigDecimal.ZERO) == 0)
        {
            throw new IllegalArgumentException("bad operation: cannot multiply row by 0");
        }

        for (int i = 0; i < MultiDimensionalArray.rowLength(matrix); i++)
        {
            matrix[rowDividing][i] = matrix[rowDividing][i].divide(divisor, 5, BigDecimal.ROUND_HALF_EVEN);
        }
    }


    /**
     *	Takes a row, multiplies each value in the row by a constant, and adds the new row to another row within a 2D array
     *	@param matrix 2D array the row is contained in
     *	@param rowAddingFrom Row using to add to other row
     *	@param rowAddingTo Row adding to
     *	@param multiple Constant multiplying first row by
     */
    public static void addRowToRow(BigDecimal[][] matrix, int rowAddingFrom, int rowAddingTo, BigDecimal multiple)
    {
        MultiDimensionalArray.rowNumberTest(matrix, rowAddingFrom);
        MultiDimensionalArray.rowNumberTest(matrix, rowAddingTo);

        for (int i = 0; i < MultiDimensionalArray.rowLength(matrix); i++)
        {
            matrix[rowAddingTo][i] = (matrix[rowAddingFrom][i].multiply(multiple)).setScale(5, BigDecimal.ROUND_HALF_EVEN).add(matrix[rowAddingTo][i]);
        }
    }


    /**
     *	Switches two rows in a 2D array
     *	@param matrix 2D array the rows contained in
     *	@param firstRowSwitching First row switching
     *	@param secondRowSwitching Second row switching
     */
    public static void switchRows(BigDecimal[][] matrix, int firstRowSwitching, int secondRowSwitching)
    {
        MultiDimensionalArray.rowNumberTest(matrix, firstRowSwitching);
        MultiDimensionalArray.rowNumberTest(matrix, secondRowSwitching);

        BigDecimal[] temporaryHoldingRow = MultiDimensionalArray.getRow(matrix, firstRowSwitching);

        matrix[firstRowSwitching] = matrix[secondRowSwitching];
        matrix[secondRowSwitching] = temporaryHoldingRow;

    }


}