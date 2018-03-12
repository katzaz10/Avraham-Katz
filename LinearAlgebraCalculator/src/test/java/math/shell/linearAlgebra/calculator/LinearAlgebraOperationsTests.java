package math.shell.linearAlgebra.calculator;

import math.shell.linearAlgebra.calculator.exceptions.NonSquareMatrixException;
import org.junit.Assert;
import math.shell.linearAlgebra.calculator.CalculatorAssert.*;
import math.shell.linearAlgebra.calculator.exceptions.UndefinedSolutionException;
import org.junit.Test;

import java.math.BigDecimal;


public class LinearAlgebraOperationsTests {
    
    
    /**
     * Tests the matrix addition operation of matrices
     */
    @Test
    public void testMatrixAddition() throws UndefinedSolutionException {
        Matrix matrix1 = new Matrix("[1,2|3,4|]");
        Matrix matrix2 = new Matrix("[2,4|6,8|]");
        Matrix resultMatrix = new Matrix("[3,6|9,12|]");

        CalculatorAssert.assertMatrixEquals(resultMatrix, matrix1.add(matrix2));
        CalculatorAssert.assertMatrixEquals(resultMatrix, matrix2.add(matrix1));
    }


    /**
     * Tests the matrix subtraction operation of matrices
     */
    @Test
    public void testMatrixSubtraction() throws UndefinedSolutionException {
        Matrix matrix1 = new Matrix("[1,2|3,4|]");
        Matrix matrix2 = new Matrix("[2,4|6,8|]");
        Matrix resultMatrix1 = new Matrix("[-1,-2|-3,-4|]");
        Matrix resultMatrix2 = new Matrix("[1,2|3,4|]");

        CalculatorAssert.assertMatrixEquals(resultMatrix1, matrix1.subtract(matrix2));
        CalculatorAssert.assertMatrixEquals(resultMatrix2, matrix2.subtract(matrix1));
    }


    /**
     * Tests the matrix multiplication operation on square matrices
     */
    @Test
    public void testMatrixMultiplicationSquareMatrices() throws UndefinedSolutionException {
        Matrix matrix1 = new Matrix("[1,2|3,4|]");
        Matrix matrix2 = new Matrix("[2,4|6,8|]");
        Matrix resultMatrix1 = new Matrix("[14,20|30,44|]");
        Matrix resultMatrix2 = new Matrix("[14,20|30,44|]");

        CalculatorAssert.assertMatrixEquals(resultMatrix1, matrix1.multiply(matrix2));
        CalculatorAssert.assertMatrixEquals(resultMatrix2, matrix2.multiply(matrix1));
    }


    /**
     * Tests the matrix multiplication operation on matrices of different sizes
     */
    @Test
    public void testMatrixMultiplicationNonSquareMatrices() throws UndefinedSolutionException {
        Matrix matrix1 = new Matrix("[1,3|2,4|3,7|]");
        Matrix matrix2 = new Matrix("[5,-2,0,0|0,1,2,7|]");
        Matrix resultMatrix = new Matrix("[5,1,6,21|10,0,8,28|15,1,14,49|]");

        CalculatorAssert.assertMatrixEquals(resultMatrix, matrix1.multiply(matrix2));
    }


    /**
     * Tests the scalar multiplication operation of matrices
     */
    @Test
    public void testScalarMultiplication() {
        Matrix matrix1 = new Matrix("[1,2|3,4|]");
        BigDecimal scalar1 = new BigDecimal(4);
        Matrix resultMatrix1 = new Matrix("[4,8|12,16|]");

        CalculatorAssert.assertMatrixEquals(resultMatrix1, matrix1.scalarMultiply(scalar1));

        BigDecimal scalar2 = new BigDecimal(.5);
        Matrix resultMatrix2 = new Matrix("[.5,1|1.5,2|]");

        CalculatorAssert.assertMatrixEquals(resultMatrix2, matrix1.scalarMultiply(scalar2));
    }


