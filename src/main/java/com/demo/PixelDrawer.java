package com.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 像素绘制工具类，模拟PutPixel(x,y,color)功能
 */
public class PixelDrawer
{
    // 单例实例，全局复用
    private static PixelDrawer instance;

    // 图像缓冲区
    private BufferedImage imageBuffer;

    // 画布尺寸
    private final int canvasWidth;

    private final int canvasHeight;

    // 私有构造器，初始化缓冲区
    private PixelDrawer(int canvasWidth, int canvasHeight)
    {
        this.canvasWidth = canvasWidth;

        this.canvasHeight = canvasHeight;

        this.imageBuffer = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * 获取工具类实例（单例）
     * @param canvasWidth 画布宽度
     * @param canvasHeight 画布高度
     * @return 工具类实例
     */
    public static PixelDrawer getInstance(int canvasWidth, int canvasHeight)
    {
        if (instance == null || instance.canvasWidth != canvasWidth || instance.canvasHeight != canvasHeight)
        {
            instance = new PixelDrawer(canvasWidth, canvasHeight);
        }
        return instance;
    }

    /**
     *
     * @param x 原始x坐标（可负，如-200~200）
     * @param y 原始y坐标（可负，如-200~200）
     * @param color 像素颜色
     */
    public void putPixel(int x, int y, Color color)
    {
        // 自动转换负坐标为窗口正坐标
        int windowX = x + canvasWidth / 2;

        int windowY = -y + canvasHeight / 2;

        // 自动边界检查，避免越界
        if (windowX >= 0 && windowX < canvasWidth && windowY >= 0 && windowY < canvasHeight)
        {
            imageBuffer.setRGB(windowX, windowY, color.toRGB());
        }
    }

    /**
     * 显示绘制结果（弹出窗口）
     * @param title 窗口标题
     */
    public void show(String title)
    {
        SwingUtilities.invokeLater(() ->
        {
            JFrame frame = new JFrame(title);

            frame.setSize(canvasWidth, canvasHeight);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setLocationRelativeTo(null);

            frame.add(new JLabel(new ImageIcon(imageBuffer)));

            frame.setVisible(true);
        });
    }
}