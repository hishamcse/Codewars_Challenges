package com.Hisham.KuThree.ClosestPair;

/*
 * Challenge: https://www.codewars.com/kata/5376b901424ed4f8c20002b7
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Kata {

    static Point point1, point2;
    static double bestDist;

    private static class XComparator implements Comparator<Point> {
        public int compare(Point p, Point q) {
            return Double.compare(p.x, q.x);
        }
    }

    private static class YComparator implements Comparator<Point> {
        public int compare(Point p, Point q) {
            return Double.compare(p.y, q.y);
        }
    }

    public static double distanceTo(Point p, Point q) {
        double dx = p.x - q.x;
        double dy = p.y - q.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static List<Point> closestPair(List<Point> points) {
        point1 = null;
        point2 = null;
        bestDist = Double.POSITIVE_INFINITY;
        int n = points.size();

        // sort by x-coordinate (breaking ties by y-coordinate via stability)
        Point[] pointsByX = new Point[n];
        for (int i = 0; i < n; i++)
            pointsByX[i] = points.get(i);
        Arrays.sort(pointsByX, new YComparator());
        Arrays.sort(pointsByX, new XComparator());

        // check for coincident points
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            if (pointsByX[i].equals(pointsByX[i + 1])) {
                point1 = pointsByX[i];
                point2 = pointsByX[i + 1];
                list.add(point1);
                list.add(point2);
                return list;
            }
        }

        // sort by y-coordinate (but not yet sorted)
        Point[] pointsByY = new Point[n];
        System.arraycopy(pointsByX, 0, pointsByY, 0, n);

        // auxiliary array
        Point[] aux = new Point[n];

        closest(pointsByX, pointsByY, aux, 0, n - 1);
        list.add(point1);
        list.add(point2);
        return list;
    }

    private static double closest(Point[] pointsByX, Point[] pointsByY, Point[] aux, int lo, int hi) {
        if (hi <= lo) return Double.POSITIVE_INFINITY;

        int mid = lo + (hi - lo) / 2;
        Point median = pointsByX[mid];

        double delta1 = closest(pointsByX, pointsByY, aux, lo, mid);
        double delta2 = closest(pointsByX, pointsByY, aux, mid + 1, hi);
        double delta = Math.min(delta1, delta2);

        merge(pointsByY, aux, lo, mid, hi);

        int m = 0;
        for (int i = lo; i <= hi; i++) {
            if (Math.abs(pointsByY[i].x - median.x) < delta)
                aux[m++] = pointsByY[i];
        }

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; (j < m) && (aux[j].y - aux[i].y < delta); j++) {
                double distance = distanceTo(aux[i], aux[j]);
                if (distance < delta) {
                    delta = distance;
                    if (distance < bestDist) {
                        bestDist = delta;
                        point1 = aux[i];
                        point2 = aux[j];
                    }
                }
            }
        }
        return delta;
    }

    // is v < w ?
    private static boolean less(Point p, Point q) {
        return p.x < q.x || p.y < q.y;
    }

    private static void merge(Point[] a, Point[] aux, int lo, int mid, int hi) {
        if (hi + 1 - lo >= 0) System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void main(String[] args) {
        List<Point> points = Arrays.asList(
                new Point(2, 2), //A
                new Point(2, 8), //B
                new Point(5, 5), //C
                new Point(6, 3), //D
                new Point(6, 7), //E
                new Point(7, 4), //F
                new Point(7, 9)  //G
        );
        System.out.println(closestPair(points));
    }
}