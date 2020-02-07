package com.company;

import java.util.ArrayList;

enum Direction {UP, RIGHT, DOWN, LEFT}


class Robot{

    private int x, y;
    private Direction dir = Direction.UP;


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDir() {
        return dir;
    }


    public Robot(int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }




    public Robot(){
        x = 0; y =0; dir = Direction.UP;

    }

    public String toString(){
        return "Пришел в: " + x + " " + y;
    }

    public void turnRight(){
        switch (dir){
            case UP:
                dir = Direction.RIGHT;
                break;
            case DOWN:
                dir = Direction.LEFT;
                break;
            case LEFT:
                dir = Direction.UP;
                break;
            case RIGHT:
                dir = Direction.DOWN;
                break;
        }

    }
    public void turnLeft(){
        switch (dir){
            case UP:
                dir = Direction.LEFT;
                break;
            case DOWN:
                dir = Direction.RIGHT;
                break;
            case LEFT:
                dir = Direction.DOWN;
                break;
            case RIGHT:
                dir = Direction.UP;
                break;
        }

    }
    public void stepForward(){
        switch (dir){
            case UP:
                y++;
                break;
            case DOWN:
                y--;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;


        }

    }

}


class SmartRobot extends Robot{
    public SmartRobot() {
        super(0,0,Direction.UP);

    }
    

   public void moveTo(int toX, int toY) {


        if (getX() < toX){
            while (getDir() != Direction.RIGHT){
                turnRight();
            }
        }

        if (getX() > toX){
            while (getDir() != Direction.LEFT){
                turnLeft();
            }
        }
        while (getX() != toX) {
            stepForward();}

        if (getY() < toY){
            while (getDir() != Direction.UP){
                turnRight();
            }
        }

        if (getY() > toY){
            while (getDir() != Direction.DOWN){
                turnRight();
            }
        }

        while (getY() != toY){
            stepForward();
        }


    }



}

public class Main {

    public static void main(String[] args) {

        Robot r = new Robot();
        SmartRobot sr = new SmartRobot();
        sr.moveTo(0,3);
        System.out.println(sr);

    }





}
