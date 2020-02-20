package com.main.uatouristassistant.repository;

import com.main.uatouristassistant.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
