package com.example.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.users.Invite;
import com.example.isa.model.users.RegUser;
import com.example.isa.repository.InviteRepository;
import com.example.isa.repository.RegUserRepository;

@Service
public class FriendshipService {
/*
	@Autowired
	private RegUserRepository regUserRepository;

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
		Invite invitationToUpdate = inviteRepository.findBySenderIdAndReceiverId(idSender, idReceiver);
		invitationToUpdate.setPrihvatio(true);
		return inviteRepository.save(invitationToUpdate);
	}

	public void deleteFriend(Long idSender, Long idReceiver) {
		Invite deleteFriend = inviteRepository.findBySenderIdAndReceiverId(idSender, idReceiver);
		inviteRepository.delete(deleteFriend);
	}

	public List<RegUser> findFriends(RegUser regUser) {
		List<RegUser> friends = new ArrayList<RegUser>();
		for (Invite i : inviteRepository.findAll()) {
			if (i.getPrimalac().equals(regUser) && i.isPrihvatio() == true) {
				friends.add(i.getPosaljilac());
			}
		}
		return friends;
	}

	public List<RegUser> findFriendshipRequest(RegUser regUser) {
		List<RegUser> nonfriends = new ArrayList<RegUser>();
		for (Invite i : inviteRepository.findAll()) {
			if (i.getPosaljilac().equals(regUser) && i.isPrihvatio() == false) {
				nonfriends.add(i.getPrimalac());
			}
		}
		return nonfriends;
	}

	public List<RegUser> findNonFriends(RegUser regUser) {
		List<RegUser> regUsers = regUserRepository.findAll();
		List<Invite> friendships = inviteRepository.findBySenderIdOrReceiverId(regUser.getId(), regUser.getId());
		for (Invite friendship : friendships) {
			if (regUser.getId() == friendship.getPosaljilac().getId()) {
				regUsers.remove(friendship.getPrimalac());
			} else {
				regUsers.remove(friendship.getPosaljilac());
			}
		}
		regUsers.remove(regUser);

		return regUsers;
	}
*/
}
