package com.example.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.BioskopPozoriste;
import com.example.isa.model.BioskopPozoristeType;
import com.example.isa.model.MovieShow;
import com.example.isa.model.Repertoire;
import com.example.isa.repository.BioskopPozoristeRepository;
import com.example.isa.repository.RepertoireRepository;
import com.example.isa.repository.UserRepository;

@Service
public class BioskopPozoristeService {

	@Autowired
	private BioskopPozoristeRepository bioskopPozoristeRepository;
	@Autowired
	private RepertoireRepository repertoarRepository;
	@Autowired
	private UserRepository userRepository;
	
	public List<BioskopPozoriste> getAllAdmin(long id){
		if(userRepository.findById(id)!=null) {
			return userRepository.findById(id).getBioskopi();
		}
		else return null;
	}
	
	public List<BioskopPozoriste> getAllAdminCin(long id){
		if(userRepository.findById(id)!=null) {
			ArrayList<BioskopPozoriste> retVal = new ArrayList<BioskopPozoriste>();
			for(BioskopPozoriste b:userRepository.findById(id).getBioskopi()) {
				if(b.getType()==BioskopPozoristeType.CINNEMA) {
					retVal.add(b);
				}
			}
			return retVal;
		}
		else return null;
	}
	
	public List<BioskopPozoriste> getAllAdminThe(long id){
		if(userRepository.findById(id)!=null) {
			ArrayList<BioskopPozoriste> retVal = new ArrayList<BioskopPozoriste>();
			for(BioskopPozoriste b:userRepository.findById(id).getBioskopi()) {
				if(b.getType()==BioskopPozoristeType.THEATRE) {
					retVal.add(b);
				}
			}
			return retVal;
		}
		else return null;
	}
	
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
    	/*if(bioskopPozoristeRepository.exists(newCinnema.getId())) {
    		return null;
    	}
    	else {*/
    		/*
    		 * Verovatno treba instancirati repertoar potencijalno i admine
    		 */
		Repertoire temp = new Repertoire();
		temp.setBioskop(newCinnema);
		temp.setMovies(new ArrayList<MovieShow>());
		newCinnema.setRepertoire(temp);
		return bioskopPozoristeRepository.save(newCinnema);
    	//}
    	
    }
    
    public List<BioskopPozoriste> findCinemaTheatreByName(String toFind){
    	return bioskopPozoristeRepository.findByNameContainingIgnoreCase(toFind);
    }
}
