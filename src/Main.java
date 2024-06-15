import Abstracts.BaseCustomerManager;
import Adapters.MernisServiceAdapter;
import Concrete.StarbucksCustomerManager;
import Entities.Customer;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception{


        BaseCustomerManager customerManager = new StarbucksCustomerManager(new MernisServiceAdapter());
        customerManager.save(new Customer(
                1,
                "Mehmet",
                "BASAT",
                2002,
                "11111111111"
        ));













    }
}