    /**
     * Tests the tranpose operation of matrices
     */
    @Test
    public void testTranspose() {
        Matrix matrix1 = new Matrix("[1,5|6,7|]");
        Matrix resultMatrix1 = new Matrix("[1,6|5,7|]");

        CalculatorAssert.assertMatrixEquals(resultMatrix1, matrix1.transpose());

        Matrix matrix2 = new Matrix("[1,2,3,4,5,6|7,8,9,0,0,0|]");
        Matrix resultMatrix2 = new Matrix("[1,7|2,8|3,9|4,0|5,0|6,0|]");

        CalculatorAssert.assertMatrixEquals(resultMatrix2, matrix2.transpose());
    }


    /**
     * Tests the trace operation of matrices
     */
    @Test
    public void testTrace() throws NonSquareMatrixException {
        Matrix matrix1 = new Matrix("[1,2,3|3,4,5|5,6,7|]");
        BigDecimal resultTrace = new BigDecimal(12);

        Assert.assertEquals(resultTrace, matrix1.trace());
    }


    /**
     * Tests the matrix raised to a power operation of matrices
     */
    @Test
    public void testMatrixToExponent() throws NonSquareMatrixException {
        Matrix matrix1 = new Matrix("[1,2|3,0|]");
        Integer power1 = 4;
        Matrix resultMatrix1 = new Matrix("[55,26|39,42|]");

        CalculatorAssert.assertMatrixEquals(resultMatrix1, matrix1.toPower(4));
    }


    /**
     * Tests the matrix raised to a negative power operation of matrices
     */
    @Test
    public void testMatrixToNegativeExponent() throws NonSquareMatrixException {
        Matrix matrix1 = new Matrix("[1,2|3,0|]");
        Integer power1 = -2;
        Matrix resultMatrix1 = new Matrix("[1/6,-1/18|-1/12,7/36|]");

        CalculatorAssert.assertMatrixEquals(resultMatrix1, matrix1.toPower(power1));
    }


    /**
     * Tests the row echelon form operation of matrices
     */
    @Test
    public void testRowEchelonForm() {
        Matrix matrix1 = new Matrix("[1,3,-2,0,2,0,0|2,6,-5,-2,4,-3,-1|0,0,5,10,0,15,5|2,6,0,8,4,18,6|]");
        Matrix resultMatrix = new Matrix("[1,3,-2,0,2,0,0|0,0,1,2,0,3,1|0,0,0,0,0,1,1/3|0,0,0,0,0,0,0|]");

        CalculatorAssert.assertMatrixEquals(resultMatrix, matrix1.rowEchelonForm());
    }


    /**
     * Tests the reduced row echelon form operation of matrices
     */
    @Test
    public void testreducedRowEchelonForm() {
        Matrix matrix1 = new Matrix("[1,3,-2,0,2,0,0|2,6,-5,-2,4,-3,-1|0,0,5,10,0,15,5|2,6,0,8,4,18,6|]");
        Matrix resultMatrix = new Matrix("[1,3,0,4,2,0,0|0,0,1,2,0,0,0|0,0,0,0,0,1,1/3|0,0,0,0,0,0,0|]");

        CalculatorAssert.assertMatrixEquals(resultMatrix, matrix1.reducedRowEchelonForm());
    }


    /**
     * Tests the inverse operation of 2x2 matrices
     */
    @Test
    public void testInverse2x2()
    {
        Matrix matrix1 = new Matrix("[6,1|5,2|]");
        Matrix resultMatrix = new Matrix("[2/7,-1/7|-5/7,6/7|]");

        CalculatorAssert.assertMatrixEquals(resultMatrix, matrix1.inverse());
    }


    /**
     * Tests the inverse operation of 3x3 matrices
     */
    @Test
    public void testInverse3x3()
    {
        Matrix matrix1 = new Matrix("[1,2,3|2,5,3|1,0,8|]");
        Matrix resultMatrix = new Matrix("[-40,16,9|13,-5,-3|5,-2,-1|]");

        CalculatorAssert.assertMatrixEquals(resultMatrix, matrix1.inverse());
    }


    /**
     * Tests the inverse operation
     */
    @Test
    public void testInverse()
    {
        Matrix matrix1 = new Matrix("[1,2|1,3|]");
        Matrix resultMatrix = new Matrix("[3,-2|-1,1|]");

        CalculatorAssert.assertMatrixEquals(resultMatrix, matrix1.inverse());
    }


