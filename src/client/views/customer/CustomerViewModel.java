package client.views.customer;

public class CustomerViewModel {
    private CustomerView customerView;
    public CustomerViewModel() {
        customerView = new CustomerView();
    }
    public CustomerView getCustomerView() {
        return customerView;
    }
}
