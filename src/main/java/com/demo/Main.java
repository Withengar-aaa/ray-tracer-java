package com.demo;


import java.util.ArrayList;
import java.util.List;

public class Main
{

    static Point O = new Point(0, 0, 0);

    static int canvasWidth=1080;

    static int canvasHeight =1080 ;

    static int canvasToViewDistance = 10;

    static int viewWidth =960;

    static int viewHeight= 960;

    Sphere sphereA = new Sphere(new Point(0,-1,3),1,new Color(255,0,0));

    Sphere sphereB = new Sphere(new Point(2,0,4),1,new Color(0,255,255));

    Sphere sphereC = new Sphere(new Point(-2,0,4),1,new Color(0,255,0));





    /**
     *
     * @param args
     *
     * c2vd射线方程 P = O + tD    其中D为 从点O指向点v的向量
     */

    public static void main(String[] args)
    {
        Main main = new Main();

        for (int x = -canvasWidth/2; x<canvasWidth/2; canvasWidth++)
        {
            for (int y=-canvasHeight/2; y<canvasHeight/2;canvasHeight++)
            {
                Vector3D c2vd = main.canvasToViewport(x,y);





            }

        }


    }


    /**
     *
     * @param x  画布上的点x坐标
     * @param y  画布上的点y坐标
     * @return 三维空间中由原点出发朝三维坐标点发出的射线
     */
    public Vector3D canvasToViewport(int x,int y)
    {
        return new Vector3D(x*viewWidth/canvasWidth,y*viewHeight/canvasHeight,canvasToViewDistance);
    }


    /**
     *
     * @param origin 摄像机的射线发射点
     * @param direction 空间中任意一点
     * @param min 射线 P = O + tD 中t的取值范围最小值
     * @param max 射线 P = O + tD 中t的取值范围最大值
     */
    public void traceRay(Point origin,Vector3D direction,double min,double max)
    {

        double closestT = Double.POSITIVE_INFINITY;

        Sphere closestSphere = null;

        List<Sphere> sphereList = new ArrayList<>();

        sphereList.add(sphereA);

        sphereList.add(sphereB);

        sphereList.add(sphereC);

        for (Sphere sphere:sphereList)
        {
            List<Double> sphereResult = intersectRaySphere(origin, direction, sphere);

            if (sphereResult.get(0) < closestT && sphereResult.get(0) < max && sphereResult.get(0) > min)
            {
                closestT = sphereResult.get(0);

                closestSphere = sphere;

            }

            if (sphereResult.get(1) < closestT && sphereResult.get(1) < max && sphereResult.get(1) > min)
            {
                closestT = sphereResult.get(1);

                closestSphere = sphere;

            }






        }


    }


   public List<Double> intersectRaySphere(Point origin,Vector3D direction,Sphere sphere)
   {
       List<Double> result = new ArrayList<>();

       Vector3D co = Vector3D.subtract(origin,sphere.center);

       double r = sphere.radius;

       double k1 = Vector3D.dotProduct(direction,direction);

       double k2 = 2*Vector3D.dotProduct(co,direction);

       double k3 = Vector3D.dotProduct(co,co)-r*r;

       double discriminant = k2 * k2 - 4 * k1 * k3;


       if (discriminant<0)
       {
           result.add(Double.POSITIVE_INFINITY);
           result.add(Double.POSITIVE_INFINITY);
           return result;
       }

       double t1 = (-k2 + Math.sqrt(discriminant)) / 2 * k1;

       double t2 = (-k2 - Math.sqrt(discriminant)) / 2 * k1;

       result.add(t1);

       result.add(t2);

       return result;

   }





}