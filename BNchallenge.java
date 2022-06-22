package com.example.bnchallenge;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.LinkedList;

/**
 * @author Fancen Wu
 * @create at 2022-06-21-17:36
 */
public class BNchallenge {


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
        int [] start = {0,0};
        int [] end = {9,9};
        BNchallenge bn = new BNchallenge();
        bn.shortestPath(matrix, start, end);
    }

}

/**
 * A class to create a 2D grid based on columns and rows
 */
class Grid{

    private int cols;
    private int rows;
    private SimpleIntegerProperty[] [] grid;

    public Grid(int cols, int rows){
        this.cols = cols;
        this.rows = rows;

        grid = new SimpleIntegerProperty[cols][rows];

        for(int y = 0; y < rows; y++){
            for(int x = 0; x < cols; x++){
                grid[x][y] = new SimpleIntegerProperty(0);
            }
        }
    }

    //return a value for a certain point
    public IntegerProperty getPointValue(int x, int y){
        return grid[x][y];
    }

    //Change the value of a point
    public void setPointValue(int x, int y, int value){
        grid[x][y].set(value);
    }

    public int get(int x, int y){
        try{
            return  grid[x][y].get();
        }catch (ArrayIndexOutOfBoundsException e){
            return -1;
        }
    }

}

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
