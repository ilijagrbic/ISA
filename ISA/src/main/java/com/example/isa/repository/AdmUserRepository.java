package com.example.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.users.AdmUser;

public interface AdmUserRepository extends JpaRepository<AdmUser, Long>{

}
