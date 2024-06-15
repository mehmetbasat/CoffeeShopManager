package Concrete;

import Abstracts.BaseCustomerManager;
import Abstracts.ICustomerCheckService;
import Entities.Customer;

public class StarbucksCustomerManager extends BaseCustomerManager {

    private ICustomerCheckService customerCheckService;

    public StarbucksCustomerManager(ICustomerCheckService customerCheckService) {
        this.customerCheckService = customerCheckService;
    }

    @Override
    public void save(Customer customer) throws Exception  {
        if (customerCheckService.CheckIfRealPerson(customer)) {
            super.save(customer);

        } else {
            System.out.println("Not a valid person");
        }
    }
}
