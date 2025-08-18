package com.example.route_service.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.route_service.model.Route;
import com.example.route_service.repository.RouteRepository;
import com.example.route_service.service.RouteService;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Route updateRoute(Long routeId, Route route) {
        Optional<Route> existing = routeRepository.findById(routeId);
        if (existing.isPresent()) {
            Route updatedRoute = existing.get();
            updatedRoute.setSource(route.getSource());
            updatedRoute.setDestination(route.getDestination());
            updatedRoute.setDistance(route.getDistance());
            updatedRoute.setDuration(route.getDuration());
            return routeRepository.save(updatedRoute);
        }
        return null; // you can throw custom exception instead
    }

    @Override
    public void deleteRoute(Long routeId) {
        routeRepository.deleteById(routeId);
    }

    @Override
    public Optional<Route> getRouteById(Long routeId) {
        return routeRepository.findById(routeId);
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteBySourceAndDestination(String source, String destination) {
        return routeRepository.findBySourceAndDestination(source, destination);
    }
}
