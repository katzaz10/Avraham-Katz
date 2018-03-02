package math.shell.linearAlgebra.calculator;

import math.shell.linearAlgebra.calculator.utilities.StringUtils;
import math.shell.linearAlgebra.calculator.exceptions.ErrorMessages;
import math.shell.linearAlgebra.calculator.exceptions.NonSquareMatrixException;
import math.shell.linearAlgebra.calculator.exceptions.UndefinedSolutionException;
import math.shell.linearAlgebra.calculator.linearAlgebraOperations.*;

import java.math.BigDecimal;

/**
 *	Constructs a Matrix	object that can be used for linear algebra purposes. Values in the Matrix are of type BigDecimal.
 *	The Matrix holds identifing information such as if it is a 2x2, 3x3, square, identity, diagonal, triangular, upper triangular, lower triangular, or symmetrix Matrix.
 *	@author Avraham Katz
 *	@version 1.1
 */

public class Matrix
{
    private BigDecimal[][] wrappedMatrix;

    private int columnHeight;
    private int rowLength;

    private boolean squareMatrixStatus;
    private boolean twoByTwoMatrixStatus;
    private boolean threeByThreeMatrixStatus;
    private boolean identityMatrixStatus;
    private boolean diagonalMatrixStatus;
    private boolean upperTriangularMatrixStatus;
    private boolean lowerTriangularMatrixStatus;
    private boolean triangularMatrixStatus;


    /**
     * 	Constructs a Matrix object that wraps a 2D array and identifying information about what type of matrix it is
     * 	@param wrappedMatrix The wrapped 2D array that the matrix holds. Every row must be of same length.
     */

    public Matrix(BigDecimal[][] wrappedMatrix)
    {
        MatrixConstructorTests.matrixTest(wrappedMatrix);

        this.wrappedMatrix = MultiDimensionalArray.copyMatrix(wrappedMatrix);

        this.columnHeight = MultiDimensionalArray.columnHeight(wrappedMatrix);
        this.rowLength = MultiDimensionalArray.rowLength(wrappedMatrix);

        this.squareMatrixStatus = MatrixConstructorTests.squareMatrix(wrappedMatrix); //from here to save memory will go through a tree. If one aspect of a matrix implies other aspect of matrix, will set those booleans to true or false without going through an entire MatrixConstructorTest for every single type of aspect for a matrix

        if (this.squareMatrixStatus == false)
        {
            this.twoByTwoMatrixStatus = false;
            this.threeByThreeMatrixStatus = false;
            this.identityMatrixStatus = false;
            this.diagonalMatrixStatus = false;
            this.upperTriangularMatrixStatus = false;
            this.lowerTriangularMatrixStatus = false;
            this.triangularMatrixStatus = false;
        }

        else
        {
            this.twoByTwoMatrixStatus = MatrixConstructorTests.twoByTwoMatrix(wrappedMatrix);

            if (this.twoByTwoMatrixStatus == true)
            {
                this.threeByThreeMatrixStatus = false;
            }

            else
            {
                this.threeByThreeMatrixStatus = MatrixConstructorTests.threeByThreeMatrix(wrappedMatrix);
            }

            this.identityMatrixStatus = MatrixConstructorTests.identityMatrix(wrappedMatrix);

            if (this.identityMatrixStatus == true)
            {
                this.diagonalMatrixStatus = true;
                this.upperTriangularMatrixStatus = true;
                this.lowerTriangularMatrixStatus = true;
                this.triangularMatrixStatus = true;
            }

            else
            {
                this.upperTriangularMatrixStatus = MatrixConstructorTests.upperTriangularMatrix(wrappedMatrix);

                if (this.upperTriangularMatrixStatus == true)
                {
                    this.triangularMatrixStatus = true;

                    this.lowerTriangularMatrixStatus = MatrixConstructorTests.lowerTriangularMatrix(wrappedMatrix);

                    if (this.lowerTriangularMatrixStatus == true)
                    {
                        this.diagonalMatrixStatus = true;
                    }

                    else
                    {
                        this.diagonalMatrixStatus = false;
                    }
                }

                else
                {
                    this.diagonalMatrixStatus = false;

                    this.lowerTriangularMatrixStatus = MatrixConstructorTests.lowerTriangularMatrix(wrappedMatrix);

                    if (this.lowerTriangularMatrixStatus == true)
                    {
                        this.triangularMatrixStatus = true;
                    }

                    else
                    {
                        this.triangularMatrixStatus = false;
                    }
                }
            }
        }
    }


