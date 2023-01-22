package com.raptor.dvz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {
    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }
//@GetMapping("/greet")
//    public GreetResponse greet(){
//        return new GreetResponse("Hello Divyanshu");
//    }
//    record GreetResponse(String greet){}
    @GetMapping
 public List<Customer> getCustomers(){
        return customerRepository.findAll();
 }

 record NewCustomerRequest(
         String name,
         String email,
         Integer age
 ){

 }
 @PostMapping
 public String addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
        return ("Record for " + request.name()+" inserted into the DB");
 }
 @DeleteMapping("{customerId}")
 public String deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
        return (" Record Deleted for given customer ID");
 }

}
