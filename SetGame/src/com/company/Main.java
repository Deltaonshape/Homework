package com.company;

import java.util.Scanner;



public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите значения первой карты");
        Card card1 = new Card(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
        System.out.println("Введите значения второй карты");
        Card card2 = new Card(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());


        //System.out.println(card1.equals(card2));
        System.out.println(card1.getThird(card2).toString());


    }
}

    class Card {
        int fill, count, shape, color;

        public Card(int fill, int count, int shape, int color) {
            this.fill = fill;
            this.count = count;
            this.shape = shape;
            this.color = color;
        }


        public int getFill() {
            return fill;
        }

        public int getCount() {
            return count;
        }

        public int getShape() {
            return shape;
        }

        public int getColor() {
            return color;
        }


        public void setFill(int fill) {
            this.fill = fill;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void setShape(int shape) {
            this.shape = shape;
        }

        public void setColor(int color) {
            this.color = color;
        }


/*
        @Override
        public boolean equals(Object o) {
            return this == o;
        }
*/


        @Override
        public boolean equals(Object o) {
            Card card = (Card) o;
            return (card.fill == this.fill) && (card.count == this.count) && (card.shape == this.shape) && (card.color == this.color);
        }

        public String toString () {
                return "Карта: " + fill + " " + count + " " + shape + " " + color;
        }



            public Card getThird (Card c){
            Card card3 = new Card(fill,count,shape,color);
                //if ((this.color != c.color) && (this.shape == c.shape) && (this.shape == 1) && (this.count == c.count) && ((this.count == c.count == 1))){


                if (this.fill != c.fill){
                    if ((this.fill == 1 && c.fill == 3) | (this.fill == 3 && c.fill == 1)){
                        card3.fill = 2;
                    }
                    else {
                        if ((this.fill == 1 && c.fill == 2) | (this.fill == 2 && c.fill == 1)) {
                            card3.fill = 3;
                        } else {
                            card3.fill = 1;
                        }
                    }

                }
                else{
                    card3.fill = this.fill;

                }


                if (this.count != c.count){
                    if ((this.count == 1 && c.count == 3) | (this.count == 3 && c.count == 1)){
                        card3.count = 2;
                    }
                    else {
                        if ((this.count == 1 && c.count == 2) | (this.count == 2 && c.count == 1)) {
                            card3.count = 3;
                        } else {
                            card3.count = 1;
                        }
                    }

                }
                else{
                    card3.count = this.count;

                }


                if (this.shape != c.shape){
                    if ((this.shape == 1 && c.shape == 3) | (this.shape == 3 && c.shape == 1)){
                        card3.shape = 2;
                    }
                    else {
                        if ((this.shape == 1 && c.shape == 2) | (this.shape == 2 && c.shape == 1)) {
                            card3.shape = 3;
                        } else {
                            card3.shape = 1;
                        }
                    }

                }
                else{
                    card3.shape = this.shape;

                }


                if (this.color != c.color){
                    if ((this.color == 1 && c.color == 3) | (this.color == 3 && c.color == 1)){
                        card3.color = 2;
                    }
                    else {
                        if ((this.color == 1 && c.color == 2) | (this.color == 2 && c.color == 1)) {
                            card3.color = 3;
                        } else {
                            card3.color = 1;
                        }
                    }

                }
                else{
                    card3.color = this.color;

                }



                return card3;
            }
        }

