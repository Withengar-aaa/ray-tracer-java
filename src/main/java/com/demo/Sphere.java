package com.demo;

import lombok.Data;

/**
 * @author Withengar
 * <ul>
 *     <li>center:球体的球心坐标</li>
 *     <li>radius:半径</li>
 *     <li>color:颜色</li>
 *     <li>specular:光泽度</li>
 *     <li>reflective:反射度</li>
 * </ul>
 */
@Data
public class Sphere
{
    Point center;

    double radius;

    Color color;

    int specular;

    double reflective;

    public Sphere(Point center, double radius, Color color, int specular, double reflective)
    {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.specular = specular;
        this.reflective = reflective;
    }
}
