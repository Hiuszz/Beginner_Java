package server.model;

import server.object.Account;
import server.object.Person;

import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PersonManager implements Manager {
    private Map<Integer, Person> hashID;
    private Map<Integer, Person> hashTel;
    private static int lastID = 0;
    private String path = "data/person.txt";
    public static void setLastID (int lastID) { PersonManager.lastID = lastID; }
    public static int getLastID () { return lastID; }

    public PersonManager() {
        hashID = new HashMap<>();
        hashTel = new HashMap<>();
    }

    @Override
    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Person person = Person.fromString(line);
                    hashID.put(person.getIdCard(), person);
                    hashTel.put(person.getTel(), person);
                    if (person.getIdCard() > PersonManager.getLastID()) {
                        PersonManager.setLastID(person.getIdCard() + 1);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("\u001B[31m" + "<Skipping invalid account data: " + line + ">" + "\u001B[0m");
                }
            }
            System.out.println("\u001B[32m" + "<Load person successfully>" + "\u001B[0m");
        } catch (IOException e) {
            System.out.println("\u001B[31m" + "<Error reading from file>" + "\u001B[0m");
        }
    }

    @Override
    public void saveToFile(String strObject) {
        try (FileWriter writer = new FileWriter(path, true)) {
            writer.write(strObject + "\n");
        } catch (IOException e) {
            System.out.println("\u001B[31m" + "<Error writing to file>" + "\u001B[0m");
        }
    }

    public Person find(int n){
        if(String.valueOf(n).length() == 6){
            return hashID.get(n);
        }
        else if (String.valueOf(n).length() == 7){
            return hashTel.get(n);
        }
        return null;
    }

    public boolean exist(int n){
        return find(n) != null;
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {

    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {

    }
}
