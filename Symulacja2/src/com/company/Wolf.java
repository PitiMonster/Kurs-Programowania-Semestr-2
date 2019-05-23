package com.company;

import java.awt.*;

public class Wolf extends Thread {

    private String wolfName = "Wolf";
    private Thread t;
    private int prevX = 0;
    private int prevY = 0;
    private int x;
    private int y;
    private MyCanvas canvas;
    private Point wolfPosition = new Point();

    // zmienne opodiwadajace za poruszenie sie tylko o jedna pozycje
    private int deltaX;
    private int deltaY;

    private int waitingTime;



    // konstruktor klasy Wolf biorący canvasa
    public Wolf(MyCanvas canvas){
        this.canvas = canvas;
        waitingTime = 0;

        // poczatkowe ulozenie wilka na planszy
        x = (int)(Math.random()*(canvas.mapWidth));
        y = (int)(Math.random()*(canvas.mapHeight));
//        System.out.println(x);
//        System.out.println(y);

        setWolfLocalization(x,y);

        canvas.changePosition(x,y,prevX,prevY,false);
    }

    @Override
    public void run() {
        while(true) {
            if (waitingTime == 0) {
                prevX = x;
                prevY = y;

                Point closestRabbit = canvas.getClosestRabbitPosition();
                int currentDistanceFromRabbit = (x - closestRabbit.x) * (x - closestRabbit.x) + (y - closestRabbit.y) * (y - closestRabbit.y);
                Point tempPoint = new Point(prevX, prevY);
                int closestDistanceFromRabbit = currentDistanceFromRabbit;

                for(int i = 0; i < 3; i++){
                    for( int j = 0; j < 3; j++){
                        int newX = i - 1;
                        int newY = j - 1;
                        if ((closestRabbit.x - (prevX + newX)) * (closestRabbit.x - (prevX + newX)) + (closestRabbit.y - (prevY + newY)) * (closestRabbit.y - (prevY + newY)) < closestDistanceFromRabbit) {
                            if(canvas.isInBoard(prevX + newX, prevY + newY)){
                                tempPoint = new Point(prevX+newX,prevY + newY);
                                closestDistanceFromRabbit = (closestRabbit.x - (prevX + newX)) * (closestRabbit.x - (prevX + newX)) + (closestRabbit.y - (prevY + newY)) * (closestRabbit.y - (prevY + newY));
                            }
                        }
                    }
                }

//                // sprawdzenie czy moze gonic w prawo
//                if ((closestRabbit.x - (prevX + 1)) * (closestRabbit.x - (prevX + 1)) + (closestRabbit.y - prevY) * (closestRabbit.y - prevY) < closestDistanceFromRabbit) {
//                    if (prevX + 1 < canvas.mapWidth && prevY < canvas.mapHeight) {
//                        tempPoint = new Point(prevX + 1, prevY);
//                        closestDistanceFromRabbit = (closestRabbit.x - (prevX + 1)) * (closestRabbit.x - (prevX + 1)) + (closestRabbit.y - prevY) * (closestRabbit.y - prevY);
//                        System.out.println(closestDistanceFromRabbit);
//                    }
//                }
//                // sprawdzenie czy moze gonic w lewo
//                if ((closestRabbit.x - (prevX - 1)) * (closestRabbit.x - (prevX - 1)) + (closestRabbit.y - prevY) * (closestRabbit.y - prevY) < closestDistanceFromRabbit) {
//                    if (prevX - 1 >= 0 && prevY < canvas.mapHeight) {
//                        tempPoint = new Point(prevX - 1, prevY);
//                        closestDistanceFromRabbit = (closestRabbit.x - (prevX - 1)) * (closestRabbit.x - (prevX - 1)) + (closestRabbit.y - prevY) * (closestRabbit.y - prevY);
//                        System.out.println(closestDistanceFromRabbit);
//
//                    }
//                }
//                // sprawdzenie czy moze gonic w gore
//                if ((closestRabbit.x - prevX) * (closestRabbit.x - prevX) + (closestRabbit.y - (prevY - 1)) * (closestRabbit.y - (prevY - 1)) < closestDistanceFromRabbit) {
//                    if (prevX < canvas.mapWidth && prevY - 1 >= 0) {
//                        tempPoint = new Point(prevX, prevY - 1);
//                        closestDistanceFromRabbit = (closestRabbit.x - prevX) * (closestRabbit.x - prevX) + (closestRabbit.y - (prevY - 1)) * (closestRabbit.y - (prevY - 1));
//                        System.out.println(closestDistanceFromRabbit);
//
//                    }
//                }
//                // sprawdzenie czy moze gonic w dol
//                if ((closestRabbit.x - prevX) * (closestRabbit.x - prevX) + (closestRabbit.y - (prevY + 1)) * (closestRabbit.y - (prevY + 1)) < closestDistanceFromRabbit) {
//                    if (prevX < canvas.mapWidth && prevY + 1 < canvas.mapHeight) {
//                        tempPoint = new Point(prevX, prevY + 1);
//                        closestDistanceFromRabbit = (closestRabbit.x - prevX) * (closestRabbit.x - prevX) + (closestRabbit.y - (prevY + 1)) * (closestRabbit.y - (prevY + 1));
//                        System.out.println(closestDistanceFromRabbit);
//
//                    }
//                }
//                // sprawdzenie czy moze gonic w lewy gorny rog
//                if ((closestRabbit.x - (prevX - 1)) * (closestRabbit.x - (prevX - 1)) + (closestRabbit.y - (prevY - 1)) * (closestRabbit.y - (prevY - 1)) < closestDistanceFromRabbit) {
//                    if (prevX - 1 >= 0 && prevY - 1 >= 0) {
//                        tempPoint = new Point(prevX - 1, prevY - 1);
//                        closestDistanceFromRabbit = (closestRabbit.x - (prevX - 1)) * (closestRabbit.x - (prevX - 1)) + (closestRabbit.y - (prevY - 1)) * (closestRabbit.y - (prevY - 1));
//                        System.out.println(closestDistanceFromRabbit);
//                    }
//                }
//                // sprawdzenie czy moze gonic w lewy dolny rog
//                if ((closestRabbit.x - (prevX - 1)) * (closestRabbit.x - (prevX - 1)) + (closestRabbit.y - (prevY + 1)) * (closestRabbit.y - (prevY + 1)) < closestDistanceFromRabbit) {
//                    if (prevX - 1 >= 0 && prevY + 1 < canvas.mapHeight) {
//                        tempPoint = new Point(prevX - 1, prevY + 1);
//                        closestDistanceFromRabbit = (closestRabbit.x - (prevX - 1)) * (closestRabbit.x - (prevX - 1)) + (closestRabbit.y - (prevY + 1)) * (closestRabbit.y - (prevY + 1));
//                        System.out.println(closestDistanceFromRabbit);
//                    }
//                }
//                // sprawdzenie czy moze gonic w prawy gorny rog
//                if ((closestRabbit.x - (prevX + 1)) * (closestRabbit.x - (prevX + 1)) + (closestRabbit.y - (prevY - 1)) * (closestRabbit.y - (prevY - 1)) < closestDistanceFromRabbit) {
//                    if (prevX + 1 < canvas.mapWidth && prevY - 1 >= 0) {
//                        tempPoint = new Point(prevX + 1, prevY - 1);
//                        closestDistanceFromRabbit = (closestRabbit.x - (prevX + 1)) * (closestRabbit.x - (prevX + 1)) + (closestRabbit.y - (prevY - 1)) * (closestRabbit.y - (prevY - 1));
//                        System.out.println(closestDistanceFromRabbit);
//                    }
//                }
//                // sprawdzenie czy moze gonic w prawy dolny rog
//                if ((closestRabbit.x - (prevX + 1)) * (closestRabbit.x - (prevX + 1)) + (closestRabbit.y - (prevY + 1)) * (closestRabbit.y - (prevY + 1)) < closestDistanceFromRabbit) {
//                    if (prevX + 1 < canvas.mapWidth && prevY + 1 < canvas.mapHeight) {
//                        tempPoint = new Point(prevX + 1, prevY + 1);
//                        closestDistanceFromRabbit = (closestRabbit.x - (prevX + 1)) * (closestRabbit.x - (prevX + 1)) + (closestRabbit.y - (prevY + 1)) * (closestRabbit.y - (prevY + 1));
//                        System.out.println(closestDistanceFromRabbit);
//                    }
//                }


                x = tempPoint.x;
                y = tempPoint.y;

                if (closestDistanceFromRabbit == 0) {

                    for (int i = 0; i < canvas.startingRabbits.length; i++) {
                        if (canvas.startingRabbits[i].x == x && canvas.startingRabbits[i].y == y) {
                            System.out.println("bbb");
                            canvas.startingRabbits[i].kill();
                            waitingTime = 5;
                            break;
                        }
                    }
                }


                // uaktualnienie zmiennej przechowujacej aktualne polozenie wilka
                setWolfLocalization(x, y);


                canvas.changePosition(x, y, prevX, prevY, false);

                try {
                    int sleepTime = RandomGenerator.nextInt(canvas.k) + canvas.k / 2;
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            } else {
                System.out.println(waitingTime);
                waitingTime -= 1;
            }
        }
    }

    public void start(){
        if(t == null){
            t = new Thread(this,wolfName);
            t.start();
        }
    }

    public Point getWolfPosition(){
        return wolfPosition;
    }

    private void setWolfLocalization(int x, int y){
//        System.out.println(x);
//        System.out.println(y);
        wolfPosition.x = x;
        wolfPosition.y = y;
    }
}
