package com.transferscheduling.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.transferscheduling.domain.Agenda;
import com.transferscheduling.repositories.AgendaRepository;
//http://localhost:8082/agenda
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
	public Agenda agendar(Agenda agenda) {
		
		return agendaRepository.save(agenda);
	
	}

	@Override
	public Agenda obter(Integer id) {

		Optional<Agenda> categoria = agendaRepository.findById(id);

		return categoria.orElse(new Agenda());
	}
	
	
	
}
