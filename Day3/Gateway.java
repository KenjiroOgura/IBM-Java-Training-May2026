sealed abstract class Gateway permits PaymentGateway{
    
    public Gateway(){
        System.out.println("Payment has been processed");
    }
    
}
