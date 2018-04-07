package com.example.isa.model.users;

import javax.persistence.*;

@Entity
@Table(name = "Invite")
public class Invite {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private User posiljalac;
	
	@ManyToOne
	private User primalac;
	
	private boolean prihvatio;

	public Invite() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getPosaljilac() {
		return posiljalac;
	}

	public void setPosaljalac(User posiljalac) {
		this.posiljalac = posiljalac;
	}

	public User getPrimalac() {
		return primalac;
	}

	public void setPrimalac(User primalac) {
		this.primalac = primalac;
	}

	public boolean isPrihvatio() {
		return prihvatio;
	}

	public void setPrihvatio(boolean prihvatio) {
		this.prihvatio = prihvatio;
	}
	
	
}
