package client.cores;

import client.views.customer.CustomerViewModel;
import client.views.menu.MenuView;
import client.views.menu.MenuViewModel;

public class ViewModelFactory {
    private ModelFactory mf;
    private MenuViewModel menuViewModel;
    private CustomerViewModel customerViewModel;
    public ViewModelFactory(ModelFactory mf) {
        this.mf = mf;
    }
    public ModelFactory getModelFactory() {
        return mf;
    }
    public MenuViewModel getMenuViewModel() {
        if (menuViewModel == null) {
            menuViewModel = new MenuViewModel();
        }
        return menuViewModel;
    }
    public CustomerViewModel getCustomerViewModel(){
        if (customerViewModel == null) {
            customerViewModel = new CustomerViewModel();
        }
        return customerViewModel;
    }

}
