
package scanningactivity;


import java.util.Scanner;
import java.util.Random;

public class SimpleTraining {
    static final double PI=3.14159;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       int[] array = {2, 5, 6, 7, 5};
       for(int i :array ){
           System.out.println(+i);
       }
       Scanner scan = new Scanner(System.in);
       System.out.println("Enter first value: ");
       double x = scan.nextDouble();
       System.out.println("Enter second value: ");
       double y = scan.nextDouble();
       System.out.printf("The result is $%1.6f", (x/y));
    
       int len = 0;
        do
        {
            if(len==0){
            System.out.println("");
            }
            else
            {
            System.out.println("It is finished!");
            }
            len++;
        }while(len <= 7);
       theRandMethod();
    }
   public static void theRandMethod(){
    Random random = new Random();
        int diRo1 = random.nextInt(6)+1;
        int diRo2= random.nextInt(6)+1;
        int[] randomArray = {diRo1, diRo2};
        for(int r : randomArray){
            System.out.println(r);
        }
   }
    
}