    /**
     * 	Constructs a Matrix object that wraps a 2D array. The 2D array wrapped is constructed from a string. Matrix holds identifying information about what type of matrix it is
     * 	@param stringContainingMultidimensionalArray This string is converted into a 2d array to be wrapped by matrix
     * 	@see StringUtils#multiDimensionalArrayFromString(String)
     */
    public Matrix(String stringContainingMultidimensionalArray)
    {
        BigDecimal[][] wrappedMatrix = StringUtils.multiDimensionalArrayFromString(stringContainingMultidimensionalArray);

        MatrixConstructorTests.matrixTest(wrappedMatrix);

        this.wrappedMatrix = wrappedMatrix;

        this.columnHeight = MultiDimensionalArray.columnHeight(wrappedMatrix);
        this.rowLength = MultiDimensionalArray.rowLength(wrappedMatrix);

        this.squareMatrixStatus = MatrixConstructorTests.squareMatrix(wrappedMatrix); //from here to save memory will go through a tree. If one aspect of a matrix implies other aspect of matrix, will set those booleans to true or false without going through an entire MatrixConstructorTest for every single type of aspect for a matrix

        if (this.squareMatrixStatus == false)
        {
            this.twoByTwoMatrixStatus = false;
            this.threeByThreeMatrixStatus = false;
            this.identityMatrixStatus = false;
            this.diagonalMatrixStatus = false;
            this.upperTriangularMatrixStatus = false;
            this.lowerTriangularMatrixStatus = false;
            this.triangularMatrixStatus = false;
        }

        else
        {
            this.twoByTwoMatrixStatus = MatrixConstructorTests.twoByTwoMatrix(wrappedMatrix);

            if (this.twoByTwoMatrixStatus == true)
            {
                this.threeByThreeMatrixStatus = false;
            }

            else
            {
                this.threeByThreeMatrixStatus = MatrixConstructorTests.threeByThreeMatrix(wrappedMatrix);
            }

            this.identityMatrixStatus = MatrixConstructorTests.identityMatrix(wrappedMatrix);

            if (this.identityMatrixStatus == true)
            {
                this.diagonalMatrixStatus = true;
                this.upperTriangularMatrixStatus = true;
                this.lowerTriangularMatrixStatus = true;
                this.triangularMatrixStatus = true;
            }

            else
            {
                this.upperTriangularMatrixStatus = MatrixConstructorTests.upperTriangularMatrix(wrappedMatrix);

                if (this.upperTriangularMatrixStatus == true)
                {
                    this.triangularMatrixStatus = true;

                    this.lowerTriangularMatrixStatus = MatrixConstructorTests.lowerTriangularMatrix(wrappedMatrix);

                    if (this.lowerTriangularMatrixStatus == true)
                    {
                        this.diagonalMatrixStatus = true;
                    }

                    else
                    {
                        this.diagonalMatrixStatus = false;
                    }
                }

                else
                {
                    this.diagonalMatrixStatus = false;

                    this.lowerTriangularMatrixStatus = MatrixConstructorTests.lowerTriangularMatrix(wrappedMatrix);

                    if (this.lowerTriangularMatrixStatus == true)
                    {
                        this.triangularMatrixStatus = true;
                    }

                    else
                    {
                        this.triangularMatrixStatus = false;
                    }
                }
            }
        }
    }

    /*
     *   Was having issues where by using getWrappedMatrix() and then doing matrix operations on the returned 2D array, was directly affecting the Matrix object the 2D array was coming from.
     *   To fix this issue, when using getWrappedMatrix() first creates a new Matrix based on the 2D array in the original Matrix object, and then returns the 2D array from that Matrix object.
    */
    public BigDecimal[][] getWrappedMatrix()
    {
        Matrix matrixUsing = new Matrix(this.wrappedMatrix);
        return matrixUsing.wrappedMatrix;
    }


    public int getColumnHeight()
    {
        return this.columnHeight;
    }


    public int getRowLength()
    {
        return this.rowLength;
    }


    public boolean getTwoByTwoStatus()
    {
        return this.twoByTwoMatrixStatus;
    }


    public boolean getThreeByThreeStatus()
    {
        return this.threeByThreeMatrixStatus;
    }


