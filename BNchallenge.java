package com.example.bnchallenge;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author Fancen Wu
 * @create at 2022-06-21-17:36
 */
public class BNchallenge {

    //Calculate the shortest path from start to end
    public void shortestPath(int[][] matrix, int[] start, int[] end){
        int sx = start[0], sy = start[1];
        int dx = end[0], dy = end[1];

        if(matrix[sx][sy] == 0 || matrix[dx][dy] == 0) return;

        Point[][] points = new Point[10][10];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j< 10 ; j++){
                if(matrix[i][j] != 0){
                    points[i][j] = new Point(i,j,Integer.MAX_VALUE, null);
                }
            }

        }

        LinkedList<Point> queue = new LinkedList<>();
        Point src = points[sx][sy];
        src.distance = 0;
        queue.add(src);
        Point dest = null;
        Point p;
        while((p = queue.poll()) != null){
            if(p.x == dx && p.y == dy ){
                dest = p;
                break;
            }


            visit(points, queue, p.x-1, p.y, p);

            visit(points, queue, p.x+1, p.y, p);

            visit(points, queue, p.x, p.y-1, p);

            visit(points, queue, p.x, p.y+1, p);

            visit(points, queue, p.x+1, p.y+1, p);

            visit(points, queue, p.x-1, p.y+1, p);

        }

        if(dest == null){
            System.out.println("Unable to reach delivery point");
            return;
        } else {
            LinkedList<Point> path = new LinkedList<>();
            p = dest;
            System.out.println("The number of steps is: " + p.distance);
            do{
                path.addFirst(p);
            } while ((p = p.prev)!= null);
            System.out.println(path);
        }
    }

    //Do some operations when the vehicle is in a certain point
    public void visit(Point[][] points, LinkedList<Point> queue, int x, int y, Point parent){
        if(x < 0 || x >= points.length || y < 0 || y >= points[0].length || points[x][y] == null){
            return;
        }

        int dist = parent.distance +1;
        Point p = points[x][y];
        if(dist < p.distance){
            p.distance = dist;
            p.prev = parent;
            queue.add(p);
        }
    }

    public static void main(String[] args){
        int[] [] matrix = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };

        int[][] matrix1 = new int[10][10];
        Random r = new Random();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if((i ==0 && j == 0) || (i == 9 && j == 9)) {
                    matrix1[i][j] = 1;
                }else if((i==9&&j==7)||(i==8&&j==7)||(i==6&&j==7)||(i==6&&j==8)){
                    matrix1[i][j] = 0;
                }else{
                    matrix1[i][j] = r.nextInt(2);
                }
                System.out.print(matrix1[i][j]+" ");
            }
            System.out.println();
        }

        int [] start = {0,0};
        int [] end = {9,9};
        BNchallenge bn = new BNchallenge();
        bn.shortestPath(matrix1, start, end);
    }

}

//Class to generate a certain point
class Point {
    int x;
    int y;
    int distance;
    Point prev;

    Point(int x, int y, int distance, Point prev){
        this.x=x;
        this.y=y;
        this.distance = distance;
        this.prev = prev;
    }

    public String toString(){
        return "(" + x + "," + y + ")";
    }
}
