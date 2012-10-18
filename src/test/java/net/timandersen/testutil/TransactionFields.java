package net.timandersen.testutil;

public class TransactionFields {
    public String amount = "0.0";
    public String transactionType = "Debit";
    public String date = "10/22/2012";

    @Override
    public String toString() {
        return "TransactionFields{" +
                "amount='" + amount + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public void reset() {
        FieldResetter.reset(this);
    }
}
