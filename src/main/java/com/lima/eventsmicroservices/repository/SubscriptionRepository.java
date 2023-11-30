package com.lima.eventsmicroservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lima.eventsmicroservices.domain.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    
}
