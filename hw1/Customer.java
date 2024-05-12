public class Customer extends Person{
    private Order order []=new Order[100];
    private int operator_ID;
    Customer(String name,String surname,String address,String phone,int ID,int operator_ID){
        super(name,surname,address,phone,ID);
        this.operator_ID=operator_ID;
    }
    Customer(Customer x){
        super(x.get_name(),x.get_surname(),x.get_address(),x.get_phone(),x.get_ID());
        this.operator_ID=x.get_operator_ID();
    }

    public int get_operator_ID(){
        return operator_ID;
    }
    

    public void print_customer(){
        System.out.println("----------------------------\r\n" + 
                        "Name & Surname:"+ get_name() + get_surname() +"\r\n" + 
                        "Address:"+ get_address() +"\r\n" + 
                        "Phone:"+get_phone() +"\r\n" + 
                        "ID:"+get_ID() +"\r\n" + 
                        "Operator ID:"+get_operator_ID()  );
        print_orders();
    }

    public void print_orders(){
        int i=0;
        while(order[i]!=null){
            System.out.print(
            "Order #" + (i+1)+ " => ");
            order[i].print_order();
            i++;
        }
    }

    public void define_orders(Order order[]){
        int i=0,j=0;
        while(order[i]!=null){
            if(this.get_ID()==order[i].getCustomer_ID()){
                this.order[j]= order[i];
                j++;
            }
            i++;
        }
    }

}
