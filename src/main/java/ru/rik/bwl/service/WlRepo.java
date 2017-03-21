package ru.rik.bwl.service;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.rik.bwl.domain.WhiteNumber;

public interface WlRepo extends JpaRepository<WhiteNumber, String> {
//	WhiteNumber getByNumber(String n);

}
