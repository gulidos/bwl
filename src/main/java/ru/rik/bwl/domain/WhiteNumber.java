package ru.rik.bwl.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="whitelist")
public class WhiteNumber {
	private String number;
	private Date date;
	private String tag;
	
	public WhiteNumber() {	}
	

}
