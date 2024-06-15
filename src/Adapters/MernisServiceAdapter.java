package Adapters;

import Abstracts.ICustomerCheckService;
import Entities.Customer;
import MernisServiceReference.QTGKPSPublicSoap;

import java.rmi.RemoteException;


public class MernisServiceAdapter implements ICustomerCheckService {

    QTGKPSPublicSoap client = new QTGKPSPublicSoap();

    @Override
    public boolean CheckIfRealPerson(Customer customer) throws Exception {
        boolean result = client.TCKimlikNoDogrula(Long.parseLong(customer.getNationalityId()),
                customer.getFirstName().toLowerCase(),
                customer.getLastName().toUpperCase(),
                customer.getDateOfBirth());


    return result;


    }
}
