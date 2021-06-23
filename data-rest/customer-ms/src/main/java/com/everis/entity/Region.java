package com.everis.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_region")
@Data @NoArgsConstructor @AllArgsConstructor @Builder

public class Region {
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
}
