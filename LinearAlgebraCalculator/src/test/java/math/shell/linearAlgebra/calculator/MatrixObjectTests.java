package math.shell.linearAlgebra.calculator;

import org.junit.Assert;
import org.junit.Test;

import org.junit.*;
import org.junit.Assert.*;

public class MatrixObjectTests
{
    @Test
    public void test1()
    {
        Matrix matrix1 = new Matrix("[1,2|3,4|]");

        Assert.assertEquals(true, matrix1.getSquareStatus());
        Assert.assertEquals(true, matrix1.getTwoByTwoStatus());
        Assert.assertEquals(false, matrix1.getThreeByThreeStatus());
        Assert.assertEquals(false, matrix1.getIdentityStatus());
        Assert.assertEquals(false, matrix1.getDiagonalStatus());
        Assert.assertEquals(false, matrix1.getLowerTriangularStatus());
        Assert.assertEquals(false, matrix1.getUpperTriangularStatus());
        Assert.assertEquals(false, matrix1.getTriangularStatus());
    }

    @Test
    public void test2()
    {
        Matrix matrix1 = new Matrix("[1,2,3|4,5,6|7,8,9|]");

        Assert.assertEquals(true, matrix1.getSquareStatus());
        Assert.assertEquals(false, matrix1.getTwoByTwoStatus());
        Assert.assertEquals(true, matrix1.getThreeByThreeStatus());
        Assert.assertEquals(false, matrix1.getIdentityStatus());
        Assert.assertEquals(false, matrix1.getDiagonalStatus());
        Assert.assertEquals(false, matrix1.getLowerTriangularStatus());
        Assert.assertEquals(false, matrix1.getUpperTriangularStatus());
        Assert.assertEquals(false, matrix1.getTriangularStatus());
    }


    @Test
    public void test3()
    {
        Matrix matrix1 = new Matrix("[1,2,3|4,5,6|]");

        Assert.assertEquals(false, matrix1.getSquareStatus());
        Assert.assertEquals(false, matrix1.getTwoByTwoStatus());
        Assert.assertEquals(false, matrix1.getThreeByThreeStatus());
        Assert.assertEquals(false, matrix1.getIdentityStatus());
        Assert.assertEquals(false, matrix1.getDiagonalStatus());
        Assert.assertEquals(false, matrix1.getLowerTriangularStatus());
        Assert.assertEquals(false, matrix1.getUpperTriangularStatus());
        Assert.assertEquals(false, matrix1.getTriangularStatus());
    }


    @Test
    public void test4()
    {
        Matrix matrix1 = new Matrix("[1,0,0|0,1,0|0,0,1|]");

        Assert.assertEquals(true, matrix1.getSquareStatus());
        Assert.assertEquals(false, matrix1.getTwoByTwoStatus());
        Assert.assertEquals(true, matrix1.getThreeByThreeStatus());
        Assert.assertEquals(true, matrix1.getIdentityStatus());
        Assert.assertEquals(true, matrix1.getDiagonalStatus());
        Assert.assertEquals(true, matrix1.getLowerTriangularStatus());
        Assert.assertEquals(true, matrix1.getUpperTriangularStatus());
        Assert.assertEquals(true, matrix1.getTriangularStatus());
    }


    @Test
    public void test5()
    {
        Matrix matrix1 = new Matrix("[1,2,3|0,4,5|0,0,6|]");

        Assert.assertEquals(true, matrix1.getSquareStatus());
        Assert.assertEquals(false, matrix1.getTwoByTwoStatus());
        Assert.assertEquals(true, matrix1.getThreeByThreeStatus());
        Assert.assertEquals(false, matrix1.getIdentityStatus());
        Assert.assertEquals(false, matrix1.getDiagonalStatus());
        Assert.assertEquals(false, matrix1.getLowerTriangularStatus());
        Assert.assertEquals(true, matrix1.getUpperTriangularStatus());
        Assert.assertEquals(true, matrix1.getTriangularStatus());
    }


    @Test
    public void test6()
    {
        Matrix matrix1 = new Matrix("[1,0,0|2,3,0|4,5,6|]");

        Assert.assertEquals(true, matrix1.getSquareStatus());
        Assert.assertEquals(false, matrix1.getTwoByTwoStatus());
        Assert.assertEquals(true, matrix1.getThreeByThreeStatus());
        Assert.assertEquals(false, matrix1.getIdentityStatus());
        Assert.assertEquals(false, matrix1.getDiagonalStatus());
        Assert.assertEquals(true, matrix1.getLowerTriangularStatus());
        Assert.assertEquals(false, matrix1.getUpperTriangularStatus());
        Assert.assertEquals(true, matrix1.getTriangularStatus());
    }


    @Test
    public void test7()
    {
        Matrix matrix1 = new Matrix("[1,0,0|0,2,0|0,0,3|]");

        Assert.assertEquals(true, matrix1.getSquareStatus());
        Assert.assertEquals(false, matrix1.getTwoByTwoStatus());
        Assert.assertEquals(true, matrix1.getThreeByThreeStatus());
        Assert.assertEquals(false, matrix1.getIdentityStatus());
        Assert.assertEquals(true, matrix1.getDiagonalStatus());
        Assert.assertEquals(true, matrix1.getLowerTriangularStatus());
        Assert.assertEquals(true, matrix1.getUpperTriangularStatus());
        Assert.assertEquals(true, matrix1.getTriangularStatus());
    }
    
    
    @Test
    public void test8()
    {
        Matrix matrix1 = new Matrix("[1,0|0,1|]");

        Assert.assertEquals(true, matrix1.getSquareStatus());
        Assert.assertEquals(true, matrix1.getTwoByTwoStatus());
        Assert.assertEquals(false, matrix1.getThreeByThreeStatus());
        Assert.assertEquals(true, matrix1.getIdentityStatus());
        Assert.assertEquals(true, matrix1.getDiagonalStatus());
        Assert.assertEquals(true, matrix1.getLowerTriangularStatus());
        Assert.assertEquals(true, matrix1.getUpperTriangularStatus());
        Assert.assertEquals(true, matrix1.getTriangularStatus());
    }


    @Test
    public void test9()
    {
        Matrix matrix1 = new Matrix("[1,0|2,3|]");

        Assert.assertEquals(true, matrix1.getSquareStatus());
        Assert.assertEquals(true, matrix1.getTwoByTwoStatus());
        Assert.assertEquals(false, matrix1.getThreeByThreeStatus());
        Assert.assertEquals(false, matrix1.getIdentityStatus());
        Assert.assertEquals(false, matrix1.getDiagonalStatus());
        Assert.assertEquals(true, matrix1.getLowerTriangularStatus());
        Assert.assertEquals(false, matrix1.getUpperTriangularStatus());
        Assert.assertEquals(true, matrix1.getTriangularStatus());
    }

    @Test
    public void test10()
    {
        Matrix matrix1 = new Matrix("[1,2|0,3|]");

        Assert.assertEquals(true, matrix1.getSquareStatus());
        Assert.assertEquals(true, matrix1.getTwoByTwoStatus());
        Assert.assertEquals(false, matrix1.getThreeByThreeStatus());
        Assert.assertEquals(false, matrix1.getIdentityStatus());
        Assert.assertEquals(false, matrix1.getDiagonalStatus());
        Assert.assertEquals(false, matrix1.getLowerTriangularStatus());
        Assert.assertEquals(true, matrix1.getUpperTriangularStatus());
        Assert.assertEquals(true, matrix1.getTriangularStatus());
    }

}
