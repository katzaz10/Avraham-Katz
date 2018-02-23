package math.shell.linearAlgebra.calculator;

public class InverseQuery implements CalculatorQuery
{
    private Matrix matrix;

    public InverseQuery(Matrix matrix)
    {
        this.matrix = matrix;
    }

    public void runOperation()
    {
        //Matrix matrix = LastMatrixOutput.Singleton.getLastOutput();
        Matrix finalMatrix = this.matrix.inverse();
        LastMatrixOutput.Singleton.add(finalMatrix);
        //currentType = CalculatorParser.OutputType.MATRIX;
        Print.printMatrix(finalMatrix);
    }
}
