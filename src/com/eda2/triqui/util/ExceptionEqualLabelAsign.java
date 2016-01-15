package com.eda2.triqui.util;

/**
 *
 * @author oarcila
 */
public class ExceptionEqualLabelAsign extends Exception
{
    private String val;

    public ExceptionEqualLabelAsign(String val)
    {
        this.val = val;
    }
    
    @Override
    public String getMessage()
    {
        return "A ambos jugadores se les asigno como marca " + val;
    }

}
