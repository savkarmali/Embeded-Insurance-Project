package com.embededinsurance.policy.service;

import com.embededinsurance.policy.dto.Policy;
import com.embededinsurance.policy.repository.PolicyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PolicyServiceTest {

    @InjectMocks
    private PolicyServiceImpl policyService;    // Class under test

    @Mock
    private PolicyRepository policyRepository;  // Mock repository

    private Policy mockPolicy;

    @BeforeEach
    void setUp(){
        mockPolicy = new Policy(1L, "12345", "Health", new Date(), new Date(), 5000.0);
    }

    /* createPolicy */

    //Positive Test Case: Save the Policy successfully.
    @Test
    void testCreatePolicy_Success() {
        //Mocking the save method
        when(policyRepository.save(mockPolicy)).thenReturn(mockPolicy);

        //When: calling the createPolicy method
        Policy result = policyService.createPolicy(mockPolicy);

        //Then: Verifying the result and interactions
        assertEquals(mockPolicy, result);
        //verify(policyRepository, Mockito.times(1)).save(mockPolicy);
        verify(policyRepository).save(mockPolicy);
    }

    //    Negative Test Case: Handle null input.
    @Test
    void testCreatePolicy_NullPolicy() {
        IllegalArgumentException exception;
        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            policyService.createPolicy(null);
        });
        assertEquals("Policy can not be null",exception.getMessage());
    }

    /* getPolicy */

    //Positive Test Case: Fetch a policy by Id.
    @Test
    void testGetPolicy_Success(){
        when(policyRepository.findById(1L)).thenReturn(Optional.of(mockPolicy));
        Policy result = policyService.getPolicy(1L);
        assertEquals(mockPolicy, result);
        verify(policyRepository, Mockito.times(1)).findById(1L);
    }

    //Negative Test Case: Handle non-existent policy.
    @Test
    void testGetPolicy_NotFound(){
        when(policyRepository.findById(999L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            policyService.getPolicy(999L);
        });
        assertEquals("Policy not found",exception.getMessage());
    }

    /* getAllPolicies */

    //Positive Test Case: Return all policies.
    @Test
    void testGetAllPolicies_Success() {
        List<Policy> policies = List.of(mockPolicy);
        Mockito.when(policyRepository.findAll()).thenReturn(policies);
        List<Policy> result = policyService.getAllPolicies();
        assertEquals(policies, result);
        Mockito.verify(policyRepository, Mockito.times(1)).findAll();
    }

    //Negative Test Case: Handle empty repository.
    @Test
    void testGetAllPolicies_NoPolicies() {
        Mockito.when(policyRepository.findAll()).thenReturn(Collections.emptyList());
        List<Policy> result = policyService.getAllPolicies();
        Assertions.assertTrue(result.isEmpty());
        Mockito.verify(policyRepository, Mockito.times(1)).findAll();
    }

    /* updatePolicy */

    //Positive Test Case: Update an existing policy.
    @Test
    void testUpdatePolicy_Success(){
        when(policyRepository.findById(1L)).thenReturn(Optional.of(mockPolicy));
        when(policyRepository.save(mockPolicy)).thenReturn(mockPolicy);

        Policy updatedPolicy = new Policy(1L, "654321", "Life", new Date(), new Date(), 6000.0);
        Policy result = policyService.updatePolicy(1L, updatedPolicy);

        assertEquals(updatedPolicy.getPolicyNumber(), result.getPolicyNumber());
        assertEquals(updatedPolicy.getPolicyType(), result.getPolicyType());
        verify(policyRepository, Mockito.times(1)).save(mockPolicy);
    }

    //Negative Test Case: Attempt update with invalid ID.
    @Test
    void testUpdatePolicy_PolicyNotFound() {
        when(policyRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            policyService.updatePolicy(999L, mockPolicy);
        });
        assertEquals("Policy not found",exception.getMessage());
    }

    /* deletePolicy */

    //Positive Test Case: Delete a policy by ID.
    @Test
    void testDeletePolicy_Success(){
        Mockito.doNothing().when(policyRepository).deleteById(1L);
        policyService.deletePolicy(1L);
        verify(policyRepository, Mockito.times(1)).deleteById(1L);
    }

    //Negative Test Case: Handle deletion of a non-existent policy.
    @Test
    void testDeletePolicy_NotFound(){
        Mockito.doThrow(new RuntimeException("Policy Not Found")).when(policyRepository).deleteById(999L);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            policyService.deletePolicy(999L);
        });
        assertEquals("Policy Not Found", exception.getMessage());
    }

}
