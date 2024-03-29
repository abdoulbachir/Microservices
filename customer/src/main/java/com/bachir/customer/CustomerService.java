package com.bachir.customer;

import com.bachir.amqp.RabbitMQMessageProducer;
import com.bachir.clients.fraud.FraudCheckResponse;
import com.bachir.clients.fraud.FraudClient;
import com.bachir.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

//    private final RestTemplate restTemplate;
    private CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer producer;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // todo: check if email is valid
        // todo: check if email not taken
        customerRepository.saveAndFlush(customer);

        // todo: check if fraudster
//        //Using RestTemplate
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId()
//        );

        //Using Open Feign
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        //send notification
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Abdoul Bachir Project......", customer.getFirstName())
        );

        producer.publish(notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");
    }
}
