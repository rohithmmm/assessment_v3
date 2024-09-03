package com.infosys.retailer.rewards.controller;

import com.infosys.retailer.rewards.model.Transaction;
import com.infosys.retailer.rewards.service.RewardsService;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    private final RewardsService rewardsService;

    public TransactionsController(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    // Calculates and saves the reward points for a specific customer for that transaction
    @PostMapping
    private ResponseEntity<Integer> addTransaction(@RequestBody Transaction transaction) {
        ResponseEntity<Integer> points = rewardsService.calculateRewardPoints(transaction.getAmount());
        rewardsService.saveTransaction(transaction);
        return points;
    }

    // Retrieves the reward points for a specific customer.
    @GetMapping("/{customerId}")
    public ResponseEntity<List<Transaction>> getTransactionsByCustomer(@PathVariable Long customerId) {
        return rewardsService.getTransactionsByCustomer(customerId);
    }

    /**
     * Endpoint to get transactions by customer ID and date range.
     *
     * @param customerId the customer ID
     * @param startDate  the start date of the range in 'yyyy-MM-dd' format
     * @param endDate    the end date of the range in 'yyyy-MM-dd' format
     * @return ResponseEntity containing a list of transactions for the given customer within the date range
     */
    @GetMapping("/{customerId}/range")
    public ResponseEntity<List<Transaction>> getTransactionsByCustomerAndDateRange(
            @PathVariable Long customerId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
        return rewardsService.getTransactionsByCustomerAndDateRange(customerId, start, end);
    }

    /**
     * Endpoint to get all transactions within a date range.
     *
     * @param startDate the start date of the range in 'yyyy-MM-dd' format
     * @param endDate   the end date of the range in 'yyyy-MM-dd' format
     * @return ResponseEntity containing a list of transactions within the date range
     */
    @GetMapping("/range")
    public ResponseEntity<List<Transaction>> getTransactionsByDateRange(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
        return rewardsService.getTransactionsByDateRange(start, end);
    }
}
