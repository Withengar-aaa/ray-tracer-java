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

    //判断点是否在圆内
    public boolean isPointInside(Point point)
    {
        double x = point.x - center.x;
        double y = point.y - center.y;
        double z = point.z - center.z;
        double distSq = x * x + y * y + z * z;
        return distSq <= radius * radius + Main.epsilon;
    }

    // 布尔A - B差集
    public static boolean isPointInDifference(Point point, Sphere sphereA, Sphere sphereB)
    {
        return sphereA.isPointInside(point) && !sphereB.isPointInside(point);
    }

    // 布尔A ∪ B并集
    public static boolean isPointInUnion(Point point, Sphere sphereA, Sphere sphereB)
    {
        return sphereA.isPointInside(point) || sphereB.isPointInside(point);
    }

    // 布尔A ∩ B交集
    public static boolean isPointInIntersection(Point point, Sphere sphereA, Sphere sphereB)
    {
        return sphereA.isPointInside(point) && sphereB.isPointInside(point);
    }

}