    public boolean getSquareStatus()
    {
        return this.squareMatrixStatus;
    }


    public boolean getIdentityStatus()
    {
        return this.identityMatrixStatus;
    }


    public boolean getUpperTriangularStatus()
    {
        return this.upperTriangularMatrixStatus;
    }


    public boolean getLowerTriangularStatus()
    {
        return this.lowerTriangularMatrixStatus;
    }


    public boolean getTriangularStatus()
    {
        return this.triangularMatrixStatus;
    }


    public boolean getDiagonalStatus()
    {
        return this.diagonalMatrixStatus;
    }


    public String getSize()
    {
        return (this.getColumnHeight() + " x " + this.getRowLength());
    }


    /**
     *	This is a test to see if the row attempting to use in a method exists within the Matrix
     *	@param rowNumber Number of row testing
     *	@throws IllegalArgumentException Thrown if row is outside of array parameters
     */
    public void rowNumberTest(int rowNumber)
    {
        if (rowNumber < 0)
        {
            throw new IllegalArgumentException(ErrorMessages.row0);
        }

        if (rowNumber > this.getColumnHeight() - 1) //important to note, output message uses 0 to n model of array, not 1 to n model of matrix. Built into interface, when user puts in row and column, subtracts 1 from row and 1 from column to do array operations
        {
            throw new IllegalArgumentException("row " + rowNumber + " does not exist in matrix...largest row = " + (this.getColumnHeight() - 1));
        }
    }


    /**
     *	This is a test to see if the column attempting to use in a method exists within the Matrix
     *	@param columnNumber Number of column testing
     *	@throws IllegalArgumentException Thrown if column is outside of array parameters
     */
    public void columnNumberTest(int columnNumber)
    {
        if (columnNumber < 0)
        {
            throw new IllegalArgumentException(ErrorMessages.column0);
        }

        if (columnNumber > this.getRowLength() - 1) //important to note, output message uses 0 to n model of array, not 1 to n model of matrix. Built into interface, when user puts in row and column, subtracts 1 from row and 1 from column to do array operations
        {
            throw new IllegalArgumentException("column " + columnNumber + " does not exist in matrix...largest row = " + (this.getRowLength() - 1));
        }
    }


    /**
     *	Returns an array of the values of a given column in a Matrix
     *	@param columnGetting Number of column getting
     *	@return BigDecimal[] Array of column values
     */
    public BigDecimal[] getColumn(int columnGetting)
    {
        this.columnNumberTest(columnGetting);

        BigDecimal[] returnColumn = new BigDecimal[this.getColumnHeight()];

        for (int i = 0; i < this.getColumnHeight(); i++)
        {
            returnColumn[i] = this.getWrappedMatrix()[i][columnGetting];
        }

        return returnColumn;
    }



    /**
     *	Returns an array of the values of a given row in a Matrix
     *	@param rowGetting Number of row getting
     *	@return BigDecimal[] Array of row values
     */
    public BigDecimal[] getRow(int rowGetting)
    {
        this.rowNumberTest(rowGetting);

        return this.getWrappedMatrix()[rowGetting];
    }



    /**
     *	Cleans up the Matrix, such that if any negative zeros exist, gets rid of the negative signs and sets the value to 0
     */
    public void unNegateAllZeros()
    {
        for (int i = 0; i < this.getColumnHeight(); i++)
        {
            for (int j = 0; j < this.getRowLength(); j++)
            {
                if (this.wrappedMatrix[i][j].compareTo(BigDecimal.ZERO.negate()) == 0)
                {
                    this.wrappedMatrix[i][j] = BigDecimal.ZERO;
                }
            }
        }
    }


    /**
     *	Determines if 2 Matrices are of equal size
     *	@param otherMatrix Matrix comparing to
     *	@return boolean If matrices are same size
     */
    public boolean sizeEquals(Matrix otherMatrix)
    {
        if (this.getRowLength() != otherMatrix.getRowLength())
        {
            return false;
        }

        if (this.getColumnHeight() != otherMatrix.getColumnHeight())
        {
            return false;
        }

        return true;
    }

