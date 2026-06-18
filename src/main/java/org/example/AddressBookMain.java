package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookMain {
    static Scanner sc = new Scanner(System.in);
    static AddressBookMain system = new AddressBookMain();
    Map<String, AddressBook> addressBookMap = new HashMap<>();
    Map<String, List<Contact>> cityStateList = new HashMap<>();


    public void addContactInAddressBookMap(String name){
        if(addressBookMap.containsKey(name)){
            System.out.println("This Name is already exists!!");
        } else {
            addressBookMap.put(name,new AddressBook());
            System.out.println("Address Book '"+name+"' created");
        }
    }


    public AddressBook getAddressBook(String key){
        if(addressBookMap.get(key) != null) {
            return addressBookMap.get(key);
        } else {
            System.out.println("InValid Name!!!");
            return null;
        }
    }

    public void addContact(String name){
        AddressBook addressBook = getAddressBook(name);
        Contact c1 = new Contact("Rahul", "Sharma", "12 MG Road", "Bengaluru", "KA", "560001", "987-654-3210", "rahul.sharma@example.com");
        Contact c2 = new Contact("Priya", "Patel", "34 Linking Road", "Mumbai", "MH", "400050", "876-543-2109", "priya.patel@example.com");
        Contact c3 = new Contact("Karthik", "Raja", "56 Anna Salai", "Chennai", "TN", "600002", "765-432-1098", "karthik.r@example.com");
        Contact c4 = new Contact("Ananya", "Singh", "78 Connaught Place", "New Delhi", "DL", "110001", "998-877-6655", "ananya.singh@example.com");
        Contact c5 = new Contact("Vikram", "Reddy", "90 Jubilee Hills", "Hyderabad", "TS", "500033", "887-766-5544", "vikram.reddy@example.com");
        Contact c6 = new Contact("Neha", "Gupta", "120 Sector 15", "Noida", "UP", "201301", "776-655-4433", "neha.g@example.com");
        Contact c7 = new Contact("Sanjay", "Kumar", "45 RS Puram", "Coimbatore", "TN", "641002", "912-345-6780", "sanjay.k@example.com");
        Contact c8 = new Contact("Divya", "Menon", "67 Marine Drive", "Kochi", "KL", "682031", "823-456-7891", "divya.m@example.com");
        Contact c9 = new Contact("Amit", "Desai", "89 SG Highway", "Ahmedabad", "GJ", "380015", "934-567-8912", "amit.desai@example.com");
        Contact c10 = new Contact("Sneha", "Joshi", "23 FC Road", "Pune", "MH", "411004", "845-678-9123", "sneha.joshi@example.com");
        addressBook.addContact(c1);
        addressBook.addContact(c2);
        addressBook.addContact(c3);
        addressBook.addContact(c4);
        addressBook.addContact(c5);
        addressBook.addContact(c6);
        addressBook.addContact(c7);
        addressBook.addContact(c8);
        addressBook.addContact(c9);
        addressBook.addContact(c10);
        addressBook.display();
        System.out.println("Successfully Created!!!");
    }

    public void editContactUsingName(String name){
        AddressBook addressBook = getAddressBook(name);
        addressBook.display();
        System.out.println("Enter the existing FirstName :");
        String firstName = sc.next();
        System.out.println("Enter the existing Last Name :");
        String lastName = sc.next();
        addressBook.editContactUsingName(firstName, lastName);
    }

    public void deleteContactUsingName(String name){
        AddressBook addressBook = getAddressBook(name);
        addressBook.display();
        System.out.println("Enter the existing FirstName :");
        String firstName = sc.next();
        System.out.println("Enter the existing Last Name :");
        String lastName = sc.next();
        addressBook.deleteContactUsingName(firstName, lastName);
    }

    public void countPersonByCityOrState_AddressBook(String city, String state) {
        for(String name : addressBookMap.keySet()){
            AddressBook addressBook = system.getAddressBook(name);
            addressBook.countBasedOnCityOrState(city,state);
        }
    }

    public void searchBasedOnCityOrStateToStore(String city, String state){

        List<Contact> list = new ArrayList<>();
        for (String name : addressBookMap.keySet()){
            AddressBook addressBook = system.getAddressBook(name);
            list = addressBook.searchBasedOnCityOrStateToStore(city, state);
        }
        list.stream().forEach(c -> System.out.println(c));
        List<Contact> cityList, stateList;
        if(!list.isEmpty()) {
            cityList = list.stream().filter(c -> c.getCity().toLowerCase().equals(city.toLowerCase())).collect(Collectors.toList());
            stateList = list.stream().filter(c -> c.getState().toLowerCase().equals(state.toLowerCase())).collect(Collectors.toList());
            cityStateList.put(city,cityList);
            cityStateList.put(state,stateList);
        }
    }

    public void displayCityOrStateList(String city, String state){
        for (Map.Entry<String,List<Contact>> mp : cityStateList.entrySet()){
            if(mp.getKey().toLowerCase().equals(city) || mp.getKey().toLowerCase().equals(state)) {
                System.out.println(mp.getKey());
                mp.getValue().stream().forEach(c -> System.out.println(c));
            }
        }
    }

    public void sortBasedOnName(String name){
        AddressBook addressBook = system.getAddressBook(name);
        addressBook.sortBasedOnName();
    }

    public void sortBasedOnCityStateZip(String name){
        AddressBook addressBook = getAddressBook(name);
        addressBook.sortBasedOnCityStateZip();
    }

    public void writeFile(){
        for(String s : addressBookMap.keySet()){
            AddressBook addressBook = getAddressBook(s);
            addressBook.writeFile(s);
        }

    }

    public void readFile(){
        for(String s : addressBookMap.keySet()){
            AddressBook addressBook = getAddressBook(s);
            addressBook.readFile();
        }
    }

    public void writeCSVFIle(){
        for(String s : addressBookMap.keySet()){
            AddressBook addressBook = getAddressBook(s);
            addressBook.writeCSVFIle();
        }
    }

    public void readCSVFile(){
        for(String s : addressBookMap.keySet()){
            AddressBook addressBook = getAddressBook(s);
            addressBook.readCSVFile();
        }
    }

    public void writeJSONFIle(){
        for(String s : addressBookMap.keySet()){
            AddressBook addressBook = getAddressBook(s);
            addressBook.writeJSONFile();
        }
    }

    public void readJSONFile(){
        for(String s : addressBookMap.keySet()){
            AddressBook addressBook = getAddressBook(s);
            addressBook.readJSONFile();
        }
    }

    public void displayAddresBook(){
        int i = 1;
        for(String name : addressBookMap.keySet()){
            System.out.println(i+"."+name);
            i++;
        }
    }



    public static void main(String[] args) {
        System.out.println("===Wecome to Address Book System===");


        while(true){
            System.out.println("1. Add AddressBook");
            System.out.println("2. Perform Opertions on Particular Address Book");
            System.out.println("3. Search Person based on City or State");
            System.out.println("4. To See the Storing Value of City and State");
            System.out.println("5. To See the Count of City and State");
            System.out.println("6. To Write Persons Contact into File");
            System.out.println("7. To Read Persons Contact into File");
            System.out.println("8. To Write Persons Contact into CSV File");
            System.out.println("9. To Read Persons Contact into CSV File");
            System.out.println("10. To Write Persons Contact on JSON file");
            System.out.println("11. To Read Persons Contact on JSON file");
            System.out.println("12. Exit the Program...");
            System.out.println("Enter ur Choice: ");
            int choice = sc.nextInt();
            String name, city, state;
            switch (choice){
                case 1:
                    System.out.println("Enter a Name for Address Book: ");
                    name = sc.next();
                    system.addContactInAddressBookMap(name);
                    break;
                case 2:
                    system.displayAddresBook();
                    System.out.println("Enter a Address Book Name");
                    name = sc.next();
                    if(system.getAddressBook(name) == null){
                        break;
                    }
                    system.optionsToSelect(name);
                    break;
                case 3:
                    System.out.println("Enter a State Name to Search" );
                    state = sc.next();
                    System.out.println("Enter a City Name to Search" );
                    city = sc.next();
                    system.searchBasedOnCityOrStateToStore(city, state);
                    break;
                case 4:
                    System.out.println("Enter city :");
                    city = sc.next().toLowerCase();
                    System.out.println("Enter state: ");
                    state = sc.next().toLowerCase();
                    System.out.println("Displaying the City and State based Persons");
                    system.displayCityOrStateList(city, state);
                    break;
                case 5:
                    System.out.println("Enter city :");
                    city = sc.next().toLowerCase();
                    System.out.println("Enter State: ");
                    state = sc.next().toLowerCase();
                    System.out.println("Displaying the Count of City and State based Persons");
                    system.countPersonByCityOrState_AddressBook(city, state);
                    break;
                case 6:
                    System.out.println("File Writing");
                    system.writeFile();
                    break;
                case 7:
                    System.out.println("File Reading");
                    system.readFile();
                    break;
                case 8:
                    System.out.println("CSV File Writing");
                    system.writeCSVFIle();
                    break;
                case 9:
                    System.out.println("CSV File Reading");
                    system.readCSVFile();
                    break;
                case 10:
                    System.out.println("JSON File Writing");
                    system.writeJSONFIle();
                    break;
                case 11:
                    System.out.println("JSON File Reading");
                    system.readJSONFile();
                    break;
                case 12:
                    return;
            }
        }

    }

    public void optionsToSelect(String name){
        while (true) {
            System.out.println("1. Add Contact in '"+name+"' AddressBook");
            System.out.println("2. Edit Contact using their name from '"+name+"'");
            System.out.println("3. Delete Contact using their name from '"+name+"'");
            System.out.println("4. To sort Based on Name's");
            System.out.println("5. To sort Based on City,State or Zip");
            System.out.println("6. Exit");
            System.out.println("Enter ur Choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    system.addContact(name);
                    break;
                case 2:
                    system.editContactUsingName(name);
                    break;
                case 3:
                    system.deleteContactUsingName(name);
                    break;
                case 4:
                    system.sortBasedOnName(name);
                    break;
                case 5:
                    sortBasedOnCityStateZip(name);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}

