package algorithms.divideandconquer;

import java.util.ArrayList;
import java.util.List;

public class Quickhull {

    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public List<Point> quickHull(Point[] points) {
        if (points.length < 3) throw new IllegalArgumentException("At least 3 points are required");

        List<Point> hull = new ArrayList<>();
        int minPoint = 0, maxPoint = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i].x < points[minPoint].x) minPoint = i;
            if (points[i].x > points[maxPoint].x) maxPoint = i;
        }

        Point A = points[minPoint];
        Point B = points[maxPoint];
        hull.add(A);
        hull.add(B);

        List<Point> leftSet = new ArrayList<>();
        List<Point> rightSet = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            if (points[i] == A || points[i] == B) continue;
            if (pointLocation(A, B, points[i]) > 0) leftSet.add(points[i]);
            else if (pointLocation(A, B, points[i]) < 0) rightSet.add(points[i]);
        }

        findHull(hull, leftSet, A, B);
        findHull(hull, rightSet, B, A);

        return hull;
    }

    private int pointLocation(Point A, Point B, Point P) {
        return (B.x - A.x) * (P.y - A.y) - (B.y - A.y) * (P.x - A.x);
    }

    private void findHull(List<Point> hull, List<Point> set, Point A, Point B) {
        int insertPosition = hull.indexOf(B);
        if (set.size() == 0) return;
        if (set.size() == 1) {
            Point P = set.get(0);
            set.remove(P);
            hull.add(insertPosition, P);
            return;
        }

        int dist = Integer.MIN_VALUE;
        int furthestPoint = -1;
        for (int i = 0; i < set.size(); i++) {
            Point P = set.get(i);
            int distance = distance(A, B, P);
            if (distance > dist) {
                dist = distance;
                furthestPoint = i;
            }
        }

        Point P = set.get(furthestPoint);
        set.remove(furthestPoint);
        hull.add(insertPosition, P);

        List<Point> leftSetAP = new ArrayList<>();
        for (int i = 0; i < set.size(); i++) {
            Point M = set.get(i);
            if (pointLocation(A, P, M) > 0) leftSetAP.add(M);
        }

        List<Point> leftSetPB = new ArrayList<>();
        for (int i = 0; i < set.size(); i++) {
            Point M = set.get(i);
            if (pointLocation(P, B, M) > 0) leftSetPB.add(M);
        }

        findHull(hull, leftSetAP, A, P);
        findHull(hull, leftSetPB, P, B);
    }

    private int distance(Point A, Point B, Point C) {
        int ABx = B.x - A.x;
        int ABy = B.y - A.y;
        int num = ABx * (A.y - C.y) - ABy * (A.x - C.x);
        if (num < 0) num = -num;
        return num;
    }
}
