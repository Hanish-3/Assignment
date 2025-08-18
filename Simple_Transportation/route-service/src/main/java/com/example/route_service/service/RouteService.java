package com.example.route_service.service;

import java.util.List;
import java.util.Optional;

import com.example.route_service.model.Route;

public interface RouteService {

    Route createRoute(Route route);

    Route updateRoute(Long routeId, Route route);

    void deleteRoute(Long routeId);

    Optional<Route> getRouteById(Long routeId);

    List<Route> getAllRoutes();

    Route getRouteBySourceAndDestination(String source, String destination);
}
