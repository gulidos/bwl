package ru.rik.bwl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Data
@Table(name="bdpn")
public class Port {

	@Id @Column(name="Number")
	private String number;
	private int mnc;
	
	public String getMncFormatted() {
		return String.format("%03d", mnc);
		
	}
} 
