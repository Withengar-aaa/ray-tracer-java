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

    public static Point2D p0 = new Point2D(-200,-250,0.3);

    public static Point2D p1 = new Point2D(200,50,0.1);

    public static Point2D p2 = new Point2D(20,250,1.0);

    public List<Triangle> triangles = new ArrayList<>();



    public static void main(String[] args)
    {

        Line line = new Line();

        line.drawLine(p0,p1,new Color(255,255,255),drawer);

        line.drawLine(p1,p2,new Color(255,255,255),drawer);

        line.drawLine(p0,p2,new Color(255,255,255),drawer);

        DrawFilledTriangle(p0,p1,p2,new Color(255,255,255));

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

        List<Double> h01 = line.interpolate(p0.y(), p0.h(), p1.y(), p1.h());

        List<Integer> x12 = line.interpolate(p1.y(), p1.x(), p2.y(), p2.x());

        List<Double> h12 = line.interpolate(p1.y(), p1.h(), p2.y(), p2.h());

        List<Integer> x02 = line.interpolate(p0.y(), p0.x(), p2.y(), p2.x());

        List<Double> h02 = line.interpolate(p0.y(), p0.h(), p2.y(), p2.h());

        List<Integer> x012 = new ArrayList<>();

        List<Double> h012 = new ArrayList<>();

        x012.addAll(x01);

        x012.remove(x012.size() - 1);

        x012.addAll(x12);

        h012.addAll(h01);

        h012.remove(h012.size() - 1);

        h012.addAll(h12);

        List<Integer> xLeft = new ArrayList<>();

        List<Double> hLeft = new ArrayList<>();

        List<Integer> xRight = new ArrayList<>();

        List<Double> hRight = new ArrayList<>();

        int m = x02.size() / 2;

        if (x02.get(m) < x012.get(m))
        {
            xLeft = x02;

            hLeft = h02;

            xRight = x012;

            hRight = h012;
        }
        else
        {
            xLeft = x012;

            hLeft = h012;

            xRight = x02;

            hRight = h02;
        }

        for (int y = p0.y();y <= p2.y();y++)
        {
            var xL = xLeft.get(y - p0.y());

            var xR = xRight.get(y - p0.y());

            var hSegment = line.interpolate(xL, hLeft.get(y - p0.y()), xR, hRight.get(y - p0.y()));

            for (var x = xL; x <= xR; x++)
            {
                drawer.writePixel(x,y,Color.multiply(hSegment.get(x - xL),color));
            }
        }


    }


}
