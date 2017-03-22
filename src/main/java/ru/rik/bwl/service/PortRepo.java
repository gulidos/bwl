package ru.rik.bwl.service;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.rik.bwl.domain.Port;

public interface PortRepo extends JpaRepository<Port, String> {


}
