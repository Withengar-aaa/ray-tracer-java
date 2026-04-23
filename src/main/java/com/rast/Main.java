package com.rast;

import com.demo.Color;
import com.demo.PixelDrawer;
import com.demo.Point;
import com.demo.Vector3D;

import java.util.ArrayList;
import java.util.List;


public class Main
{

    public static PixelDrawer drawer = PixelDrawer.getInstance(600,800);

    public static void main(String[] args)
    {

        Line line = new Line();

        line.drawLine(new Point2D(-200,-250),new Point2D(200,50),new Color(255,255,255),drawer);

        line.drawLine(new Point2D(200,50),new Point2D(20,250),new Color(255,255,255),drawer);

        line.drawLine(new Point2D(20,250),new Point2D(-200,-250),new Color(255,255,255),drawer);

        DrawFilledTriangle(new Point2D(-200,-250),new Point2D(200,50),new Point2D(20,250),new Color(255,255,255));

        drawer.show("直线");

    }


    public static void DrawFilledTriangle(Point2D p0,Point2D p1,Point2D p2,Color color)
    {
        // Sort the points from bottom to top.
        if (p1.y() < p0.y())
        {
            var swap = p0;
            p0 = p1;
            p1 = swap;
        }
        if (p2.y() < p0.y())
        {
            var swap = p0;
            p0 = p2;
            p2 = swap;
        }
        if (p2.y() < p1.y())
        {
            var swap = p1;
            p1 = p2;
            p2 = swap;
        }

        Line line = new Line();

        List<Integer> x01 = line.interpolate(p0.y(), p0.x(), p1.y(), p1.x());

        List<Integer> x12 = line.interpolate(p1.y(), p1.x(), p2.y(), p2.x());

        List<Integer> x02 = line.interpolate(p0.y(), p0.x(), p2.y(), p2.x());

        List<Integer> x012 = new ArrayList<>();

        x012.addAll(x01);

        x012.remove(x012.size() - 1);

        x012.addAll(x12);

        List<Integer> xLeft = new ArrayList<>();

        List<Integer> xRight = new ArrayList<>();

        int m = x02.size() / 2;

        if (x02.get(m) < x012.get(m))
        {
            xLeft = x02;
            xRight = x012;
        }
        else
        {
            xLeft = x012;
            xRight = x02;
        }

        for (int y = p0.y();y <= p2.y();y++)
        {
            for (var x = xLeft.get(y - p0.y()); x <= xRight.get(y - p0.y()); x++)
            {
                drawer.writePixel(x,y,color);
            }
        }


    }


}
