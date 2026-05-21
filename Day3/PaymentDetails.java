import java.time.LocalTime;

public record PaymentDetails(int transactionId,String branch, double amount, String paymentMethod,LocalTime lt) {

    public PaymentDetails(int transactionId,String branch,double amount, String paymentMethod,LocalTime lt){
        this.transactionId=(int)((Math.random() * 10000001));
        this.amount=amount;
        this.paymentMethod=paymentMethod;
        this.lt = LocalTime.now();
        this.branch = branch;
    }

    public int getTransactionID(){
        return transactionId;
    }
    public double getAmount(){
        return amount;
    }

    public String getPaymentMethod(){
        return paymentMethod;
    }
    public LocalTime getLt(){
        return lt;
    }
    public String getBranch(){
        return branch;
    }
    
    public void retreiveDetails(){
        System.out.println("\nTransaction ID: "+ getTransactionID()+"\nBranch: "+getBranch()+"\nAmount: "+getAmount()+"\nPayment Method: "+getPaymentMethod()+"\nTime: "+getLt());
    }

    
    
}
