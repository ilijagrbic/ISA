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
	public ResponseEntity<Collection<MovieShow>> getMoviesFromCinema(@PathVariable("id") Long id){
		ArrayList<MovieShow> retVal = (ArrayList<MovieShow>)movieService.getFromCinema(id);
		if(retVal!=null)
			return new ResponseEntity<Collection<MovieShow>>(retVal, HttpStatus.OK);
		else
			return new ResponseEntity<Collection<MovieShow>>(retVal, HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping(
			value = "/api/movies/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MovieShow> getOneMovie(@PathVariable("id") Long id){
		MovieShow retVal = movieService.getOneMovie(id);
		if(retVal!=null) {
			return new ResponseEntity<MovieShow>(retVal, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<MovieShow>(retVal, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(
			value = "/api/cinnemas/{id}/movies",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MovieShow> addMovie(@RequestBody MovieShowDTO createMovie, @PathVariable("id") Long id){
		MovieShow retVal = movieService.create(createMovie.getMovieShow(), id);
		if(retVal!=null) {
			return new ResponseEntity<MovieShow>(retVal, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<MovieShow>(retVal, HttpStatus.BAD_REQUEST);
		}
	
		
		//TODO Uraditi brisanje i update filma ukoliko bude potrebno.
	}
	
	@RequestMapping(
			value = "/api/movies/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MovieShow> updateMovie(@RequestBody MovieShowDTO createMovie, @PathVariable("id") Long idMovie){
		MovieShow temp = createMovie.getMovieShow();
		temp.setId(createMovie.getId());
		MovieShow retVal = movieService.update(temp, idMovie);
		if(retVal!=null) {
			return new ResponseEntity<MovieShow>(retVal, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<MovieShow>(retVal, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(
			value = "/api/movies/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> updateMovie(@PathVariable("id") Long idMovie){
		Long retVal = movieService.delete(idMovie);
		if(retVal!=null) {
			return new ResponseEntity<Long>(retVal, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Long>(retVal, HttpStatus.BAD_REQUEST);
		}
	}
}