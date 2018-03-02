package math.shell.linearAlgebra.calculator;

import org.junit.ComparisonFailure;

public class CalculatorAssert
{
    /**
     * Asserts 2 values are equals within an error of .000000001
     * @param matrix1   First value comparing
     * @param matrix2   Second value comparing
     */
    public static void assertMatrixEquals(Matrix matrix1, Matrix matrix2)
    {
        if (!matrix1.equalsWithinMargin(matrix2))
        {
            throw new ComparisonFailure("", matrix1.toString(), matrix2.toString());
        }
    }
}
