import java.text.DecimalFormat;

public class Main {
   
   public static final double p1 = .463;
   public static final double p2 = .537;

   public static void main(String[] args) {
      
      DecimalFormat df = new DecimalFormat("00.00");

      long start = System.nanoTime();
      double p = prob();
      long end = System.nanoTime();
      long time = end - start;
      System.out.println("Mets win probability (memo): " +df.format(p*100)+ "% ["+time+"]");
      start = System.nanoTime();
      double pdp = prob2();
      end = System.nanoTime();
      time = end - start;
      System.out.println("Mets win probability (dp): " +df.format(pdp*100)+ "% ["+time+"]");
   }

   public static double prob() {
      double[][] memo = new double[5][5];
      for (int i = 0; i < 5; i++) {
         for (int j = 0; j < 5; j++) {
            memo[i][j] = -1;
         }
      }
      for (int i = 0; i < 5; i++) {
         memo[4][i] = 1; // mets win all 4
         memo[i][4] = 0; // yankees win all 4
      }
      return prob(memo,0,0);
   }
   
   public static double prob(double[][] memo, int i, int j) {
      if (memo[i][j] != -1) return memo[i][j];
      else {
         double result;
         result = p1 * prob(memo, i+1, j) + p2 * prob(memo, i, j+1);
         memo[i][j] = result;
         return result;
      }
   }
   
   public static double prob2() {
      double[][] dp = new double[5][5];
      for (int i = 0; i < 5; i++) {
         dp[4][i] = 1; // mets win all 4
         dp[i][4] = 0; // yankees win all 4
      }
      for (int i = 3; i >= 0; i--) {
         for (int j = 3; j >= 0; j--) {
            dp[i][j] = p1 * dp[i+1][j] + p2 * dp[i][j+1];
         }
      }
      return dp[0][0];
   }
}
