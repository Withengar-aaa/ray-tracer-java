package com.demo;

import lombok.Data;

@Data
public class Vector3D
{
    double x;

    double y;

    double z;

    public Vector3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3D subtract(Point v1,Point v2)
    {
        return new Vector3D(v1.x-v2.x,v1.y-v2.y,v1.z-v2.z);
    }

    public static double dotProduct(Vector3D v1,Vector3D v2)
    {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }
}
