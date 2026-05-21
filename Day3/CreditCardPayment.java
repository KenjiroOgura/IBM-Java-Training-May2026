public class CreditCardPayment extends Payment implements Verifiable{
    private String cardNumber;
    private int transactionID;
    private String method;
    OnlinePaymentType ont;
    public CreditCardPayment(double amount,String cardNumber){
        super(amount);
        this.ont = new OnlinePaymentType();
        this.cardNumber= cardNumber;
        this.transactionID = (int)((Math.random() * 10000001));
        this.executePayment();
        this.method = "Credit Card";
    }

    @Override
    public void executePayment() {
        System.out.println("Processing credit card payment...");
    }

    @Override
    public boolean verify() {
        if(getCardNumber().length()==16){
            return true;
        }else{
            return false;
        }
    }

    public String getCardNumber(){
        return cardNumber;
    }

    @Override
    public int getTransactionID() {
        return transactionID;
    }

    @Override
    public String getPaymentType() {
        return ont.paymentType();
    }

    public String getBranch(){
        return method;
    }

    @Override
    public String getPaymentMethod() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
