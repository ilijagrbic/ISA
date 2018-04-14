package com.example.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.users.Invite;
import com.example.isa.model.users.User;
import com.example.isa.repository.InviteRepository;
import com.example.isa.repository.UserRepository;

@Service
public class FriendshipService {

	@Autowired
	private UserRepository regUserRepository;

	@Autowired
	private InviteRepository inviteRepository;

	// Slanje zahteva za prijateljstvo
	public List<User> createFriendshipRequest(Long idSender, Long idReceiver) {
		Invite invitation = new Invite();
		invitation.setPosaljalac(regUserRepository.findById(idSender));
		invitation.setPrimalac(regUserRepository.findById(idReceiver));
		invitation.setPrihvatio(false);
		inviteRepository.save(invitation);
		return findNonFriends(idSender);
	}

	// Ili da uzmem trenutnog korisnika
	public List<User> acceptFriendshipRequest(Long idSender, Long idReceiver) {
		Invite invitationToUpdate = inviteRepository.findByPosiljalacIdAndPrimalacId(idSender, idReceiver);
		invitationToUpdate.setPrihvatio(true);
		inviteRepository.save(invitationToUpdate);
		return findFriendshipRequest(idReceiver);
	}

	public List<User> deleteFriend(Long idSender, Long idReceiver) {
		Invite deleteFriend = inviteRepository.findByPosiljalacIdAndPrimalacId(idSender, idReceiver);
		inviteRepository.delete(deleteFriend);
		return findFriends(idReceiver);
	}

	public List<User> findFriends(Long id) {
		List<User> friends = new ArrayList<User>();
		for (Invite i : inviteRepository.findAll()) {
			if (i.getPrimalac().getId() == id && i.isPrihvatio() == true) {
				friends.add(i.getPosaljilac());
			}
		}
		return friends;
	}

	public List<User> findFriendshipRequest(Long id) {
		List<User> nonfriends = new ArrayList<User>();
		for (Invite i : inviteRepository.findAll()) {
			if (i.getPrimalac().getId() == (id) && i.isPrihvatio() == false) {
				nonfriends.add(i.getPosaljilac());
			}
		}
		return nonfriends;
	}

	public List<User> findNonFriends(Long id) {
		List<User> users = regUserRepository.findAll();
		List<Invite> friendships = inviteRepository.findByPosiljalacIdOrPrimalacId(id, id);
		for (Invite friendship : friendships) {
			if (id == friendship.getPosaljilac().getId()) {
				users.remove(friendship.getPrimalac());
			} else {
				users.remove(friendship.getPosaljilac());
			}
		}
		users.remove(regUserRepository.findById(id));

		return users;
	}

}
