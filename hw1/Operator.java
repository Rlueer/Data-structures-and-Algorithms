public class Operator extends Person{
    private Customer customer[]=new Customer[100];
    private int wage;
    Operator(String name,String surname,String address,String phone,int ID,int wage){
        super(name,surname,address,phone,ID);
        this.wage=wage;
    }

    public int get_wage(){
        return wage;
    }

    public void print_operator(){
        System.out.println("----------------------------\r\n" + 
                        "Name & Surname:"+ get_name() + get_surname() +"\r\n" + 
                        "Address:"+ get_address() +"\r\n" + 
                        "Phone:"+get_phone() +"\r\n" + 
                        "ID:"+get_ID() +"\r\n" + 
                        "Wage:"+get_wage() );
    }

    public void print_customer(){
        int i=0;
        if(customer[0]==null){
            System.out.print("----------------------------\r\n" +
             "This operator doesn't have any customer.");
        }
        while(customer[i]!=null){
            System.out.print("----------------------------\r\n" + 
            "Customer #" + (i+1));
            if (customer[i] instanceof Retail_customer) {
                System.out.println(" (a retail customer)");
            }
            else if (customer[i] instanceof Corporate_customer) {
                System.out.println(" (a corporate customer)");
            }
            customer[i].print_customer();
            i++;
        }
    }

    public void define_customers(Customer customer[]){
        int i=0,j=0;
        while(customer[i]!=null){
            if(this.get_ID()==customer[i].get_operator_ID()){
                this.customer[j]= customer[i];
                j++;
            }
            i++;
        }
    }

}
