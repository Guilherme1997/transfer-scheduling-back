package com.transferscheduling.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.transferscheduling.domain.Agenda;
import com.transferscheduling.services.AgendaService;

@RestController
public class AgendaController {
	
   @Autowired
	private AgendaService agendaService;

	@RequestMapping(value="agendas",method = RequestMethod.GET)
	public ResponseEntity<List<Agenda>> listar() 
	{
		return ResponseEntity.ok(agendaService.listar());
	}
	
	
	@RequestMapping(value="agenda",method = RequestMethod.POST)
	public ResponseEntity<Agenda> agendar(@RequestBody Agenda agenda) 
	{
		return ResponseEntity.ok(agendaService.agendar(agenda));
	}
	
	
	@RequestMapping(value="agenda",method = RequestMethod.GET)
	public ResponseEntity<Agenda> agendar(@PathVariable Integer id) 
	{
		return ResponseEntity.ok(agendaService.obter(id));
	}
	
	
	
}
