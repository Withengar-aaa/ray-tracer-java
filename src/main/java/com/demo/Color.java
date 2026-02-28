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

}
