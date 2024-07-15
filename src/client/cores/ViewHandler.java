package client.cores;

import client.network.Client;
import client.network.SocketClient;
import client.views.menu.MenuView;
import client.views.menu.MenuViewModel;

import java.util.Scanner;

public class ViewHandler {
    Scanner sc = new Scanner(System.in);
    private ViewModelFactory vmf;
    public ViewHandler(ViewModelFactory vmf) {
        this.vmf = vmf;
    }
    public void startApp(){
        while (true){
            loadMainMenu();
        }
    }

    public void loadMainMenu(){
        MenuViewModel menuViewModel = vmf.getMenuViewModel();
        MenuView menuView = menuViewModel.getMenuView();
        menuView.menuA();
        ModelFactory modelFactory = vmf.getModelFactory();
        ClientFactory clientFactory = modelFactory.getClientFactory();
        SocketClient client = (SocketClient) clientFactory.getClient();
        client.startClient();
        System.out.println("\u001B[36m"+"#Main Menu:"+"\u001B[0m");
        boolean flag = true;
        int choice;
        if(flag){
            do{choice = getChoice();
                switch (choice){
                    case 1:
                        client.createAccount();
                        break;
                    case 2:
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\u001B[31m"+"<Invalid choice>"+"\u001B[0m");
                }
            } while (choice < 1 || choice > 3);
        }
    }

    public int getChoice() {
        int choice = 0;
        try {
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
        } catch (Exception e) {}
        return choice;
    }
}
