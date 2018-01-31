package com.example.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.users.RegUser;

public interface RegUserRepository extends JpaRepository<RegUser, Long>{

}
