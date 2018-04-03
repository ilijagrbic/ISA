package com.example.isa.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.isa.model.users.Invite;



public interface InviteRepository extends JpaRepository<Invite, Long>{
	// Proveri
	/*@Query("SELECT i FROM Invite i WHERE i.primalac.getId=receiverId and i.posiljalac.getId=senderId")
	public Invite findBySenderIdAndReceiverId(long senderId, long receiverId);
	
	@Query("SELECT i FROM Invite i WHERE i.primalac.getId=receiverId or i.posiljalac.getId=senderId")
	public  List<Invite> findBySenderIdOrReceiverId(long senderId, long receiverId);*/
}
