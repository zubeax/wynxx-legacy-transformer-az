package com.example.demo.service;

import com.example.demo.dto.CreateCustomerRequestDto;
import com.example.demo.dto.CustomerResponseDto;
import com.example.demo.dto.UpdateCustomerRequestDto;
import com.example.demo.entity.Customer;
import com.example.demo.enums.CustomerStatus;
import com.example.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public CustomerResponseDto createCustomer(CreateCustomerRequestDto request) {
        log.info("Creating new customer with email: {}", request.getEmail());

        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Customer with email '" + request.getEmail() + "' already exists");
        }

        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setAddressLine1(request.getAddressLine1());
        customer.setAddressLine2(request.getAddressLine2());
        customer.setCity(request.getCity());
        customer.setState(request.getState());
        customer.setPostalCode(request.getPostalCode());
        customer.setCountry(request.getCountry());
        customer.setNotes(request.getNotes());
        customer.setStatus(CustomerStatus.ACTIVE);
        customer.setLoyaltyPoints(0);

        Customer savedCustomer = customerRepository.save(customer);
        log.info("Customer created successfully with ID: {}", savedCustomer.getId());
        return convertToResponse(savedCustomer);
    }

    @Transactional(readOnly = true)
    public Optional<CustomerResponseDto> getCustomerById(Long id) {
        log.debug("Fetching customer by ID: {}", id);
        return customerRepository.findById(id).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Optional<CustomerResponseDto> getCustomerByEmail(String email) {
        log.debug("Fetching customer by email: {}", email);
        return customerRepository.findByEmail(email).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<CustomerResponseDto> getAllCustomers(Pageable pageable) {
        log.debug("Fetching all customers with pageable: {}", pageable);
        return customerRepository.findAll(pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<CustomerResponseDto> getCustomersByStatus(CustomerStatus status, Pageable pageable) {
        log.debug("Fetching customers by status: {}", status);
        return customerRepository.findByStatus(status, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<CustomerResponseDto> searchCustomers(String searchTerm, CustomerStatus status, Pageable pageable) {
        log.debug("Searching customers with term: '{}', status: {}", searchTerm, status);
        if (status != null) {
            return customerRepository.findByStatusAndSearchTerm(status, searchTerm, pageable).map(this::convertToResponse);
        }
        return customerRepository.findBySearchTerm(searchTerm, pageable).map(this::convertToResponse);
    }

    @Transactional
    public CustomerResponseDto updateCustomer(Long id, UpdateCustomerRequestDto request) {
        log.info("Updating customer with ID: {}", id);

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));

        if (request.getFirstName() != null) customer.setFirstName(request.getFirstName());
        if (request.getLastName() != null) customer.setLastName(request.getLastName());
        if (request.getPhoneNumber() != null) customer.setPhoneNumber(request.getPhoneNumber());
        if (request.getAddressLine1() != null) customer.setAddressLine1(request.getAddressLine1());
        if (request.getAddressLine2() != null) customer.setAddressLine2(request.getAddressLine2());
        if (request.getCity() != null) customer.setCity(request.getCity());
        if (request.getState() != null) customer.setState(request.getState());
        if (request.getPostalCode() != null) customer.setPostalCode(request.getPostalCode());
        if (request.getCountry() != null) customer.setCountry(request.getCountry());
        if (request.getStatus() != null) customer.setStatus(request.getStatus());
        if (request.getNotes() != null) customer.setNotes(request.getNotes());

        Customer updatedCustomer = customerRepository.save(customer);
        log.info("Customer updated successfully with ID: {}", updatedCustomer.getId());
        return convertToResponse(updatedCustomer);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        log.info("Deleting customer with ID: {}", id);
        if (!customerRepository.existsById(id)) {
            throw new IllegalArgumentException("Customer not found with ID: " + id);
        }
        customerRepository.deleteById(id);
        log.info("Customer deleted successfully with ID: {}", id);
    }

    @Transactional
    public CustomerResponseDto updateCustomerStatus(Long id, CustomerStatus newStatus) {
        log.info("Updating status for customer ID: {} to {}", id, newStatus);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));
        customer.setStatus(newStatus);
        Customer updatedCustomer = customerRepository.save(customer);
        log.info("Customer status updated to {} for ID: {}", newStatus, id);
        return convertToResponse(updatedCustomer);
    }

    @Transactional
    public CustomerResponseDto addLoyaltyPoints(Long id, int points) {
        log.info("Adding {} loyalty points to customer ID: {}", points, id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));
        customer.addLoyaltyPoints(points);
        Customer updatedCustomer = customerRepository.save(customer);
        log.info("Loyalty points updated for customer ID: {}. New balance: {}", id, updatedCustomer.getLoyaltyPoints());
        return convertToResponse(updatedCustomer);
    }

    @Transactional
    public CustomerResponseDto deductLoyaltyPoints(Long id, int points) {
        log.info("Deducting {} loyalty points from customer ID: {}", points, id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));
        customer.deductLoyaltyPoints(points);
        Customer updatedCustomer = customerRepository.save(customer);
        log.info("Loyalty points deducted for customer ID: {}. New balance: {}", id, updatedCustomer.getLoyaltyPoints());
        return convertToResponse(updatedCustomer);
    }

    private CustomerResponseDto convertToResponse(Customer customer) {
        CustomerResponseDto response = new CustomerResponseDto();
        response.setId(customer.getId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setFullName(customer.getFullName());
        response.setEmail(customer.getEmail());
        response.setPhoneNumber(customer.getPhoneNumber());
        response.setAddressLine1(customer.getAddressLine1());
        response.setAddressLine2(customer.getAddressLine2());
        response.setCity(customer.getCity());
        response.setState(customer.getState());
        response.setPostalCode(customer.getPostalCode());
        response.setCountry(customer.getCountry());
        response.setFullAddress(customer.getFullAddress());
        response.setStatus(customer.getStatus());
        response.setStatusDisplayName(customer.getStatus() != null ? customer.getStatus().getDisplayName() : null);
        response.setLoyaltyPoints(customer.getLoyaltyPoints());
        response.setNotes(customer.getNotes());
        response.setCreatedAt(customer.getCreatedAt());
        response.setUpdatedAt(customer.getUpdatedAt());
        return response;
    }
}
