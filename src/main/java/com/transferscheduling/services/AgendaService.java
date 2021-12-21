package com.transferscheduling.services;

import java.util.List;
import com.transferscheduling.domain.Agenda;

public interface AgendaService {
	public List<Agenda> listar() ;
	
	public Agenda agendar(Agenda agenda) ;
	
	public Agenda obter(Integer id);
	
}
