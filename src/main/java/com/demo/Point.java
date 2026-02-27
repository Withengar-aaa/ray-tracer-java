package com.demo;

import lombok.Data;

/**
 * @author Withengar
 */
@Data
public class Point
{
    double x;
    double y;
    double z;

    public Point(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }



}
