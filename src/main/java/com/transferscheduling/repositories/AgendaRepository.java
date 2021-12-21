package com.transferscheduling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.transferscheduling.domain.Agenda;

@Repository
public interface AgendaRepository  extends JpaRepository<Agenda,Integer> {

}