    /**
     * Tests the determinant operation of 1x1 matrices
     */
    @Test
    public void testDeterminant1x1() throws NonSquareMatrixException
    {
        Matrix matrix1 = new Matrix("[5|]");
        BigDecimal resultDeterminant = new BigDecimal(5);

        Assert.assertEquals(resultDeterminant, matrix1.determinant());
    }


    /**
     * Tests the determinant operation of 2x2 matrices
     */
    @Test
    public void testDeterminant2x2() throws NonSquareMatrixException
    {
        Matrix matrix1 = new Matrix("[5,6|1,5|]");
        BigDecimal resultDeterminant = new BigDecimal(19);

        Assert.assertEquals(resultDeterminant, matrix1.determinant());
    }


    /**
     * Tests the determinant operation of 3x3 matrices
     */
    @Test
    public void testDeterminant3x3() throws NonSquareMatrixException
    {
        Matrix matrix1 = new Matrix("[1,2,3|-4,5,6|7,-8,9|]");
        BigDecimal resultDeterminant = new BigDecimal(240);

        Assert.assertEquals(resultDeterminant, matrix1.determinant());
    }


    /**
     * Tests the determinant operation of triangular matrices
     */
    @Test
    public void testDeterminantTriangular() throws NonSquareMatrixException
    {
        Matrix matrix1 = new Matrix("[1,2,3|0,5,6|0,0,9|]");
        BigDecimal resultDeterminant = new BigDecimal(45);

        Assert.assertEquals(resultDeterminant, matrix1.determinant());
    }


    /**
     * Tests the determinant operation of matrices
     */
    @Test
    public void testDeterminant() throws NonSquareMatrixException
    {
        Matrix matrix1 = new Matrix("[1,0,0,3|2,7,0,6|0,6,3,0|7,3,1,-5|]");
        BigDecimal resultDeterminant = new BigDecimal(-546);

        Assert.assertEquals(resultDeterminant, matrix1.determinant());
    }


    /**
     * Tests the minor operation of matrices
     */
    @Test
    public void testMinor() throws NonSquareMatrixException
    {
        Matrix matrix1 = new Matrix("[1,2,3|4,5,6|7,8,9|]");
        int row = 0;    //by user interface, user inputs values 1 to n, and program reads in as 0 to n-1 for 2d array purposes
        int column = 0;
        Matrix resultMatrix = new Matrix("[5,6|8,9|]");

        CalculatorAssert.assertMatrixEquals(resultMatrix, matrix1.minor(row, column));
    }


    /**
     * Tests the cofactor operation of matrices
     */
    @Test
    public void testCofactor() throws NonSquareMatrixException
    {
        Matrix matrix1 = new Matrix("[3,1,-4|2,5,6|1,4,8|]");
        int row = 0;
        int column = 0;
        BigDecimal cofactorResult = new BigDecimal(16);

        Assert.assertEquals(cofactorResult, matrix1.cofactor(row, column));
    }


    /**
     * Tests the cofactor matrix operation of matrices
     */
    @Test
    public void testCofactorMatrix() throws NonSquareMatrixException
    {
        Matrix matrix1 = new Matrix("[3,2,-1|1,6,3|2,-4,0|]");
        Matrix cofactorMatrixResult = new Matrix("[12,6,-16|4,2,16|12,-10,16|]");

        CalculatorAssert.assertMatrixEquals(cofactorMatrixResult, matrix1.cofactorMatrix());
    }


    /**
     * Tests the adjoint matrix operation of matrices
     */
    @Test
    public void testAdjointMatrix() throws NonSquareMatrixException
    {
        Matrix matrix1 = new Matrix("[3,2,-1|1,6,3|2,-4,0|]");
        Matrix cofactorMatrixResult = new Matrix("[12,4,12|6,2,-10|-16,16,16|]");

        CalculatorAssert.assertMatrixEquals(cofactorMatrixResult, matrix1.adjointMatrix());
    }


    /**
     * Tests the identity matrix operation of matrices
     */
    @Test
    public void testIdentity()
    {
        int matrixSize = 3;
        Matrix resultMatrix = new Matrix("[1,0,0|0,1,0|0,0,1|]");

        CalculatorAssert.assertMatrixEquals(resultMatrix, Matrix.identity(matrixSize));
    }


}