package org.foodie_tour;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseMigrationRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running Database Migration: Removing bookings_payment_method_check constraint...");
        try {
            jdbcTemplate.execute("ALTER TABLE bookings DROP CONSTRAINT IF EXISTS bookings_payment_method_check;");
            System.out.println("Database Migration Successful!");
        } catch (Exception e) {
            System.err.println("Database Migration Warning: " + e.getMessage());
        }
    }
}
