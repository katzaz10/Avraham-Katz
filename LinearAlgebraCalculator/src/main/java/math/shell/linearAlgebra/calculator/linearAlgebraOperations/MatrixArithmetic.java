package math.shell.linearAlgebra.calculator.linearAlgebraOperations;

import math.shell.linearAlgebra.calculator.exceptions.ErrorMessages;
import math.shell.linearAlgebra.calculator.Matrix;
import math.shell.linearAlgebra.calculator.MultiDimensionalArray;
import math.shell.linearAlgebra.calculator.exceptions.NonSquareMatrixException;
import math.shell.linearAlgebra.calculator.exceptions.UndefinedSolutionException;

import java.math.BigDecimal;

/**
 *	A series of operations that involve Matrix arithmetic
 *	@author Avraham Katz
 *	@version 1.1
 */

public class MatrixArithmetic
{


    /**
     *	Adds 2 Matrices together, Matrices must be of same size
     *	@param matrix1 First Matrix adding
     *	@param matrix2 Second Matrix adding
     *	@return Matrix Matrix created from adding 2 Matrices together
     *	@throws UndefinedSolutionException Thrown when 2 Matrices not of same size
     */
    public static Matrix matrixAddition(Matrix matrix1, Matrix matrix2) throws UndefinedSolutionException
    {
        BigDecimal[][] returnMatrix = matrix1.getWrappedMatrix();

        if (!matrix1.sizeEquals(matrix2))
        {
            throw new UndefinedSolutionException(ErrorMessages.notSameSize);
        }

        else
        {
            for (int i = 0; i < MultiDimensionalArray.columnHeight(returnMatrix); i++)
            {
                for (int j = 0; j < MultiDimensionalArray.rowLength(returnMatrix); j++)
                {
                    returnMatrix[i][j] = returnMatrix[i][j].add(matrix2.getWrappedMatrix()[i][j]);
                }
            }

            Matrix additionMatrix = new Matrix(returnMatrix);

            return additionMatrix;
        }
    }


    /**
     *	Subtracts 2 Matrices from each other, Matrices must be of same size
     *	@param matrix1 Matrix subtracting from
     *	@param matrix2 Matrix subtracting
     *	@return Matrix Matrix created from subtracting 2 Matrices from each other
     *	@throws UndefinedSolutionException Thrown when 2 Matrices not of same size
     */
    public static Matrix matrixSubtraction(Matrix matrix1, Matrix matrix2) throws UndefinedSolutionException
    {
        BigDecimal[][] returnMatrix = matrix1.getWrappedMatrix();

        if (!matrix1.sizeEquals(matrix2))
        {
            throw new UndefinedSolutionException(ErrorMessages.notSameSize);
        }

        else
        {
            for (int i = 0; i < MultiDimensionalArray.columnHeight(returnMatrix); i++)
            {
                for (int j = 0; j < MultiDimensionalArray.rowLength(returnMatrix); j++)
                {
                    returnMatrix[i][j] =  returnMatrix[i][j].subtract(matrix2.getWrappedMatrix()[i][j]);
                }
            }

            Matrix subtractionMatrix = new Matrix(returnMatrix);

            return subtractionMatrix;
        }
    }


    /**
     *	Multiplies all values of Matrix by scalar
     *	@param matrix Matrix multiplying
     * 	@param scalar Number multiplying Matrix by
     * 	@return Matrix New Matrix from scalar multiplication
     */
    public static Matrix scalarMultiplication(Matrix matrix, BigDecimal scalar)
    {
        BigDecimal[][] returnMatrix = matrix.getWrappedMatrix();

        for (int i = 0; i < MultiDimensionalArray.columnHeight(returnMatrix); i++)
        {
            for (int j = 0; j < MultiDimensionalArray.rowLength(returnMatrix); j++)
            {
                returnMatrix[i][j] = returnMatrix[i][j].multiply(scalar);
            }
        }

        Matrix scalarMultiplicationMatrix = new Matrix(returnMatrix);

        return scalarMultiplicationMatrix;
    }


