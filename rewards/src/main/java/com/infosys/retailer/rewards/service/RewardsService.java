package com.infosys.retailer.rewards.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.infosys.retailer.rewards.model.Transaction;
import com.infosys.retailer.rewards.repository.TransactionRepository;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Service class to handle business logic for calculating reward points.
 */
@Service
public class RewardsService {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(RewardsService.class);
    private final TransactionRepository transactionRepository;

    public RewardsService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

//    Business logic to calculate the reward points
    public ResponseEntity<Integer> calculateRewardPoints(double amount) {
        logger.info("Calculating reward points for amount: " + amount);
        int points = 0;
        try {
            if (amount > 100) {
                points += (int) ((amount - 100) * 2 + 50);
            } else if (amount > 50) {
                points += (int) (amount - 50);
            }
            logger.info("Reward points calculated: " + points);
            return ResponseEntity.ok(points);
        }
        catch (Exception e) {
            logger.warning("Error calculating reward points" + e);
            throw new RewardCalculationException("Failed to calculate reward points");
        }
    }

//    Saves the transaction
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

//    Retrieves a list of transactions for a given customer.
    public ResponseEntity<List<Transaction>> getTransactionsByCustomer(Long customerId) {
        // Retrieve transactions from the repository
    logger.info("Fetching transactions for customer ID: " + customerId);
    try {
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
        return ResponseEntity.ok(transactions);
    } catch (Exception e) {
        logger.warning("Error fetching transactions for customer ID: "+ customerId + e);
        throw new TransactionRetrievalException("Failed to retrieve transactions");
    }
    }

//  Calculates the total reward points for all customers within given dates
    public ResponseEntity<List<Transaction>> getTransactionsByCustomerAndDateRange(Long customerId, LocalDate startDate, LocalDate endDate) {
    logger.info("Fetching transactions for customer ID: " + customerId + " from " + startDate + " to " + endDate);
    try {
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndDateRange(customerId, startDate, endDate);
        return ResponseEntity.ok(transactions);
    } catch (Exception e) {
        logger.warning("Error fetching transactions for customer ID: " + customerId + " within date range" + e);
        throw new TransactionRetrievalException("Failed to retrieve transactions within date range");
    }
}

    public ResponseEntity<List<Transaction>> getTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
        logger.info("Fetching transactions from " + startDate + "to " + endDate);
        try {
            List<Transaction> transactions = transactionRepository.findByDateRange(startDate, endDate);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            logger.warning("Error fetching transactions within date range" + e);
            throw new TransactionRetrievalException("Failed to retrieve transactions within date range");
        }
    }

    // Custom exceptions
    public static class RewardCalculationException extends RuntimeException {
        public RewardCalculationException(String message) {
            super(message);
        }
    }

    public static class TransactionRetrievalException extends RuntimeException {
        public TransactionRetrievalException(String message) {
            super(message);
        }
    }

}
