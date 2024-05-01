/**
 * Name: Ahnaf Ahmed
 * Project #2 - Dynamic Programming Technique
 * 
 * CS323 Design and Analysis of Algorithms
 * Spring 2024
 * Prof. Anne Smith-Thompson
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

   public static DecimalFormat df;
   public static Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) {
      
      System.out.println("Welcome to the World Series!");
      System.out.println("-----------------------------");
      System.out.println("It's the Mets vs the Yankees!");
      System.out.println("-----------------------------");

      int n = getNumberOfGames();
      double p = getWinPercentage();
      
      double metsWin = probability(n,p);
      format(metsWin);
      
      System.out.println("Mets win probability: " +df.format(metsWin*100)+ "%");
   }
   
   /**
    * 
    * @param n
    * @param p
    * @return
    */
   public static double probability(int n, double p) {
      // minimum wins
      int w = Math.ceilDiv(n, 2) + 1; // odd number of games
      if (n%2 == 0) w++; // even number of games
      
      // memorization table
      // rows represent the number of games won by the Mets
      // columns represent the number of games won by the Yankees
      double[][] table = new double[w][w];
      
      // initialize base cases
      // if the Mets win the minimum number of games, they 'win' the world series (probability = 1)
      // if the Yankees win the minimum number of games, the Mets 'lose' the world series (probability = 0)
      for (int i = 0; i < w; i++) {
         table[w-1][i] = 1; // the Mets win the minimum number of games
         table[i][w-1] = 0; // the Yankees win the minimum number of games
      }
      
      // dynamically calculate the overall probability of the Mets winning the world series
      for (int i = w-2; i >= 0; i--) {
         for (int j = w-2; j >= 0; j--) {
            // probability of winning the world series from the current state
            // = probability * probability of winning the world series if they win the next game
            // + (1 - probability) * probability of winning the world series if they lose the next game
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
    */
   public static int getNumberOfGames() {
      int choice = -1;
      do {
         // choice to change the default number of games
         while (true) {
            // prompt
            System.out.println("Would you like to change the default number of games (7)?");
            System.out.print("Enter 1 for 'yes' or 0 for 'no'\n> ");
            // input
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
      
      // update the number of games
      if (choice == 1) {
         int num = -1;
         do {
            while (true) {
               // prompt
               System.out.print("Enter the number of games\n> ");
               // input
               String line = scanner.nextLine();
               try {
                  // type validation
                  num = Integer.parseInt(line);
                  break;
               } catch (NumberFormatException e) {
                  System.out.println("Error! Please enter an integer!");
                  System.out.println("-----------------------------");
               }
            }
            // value validation
            if (num <= 0)
               System.out.println("Error! Please enter a positive integer!");
            System.out.println("-----------------------------");
         } while (num <= 0);
         return num;
      }
      // default number of games
      else return 7;
   }

   /**
    * 
    * @return
    */
   public static double getWinPercentage() {
      int choice = -1;
      do {
         // choice to change the default probability
         while (true) {
            System.out.println("Would you like to change the default probability (46.3% / 0.463) of the Mets winning a single game?");
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
      
      // update the probability
      if (choice == 1) {
         double num = -1.0;
         do {
            while (true) {
               System.out.print("Enter the new probability as a decimal number between 0 and 1\n> ");
               String line = scanner.nextLine();
               try {
                  // type validation
                  num = Double.parseDouble(line);
                  break;
               } catch (NumberFormatException e) {
                  System.out.println("Error! Please enter a decimal number!");
                  System.out.println("-----------------------------");
               }
            }
            // value validation
            if (num < 0 || num > 1)
               System.out.println("Error! Please enter a decimal number between 0 and 1!");
            System.out.println("-----------------------------");
         } while (num < 0 || num > 1);
         return num;
      }
      // default win percentage
      else return 0.463;
   }
   
   /**
    * 
    * @param percent
    */
   public static void format(double percent) {
      if (percent >= 100) df = new DecimalFormat("000.00");
      if (percent < 100 && percent >= 10) df = new DecimalFormat("00.00");
      if (percent < 10) df = new DecimalFormat("0.00");
   }
   /*
   public static int getInt(String line) {
      while (true) {
         try {
            return Integer.parseInt(line);
         } catch(NumberFormatException e) {
            System.out.println("Error! Please enter an integer!");
         }
      }
   }

   public static double getDouble(String line) {
      while (true) {
         try {
            return Double.parseDouble(line);
         } catch(NumberFormatException e) {
            System.out.println("Error! Please enter a decimal number!");
         }
      }
   }
   */
}
