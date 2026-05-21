import java.time.LocalTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Payment> list = new ArrayList<>();
        CreditCardPayment ccp = new CreditCardPayment(225, "1234567890123456");
        PaypalPayment pp = new PaypalPayment("Kenjiro@email.com", 450);
        BankTransferPayment btp = new BankTransferPayment(800 , "1234567890");

        list.add(ccp);
        list.add(pp);
        list.add(btp);

        

        PaymentGateway pg = new PaymentGateway();
        ArrayList<PaymentDetails>pdList = new ArrayList<>();
        for (Payment elem : list) {
            if(elem.verify()==true){
                pg.processPayment();
                PaymentDetails pd = new PaymentDetails(elem.getTransactionID(), elem.getBranch(), elem.retreiveAmount(), elem.getPaymentType(), LocalTime.now());
                pdList.add(pd);
            }
        }

        for (PaymentDetails pd : pdList) {
            pd.retreiveDetails();
            System.out.println();
            
        }



        
    }

}
