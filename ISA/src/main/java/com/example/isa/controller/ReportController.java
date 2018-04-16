package com.example.isa.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.controller.dataTransfer.ReportDTO;
import com.example.isa.model.BioskopPozoriste;
import com.example.isa.service.ReservationService;


@RestController
@RequestMapping("/api")
public class ReportController {
	
	@Autowired
	private ReservationService reservationService;

	@RequestMapping(
			value = "/cinnemas/{id}/avgAmbijent",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReportDTO> getBioskopPozoriste(@PathVariable("id") Long id){
		return new ResponseEntity<ReportDTO>(new ReportDTO(reservationService.getAvgAmbijent(id)), HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/projections/{id}/avgAmbijent",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReportDTO> getProjAvg(@PathVariable("id") Long id){
		return new ResponseEntity<ReportDTO>(new ReportDTO(reservationService.getAvgProjOcena(id)), HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/movies/{id}/avgAmbijent",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReportDTO> getMovAvg(@PathVariable("id") Long id){
		return new ResponseEntity<ReportDTO>(new ReportDTO(reservationService.getAvgMovOcena(id)), HttpStatus.OK);
	}
	
}
