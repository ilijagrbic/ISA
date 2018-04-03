package com.example.isa.model.users;

import javax.persistence.*;

@Entity
@Table(name = "Invite")
public class Invite {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private RegUser posiljalac;
	
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
		return posiljalac;
	}

	public void setPosaljalac(RegUser posiljalac) {
		this.posiljalac = posiljalac;
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
