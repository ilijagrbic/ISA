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
	public Invite createFriendshipRequest(Long idSender, Long idReceiver) {
		Invite invitation = new Invite();
		invitation.setPosaljalac(regUserRepository.findById(idSender));
		invitation.setPrimalac(regUserRepository.findById(idReceiver));
		invitation.setPrihvatio(false);
		return inviteRepository.save(invitation);
	}

	// Ili da uzmem trenutnog korisnika
	public Invite acceptFriendshipRequest(Long idSender, Long idReceiver) {
		Invite invitationToUpdate = inviteRepository.findByPosiljalacIdAndPrimalacId(idSender, idReceiver);
		invitationToUpdate.setPrihvatio(true);
		return inviteRepository.save(invitationToUpdate);
	}

	public void deleteFriend(Long idSender, Long idReceiver) {
		Invite deleteFriend = inviteRepository.findByPosiljalacIdAndPrimalacId(idSender, idReceiver);
		inviteRepository.delete(deleteFriend);
	}

	public List<User> findFriends(User user) {
		List<User> friends = new ArrayList<User>();
		for (Invite i : inviteRepository.findAll()) {
			if (i.getPrimalac().equals(user) && i.isPrihvatio() == true) {
				friends.add(i.getPosaljilac());
			}
		}
		return friends;
	}

	public List<User> findFriendshipRequest(User user) {
		List<User> nonfriends = new ArrayList<User>();
		for (Invite i : inviteRepository.findAll()) {
			if (i.getPosaljilac().equals(user) && i.isPrihvatio() == false) {
				nonfriends.add(i.getPrimalac());
			}
		}
		return nonfriends;
	}

	public List<User> findNonFriends(User user) {
		List<User> users = regUserRepository.findAll();
		List<Invite> friendships = inviteRepository.findByPosiljalacIdOrPrimalacId(user.getId(), user.getId());
		for (Invite friendship : friendships) {
			if (user.getId() == friendship.getPosaljilac().getId()) {
				users.remove(friendship.getPrimalac());
			} else {
				users.remove(friendship.getPosaljilac());
			}
		}
		users.remove(user);

		return users;
	}

}
