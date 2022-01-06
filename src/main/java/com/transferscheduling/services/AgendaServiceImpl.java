package com.transferscheduling.services;

import java.text.DecimalFormat;
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
			calcularTaxaOperacaoTipoA(agenda);
		}
	}
	
	private void calcularTaxaOperacaoTipoA(Agenda agenda) {
		var taxa = 3;
		
		var porcentagem = (3 * agenda.getValor()) / 100;
		
		var valor = taxa + porcentagem;
		
		agenda.setTaxa( Math.round(  valor *100) / 100.0 );
	}
	
	private void OperacaoTipoB(Agenda agenda) {
		if(obterRangeOperacaoB(agenda)) {
			calcularTaxaOperacaoTipoB(agenda);
		}
	}
	
	
	private void calcularTaxaOperacaoTipoB(Agenda agenda) {
		agenda.setTaxa( 12.00);
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
	
	private void operacaoTipoC(Agenda agenda) {

		if(agenda.getDataTransferencia().isAfter(LocalDate.now().plusDays(10))) {
			agenda.setTaxa(  (8.2 * agenda.getValor()) / 100);
		}
		
		if(agenda.getDataTransferencia().isAfter(LocalDate.now().plusDays(20))) {
			agenda.setTaxa(  (6.9 * agenda.getValor()) / 100);
		}
		
		if(agenda.getDataTransferencia().isAfter(LocalDate.now().plusDays(30))) {
			agenda.setTaxa( (4.7 * agenda.getValor()) / 100);
		}
		if(agenda.getDataTransferencia().isAfter(LocalDate.now().plusDays(40))) {
			agenda.setTaxa(  (1.7 * agenda.getValor()) / 100);
		}
	}
	
	private void OperacaoTipoD(Agenda agenda) {
		if(agenda.getValor() > 0 && agenda.getValor() < 1001) {
			calcularTaxaOperacaoTipoA(agenda);
		}
		
		if(agenda.getValor() >= 1001 && agenda.getValor() <= 2000) {
			calcularTaxaOperacaoTipoB(agenda);
		}
		
		if(agenda.getValor() > 2000) {
			operacaoTipoC(agenda);
		}
	}
	
	@Override
	public Agenda agendar(Agenda agenda) {
		
		OperacaoTipoD(agenda);
		
		if(agenda.getTaxa() == null) {
			
			OperacaoTipoA(agenda);
			
			OperacaoTipoB(agenda);
			
			operacaoTipoC(agenda);
			
			agenda.setTaxa( Math.round(agenda.getTaxa()*100) / 100.0 );

		}
				
		return agendaRepository.save(agenda);
	
	}

	@Override
	public Agenda obter(Integer id) {

		Optional<Agenda> agenda = agendaRepository.findById(id);
		
		

		return agenda.orElse(new Agenda());
	}
	
	
	
}
