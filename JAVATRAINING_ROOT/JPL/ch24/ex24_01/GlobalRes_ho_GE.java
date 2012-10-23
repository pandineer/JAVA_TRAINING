package ch24.ex24_01;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.ResourceBundle;

public class GlobalRes_ho_GE extends ResourceBundle
{

    @Override
    protected Object handleGetObject(String key)
    {
        if (key == GlobalRes.HELLO)
        {
            return "hoge!";
        }
        return null;
    }

    @Override
    public Enumeration<String> getKeys()
    {
        // return Collections.enumeration(keySet());
        return Collections.enumeration(new HashSet<String>(Arrays.asList(GlobalRes.HELLO)));
    }

}
