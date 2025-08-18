package com.example.route_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.route_service.model.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    // You can define custom query methods if needed, for example:
    Route findBySourceAndDestination(String source, String destination);
}
