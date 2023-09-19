package io.zgc.design.patterns.structural.decorator;

public class Circle implements Shape {
 
   @Override
   public void draw() {
      System.out.println("Shape: Circle");
   }
}