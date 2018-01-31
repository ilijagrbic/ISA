package com.example.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.BioskopPozoriste;
import com.example.isa.model.BioskopPozoristeType;
import com.example.isa.repository.BioskopPozoristeRepository;

@Service
public class BioskopPozoristeService {

	@Autowired
	private BioskopPozoristeRepository bioskopPozoristeRepository;
	
	public List<BioskopPozoriste> getAll(){
		return bioskopPozoristeRepository.findAll();
	}
	
	public List<BioskopPozoriste> getAllCinemas(){
		return bioskopPozoristeRepository.findByType(BioskopPozoristeType.CINNEMA);
	}
	
	public List<BioskopPozoriste> getAllTheatres(){
		return bioskopPozoristeRepository.findByType(BioskopPozoristeType.THEATRE);
	} 
	
	public BioskopPozoriste getById(long id) {
		return bioskopPozoristeRepository.findById(id);
	}
	
	public BioskopPozoriste update(BioskopPozoriste updatedCinnema) {
		BioskopPozoriste toUpdate = getById(updatedCinnema.getId());
		if(toUpdate!=null) {
			return bioskopPozoristeRepository.save(toUpdate.update(updatedCinnema));
		}
		
		return null;
    }

    public BioskopPozoriste create(BioskopPozoriste newCinnema) {
    	if(bioskopPozoristeRepository.exists(newCinnema.getId())) {
    		return null;
    	}
    	else {
    		/*
    		 * Verovatno treba instancirati repertoar potencijalno i admine
    		 */
    		return bioskopPozoristeRepository.save(newCinnema);
    	}
    	
    }
}
