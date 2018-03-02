package math.shell.linearAlgebra.calculator.exceptions;

/**
 *	A list of Strings used for error messages and exceptions
 *	@author Avraham Katz
 *	@version 1.1
 */

public class ErrorMessages
{
    public static String column0 = "cannot have column less than 0";
    public static String row0 = "cannot have row less than 0";
    public static String nullMatrix = "matrix cannot be null";
    public static String badRowNumber = "row i does not exist in matrix"; //might pull this back
    public static String badColumnNumber = "column j does not exist in matrix"; //might pull this back
    public static String needLeadingOne = "need leading one to use operation";
    public static String zeroRow = "no non-zero values in row";
    public static String notSameSize = "matrices must be of same size to complete matrix operation";
    public static String rowNotColumn = "row length of first matrix must be same size as column height of second matrix";
    public static String notInvertible = "matrix not invertible";
    public static String nonSquare = "matrix must be square matrix to complete this operation";
    public static String nullName = "name cannot be null";
    public static String nullString = "string cannot be null";
    public static String emptyName = "name cannot be empty";
    public static String badSyntax = "ERROR: cannot perform operation..incorrect/unoperational syntax";
    public static String nonMatrix = "ERROR: cannot complete opertion...last output not of type Matrix";
    public static String nullState = "ERROR: cannot complete operation...no matrices or doubles in queue (either no operations performed, or previous operation illegal/bad operation)";
    public static String nonDouble = "ERROR: cannot complete operation...last output not of type double";
}