package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@Entity
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	private String street;
	private String city;
	private int zipcode;
	private String country;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "{" + "street='" + street + '\'' + ", city='" + city + '\'' + ", zipcode=" + zipcode + ", country='"
				+ country + '\'' + '}';
	}

}
