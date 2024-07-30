public class DependencyInjectionExample {
    public static void main(String[] args) {
        Main main = new Main();
        main.test();
    }
}

class Main {
    void test() {
        CustomerService service = new CustomerService(new CustomerRepositoryImpl());
        service.findCustomerById(1023);
    }
}

interface CustomerRepository  {
    void findCustomerById(int id);
}

class CustomerRepositoryImpl implements CustomerRepository {
    public void findCustomerById(int id) {
        System.out.printf("A Customer with ID %d has been found.\n", id);
    }
}

class CustomerService {
    private CustomerRepository repository;

    CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    void findCustomerById(int id) {
        this.repository.findCustomerById(id);
    }
}