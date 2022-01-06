package com.transferscheduling.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.transferscheduling.domain.Agenda;
import com.transferscheduling.services.AgendaService;

@RestController
@RequestMapping(value="/agendas")
@CrossOrigin(origins = "http://localhost:8081")

public class AgendaController {
	
	
@Autowired
	private AgendaService agendaService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Agenda>> listar() 
	{
		String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
		System.out.println(cars[10]);
		
		return ResponseEntity.ok(agendaService.listar());
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Agenda> agendar(@Valid  @RequestBody Agenda agenda) 
	{
		return ResponseEntity.ok(agendaService.agendar(agenda));
	}
	
	
	@RequestMapping(value="{id}",method = RequestMethod.GET)
	public ResponseEntity<Agenda> obter(@PathVariable Integer id) 
	{
		return ResponseEntity.ok(agendaService.obter(id));
	}
	
}
