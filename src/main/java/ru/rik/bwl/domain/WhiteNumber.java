package ru.rik.bwl.domain;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.cache.annotation.Cacheable;

import lombok.Data;

@Entity
@Data
@Table(name="whitelist")
public class WhiteNumber {
	@Id
	private String number;
	private Date date;
	private String tag;
	
	

}
