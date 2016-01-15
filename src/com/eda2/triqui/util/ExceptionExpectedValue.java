package com.eda2.triqui.util;

/**
 *
 * @author oarcila
 */
public class ExceptionExpectedValue extends Exception
{
    private int v;

    public ExceptionExpectedValue(int v)
    {
        this.v = v;
    }

    @Override
    public String getMessage()
    {
        return "El valor " + v + " no es permitido en este juego";
    }
    
    
    
}
