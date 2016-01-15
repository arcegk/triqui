package com.eda2.triqui.util;

/**
 *
 * @author oarcila
 */
public class ExceptionBadLabelAsign extends Exception
{
    private String val;
    
    public ExceptionBadLabelAsign(String val)
    {
        this.val = val;
    }

    @Override
    public String getMessage()
    {
        return "Se asino un valor erroneo a " + val;
    }
    
}
