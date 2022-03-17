package com.disney.api.rest.dto;



import javax.validation.constraints.NotBlank;

public class CharacterDto {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private Integer age;

	
	
	public CharacterDto() {
	}
	
	public CharacterDto(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	

}
