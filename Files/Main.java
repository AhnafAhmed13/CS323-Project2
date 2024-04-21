import java.text.DecimalFormat;

public class Main {
   
   public static final double p1 = .463;
   public static final double p2 = .537;

   public static void main(String[] args) {
      
      DecimalFormat df = new DecimalFormat("00.00");

      double p = prob();
      System.out.println("Mets win probability: " +df.format(p*100)+ "%");
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
}
