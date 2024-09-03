package com.infosys.retailer.rewards.controller;

import com.infosys.retailer.rewards.service.RewardsService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TransactionsController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class TransactionsControllerDiffblueTest {
    @MockBean
    private RewardsService rewardsService;

    @Autowired
    private TransactionsController transactionsController;

    /**
     * Method under test:
     * {@link TransactionsController#getTransactionsByCustomer(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTransactionsByCustomer() throws Exception {

        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/{customerId}", 1L);

        // Act
        MockMvcBuilders.standaloneSetup(transactionsController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link TransactionsController#getTransactionsByCustomerAndDateRange(Long, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTransactionsByCustomerAndDateRange() throws Exception {

        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/{customerId}/range", 1L)
                .param("endDate", "foo")
                .param("startDate", "foo");

        // Act
        MockMvcBuilders.standaloneSetup(transactionsController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link TransactionsController#getTransactionsByDateRange(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTransactionsByDateRange() throws Exception {

        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/range")
                .param("endDate", "foo")
                .param("startDate", "foo");

        // Act
        MockMvcBuilders.standaloneSetup(transactionsController).build().perform(requestBuilder);
    }
}