    /**
     *	Multiplies 2 Matrices together
     *	@param matrix1 First Matrix multiplying
     *	@param matrix2 Second Matrix multiplying
     *	@return Matrix New Matrix from Matrix multiplication
     *	@throws UndefinedSolutionException Thrown when row length of Matrix 1 not same length as column height of Matrix 2
     */
    public static Matrix matrixMultiplication(Matrix matrix1, Matrix matrix2) throws UndefinedSolutionException
    {
        if (matrix1.getRowLength() != matrix2.getColumnHeight())
        {
            throw new UndefinedSolutionException("row length of first matrix must be same size as column height of second matrix to perform matrix multiplication: " + matrix1.getSize() + " & " + matrix2.getSize());
        }

        BigDecimal[][] matrixProduct = new BigDecimal[matrix1.getColumnHeight()][matrix2.getRowLength()];
        BigDecimal[] rowMatrix1;
        BigDecimal[] columnMatrix2;

        for (int i = 0; i < MultiDimensionalArray.columnHeight(matrixProduct); i++)
        {
            for (int j = 0; j < MultiDimensionalArray.rowLength(matrixProduct); j++)
            {
                matrixProduct[i][j] = BigDecimal.ZERO;

                rowMatrix1 = matrix1.getRow(i);
                columnMatrix2 = matrix2.getColumn(j);

                for (int a = 0; a < rowMatrix1.length; a++)
                {
                    matrixProduct[i][j] = matrixProduct[i][j].add(rowMatrix1[a].multiply(columnMatrix2[a]));
                }
            }
        }

        Matrix product = new Matrix(matrixProduct);

        return product;
    }


    /**
     *	Returns the transpose of a Matrix
     *	@param matrix Matrix taking tranpose of
     *	@return Matrix Tranpose of Matrix input
     */
    public static Matrix transposeMatrix(Matrix matrix)
    {
        BigDecimal[][] transposeMatrix = new BigDecimal[matrix.getRowLength()][matrix.getColumnHeight()];

        for (int i = 0; i < MultiDimensionalArray.columnHeight(transposeMatrix); i++)
        {
            for (int j = 0; j < MultiDimensionalArray.rowLength(transposeMatrix); j++)
            {
                transposeMatrix[i][j] = matrix.getWrappedMatrix()[j][i];
            }
        }

        Matrix transpose = new Matrix(transposeMatrix);

        return transpose;
    }



    /**
     *	Finds the trace of a square Matrix
     *	@param matrix Matrix taking trace of
     *	@return double Trace of Matrix
     *	@throws NonSquareMatrixException Thrown when Matrix not square
     */
    public static BigDecimal traceMatrix(Matrix matrix) throws NonSquareMatrixException
    {
        if (!matrix.getSquareStatus())
        {
            throw new NonSquareMatrixException(ErrorMessages.nonSquare);
        }

        BigDecimal trace = BigDecimal.ZERO;

        for (int i = 0; i < matrix.getColumnHeight(); i++)
        {
            trace = trace.add(matrix.getWrappedMatrix()[i][i]);
        }

        return trace;
    }


    /**
     *	Multiples Matrix to power n
     *	@param matrix Matrix raising to power
     *	@param power Power raising Matrix to
     *	@return Matrix Matrix after raised to power
     *	@throws NonSquareMatrixException Thrown when Matrix not square
     */
    public static Matrix matrixToPower(Matrix matrix, int power) throws NonSquareMatrixException
    {
        if (!matrix.getSquareStatus())
        {
            throw new NonSquareMatrixException(ErrorMessages.nonSquare);
        }

        if (power == 0)
        {
            return Matrix.identity(matrix.getColumnHeight());
        }

        else if (power > 0)
        {
            try
            {
                return matrixMultiplication(matrix, matrixToPower(matrix, power - 1));
            }

            catch (UndefinedSolutionException rowNotColumn)
            {
                //won't get here because of square test
            }
        }

        else if (power < 0)
        {
            Matrix returnMatrix = matrixToPower(matrix, Math.abs(power));

            return returnMatrix.inverse();
        }

        return null; //will never get here because of square test
    }


}
