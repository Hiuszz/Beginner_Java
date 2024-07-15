package server.model;

import server.object.*;
import shared.transferObject.Request;
import shared.transferObject.Response;

import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AccountManager implements Manager{
    private String path = "data/account.txt";

    private static int lastAcc = 0;
    public static void setLastAcc(int n){AccountManager.lastAcc = n;}
    public static int getLastAcc(){return lastAcc;}

    private Map<Integer, Account> hashAcc;
    private PersonManager personManager;

    public AccountManager(){
        hashAcc = new HashMap<>();
        personManager = new PersonManager();
        personManager.loadFromFile();
    }

    public Response createAccount(String data){
        String[] messageArr = {
                "<This phone number is not exist>",
                "<This phone number is registed>",
                "<Create account successfully>",
                "<Create account failed>"};
        Response response = null;
        try {
            String[] dataArr = data.split(",");
            int tel = Integer.valueOf(dataArr[1]);

            if(!personManager.exist(tel)){
                response = new Response("CreateAccount",messageArr[0]);
            }
            else {
                if (hashAcc.get(tel)!=null) response = new Response("CreateAccount",messageArr[1]);
                else{
                    try {
                        int id = AccountManager.getLastAcc()+1;
                        Account account = Account.fromString(id+","+data);
                        AccountManager.setLastAcc(AccountManager.getLastAcc()+1);
                        hashAcc.put(tel,account);
                        saveToFile(account.toString());
                        response = new Response("CreateAccount",messageArr[2]);
                    }
                    catch(Exception e){
                        response = new Response("CreateAccount",messageArr[3]);
                    }
                }
            }
        }catch (Exception ex){};
        return response;
    }

    @Override
    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Account account = Account.fromString(line);
                    hashAcc.put(account.getTel(), account);
                    if (account.getIdAcc() > AccountManager.getLastAcc()) {
                        AccountManager.setLastAcc(account.getIdAcc() + 1);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("\u001B[31m" + "<Skipping invalid account data: " + line + ">" + "\u001B[0m");
                }
            }
            System.out.println("\u001B[32m" + "<Load account successfully>" + "\u001B[0m");
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

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {

    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {

    }
}
