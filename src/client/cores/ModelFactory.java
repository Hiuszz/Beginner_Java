package client.cores;

import client.models.CustomerManager;
import client.models.ProductManager;
import shared.object.Customer;

public class ModelFactory {
    private ClientFactory cf;
    public ModelFactory(ClientFactory cf) {
        this.cf = cf;
    }
    public ClientFactory getClientFactory() {
        return cf;
    }
    public CustomerManager getCustomerManager(){
        return new CustomerManager(cf);
    }
    public ProductManager getProductManager(){
        return new ProductManager(cf);
    }
}
