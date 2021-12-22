package com.transferscheduling.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
	
	private void OperacaoTipoA(Agenda agenda) {
		if( agenda.getDataTransferencia().equals(agenda.getDataAgendamento())) {
			
			var taxa = 3;
			
			var porcentagem = (3 * agenda.getValor()) / 100;
			
			agenda.setTaxa(taxa + porcentagem );
		}
	}
	
	private void OperacaoTipoB(Agenda agenda) {
		if(obterRangeOperacaoB(agenda)) {
			agenda.setTaxa((float) 12);
		}
	}
	
	private boolean obterRangeOperacaoB(Agenda agenda) {
		
		var diaAtual =  LocalDate.now();
		
		var diaPosterior = diaAtual.plusDays(1);
		
		var dataLimite = diaPosterior.plusDays(10);
		
		return ((agenda.getDataTransferencia()
				.isAfter(diaPosterior) || agenda.getDataTransferencia()
				.equals(diaPosterior)) && (agenda.getDataTransferencia()
				.isBefore(dataLimite) || agenda.getDataTransferencia()
				.equals(diaPosterior.plusDays(9))));
	}
	
	@Override
	public Agenda agendar(Agenda agenda) {

		OperacaoTipoA(agenda);
		
		OperacaoTipoB(agenda);
		
		return agendaRepository.save(agenda);
	
	}

	@Override
	public Agenda obter(Integer id) {

		Optional<Agenda> agenda = agendaRepository.findById(id);
		
		

		return agenda.orElse(new Agenda());
	}
	
	
	
}
