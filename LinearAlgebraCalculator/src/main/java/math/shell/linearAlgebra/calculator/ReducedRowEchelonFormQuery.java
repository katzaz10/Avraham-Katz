package math.shell.linearAlgebra.calculator;

public class ReducedRowEchelonFormQuery implements CalculatorQuery
{
    private Matrix matrix;

    public ReducedRowEchelonFormQuery(Matrix matrix)
    {
        this.matrix = matrix;
    }

    public void runOperation()
    {
        //Matrix matrix = LastMatrixOutput.Singleton.getLastOutput();
        Matrix finalMatrix = this.matrix.reducedRowEchelonForm();
        LastMatrixOutput.Singleton.add(finalMatrix);
        //currentType = CalculatorParser.OutputType.MATRIX;
        Print.printMatrix(finalMatrix);
    }
}
