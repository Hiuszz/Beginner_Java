package client.models;

import client.cores.ClientFactory;
import client.network.Client;
import shared.object.Customer;

import java.beans.PropertyChangeListener;

public class CustomerManager implements Manager {
    private Customer customer;
    private Client client;

    public CustomerManager(ClientFactory cf) {
        this.client = cf.getClient();
    }
    @Override
    public void loadFromServer() {
    }

    @Override
    public void upToServer() {
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {

    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {

    }
}
