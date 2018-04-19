package com.example.isa.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.controller.dataTransfer.AlertMessageDTO;
import com.example.isa.controller.dataTransfer.MovieShowDTO;
import com.example.isa.model.MovieShow;
import com.example.isa.service.MovieShowService;

/*
 * testiranje potrebno
 */
@RestController
public class MovieShowController {

	@Autowired
	private MovieShowService movieService;
	
	@RequestMapping(
			value = "/api/movies",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<MovieShow>> getMovies(){
		
		return new ResponseEntity<Collection<MovieShow>>(movieService.getAll(), HttpStatus.OK);
		
	}
	
	@RequestMapping(
			value = "/api/cinnemas/{id}/movies",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getMoviesFromCinema(@PathVariable("id") Long id){
		ArrayList<MovieShow> retVal = (ArrayList<MovieShow>)movieService.getFromCinema(id);
		if(retVal!=null)
			return new ResponseEntity<Collection<MovieShow>>(retVal, HttpStatus.OK);
		else
			return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Ne postoji trazeno pozoriste/bioskop."), HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping(
			value = "/api/movies/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getOneMovie(@PathVariable("id") Long id){
		MovieShow retVal = movieService.getOneMovie(id);
		if(retVal!=null) {
			return new ResponseEntity<MovieShow>(retVal, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Ne postoji trazeni film."), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(
			value = "/api/cinnemas/{id}/movies",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addMovie(@RequestBody MovieShowDTO createMovie, @PathVariable("id") Long id){
		if(createMovie.getDuration()<1) {
			return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Trajanje ne moze biti negativno."), HttpStatus.BAD_REQUEST);
		}
		else if(createMovie.getPrice()<1) {
			return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Cena ne moze biti negativna."), HttpStatus.BAD_REQUEST);
		}
		else if(createMovie.getName()==null||createMovie.getName().isEmpty()||createMovie.getName().equals("")) {
			return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Ime ne sme biti prazno"), HttpStatus.BAD_REQUEST);
		}
		else {
			MovieShow retVal = movieService.create(createMovie.getMovieShow(), id);
			if(retVal!=null) {
				return new ResponseEntity<MovieShow>(retVal, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Ne postoji trazeno pozoriste/bioskop ili film vec postoji."), HttpStatus.BAD_REQUEST);
			}
		}
	
	}
	
	@RequestMapping(
			value = "/api/movies/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateMovie(@RequestBody MovieShowDTO createMovie, @PathVariable("id") Long idMovie){
		if(createMovie.getDuration()<1) {
			return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Trajanje ne moze biti negativno."), HttpStatus.BAD_REQUEST);
		}
		else if(createMovie.getPrice()<1) {
			return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Cena ne moze biti negativna."), HttpStatus.BAD_REQUEST);
		}
		else if(createMovie.getName().isEmpty()||createMovie.getName()==null||createMovie.getName().equals("")) {
			return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Ime ne sme biti prazno"), HttpStatus.BAD_REQUEST);
		}
		else {
			MovieShow temp = createMovie.getMovieShow();
			temp.setId(createMovie.getId());
			MovieShow retVal = movieService.update(temp, idMovie);
			if(retVal!=null) {
				return new ResponseEntity<MovieShow>(retVal, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Ne postoji trazeni film."), HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@RequestMapping(
			value = "/api/movies/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateMovie(@PathVariable("id") Long idMovie){
		Long retVal = movieService.delete(idMovie);
		if(retVal!=null) {
			return new ResponseEntity<Long>(retVal, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Ne postoji trazeni film."), HttpStatus.BAD_REQUEST);
		}
	}
}