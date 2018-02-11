package math.shell.linearAlgebra.calculator;

import java.math.BigDecimal;

/**
 *	Operations involving determinants, cofactors, minors, and adjoints
 *	@author Avraham Katz
 *	@version 1.0
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
     *	Goes down column x of a matrix, finding the first non-zero value below row z. Once it finds this value, it pushes the row this value is in to a given row y. Whenever move row, multiply determinant by -1
     *	@param matrix 2D array row and column contained in
     *	@param column Number of column going down
     *	@param belowRow Number of row going down from
     *	@param rowMovingTo Number of row moving row with first non-zero value in column to
     *	@param determinant Value of determinant to be returned at end of determinant operation
     *	@see resume.linear_algebra.calculator3.GaussianElimination#moveFirstRowWithNonZeroValueBelowZForColumnXToRowY(BigDecimal[][], int, int, int)
     */
    private static void moveFirstRowWithNonZeroValueBelowZForColumnXToRowYForDeterminant(BigDecimal[][] matrix, int column, int belowRow, int rowMovingTo, BigDecimal determinant)
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
                    determinant = determinant.multiply(BigDecimal.ONE.negate());
                }

                break;
            }
        }
    }


    /**
     *	Goes to the first non-zero slot in a row, and divides the entire row by that value to get a leading 1 row. Whatever value divide row by, multiply determinant by that value
     *	@param matrix 2D array row contained in
     *	@param row Number of row
     *	@param determinant Value of determinant to be returned at end of determinant operation
     *	@see resume.linear_algebra.calculator3.GaussianElimination#reduceRowToGetLeadingOne(BigDecimal[][], int)
     */
    private static void reduceRowToGetLeadingOneForDeterminant(BigDecimal[][] matrix, int row, BigDecimal determinant)
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

            determinant = determinant.multiply(divideRow);
        }
    }


    /**
     *	Finds the determinant of a matrix by row reduction
     *	@param matrix Matrix finding determinant of
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
                moveFirstRowWithNonZeroValueBelowZForColumnXToRowYForDeterminant(rowEchelon, i, rowToPutNextLeadingOne, rowToPutNextLeadingOne, determinant);
                reduceRowToGetLeadingOneForDeterminant(rowEchelon, rowToPutNextLeadingOne, determinant);
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

        Matrix minor = new Matrix(minor(matrix.getWrappedMatrix(), row, column));

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