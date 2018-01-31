package com.example.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.users.Invite;

public interface InviteRepository extends JpaRepository<Invite, Long>{

}
