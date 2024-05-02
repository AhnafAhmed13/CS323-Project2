/**
 * Name: Ahnaf Ahmed
 * Project #2 - Dynamic Programming Technique
 * Dynamic Programming Problem Set 'part d'
 * 
 * CS323 Design and Analysis of Algorithms
 * Spring 2024
 * Prof. Anne Smith-Thompson
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class Project2 {

   public static DecimalFormat df = new DecimalFormat("0.00");
   public static Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) {
      
      System.out.println("Welcome to the World Series!");
      System.out.println("-----------------------------");
      System.out.println("It's the Mets vs the Yankees!");
      System.out.println("-----------------------------");

      final int n = 7;
      final double p = 0.463;
      
      double metsWin = probability(n,p);

      System.out.println("-> " +df.format(metsWin*100)+ "%!!!");
      System.out.println("We can do it if the team stays healthy!");
   }
   
   /**
    * probability   : finds the probability of winning the world series
    * @param n      : number of games
    * @param p      : probability of winning a single game
    * @return       : probability of winning the world series
    */
   public static double probability(int n, double p) {
      // minimum wins
      int w = 4;
      
      // memorization table
      // rows represent the number of games won by the Mets (0 inclusive)
      // columns represent the number of games won by the Yankees (0 inclusive)
      double[][] table = new double[w+1][w+1];
      
      // initialize base cases
      // if the Mets win the minimum number of games, they 'win' the world series (probability = 1)
      // if the Yankees win the minimum number of games, the Mets 'lose' the world series (probability = 0)
      for (int i = 0; i <= w; i++) {
         table[w][i] = 1; // the Mets win the minimum number of games
         table[i][w] = 0; // the Yankees win the minimum number of games
      }
      
      // dynamically calculate the overall probability of the Mets winning the world series
      for (int i = w-1; i >= 0; i--) {
         for (int j = w-1; j >= 0; j--) {
            // probability of winning the world series from the current state
            // = probability of winning the next game
            // * probability of winning the world series if they win the next game
            // + probability of NOT winning the next game
            // * probability of winning the world series if they DON'T win the next game
            table[i][j] = p * table[i+1][j] + (1-p) * table[i][j+1];
         }
      }
      
      // print option
      boolean willPrint = printOption();
      if (willPrint) {
         // print table
         printTable(table,w);
      }
      return table[0][0];
   }

   /**
    * printOption: asks the user if they want to print the probability table
    */
   public static boolean printOption() {
      int choice = -1;
      do {
         // choice to print the table
         while (true) {
            System.out.println("Would you like to view the probability table?");
            System.out.print("Enter 1 for 'yes' or 0 for 'no'\n> ");
            String line = scanner.nextLine();
            try {
               // type validation
               choice = Integer.parseInt(line);
               break;
            } catch (NumberFormatException e) {
               System.out.println("Error! Please enter an integer!");
               System.out.println("-----------------------------");
            }
         }
         // value validation
         if (choice != 1 && choice != 0)
            System.out.println("Error! Please enter a valid input!");
         System.out.println("-----------------------------");
      } while (choice != 1 && choice != 0);
      if (choice == 1) return true;
      else return false;
   }
   
   /**
    * printTable    : prints the probability table
    * @param table  : probability table
    * @param w      : size/minimum number of wins
    */
   public static void printTable(double[][] table, int w) {
      System.out.print("W/L\t");
      for (int i = 0; i <= w; i++) System.out.print(i+"\t");
      System.out.println();
      for (int i = 0; i <= w; i++) {
         System.out.print(i);
         for (int j = 0; j <= w; j++) {
            System.out.print("\t"+df.format(table[i][j]*100));
         }
         System.out.println();
      }
      System.out.println();
   }
}
