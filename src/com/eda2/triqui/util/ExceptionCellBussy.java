package com.eda2.triqui.util;

/**
 *
 * @author oarcila
 */
public class ExceptionCellBussy extends Exception
{
    private int f;
    private int c;

    public ExceptionCellBussy(int f, int c)
    {
        this.f = f;
        this.c = c;
    }

    @Override
    public String getMessage()
    {
        return "La casilla " + f + ", " + c + " ya esta usada";
    }
    
}
