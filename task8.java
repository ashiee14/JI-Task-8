//interface 1:base payment abstraction
package task8;
interface Payment 
{
    void pay(double amount);
}

//interface 2:for logging 
interface PaymentLogger 
{
    void log(String message);
}

//abstract class
abstract class AbstractPayment implements Payment, PaymentLogger 
{

    // Common validation logic
    protected void validateAmount(double amount) 
    {
        if (amount <= 0) 
            {
            throw new IllegalArgumentException("Invalid payment amount");
        }
    }

    //common logging implementation
    public void log(String message) 
    {
        System.out.println("[LOG] " + message);
    }
}

//credit card payment implementation
class CreditCardPayment extends AbstractPayment 
{

    public void pay(double amount) 
    {
        validateAmount(amount);
        log("Processing Credit Card payment");
        System.out.println("Paid ₹" + amount + " using Credit Card");
    }
}

//UPI payment implementation
class UPIPayment extends AbstractPayment 
{

    public void pay(double amount) 
    {
        validateAmount(amount);
        log("Processing UPI payment");
        System.out.println("Paid ₹" + amount + " using UPI");
    }
}

//cash payment implementation
class CashPayment extends AbstractPayment 
{

    public void pay(double amount) 
    {
        validateAmount(amount);
        log("Processing Cash payment");
        System.out.println("Paid ₹" + amount + " using Cash");
    }
}

//payment processor 
class PaymentProcessor 
{
    private Payment paymentMode;
    //switch payment mode dynamically
    public void setPaymentMode(Payment paymentMode) 
    {
        this.paymentMode = paymentMode;
        System.out.println("Payment mode switched successfully");
    }

    public void makePayment(double amount) 
    {
        try 
        {
            paymentMode.pay(amount);
        } catch (IllegalArgumentException e) 
        {
            System.out.println("Payment failed: " + e.getMessage());
        }
    }
}
public class task8 
{

    public static void main(String[] args) 
    {
        PaymentProcessor processor = new PaymentProcessor();
        //dynamic switching of payment modes
        processor.setPaymentMode(new CreditCardPayment());
        processor.makePayment(5000);
        System.out.println();
        processor.setPaymentMode(new UPIPayment());
        processor.makePayment(1200);
        System.out.println();
        processor.setPaymentMode(new CashPayment());
        processor.makePayment(-300); //invalid payment case
    }
}