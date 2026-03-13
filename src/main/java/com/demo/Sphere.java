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

    int specular;

    public Sphere( Point center,double radius,Color color,int specular)
    {
        this.color = color;
        this.radius = radius;
        this.center = center;
        this.specular = specular;
    }
}
