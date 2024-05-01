/**
 * Name: Ahnaf Ahmed
 * Project #2 - Dynamic Programming Technique
 * 
 * CS323 Design and Analysis of Algorithms
 * Spring 2024
 * Prof. Anne Smith-Thompson
 */

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

   public static DecimalFormat df = new DecimalFormat("00.00");
   public static Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException {
      
      System.out.println("Welcome to the World Series!");
      System.out.println("-----------------------------");
      System.out.println("It's the Mets vs the Yankees!");
      System.out.println("-----------------------------");

      int n = bestOf();
      double p = winPercent();
      
      double mets_win = probability(n,p);
      System.out.println("Mets win probability: " +df.format(mets_win*100)+ "%");
   }
   
   /**
    * 
    * @param n
    * @param p
    * @return
    */
   public static double probability(int n, double p) {
      int w = Math.ceilDiv(n, 2) + 1;
      if (n%2 == 0) w++;
      double[][] table = new double[w][w];
      for (int i = 0; i < w; i++) {
         table[w-1][i] = 1;
         table[i][w-1] = 0;
      }
      for (int i = w-2; i >= 0; i--) {
         for (int j = w-2; j >= 0; j--) {
            table[i][j] = p * table[i+1][j] + (1-p) * table[i][j+1];
         }
      }
      // print table
//      for (int i = 0; i < w; i++) {
//         for (int j = 0; j < w; j++) {
//            System.out.print(df.format(table[i][j])+"\t");
//         }
//         System.out.println();
//      }
      return table[0][0];
   }
   
   /**
    * 
    * @return
    * @throws IOException
    */
   public static int bestOf() throws IOException {
      int choice = -1;
      do {
         System.out.println("Would you like to change the default number of games (7)?");
         System.out.print("Enter '1' for 'yes' or '0' for 'no'\n> ");
         choice = scanner.nextInt();
         if (choice != 1 && choice != 0)
            System.out.println("Error! Please enter a valid input!");
         System.out.println("-----------------------------");
      } while (choice != 1 && choice != 0);
      if (choice == 1) {
         int num = -1;
         do {
            System.out.print("Enter the number of games\n> ");
            num = scanner.nextInt();
            if (num <= 0)
               System.out.println("Error! Please enter a positive integer!");
            System.out.println("-----------------------------");
         } while (num <= 0);
         return num;
      }
      else return 7;
   }

   /**
    * 
    * @return
    * @throws IOException
    */
   public static double winPercent() throws IOException {
      int choice = -1;
      do {
         System.out.println("Would you like to change the default probability (46.3% / 0.463) of the Mets winning a single game?");
         System.out.print("Enter '1' for 'yes' or '0' for 'no'\n> ");
         choice = scanner.nextInt();
         if (choice != 1 && choice != 0)
            System.out.println("Error! Please enter a valid input!");
         System.out.println("-----------------------------");
      } while (choice != 1 && choice != 0);
      if (choice == 1) {
         double num = -1.0;
         do {
            System.out.print("Enter the new probability as a decimal number\n> ");
            num = scanner.nextDouble();
            if (num <= 0)
               System.out.println("Error! Please enter a positive decimal number!");
            System.out.println("-----------------------------");
         } while (num <= 0);
         return num;
      }
      else return 0.463;
   }
}
