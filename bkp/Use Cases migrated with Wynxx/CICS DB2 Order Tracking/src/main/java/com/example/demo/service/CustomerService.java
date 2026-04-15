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
            throw new IllegalArgumentException("A customer with email '" + request.getEmail() + "' already exists");
        }

        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setCompanyName(request.getCompanyName());
        customer.setTaxId(request.getTaxId());
        customer.setBillingAddressLine1(request.getBillingAddressLine1());
        customer.setBillingAddressLine2(request.getBillingAddressLine2());
        customer.setBillingCity(request.getBillingCity());
        customer.setBillingState(request.getBillingState());
        customer.setBillingPostalCode(request.getBillingPostalCode());
        customer.setBillingCountry(request.getBillingCountry());
        customer.setShippingAddressLine1(request.getShippingAddressLine1());
        customer.setShippingAddressLine2(request.getShippingAddressLine2());
        customer.setShippingCity(request.getShippingCity());
        customer.setShippingState(request.getShippingState());
        customer.setShippingPostalCode(request.getShippingPostalCode());
        customer.setShippingCountry(request.getShippingCountry());
        customer.setCreditLimit(request.getCreditLimit());
        customer.setNotes(request.getNotes());
        customer.setStatus(CustomerStatus.ACTIVE);
        customer.setTotalOrders(0);
        customer.setTotalSpent(java.math.BigDecimal.ZERO);

        Customer savedCustomer = customerRepository.save(customer);
        log.info("Customer created successfully with ID: {}", savedCustomer.getId());
        return convertToResponse(savedCustomer);
    }

    @Transactional(readOnly = true)
    public Optional<CustomerResponseDto> getCustomerById(Long id) {
        log.debug("Fetching customer with ID: {}", id);
        return customerRepository.findById(id).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Optional<CustomerResponseDto> getCustomerByEmail(String email) {
        log.debug("Fetching customer with email: {}", email);
        return customerRepository.findByEmail(email).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<CustomerResponseDto> getAllCustomers(Pageable pageable) {
        log.debug("Fetching all customers, page: {}", pageable.getPageNumber());
        return customerRepository.findAll(pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<CustomerResponseDto> getCustomersByStatus(CustomerStatus status, Pageable pageable) {
        log.debug("Fetching customers with status: {}", status);
        return customerRepository.findByStatus(status, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<CustomerResponseDto> searchCustomers(String searchTerm, Pageable pageable) {
        log.debug("Searching customers with term: {}", searchTerm);
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
        if (request.getCompanyName() != null) customer.setCompanyName(request.getCompanyName());
        if (request.getTaxId() != null) customer.setTaxId(request.getTaxId());
        if (request.getBillingAddressLine1() != null) customer.setBillingAddressLine1(request.getBillingAddressLine1());
        if (request.getBillingAddressLine2() != null) customer.setBillingAddressLine2(request.getBillingAddressLine2());
        if (request.getBillingCity() != null) customer.setBillingCity(request.getBillingCity());
        if (request.getBillingState() != null) customer.setBillingState(request.getBillingState());
        if (request.getBillingPostalCode() != null) customer.setBillingPostalCode(request.getBillingPostalCode());
        if (request.getBillingCountry() != null) customer.setBillingCountry(request.getBillingCountry());
        if (request.getShippingAddressLine1() != null) customer.setShippingAddressLine1(request.getShippingAddressLine1());
        if (request.getShippingAddressLine2() != null) customer.setShippingAddressLine2(request.getShippingAddressLine2());
        if (request.getShippingCity() != null) customer.setShippingCity(request.getShippingCity());
        if (request.getShippingState() != null) customer.setShippingState(request.getShippingState());
        if (request.getShippingPostalCode() != null) customer.setShippingPostalCode(request.getShippingPostalCode());
        if (request.getShippingCountry() != null) customer.setShippingCountry(request.getShippingCountry());
        if (request.getStatus() != null) customer.setStatus(request.getStatus());
        if (request.getCreditLimit() != null) customer.setCreditLimit(request.getCreditLimit());
        if (request.getNotes() != null) customer.setNotes(request.getNotes());

        Customer updatedCustomer = customerRepository.save(customer);
        log.info("Customer updated successfully with ID: {}", updatedCustomer.getId());
        return convertToResponse(updatedCustomer);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        log.info("Deleting customer with ID: {}", id);

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));

        if (customer.getTotalOrders() != null && customer.getTotalOrders() > 0) {
            throw new IllegalStateException("Cannot delete customer with existing orders. Consider deactivating instead.");
        }

        customerRepository.deleteById(id);
        log.info("Customer deleted successfully with ID: {}", id);
    }

    @Transactional
    public CustomerResponseDto activateCustomer(Long id) {
        log.info("Activating customer with ID: {}", id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));
        customer.setStatus(CustomerStatus.ACTIVE);
        return convertToResponse(customerRepository.save(customer));
    }

    @Transactional
    public CustomerResponseDto deactivateCustomer(Long id) {
        log.info("Deactivating customer with ID: {}", id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));
        customer.setStatus(CustomerStatus.INACTIVE);
        return convertToResponse(customerRepository.save(customer));
    }

    @Transactional
    public CustomerResponseDto suspendCustomer(Long id, String reason) {
        log.info("Suspending customer with ID: {}, reason: {}", id, reason);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));
        customer.setStatus(CustomerStatus.SUSPENDED);
        if (reason != null && !reason.isBlank()) {
            String existingNotes = customer.getNotes() == null ? "" : customer.getNotes();
            customer.setNotes(existingNotes + "\n[SUSPENDED] " + reason);
        }
        return convertToResponse(customerRepository.save(customer));
    }

    private CustomerResponseDto convertToResponse(Customer customer) {
        CustomerResponseDto response = new CustomerResponseDto();
        response.setId(customer.getId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setFullName(customer.getFullName());
        response.setDisplayName(customer.getDisplayName());
        response.setEmail(customer.getEmail());
        response.setPhoneNumber(customer.getPhoneNumber());
        response.setCompanyName(customer.getCompanyName());
        response.setTaxId(customer.getTaxId());
        response.setBillingAddressLine1(customer.getBillingAddressLine1());
        response.setBillingAddressLine2(customer.getBillingAddressLine2());
        response.setBillingCity(customer.getBillingCity());
        response.setBillingState(customer.getBillingState());
        response.setBillingPostalCode(customer.getBillingPostalCode());
        response.setBillingCountry(customer.getBillingCountry());
        response.setShippingAddressLine1(customer.getShippingAddressLine1());
        response.setShippingAddressLine2(customer.getShippingAddressLine2());
        response.setShippingCity(customer.getShippingCity());
        response.setShippingState(customer.getShippingState());
        response.setShippingPostalCode(customer.getShippingPostalCode());
        response.setShippingCountry(customer.getShippingCountry());
        response.setStatus(customer.getStatus());
        response.setStatusDisplayName(customer.getStatus() != null ? customer.getStatus().getDisplayName() : null);
        response.setCreditLimit(customer.getCreditLimit());
        response.setTotalOrders(customer.getTotalOrders());
        response.setTotalSpent(customer.getTotalSpent());
        response.setNotes(customer.getNotes());
        response.setCanPlaceOrders(customer.canPlaceOrders());
        response.setCreatedAt(customer.getCreatedAt());
        response.setUpdatedAt(customer.getUpdatedAt());
        return response;
    }
}