    /**
     *	Adds one Matrix to another, returning a new Matrix
     *	@param addingMatrix Value adding to this Matrix
     *	@return Matrix New Matrix after adding value
     *	@throws UndefinedSolutionException Thrown if 2 matrices are not of same size '#rows x #columns'
     */
    public Matrix add(Matrix addingMatrix) throws UndefinedSolutionException
    {
        return MatrixArithmetic.matrixAddition(this, addingMatrix);
    }


    /**
     *	Subtracts one Matrix from another, returning a new Matrix
     *	@param subtractMatrix Value subtracting from this Matrix
     *	@return Matrix New Matrix after subtracting value
     *	@throws UndefinedSolutionException Thrown if 2 matrices are not of same size '#rows x #columns'
     */
    public Matrix subtract(Matrix subtractMatrix) throws UndefinedSolutionException
    {
        return MatrixArithmetic.matrixSubtraction(this, subtractMatrix);
    }


    /**
     *	Scalar multiplication of a Matrix
     *	@param scalar Scalar multiplying Matrix by
     *	@return Matrix New Matrix after multiplying by scalar
     */
    public Matrix scalarMultiply(BigDecimal scalar)
    {
        return MatrixArithmetic.scalarMultiplication(this, scalar);
    }


    /**
     *	Matrix multiplication of two Matrices
     *	@param otherMatrix Value multiplying Matrix by
     *	@return Matrix New Matrix after multiplying matrices
     *	@throws UndefinedSolutionException Thrown if row length of this Matrix not equal to column height of Matrix in parameters
     */
    public Matrix multiply(Matrix otherMatrix) throws UndefinedSolutionException
    {
        return MatrixArithmetic.matrixMultiplication(this, otherMatrix);
    }


    /**
     *	Gets transpose of a Matrix
     *	@return Matrix Transpose Matrix
     */
    public Matrix transpose()
    {
        return MatrixArithmetic.transposeMatrix(this);
    }

    /**
     *	Gets trace of a Matrix
     *	@return BigDecimal	Value of trace of Matrix
     *	@throws NonSquareMatrixException Thrown if Matrix finding trace of is non-square
     */
    public BigDecimal trace() throws NonSquareMatrixException
    {
        return MatrixArithmetic.traceMatrix(this);
    }


    /**
     *	Raises Matrix to a power, both negative and positive
     *	@param power Exponent of Matrix
     *	@return Matrix Matrix raised to this exponent
     *	@throws NonSquareMatrixException Throw if Matrix trying to raise to power is non-square
     */
    public Matrix toPower(int power) throws NonSquareMatrixException
    {
        return MatrixArithmetic.matrixToPower(this, power);
    }

    /**
     *	Reduces Matrix to row echelon form
     *	@return Matrix Matrix in row echelon form
     */
    public Matrix rowEchelonForm()
    {
        return GaussianElimination.rowEchelonForm(this);
    }

    /**
     *	Reduces Matrix to reduced row echelon form
     *	@return Matrix Matrix in reduced row echelon form
     */
    public Matrix reducedRowEchelonForm()
    {
        return GaussianElimination.reducedRowEchelonForm(this);
    }

    /**
     *	Inverts a Matrix
     *	@return Matrix Inverse Matrix
     */
    public Matrix inverse()
    {
        return Inverse.inverse(this);
    }


    /**
     *	Finds the determinant of a Matrix
     *	@return BigDecimal Determinant
     *	@throws NonSquareMatrixException Throw if Matrix trying to get determinant of is non-square
     */
    public BigDecimal determinant() throws NonSquareMatrixException
    {
        return Determinant.determinant(this);
    }

    /**
     *	Finds the minor of a spot i,j in a Matrix
     *	@param row i value
     *	@param column j value
     *	@return Matrix Minor of i,j
     *	@throws NonSquareMatrixException Throw if Matrix trying to get minor of is non-square
     */
    public Matrix minor(int row, int column) throws NonSquareMatrixException
    {
        return Minor.minor(this, row, column);
    }


    /**
     *	Finds the cofactor of a spot i,j in a Matrix
     *	@param row i value
     *	@param column j value
     *	@return Matrix Cofactor of i,j
     *	@throws NonSquareMatrixException Throw if Matrix trying to get cofactor of is non-square
     */
    public BigDecimal cofactor(int row, int column) throws NonSquareMatrixException
    {
        return CofactorAndAdjoint.cofactor(this, row, column);
    }


