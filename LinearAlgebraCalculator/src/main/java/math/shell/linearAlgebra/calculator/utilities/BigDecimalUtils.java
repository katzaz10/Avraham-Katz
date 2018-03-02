package math.shell.linearAlgebra.calculator.utilities;

import java.math.BigDecimal;

public class BigDecimalUtils
{
    public static BigDecimal precisionCheck(BigDecimal value)
    {
        String valueAsString = value.toPlainString();

        if (valueAsString.contains(".000000000"))
        {
            String wholeValue = valueAsString.substring(0, valueAsString.indexOf(".000000000"));
            BigDecimal returnValue = new BigDecimal(wholeValue);
            return returnValue;
        }

        return value;
    }

}
