public class BankTransferPayment extends Payment implements Verifiable{
    OfflinePaymentType opt;
    private String accNumber;
    private int transactionID;
    private String method;
    public BankTransferPayment(double amount, String accNumber){
        this.opt = new OfflinePaymentType();
        this.accNumber=accNumber;
        this.transactionID= (int)((Math.random() * 10000001));
        super(amount);
        this.executePayment();
        this.method = "Bank";
    }
    @Override
    public void executePayment() {
        System.out.println("Processing bank transfer...");
    }

    @Override
    public boolean verify() {
        if(getAccNumber().length()==10){
            return true;
        }else{
            return false;
        }
    }
    
    public String getBranch(){
        return method;
    }
    public String getAccNumber(){
        return accNumber;
    }
    @Override
    public int getTransactionID() {
        return transactionID;
     }
    @Override
    public String getPaymentType(){
        return opt.paymentType();
    }
    public String getPaymentMethod(){
        return opt.paymentType();
    }
}
