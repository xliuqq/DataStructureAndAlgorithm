package org.xliu.cs.algs_ds.algs.geometry;

import org.locationtech.jts.geom.*;
import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;

@ClassNote("判断点是否在多边形内部（包含在边上)")
public class PointInPolygon {

    public static void containsByJTS() {
        // 创建一个 GeometryFactory 实例，线程安全

        // TS提供了三种精度类型，用户可以根据需要选择合适的精度类型：
        //FLOATING：这是默认的精度类型，等同于Java中的双精度double类型。它提供了较高的精度，适用于大多数几何计算场景。
        //FLOATING_SINGLE：等同于Java中的单精度float类型。与FLOATING相比，它的精度较低，但计算速度可能更快，适用于对精度要求不高的场景。
        //FIXED：固定精度表示坐标有固定的小数位数。小数点的位数由比例因子的对数10决定（log10 of the scale）。这种精度类型允许用户指定一个固定的小数位数，从而确保坐标值的精度符合特定要求。然而，需要注意的是，固定精度可能会导致计算过程中的舍入误差。
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING_SINGLE));

        // 定义一个多边形
        Coordinate[] polygonCoordinates = {
                new Coordinate(0.5f, 0.5f),
                new Coordinate(4.5f, 0.5f),
                new Coordinate(4.5f, 4.5f),
                new Coordinate(0.5f, 4.5f),
                new Coordinate(0.5f, 0.5f) // 闭合多边形
        };

        Polygon polygon = geometryFactory.createPolygon(polygonCoordinates);

        // 定义一个点
        long startTime = System.nanoTime();
        for (int x = 0; x < 1000; x++) {
            for (int y = 0; y < 1000; y++) {
                Coordinate pointCoordinate = new Coordinate(x, y);
                Point point = geometryFactory.createPoint(pointCoordinate);

                // 判断点是否在多边形内(包括在边上）
                boolean contains = polygon.covers(point);

//                System.out.println("contains: " + contains);
            }
        }
        long endTime = System.nanoTime();

        System.out.println("cost ms: " + (endTime - startTime) / 1000000.0);

    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            containsByJTS();
        }
    }
}
