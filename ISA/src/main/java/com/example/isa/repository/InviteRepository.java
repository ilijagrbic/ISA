package com.example.isa.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.isa.model.users.Invite;



public interface InviteRepository extends JpaRepository<Invite, Long>{
	
	public Invite findByPosiljalacIdAndPrimalacId(long posiljalacId, long primalacId);
	
	public  List<Invite> findByPosiljalacIdOrPrimalacId(long posiljalacId, long primalacId);
}
