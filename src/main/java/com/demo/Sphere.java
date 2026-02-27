package com.demo;

import lombok.Data;

/**
 * @author Withengar
 */
@Data
public class Sphere
{
    Point center;

    double radius;

    Color color;

    public Sphere( Point center,double radius,Color color)
    {
        this.color = color;
        this.radius = radius;
        this.center = center;
    }
}
