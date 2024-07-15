package client.models;

import client.cores.ClientFactory;
import client.network.Client;
import server.model.PersonManager;

import java.beans.PropertyChangeListener;

public class ProductManager implements Manager{
    private Client client;
    public ProductManager(ClientFactory cf){
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
