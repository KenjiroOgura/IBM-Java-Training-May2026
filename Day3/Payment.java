public abstract class Payment {
    private double amount;
    private int transactionID;
    public Payment(double amount){
        this.amount=amount;
    }
    public abstract String getPaymentType();
    public int getTransactionID(){
        return transactionID;
    }
    public abstract void executePayment();
    public abstract boolean verify();
    public void displayAmount(){
        System.out.println(amount);
    }
    public abstract String getPaymentMethod();
    public double retreiveAmount(){
        return amount;
    }
    public abstract String getBranch();

    
}
