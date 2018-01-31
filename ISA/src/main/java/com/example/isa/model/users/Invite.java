package com.example.isa.model.users;

import javax.persistence.*;

@Entity
public class Invite {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private RegUser posaljilac;
	
	@ManyToOne
	private RegUser primalac;
	
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

	public RegUser getPosaljilac() {
		return posaljilac;
	}

	public void setPosaljilac(RegUser posaljilac) {
		this.posaljilac = posaljilac;
	}

	public RegUser getPrimalac() {
		return primalac;
	}

	public void setPrimalac(RegUser primalac) {
		this.primalac = primalac;
	}

	public boolean isPrihvatio() {
		return prihvatio;
	}

	public void setPrihvatio(boolean prihvatio) {
		this.prihvatio = prihvatio;
	}
	
	
}
