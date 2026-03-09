package com.demo;

import lombok.Data;

/**
 * 定义颜色类RGB
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

    // 将自定义Color转换为imageBuffer.setRGB需要的int格式
    public int toRGB()
    {
        return (255 << 24) | (this.red << 16) | (this.green << 8) | this.blue;
    }


    //使值保持在0到255并转换为int值
    public static int clamp(double value)
    {
        double clampedValue = Math.max(0, Math.min(255, value));

        long roundedValue = Math.round(clampedValue);

        return  Math.toIntExact(roundedValue);
    }



}
