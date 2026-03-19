package org.foodie_tour.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseMigration {

    @PersistenceContext
    private EntityManager entityManager;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void onStartup() {
        dropConstraintIfExists("transactions", "transactions_payment_method_check");
        dropConstraintIfExists("bookings", "bookings_payment_method_check");
    }

    private void dropConstraintIfExists(String table, String constraint) {
        try {
            String sql = String.format(
                    "ALTER TABLE %s DROP CONSTRAINT IF EXISTS %s", table, constraint);
            entityManager.createNativeQuery(sql).executeUpdate();
            System.out.println("[Migration] Dropped constraint " + constraint + " from " + table);
        } catch (Exception e) {
            System.out.println("[Migration] Skipping constraint " + constraint + ": " + e.getMessage());
        }
    }
}
