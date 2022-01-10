package com.jmx.listener;


import com.jmx.dao.ProductDetails;

public interface ConsumerService {
    public ProductDetails onMessage(String message);
}
