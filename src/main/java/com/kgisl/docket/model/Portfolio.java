package com.kgisl.docket.model;

import javax.persistence.*;


@Entity
public class Portfolio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column
	String cat;

	@Column
	String symbol;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}


	@Override
	public String toString() {
		return "[" + id + " " + cat + " " + symbol + "]";
	}

}