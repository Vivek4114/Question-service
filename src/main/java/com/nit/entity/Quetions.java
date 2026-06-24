package com.nit.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="Quetions")
public class Quetions {
	 
	@Id 
	@Schema(hidden = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;	
	private String Quetion;
	private String opt1;
	private String opt2;
	private String opt3;
	private String opt4;
	private Integer correctAns;
	private String cateogary;
	private String level;
	

}
