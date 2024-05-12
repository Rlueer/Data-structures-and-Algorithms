import java.util.Scanner;
import java.io.File;

public class main{

    public static int countChar(String str, char c){
        int count = 0;
        for(int i=0; i < str.length(); i++){    
        if(str.charAt(i) == c)
        count++;
        }
    
        return count;
    }

    public static boolean check_id(Operator ope[],Customer cus[],int id){
        int i=0,j=0;
        while(ope[i]!=null){
            if(ope[i].get_ID()==id){
                return false;
            }
            i++;
        }
        while(cus[j]!=null){
            if(cus[j].get_ID()==id){
                return false;
            }
            j++;
        }
        return true;
        
    }
    public static void main(String args[]) {
        Operator operators[]=new Operator[100];
        Customer customers[]=new Customer[100];
        Order orders[]=new Order[100];
        String line_arrays[][]=new String[100][100];
        int i=0,j=0,s=0;
        int a=0,d=0;
        try {
            File myObj = new File("content.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String temp[]=data.split(";");
                boolean semicolon_checker=false;
                if((temp[0].equals("order")&& countChar(data, ';')==5) ||
                 (temp[0].equals("retail_customer")&& countChar(data, ';')==6) ||
                 (temp[0].equals("corporate_customer")&& countChar(data, ';')==7 )||
                 (temp[0].equals("operator")&& countChar(data, ';')==6) ){
                    semicolon_checker=true;
                }
                if(semicolon_checker==true){
                    for (int k = 0; k < temp.length && k < countChar(data, ';') + 1; k++) {
                        line_arrays[i][k] = temp[k];
                    }
                    i++;
                }
            }
            myReader.close();
        } catch (Exception e) { // add exception
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        while(line_arrays[s][0]!=null){
            if(line_arrays[s][0].length()!=0 && line_arrays[s][1].length()!=0 && line_arrays[s][2].length()!=0
            && line_arrays[s][3].length()!=0 && line_arrays[s][4].length()!=0 && line_arrays[s][5].length()!=0){
                try {

                if(line_arrays[s][0].equals("order")  && line_arrays[s][6]==null && 
                    Integer.parseInt(line_arrays[s][2])>0 ){ 
                    // checking if it has more parameters than 5 and 
                    Order temp=new Order(line_arrays[s][1], Integer.parseInt(line_arrays[s][2]), 
                    Integer.parseInt(line_arrays[s][3]),Integer.parseInt(line_arrays[s][4]),
                    Integer.parseInt(line_arrays[s][5]));
                    if(temp.getCount()>0 && temp.getStatusint()>=0 && temp.getTotal_price()>0 && temp.getCustomer_ID()>0 ){
                        orders[a]=temp;
                        a++;//number of order
                    }
                }
                else if(line_arrays[s][0].equals("retail_customer") && 
                    line_arrays[s][7]==null && line_arrays[s][6]!=null && line_arrays[s][6].length()!=0
                    ){
                     // checking if it has more parameters than 6
                    Retail_customer temp= new Retail_customer(line_arrays[s][1], line_arrays[s][2], 
                    line_arrays[s][3],line_arrays[s][4],Integer.parseInt(line_arrays[s][5]),
                    Integer.parseInt(line_arrays[s][6]));
            
                    if(temp.get_operator_ID()>0 && temp.get_ID()>0 && check_id(operators,customers,temp.get_ID())==true){ // checking id if it is already created
                        customers[j]=temp;
                        j++; //number of customer 
                    }    
                }
                else if(line_arrays[s][0].equals("corporate_customer") &&line_arrays[s][8]==null &&
                     line_arrays[s][7]!=null  && line_arrays[s][6]!=null && line_arrays[s][7].length()!=0 
                    && line_arrays[s][6].length()!=0 ){
                    // checking if it has more parameters than 7
                    Corporate_customer temp=new Corporate_customer(line_arrays[s][1], line_arrays[s][2], 
                    line_arrays[s][3],line_arrays[s][4],Integer.parseInt(line_arrays[s][5]),
                    Integer.parseInt(line_arrays[s][6]),line_arrays[s][7]);

                    if(temp.get_operator_ID()>0 && temp.get_ID()>0 && check_id(operators,customers,temp.get_ID())==true){ // checking id if it is already created
                        customers[j]=temp;
                    j++; //number of customer 
                    }
                }
                else if(line_arrays[s][0].equals("operator") && line_arrays[s][7]==null
                    && line_arrays[s][6]!=null && line_arrays[s][6].length()!=0 ){
                    // checking if it has more parameters than 6
                    Operator temp= new Operator(line_arrays[s][1], line_arrays[s][2], 
                    line_arrays[s][3],line_arrays[s][4],Integer.parseInt(line_arrays[s][5]),
                    Integer.parseInt(line_arrays[s][6])
                    );
                    if(temp.get_wage()>0 && temp.get_ID()>0 && check_id(operators,customers,temp.get_ID())==true){ // checking id if it is already created
                        operators[d]=temp;
                        d++; //number of operator
                    }    
                }
                } catch (Exception e) { // checking for parseint exception ( int max error)
                    
                }
            }
            s++; //number of lines
        }
        


        for(int h=0;h<d;h++){
            operators[h].define_customers(customers);
        }
        for (int v = 0; v < j; v++) {
            if (customers[v] != null) {
                customers[v].define_orders(orders);
            }
        }

        System.out.println("Please enter your ID...");
        Scanner myObj = new Scanner(System.in);  // exception if it is not integer 
        try {
            int id = myObj.nextInt();  // Read user input
            myObj.close();
        
        boolean is_operator=false,is_customer=false;
        for(int z=0;z<d;z++){ // search id in operators
            if(id==operators[z].get_ID()){
                System.out.println("*** Operator Screen ***");
                operators[z].print_operator();
                operators[z].print_customer();
                is_operator=true;
            }
        }
        for(int x=0;x<j;x++){ // search id in customers
            if(is_operator==false && id==customers[x].get_ID()){
                System.out.println("*** Customer Screen ***");
                customers[x].print_customer();
                is_customer=true;
            }
        }
        if(is_customer==false && is_operator==false){
            System.out.println("No operator/customer was found with ID "+id +". Please try again.");
        }
    } catch (Exception e) { // exception handler for non integer input 
        System.out.println("input is not valid ");
    }
    }
      
}
