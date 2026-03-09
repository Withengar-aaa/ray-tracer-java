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



    public static Vector3D normalize(Vector3D v)
    {
        double mag = Math.sqrt(dotProduct(v, v));

        return new Vector3D(v.x/mag, v.y/mag, v.z/mag);
    }


    public static double vectorLength(Vector3D vector3D)
    {
        return Math.sqrt(dotProduct(vector3D,vector3D));
    }


    public static Vector3D multiply(double k,Vector3D vector3D)
    {
        return new Vector3D(k * vector3D.x,k * vector3D.y,k*vector3D.z);
    }


    public static Vector3D add(Vector3D v1,Vector3D v2)
    {
        return new Vector3D(v1.x + v2.x,v1.y + v2.y,v1.z + v2.z);
    }

    public static Point vectorToPoint(Vector3D vector3D)
    {
        return new Point(vector3D.x,vector3D.y,vector3D.z);
    }


}
