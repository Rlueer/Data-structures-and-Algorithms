public class Person{
    private String name;
    private String surname;
    private String address;
    private String phone;
    private int ID;

    Person(String name,String surname,String address,String phone,int ID){
        this.name=name;
        this.surname=surname;
        this.address=address;
        this.phone=phone;
        this.ID=ID;
    }

    public String get_name() {
        return name;
    }

    public String get_surname() {
        return surname;
    }

    public String get_address() {
        return address;
    }

    public String get_phone() {
        return phone;
    }

    public int get_ID() {
        return ID;
    }

}