    /**
     *	Finds the cofactor Matrix of a Matrix
     *	@return Matrix Cofactor Matrix
     *	@throws NonSquareMatrixException Throw if Matrix trying to get cofactor Matrix of is non-square
     */
    public Matrix cofactorMatrix() throws NonSquareMatrixException
    {
        return CofactorAndAdjoint.cofactorMatrix(this);
    }


    /**
     *	Finds the adjoint Matrix of a Matrix
     *	@return Matrix Adjoint Matrix
     *	@throws NonSquareMatrixException Throw if Matrix trying to get adjoint Matrix of is non-square
     */
    public Matrix adjointMatrix() throws NonSquareMatrixException
    {
        return CofactorAndAdjoint.adjointMatrix(this);
    }


    /**
     *	Creates an identity matrix of any size
     *	@param sizing Size of identity Matrix. Identity Matrix must be square so this is both the length and height of the Matrix
     *	@return Matrix Indentity Matrix of size sizing
     */
    public static Matrix identity(int sizing)
    {
        BigDecimal[][] identity = new BigDecimal[sizing][sizing];

        for (int i = 0; i < sizing; i++)
        {
            for (int j = 0; j < sizing; j++)
            {
                if (i == j)
                {
                    identity[i][j] = BigDecimal.ONE;
                }

                if (i != j)
                {
                    identity[i][j] = BigDecimal.ZERO;
                }
            }
        }

        Matrix identityMatrix = new Matrix(identity);

        return identityMatrix;
    }


    /**
     *	Converts a Matrix to its string representation
     *	String represenation starts with '[' and ends with ']׳. A comma denotes a new value, moving from value in (i,j) to (i,j+1). A '|' denotes moving to the next line, moving from (i,j) to (i+1,j).
     *	Example:
     *			|1 2|
     *			|3 4| = "[1,2|3,4|]"
     *
     *			|1 0 0|
     *			|0 1 0| = "[1,0,0|0,1,0|]"
     *
     *	@return String String represenation of Matrix
     */
    @Override
    public String toString()
    {
        String matrixString = "[";

        for (int i = 0; i < this.getColumnHeight(); i++)
        {
            for (int j = 0; j < this.getRowLength(); j++)
            {
                matrixString = matrixString + this.getWrappedMatrix()[i][j].stripTrailingZeros().toPlainString();

                if (j != this.getRowLength() - 1)
                {
                    matrixString = matrixString + ",";
                }
            }

            matrixString = matrixString + "|";
        }

        matrixString = matrixString + "]";

        return matrixString;
    }


    @Override
    public boolean equals(Object that)
    {
        if (this == that)
        {
            return true;
        }

        if (that == null)
        {
            return false;
        }

        if (getClass() != that.getClass())
        {
            return false;
        }

        Matrix otherMatrix = (Matrix) that;

        if (!this.sizeEquals(otherMatrix))
        {
            return false;
        }

        for (int i = 0; i < this.getColumnHeight(); i++)
        {
            for (int j = 0; j < this.getRowLength(); j++)
            {
               if (this.getWrappedMatrix()[i][j].compareTo(otherMatrix.getWrappedMatrix()[i][j]) != 0)
               {
                   return false;
               }
            }
        }

        return true;
    }


    /**
     * Finds if 2 matrices are equal within a certain margin of error, each value in spot i,j of matrix #1 can be within .000000001 of spot i,j in matrix #2
     * @param that Other matrix comparing to
     * @return boolean Whether they are equal within the margin or not
     */
    public boolean equalsWithinMargin(Object that)
    {
        if (this == that)
        {
            return true;
        }

        if (that == null)
        {
            return false;
        }

        if (getClass() != that.getClass())
        {
            return false;
        }

        Matrix otherMatrix = (Matrix) that;

        if (!this.sizeEquals(otherMatrix))
        {
            return false;
        }

        for (int i = 0; i < this.getColumnHeight(); i++)
        {
            for (int j = 0; j < this.getRowLength(); j++)
            {
                if (this.getWrappedMatrix()[i][j].compareTo(otherMatrix.getWrappedMatrix()[i][j]) != 0)
                {
                    BigDecimal marginOfError = new BigDecimal(.000000001);
                    BigDecimal subtractValues = this.getWrappedMatrix()[i][j].subtract(otherMatrix.getWrappedMatrix()[i][j]).abs();

                    if (subtractValues.compareTo(marginOfError) != -1)
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }



}