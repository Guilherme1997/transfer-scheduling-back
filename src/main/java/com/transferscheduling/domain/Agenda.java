package com.transferscheduling.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Agenda implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer Id;
	
    public String ContaOrigem;
    
    public String ContaDestino;
    
    public Float Valor;
    
    public LocalDate DataTransferencia;
}
