public class Corporate_customer extends Customer{
    private String company_name;

    Corporate_customer(String name,String surname,String address,String phone,
    int ID,int operator_ID,String company_name){
        super(name,surname,address,phone,ID,operator_ID);
        this.company_name=company_name;
    }
    String get_company_name(){
        return company_name;
    }
    @Override
    public void print_customer(){ 
        System.out.println( "Name & Surname:"+ get_name() + get_surname() +"\r\n" + 
                        "Address:"+ get_address() +"\r\n" + 
                        "Phone:"+get_phone() +"\r\n" + 
                        "ID:"+get_ID() +"\r\n" + 
                        "Operator ID:"+get_operator_ID() +"\r\n"+
                        "Company name:"+ get_company_name() );
                        print_orders();                
    }

}
