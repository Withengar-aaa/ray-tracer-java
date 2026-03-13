package com.demo;


import java.util.ArrayList;
import java.util.List;

public class Main
{

    static Point O = new Point(0, 0, 0);

    static int canvasWidth=960;

    static int canvasHeight =960 ;

    static int canvasToViewDistance = 1;

    static double viewWidth =1;

    static double viewHeight= 1;

    private final Color backgroundColor = new Color(255,255,255);

    private final Sphere sphereA = new Sphere(new Point(0,-1,3),1,new Color(255,0,0),500);

    private final Sphere sphereB = new Sphere(new Point(2,0,4),1,new Color(0,255,255),500);

    private final Sphere sphereC = new Sphere(new Point(-2,0,4),1,new Color(0,255,0),10);

    private final Sphere sphereD = new Sphere(new Point(0,-5001,0),5000,new Color(255,255,0),1000);


    //定义光,所有光源的光强加起来不超过1
    private final Light lAmbient = new Light(LightType.AMBIENT,0.2);

    private final Light lPoint = new Light(LightType.POINT,0.6,new Point(2,1,0));

    private final Light lDirectional = new Light(LightType.DIRECTIONAL,0.2,new Vector3D(1,4,4));




    /**
     *
     * @param args
     *
     * c2vd射线方程 P = O + tD    其中D为 从点O指向点v的向量
     */

    public static void main(String[] args)
    {
        Main main = new Main();

        PixelDrawer drawer = PixelDrawer.getInstance(canvasWidth,canvasHeight);

        for (int x = -canvasWidth/2; x<canvasWidth/2; x++)
        {
            for (int y=-canvasHeight/2; y<canvasHeight/2;y++)
            {
                Vector3D c2vd = main.canvasToViewport(x,y);

                Color color = main.traceRay(O, c2vd, 1, Double.POSITIVE_INFINITY);

                drawer.putPixel(x,y,color);

            }

        }
        drawer.show("光线追踪");

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
    public Color traceRay(Point origin,Vector3D direction,double min,double max)
    {

        double closestT = Double.POSITIVE_INFINITY;

        Sphere closestSphere = null;

        List<Sphere> sphereList = new ArrayList<>();

        sphereList.add(sphereA);

        sphereList.add(sphereB);

        sphereList.add(sphereC);

        sphereList.add(sphereD);

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

        if (closestSphere == null)
        {
            return backgroundColor;
        }


        Vector3D point = Vector3D.add(Point.pointToVector(origin),Vector3D.multiply(closestT,direction));

        Vector3D normal = Vector3D.subtract(Vector3D.vectorToPoint(point),closestSphere.center);

        normal = Vector3D.multiply(1.0/Vector3D.vectorLength(normal),normal);

        Vector3D view = Vector3D.multiply(-1,direction);

        double k = computeLighting(Vector3D.vectorToPoint(point), normal,view,closestSphere.specular);


        return new Color(Color.clamp(k * closestSphere.color.red),
                Color.clamp(k * closestSphere.color.green),
                Color.clamp(k * closestSphere.color.blue));

    }


    /**
     *
     * @param origin 摄像机原点
     * @param direction 过摄像机原点的射线
     * @param sphere 球体与射线交点的解
     * @return
     */
   public List<Double> intersectRaySphere(Point origin,Vector3D direction,Sphere sphere)
   {
       List<Double> result = new ArrayList<>();

       Vector3D oc = Vector3D.subtract(origin,sphere.center);

       double r = sphere.radius;

       double k1 = Vector3D.dotProduct(direction,direction);

       double k2 = 2*Vector3D.dotProduct(oc,direction);

       double k3 = Vector3D.dotProduct(oc,oc)-r*r;

       double discriminant = k2 * k2 - 4 * k1 * k3;


       if (discriminant<0)
       {
           result.add(Double.POSITIVE_INFINITY);
           result.add(Double.POSITIVE_INFINITY);
           return result;
       }

       double t1 = (-k2 + Math.sqrt(discriminant)) / (2 * k1);

       double t2 = (-k2 - Math.sqrt(discriminant)) / (2 * k1);

       result.add(t1);

       result.add(t2);

       return result;

   }

    /**
     *
     * @param point 三维物体上一点
     * @param normal 法线方向,需要归一化
     * @param view 物体指向相机
     * @param specular 物体粗糙度
     * @return 所有光源的光强
     */
   private double computeLighting(Point point,Vector3D normal,Vector3D view,int specular)
   {
       double intensity = 0;

       List<Light> lightList = new ArrayList<>();

       lightList.add(lAmbient);

       lightList.add(lPoint);

       lightList.add(lDirectional);

       for (Light light:lightList)
       {
           if (light.getLightType() == LightType.AMBIENT)
           {
               intensity += light.getIntensity();
           }
           else
           {
               Vector3D vecL = null;

               if (light.getLightType() == LightType.POINT)
               {
                   vecL = Vector3D.subtract(light.getPosition(),point);
               }
               else
               {
                   vecL = light.getDirection();
               }

               double nDotL = Vector3D.dotProduct(vecL, normal);

               //漫反射
               if (nDotL>0)
               {
                    intensity += light.getIntensity() * nDotL / (Vector3D.vectorLength(normal) * Vector3D.vectorLength(vecL));
               }

               //镜面反射
               if (specular != -1)
               {
                    Vector3D vecReflection =  Vector3D.vecSubtract(Vector3D.multiply(2 * Vector3D.dotProduct(normal,vecL) ,normal),vecL);

                    double rDotV = Vector3D.dotProduct(vecReflection,view);

                    if (rDotV>0)
                    {
                        intensity += light.getIntensity() * Math.pow(rDotV / (Vector3D.vectorLength(vecReflection) * Vector3D.vectorLength(view)),specular);
                    }


               }


           }
       }

       return intensity;

   }





}