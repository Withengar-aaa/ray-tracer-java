package com.demo;


import lombok.Data;

/**
 * @author Withengar
 * @see LightType
 *
 *
 */
@Data
public class Light
{
    private LightType lightType;

    private double intensity;

    private Point position;

    private Vector3D direction;

    public Light(LightType lightType, double intensity, Point position)
    {
        this.lightType = lightType;

        this.intensity = intensity;

        this.position = position;
    }

    public Light(LightType lightType,double intensity)
    {
        this.lightType = lightType;

        this.intensity = intensity;
    }

    public Light(LightType lightType,double intensity,Vector3D direction)
    {
        this.lightType = lightType;

        this.intensity = intensity;

        this.direction = direction;
    }
}
