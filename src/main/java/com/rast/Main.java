package com.rast;

import com.demo.Color;
import com.demo.PixelDrawer;
import com.demo.Point;
import com.demo.Vector3D;

public class Main
{

    PixelDrawer drawer = PixelDrawer.getInstance(600,800);

    public static void main(String[] args)
    {

        Main main = new Main();

        main.drawLine(-200,-100,140,120,new Color(255,255,255));

        main.drawLine(-50,-200,60,240,new Color(255,255,255));

        main.drawer.show("直线");

    }


    public void drawLine(int x0, int y0, int x1, int y1,Color color)
    {

        float dx = x1 - x0;

        float dy = y1 - y0;

        float a = dy / dx;

        float b = y0 - a * x0;

        for (int x = x0;x <= x1;x++)
        {
            int y =Math.round(a * x + b);

            drawer.writePixel(x,y,color);
        }

    }

}
