package com.transferscheduling.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.transferscheduling.domain.Agenda;

public interface AgendaService {
	public List<Agenda> listar() ;
	
	public Integer agendar(Agenda agenda) ;
}
