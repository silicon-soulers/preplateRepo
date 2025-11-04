package com.codewithyin.preplate.model;

public enum OrderStatus {
    PENDING, // created and waiting for restaurant decision
    ACCEPTED, // restaurant accepted and user will pay
    DECLINED,
    PAID,
    PREPARING,
    READY,
    COMPLETED,
    CANCELED
}
