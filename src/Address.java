import java.util.*;
public class Address {

    public static List<Contact> contacts = new ArrayList<>();

    public static void insert(Contact c){
        contacts.add(c);
        System.out.println("Contact saved successfully");
    }

    public static void view(){
        for(Contact c : contacts){
            System.out.println("name : " + c.name + "\n"+
              "phone : " + c.phone + "\n" +
               "email : " + c.email + "\n"
            );
        }
    }


    public static void delete(String name){

        boolean found = false;

        for(Contact c : contacts){

            if(c.name.equalsIgnoreCase(name)){
                contacts.remove(c);
                System.out.println("Deleted Successfully");
                found = true;
                break;
            }
        }

        if(!found){
            System.out.println("Contact not found");
        }


    }


    public static void update(String original_name,String name, long phone, String email){

        boolean flag = false;
        for(Contact c : contacts){

            if(c.name.equalsIgnoreCase(original_name)){
                c.name = name;
                c.phone = phone;
                c.email = email;
                System.out.println("Updated details successfully");
                System.out.println(c);

                flag = true;
            }
        }

        if(!flag) System.out.println("Details not found");


    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a choice");


        while(true){

            System.out.println("\n--- Address Book Menu ---");
            System.out.println("1. View Address Book");
            System.out.println("2. Add Record");
            System.out.println("3. Delete Record by Name");
            System.out.println("4. Update");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){

                case 1:
                    view();
                    break;

                case 2:
                    System.out.println("Enter name :");
                    String name = sc.nextLine();

                    System.out.println("Enter phone :");
                    long phone = Long.parseLong(sc.nextLine());
                    sc.nextLine();

                    System.out.println("Enter email : ");
                    String email = sc.nextLine();


                    insert(new Contact(name,phone,email));

                    break;


                case 3:
                    System.out.println("Enter a record to delete");
                    String name1 = sc.nextLine();
                    delete(name1);
                    break;

                case 4:
                    System.out.println("Enter a record to update");

                    System.out.println("Enter original name");
                    String original_name = sc.nextLine();

                    System.out.println("Enter name :");
                    String name2 = sc.nextLine();

                    System.out.println("Enter phone :");
                    long phone2 = Long.parseLong(sc.nextLine());
                    sc.nextLine();

                    System.out.println("Enter email : ");
                    String email2 = sc.nextLine();

                    update(original_name,name2,phone2,email2);

                    break;


                case 5:
                    System.out.println("Exiting");
                    return;


                default:
                    System.out.println("Invalid Choice");







            }







        }


    }

}
