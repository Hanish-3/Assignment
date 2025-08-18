package com.example.route_service.dto;

import java.io.Serializable;

public class RouteEventDTO implements Serializable {

    private Long routeId;
    private String source;
    private String destination;
    private String action; // e.g., CREATED, UPDATED, DELETED

    // Default constructor
    public RouteEventDTO() {}

    // Parameterized constructor
    public RouteEventDTO(Long routeId, String source, String destination, String action) {
        this.routeId = routeId;
        this.source = source;
        this.destination = destination;
        this.action = action;
    }

    // Getters and Setters
    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "RouteEventDTO{" +
                "routeId=" + routeId +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
