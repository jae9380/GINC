package com.example.ginc.domain.garage.controller.port;

import com.example.ginc.domain.garage.domain.Refueling;

public interface RefuelService {
    void refueling(Refueling refueling, Long user_id);
}
