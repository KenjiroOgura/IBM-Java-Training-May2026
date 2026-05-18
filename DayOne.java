public class DayOne {
    public static void main(String[] args) {
        System.out.println(blackjack(1,2));
        System.out.println(blackjack(21,22));
        System.out.println(blackjack(22,22));
        System.out.println(blackjack(2,10));

    }
    
    private static int blackjack(int a, int b) {
    	if (b>21 && a <=21 && a>0) {
    		return a;
    	}
    	else if(b>0 && b<=21){
    		return b;
    	}
    	return 0;
    }
}
