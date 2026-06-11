package com.shop.interfaces;

import com.shop.model.Client;

public interface ClientSelectionStrategy {
    Client selectClient(int id, String name, double balance);
}