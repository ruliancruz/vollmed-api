package med.voll.api.infrastructure.utility;

public class StringValidation
{
    public static boolean isNullEmptyOrBlank(String string)
    {
        if(string == null || string.isEmpty() || string.isBlank())
            return true;
        else
            return false;
    }
}