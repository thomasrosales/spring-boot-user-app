package com.example.usersapp.repositories;

import com.example.usersapp.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, String> {
}
