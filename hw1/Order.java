public class Order {
    private String product_name;
    private int count;
    private int total_price;  
    private int status;
    private int customer_ID;
    Order(String product_name,int count,int total_price,int status,int customer_ID){
        this.product_name=product_name;
        this.count=count;
        this.total_price=total_price;
        this.status=status;
        this.customer_ID=customer_ID;
    }

    public String getProduct_name(){
        return product_name;
    }
    public int getCount() {
        return count;
    }
    public int getTotal_price() {
        return total_price;
    }
    public String getStatus() {
        if(status==0){
            return "Initialized.";
        }
        else if(status==1){
            return "Processing.";
        }
        else if(status==2){
            return "Completed.";
        }
        else if(status==3){
            return "Cancelled.";
        }
        else{
            return "Unvalid";
        }
        
    }
    public int getStatusint(){
        return status;
    }
    public int getCustomer_ID() {
        return customer_ID;
    }

    public void print_order(){
        System.out.println("Product name: "+this.getProduct_name() +
        " Count: "+ this.getCount()+
        " Total price: "+ this.getTotal_price()+
        " Status: " +this.getStatus());
    }
}
