package com.infosys.retailer.rewards.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.infosys.retailer.rewards.repository.TransactionRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class RewardsServiceDiffblueTest {
    /**
     * Method under test:
     * {@link RewardsService#RewardsService(TransactionRepository)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testNewRewardsService() {

        // Arrange
        // TODO: Populate arranged inputs
        TransactionRepository transactionRepository = null;

        // Act
        RewardsService actualRewardsService = new RewardsService(transactionRepository);

    }

    /**
     * Method under test:
     * {@link RewardsService.RewardCalculationException#RewardCalculationException(String)}
     */
    @Test
    void testRewardCalculationExceptionNewRewardCalculationException() {
        // Arrange and Act
        RewardsService.RewardCalculationException actualRewardCalculationException = new RewardsService.RewardCalculationException(
                "An error occurred");

        // Assert
        assertEquals("An error occurred", actualRewardCalculationException.getMessage());
        assertNull(actualRewardCalculationException.getCause());
        assertEquals(0, actualRewardCalculationException.getSuppressed().length);
    }

    /**
     * Method under test:
     * {@link RewardsService.TransactionRetrievalException#TransactionRetrievalException(String)}
     */
    @Test
    void testTransactionRetrievalExceptionNewTransactionRetrievalException() {
        // Arrange and Act
        RewardsService.TransactionRetrievalException actualTransactionRetrievalException = new RewardsService.TransactionRetrievalException(
                "An error occurred");

        // Assert
        assertEquals("An error occurred", actualTransactionRetrievalException.getMessage());
        assertNull(actualTransactionRetrievalException.getCause());
        assertEquals(0, actualTransactionRetrievalException.getSuppressed().length);
    }
}
