package com.main.uatouristassistant.repository;

import com.main.uatouristassistant.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByStreetAndNumberHouse(String streetName, String numberHouse);

    boolean existsByStreetAndNumberHouse(String street, String numberHouse);
}
