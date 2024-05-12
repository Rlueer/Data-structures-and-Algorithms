public class Retail_customer extends Customer{
    Retail_customer(String name,String surname,String address,String phone,int ID,int operator_ID){
        super(name,surname,address,phone,ID,operator_ID);
    }
    @Override
    public void print_customer(){ 
        System.out.println(
                        "Name & Surname:"+ get_name() + get_surname() +"\r\n" + 
                        "Address:"+ get_address() +"\r\n" + 
                        "Phone:"+get_phone() +"\r\n" + 
                        "ID:"+get_ID() +"\r\n" + 
                        "Operator ID:"+get_operator_ID() );
                        print_orders();
    }

}
