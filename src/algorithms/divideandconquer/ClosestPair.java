package algorithms.divideandconquer;

import java.util.Arrays;

public class ClosestPair {

    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public double closestPair(Point[] points) {
        Arrays.sort(points, (p1, p2) -> p1.x - p2.x);
        return closestPairRecursive(points, 0, points.length - 1);
    }

    private double closestPairRecursive(Point[] points, int left, int right) {
        if (right - left <= 3) return bruteForce(points, left, right);

        int mid = (left + right) / 2;
        double dl = closestPairRecursive(points, left, mid);
        double dr = closestPairRecursive(points, mid + 1, right);

        double d = Math.min(dl, dr);

        Point[] strip = new Point[right - left + 1];
        int j = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - points[mid].x) < d) strip[j++] = points[i];
        }

        Arrays.sort(strip, 0, j, (p1, p2) -> p1.y - p2.y);

        return Math.min(d, stripClosest(strip, j, d));
    }

    private double stripClosest(Point[] strip, int size, double d) {
        double min = d;
        for (int i = 0; i < size; ++i)
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; ++j)
                if (distance(strip[i], strip[j]) < min)
                    min = distance(strip[i], strip[j]);
        return min;
    }

    private double bruteForce(Point[] points, int left, int right) {
        double min = Double.MAX_VALUE;
        for (int i = left; i < right; i++)
            for (int j = i + 1; j <= right; j++)
                if (distance(points[i], points[j]) < min)
                    min = distance(points[i], points[j]);
        return min;
    }

    private double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }
}
