package math.shell.linearAlgebra.calculator.storages;

import math.shell.linearAlgebra.calculator.exceptions.ErrorMessages;
import math.shell.linearAlgebra.calculator.Matrix;
import math.shell.linearAlgebra.calculator.utilities.StringUtils;
import math.shell.linearAlgebra.calculator.exceptions.IncorrectSyntaxException;
import math.shell.linearAlgebra.calculator.userInterface.Print;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.Writer;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 *	Methods that deal with reading and writing the calculators external storages, stored in "calculator_storage.txt"
 *	@author Avraham Katz
 *	@version 1.1
 */

public class ExternalStorage
{


    /**
     *	When exit program, converts all Matrices in calculator internal storages (InternalStorage.Singleton) to their string represenation and stores them in external file "calculator_storage.txt"
     */
    public static void writeExternalStorage()
    {
        Writer fileWriter = null;
        Iterator it = InternalStorage.Singleton.storedInternalStorage.entrySet().iterator();

        try
        {
            fileWriter = new FileWriter("calculator_storage.txt");

            while (it.hasNext())
            {
                Map.Entry pair = (Map.Entry)it.next();
                Matrix matrix = (Matrix) pair.getValue();
                fileWriter.write(pair.getKey() + " = " + matrix.toString() + System.getProperty( "line.separator" ));
            }

            fileWriter.close();
        }

        catch (IOException e)
        {
            System.out.println("cannot find calculator_storage.txt");
        }
    }


    /**
     *	When open program, any Matrices in string represenation in external file "calculator_storage.txt" converted to Matrix object, and stored with associated variables in calculator internal storages (InternalStorage.Singleton)
     */
    public static void readExternalStorage()
    {
        int line = 0;

        try
        {
            FileReader fr = new FileReader("calculator_storage.txt");
            BufferedReader br = new BufferedReader(fr);

            String lineReading = br.readLine();

            while (lineReading != null)
            {
                if (lineReading.trim().length() > 0)
                {
                    line++;

                    String equation = lineReading.toLowerCase();
                    String string = StringUtils.removeBlankSpace(equation);

                    if (Character.isLetter(string.charAt(0)) && string.charAt(1) == '=' && string.charAt(2) == '[' && string.endsWith("]")) //M=[R,R,R,R/R,R,R,R]
                    {
                        String matrixName = "" + string.charAt(0);
                        String matrixString = string.substring(2);

                        try
                        {
                            Matrix newMatrix = new Matrix(matrixString);
                            InternalStorage.Singleton.add(matrixName, newMatrix);
                        }

                        catch (IllegalArgumentException all)
                        {
                            System.out.println("Matrix " + matrixName + " not stored, matrix cannot have different sized rows");
                            line--;
                        }
                    }

                    else
                    {
                        throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                    }
                }

                lineReading = br.readLine();
            }

            if (line == 0)
            {
                System.out.printf("No matrices in storages. Must create at least 1 matrix to do any operations. %nIf needs to reference format to create matrix, enter 'frmt' to go to operations and format menu, enter '17', and will display how to create a new matrix %n%n");
                fr.close();
                br.close();
            }

            else
            {
                System.out.printf("You have the following matrices from input file in your storages currently... %n%n");
                Print.printAllInternalStorage();
                System.out.printf("******************************************************************************************************************************** %n%n");
                fr.close();
                br.close();
            }
        }


        catch (IOException e)
        {
            System.out.println("cannot find calculator_storage.txt");
            System.exit(0);
        }
    }
}