package Day1;
import java.util.Scanner;
public class DayOne {
    public static void main(String[] args) {
//        System.out.println(blackjack(1,2));
//        System.out.println(blackjack(21,22));
//        System.out.println(blackjack(22,22));
//        System.out.println(blackjack(2,10));

        
    //	zigzag();
//        
       //dayOfWeek();
pyramid();
  	//dayOfWeek2();
//    	dayOfWeek2();

    }
    
    private static int blackjack(int a, int b) {
    	if (b>21 && a <=21 && a>0 || (a<=21 && b<=21 && a>b)) {
    		return a;
    	}
    	else if(b>0 && b<=21){
    		return b;
    	}
    	return 0;
    }
    private static void zigzag(){
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
    
    
   
    
    	private static void dayOfWeek() {
    		Scanner sc = new Scanner(System.in);
    		int num = -1;
    		
    		
    			System.out.println("Enter a number 1 to 7:");
    			num = sc.nextInt();
    			switch(num) {
	    			case 1:
	    				System.out.println("Monday");
	    				break;
	    			case 2:
	    				System.out.println("Tuesday");
	    				break;
	    			case 3:
	    				System.out.println("Wednesday");
	    				break;
	    				
	    			case 4:
	    				System.out.println("Thursday");
	    				break;
	    			case 5:
	    				System.out.println("Friday");
	    				break;

	    			case 6:
	    				System.out.println("Saturday");
	    				break;
	    			case 7:
	    				System.out.println("Sunday");
	    				break;
    				default:
    					System.out.println("Invalid day number");

    			}
    					
    		
    		sc.close();
    	}
    	
    	//Alternate solution Pattern Matching
    	private static void dayOfWeek2() {
    		 System.out.println("Enter a number: ");
    		Scanner sc = new Scanner(System.in);
    		int num = sc.nextInt();
    		
    		switch(num) {
    		
    		case 1 -> System.out.println("Monday");
    		case 2 -> System.out.println("Tuesday");
    		case 3 -> System.out.println("Wednesday");
    		case 4 -> System.out.println("Thursday");
    		case 5 -> System.out.println("Friday");
    		case 6 -> System.out.println("Saturday");
    		case 7 -> System.out.println("Sunday");
    		default -> System.out.println("Invalid day number");
    		}
//    		do {
//    			System.out.println("Enter numbers 1 to 7:");
//    			num = sc.nextInt();
//    			if(num==1) {
//        			System.out.println("Monday");
//        			break;
//        		}
//        		else if(num==2) {
//        			System.out.println("Tuesday");
//        		
//        			break;
//        		}
//        		else if(num==3) {
//        			System.out.println("Wednesday");
//        	
//        			break;
//
//        		}
//        		else if(num==4) {
//        			System.out.println("Thursday");
//        		
//        			break;
//
//        		}
//        		else if(num==5) {
//        			System.out.println("Friday");
//        			break;
//
//        		}
//        		else if(num==6) {
//        			System.out.println("Saturday");
//        			break;
//
//        		}
//        		else if(num==7) {
//        			System.out.println("Sunday");
//        			break;
//
//        		}else {
//        			System.out.println("Enter only numbers 1-7");
//        			continue;
//        		}
//    			
//    		}while(true);
    		
    		
    		
    		sc.close();
    	}
    	
    	private static void pyramid() {
    		int num=0;
    		boolean isvalid=false;
    		Scanner sc = new Scanner(System.in);
    		do {
    			System.out.println("Enter a number between 1 and 20:");
    			num = sc.nextInt();
    			if(num<21 && num >0) {
    				isvalid = true;
    			}
//    			if(sc.hasNextInt()){
//    				num = sc.nextInt();
//    				if(num<21 && num >0) {
//    					isvalid = true;
//    				}else {
//    					System.out.println("Error: Enter numbers between 1 to 20");
//    				}
//    				
//    			}else {
//    				System.out.println("Error: Enter numbers between 1 to 20");
//    				sc.next();
//    			}
    		}while(!isvalid);
    		sc.close();
    		
    		for(int i =1;i<=num;i++) {
    			for(int j=1;j<=i;j++ ) {
    				System.out.print(j);
    			}
    			System.out.println();
    		}
    		
    	}
    	
    }
    
    

