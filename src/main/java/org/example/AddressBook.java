package org.example;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {

    private static final String FILE_NAME = "contact.txt";
    private static final String CSV_FILENAME = "contact.csv";
    private static final String JSON_FILENAME = "contacts.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    Scanner sc = new Scanner(System.in);
    List<Contact> contacts;

    public AddressBook(){
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact){
        contacts.add(contact);
    }

    public void editContactUsingName(String firstName, String lastName){
        for(Contact c : contacts){
            if(c.getFirstName().equals(firstName) && c.getLastName().equals(lastName)){
                editing(c);
                return;
            }
        }
        System.out.println("Contact Not Found while trying to edit!!!");
    }

    private void editing(Contact contact){
        boolean flag = true;
        while(flag){
            System.out.println("1. First Name");
            System.out.println("2. Last Name ");
            System.out.println("3. Address");
            System.out.println("4. State");
            System.out.println("5. City");
            System.out.println("6. Zip");
            System.out.println("7. Phone Number");
            System.out.println("8. Email");
            System.out.println("Enter ur Choice : ");
            int choice = sc.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Enter a First Name to Update");
                    String updateFirstName = sc.next();
                    contact.setFirstName(updateFirstName);
                    break;
                case 2:
                    System.out.println("Enter a Last Name to Update");
                    String updateLastName = sc.next();
                    contact.setLastName(updateLastName);
                    break;
                case 3:
                    System.out.println("Enter a Address to Update");
                    String updateAddress = sc.next();
                    contact.setAdrress(updateAddress);
                    break;
                case 4:
                    System.out.println("Enter a State to Update");
                    String updateState = sc.next();
                    contact.setState(updateState);
                    break;
                case 5:
                    System.out.println("Enter a City to Update");
                    String updateCity = sc.next();
                    contact.setState(updateCity);
                    break;
                case 6:
                    System.out.println("Enter a ZIP to Update");
                    String updateZIP = sc.next();
                    contact.setZip(updateZIP);
                    break;
                case 7:
                    System.out.println("Enter a Phone Number to Update");
                    String updatePhone = sc.next();
                    contact.setPhoneNumber(updatePhone);
                    break;
                case 8:
                    System.out.println("Enter a Email to Update");
                    String updateEmail = sc.next();
                    contact.setEmail(updateEmail);
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
            System.out.println("Do you want to Update more??");
            String toUpdate = sc.next();
            if(toUpdate.toLowerCase().equals("no")){
                flag = false;
            }

        }
    }

    public void deleteContactUsingName(String firstName, String lastName){
        for(Contact c : contacts){
            if(c.getFirstName().equals(firstName) && c.getLastName().equals(lastName)){
                contacts.remove(c);
                return;
            }
        }
        System.out.println("Cotact Not Found while trying to delete");
    }

    public void countBasedOnCityOrState(String city, String state){
        int cityCount = 0, stateCount = 0;
        cityCount = (int)contacts.stream().filter(c -> c.getCity().toLowerCase().equals(city)).toList().stream().count();
        System.out.println("City("+city+") : "+cityCount);
        stateCount = (int)contacts.stream().filter(c -> c.getState().toLowerCase().equals(state)).toList().stream().count();
        System.out.println("State("+state+") : "+stateCount);
    }

    public List<Contact> searchBasedOnCityOrStateToStore(String city, String state){
        return  contacts.stream()
                .filter(c -> c.getState().toLowerCase().equals(state.toLowerCase())
                        || c.getCity().toLowerCase().equals(city.toLowerCase())).collect(Collectors.toList());
    }

    public void sortBasedOnName(){
        contacts.stream().sorted(Comparator.comparing(Contact::getFirstName).thenComparing(Contact::getLastName))
                .forEach(c -> System.out.println(c));
    }

    public void sortBasedOnCityStateZip(){
        contacts.stream().sorted(Comparator.comparing(Contact::getCity).thenComparing(Contact::getState).thenComparing(Contact::getZip))
                .forEach(c -> System.out.println(c));
    }

    public void writeFile(String name){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME,true));) {
            for(Contact c : contacts){
                writer.write(name+" : "+ c.toString());
                writer.newLine();
            }
        } catch (IOException e){
            e.getMessage();
        }
    }

    public void readFile(){
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));){
            while(reader.ready()){
                System.out.println(reader.readLine());
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void writeCSVFIle(){

        try(CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILENAME))){
            for(Contact c : contacts) {
                String[] record = {
                        c.getFirstName(),
                        c.getLastName(),
                        c.getAdrress(),
                        c.getCity(),
                        c.getState(),
                        c.getPhoneNumber(),
                        c.getEmail(),
                        c.getZip()
                };
                writer.writeNext(record);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readCSVFile(){
        try(CSVReader reader = new CSVReader(new FileReader(CSV_FILENAME))){
            String[] nextLine;
            while((nextLine = reader.readNext()) != null){
                Arrays.stream(nextLine).forEach(s -> System.out.print(s+" "));
                System.out.println();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeJSONFile() {
        try (FileWriter writer = new FileWriter(JSON_FILENAME)) {
            gson.toJson(contacts, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readJSONFile() {
        try (FileReader reader = new FileReader(JSON_FILENAME)) {
            Type listType = new TypeToken<ArrayList<Contact>>(){}.getType();
            List<Contact> importedData = gson.fromJson(reader, listType);

            if (importedData != null) {
                contacts = importedData;
            }

            contacts.forEach(c -> System.out.println(c));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void display(){
        for(Contact c : contacts){
            System.out.println(c);
        }
    }

}

