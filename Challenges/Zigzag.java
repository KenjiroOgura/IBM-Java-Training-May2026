package Challenges;
import java.util.Scanner;

public class Zigzag {
		public static void main(String[] args) {
			
		zigzag();

	}
		private static void zigzag() {
			boolean isvalid= false;
			int num=0;
			Scanner sc = new Scanner(System.in);
		  
			System.out.println("Enter a number:");
		    num = sc.nextInt();
		    isvalid = true;


		    boolean reverse=false;
		    int max = num*num;
		    int pointer =0;
		    int counter = 0;
		    for(int i=1; i<=max;i++) {
		    	if(reverse) {
		    		for(int j=i+num-1;j>=i;j--) {
		    			System.out.print(j+" ");
		    		}
		    		reverse = false;
		    		i+=num-1;
		    		System.out.println();
		    		counter =0;
		    	}
		    	else {
		    		System.out.print(i+" ");
		    		counter++;
		    		if(counter==num) {
		    			System.out.println();
		    			counter =0;
		    			reverse = true;
		    	}
		    		
		    		}
		    	}

		    	
		    	sc.close();
		    	
		    }
}
