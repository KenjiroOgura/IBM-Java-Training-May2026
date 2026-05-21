public class PaypalPayment extends Payment implements Verifiable{

    private String email;
    private String method;
    OnlinePaymentType ont;
    public PaypalPayment(String email,double amount){
        this.ont = new OnlinePaymentType();
        this.email=email;
        super(amount);
        this.executePayment();
        this.method = "Paypal";
    }
    @Override
    public void executePayment() {
        System.out.println("Processing PayPal payment...");
    }

    @Override
    public boolean verify() {
        if(email.contains("@")){
            return true;
        }else{
            return false;
        }
    }

    public String getEmail(){
        return email;
    }
    @Override
    public String getPaymentType() {
        return ont.paymentType();
    }

    @Override
    public String getPaymentMethod() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public String getBranch(){
        return method;
    }
}
