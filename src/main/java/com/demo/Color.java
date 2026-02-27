package com.demo;

import lombok.Data;

/**
 * @author withengar
 */
@Data
public class Color
{

    int red;
    int green;
    int blue;


    public Color(int red,int green,int blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
}
