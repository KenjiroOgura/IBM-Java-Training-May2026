sealed class PaymentType permits OnlinePaymentType,OfflinePaymentType{
    public String paymentType(){
        return "Payment type: default";
    }
}
