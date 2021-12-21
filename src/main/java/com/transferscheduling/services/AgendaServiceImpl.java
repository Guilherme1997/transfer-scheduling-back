package com.transferscheduling.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.transferscheduling.domain.Agenda;
import com.transferscheduling.repositories.AgendaRepository;
	
@Service
public class AgendaServiceImpl implements AgendaService {
	
	@Autowired
	private AgendaRepository agendaRepository;

	@Override
	public List<Agenda> listar() {
		
	  List<Agenda> agenda = agendaRepository.findAll();
		
		return agenda;
	}

	@Override
	public Integer agendar(Agenda agenda) {
		
		agendaRepository.save(agenda);
		return null;
	}
	
	
	
}
