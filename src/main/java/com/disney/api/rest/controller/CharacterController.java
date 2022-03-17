package com.disney.api.rest.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.disney.api.rest.dto.CharacterDto;
import com.disney.api.rest.dto.Message;
import com.disney.api.rest.entity.Character;
import com.disney.api.rest.service.CharacterService;

@RestController
@RequestMapping("/characters")
@CrossOrigin
public class CharacterController {
	
	@Autowired
	CharacterService characterService;
	
	@GetMapping("/")
	public ResponseEntity<List<Character>> list() {
		List<Character> list = characterService.list();
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<Character> getByName(@PathVariable("name") String name) {
		if (!characterService.existsByName(name))
			return new ResponseEntity(new Message("The character does not exist!"), HttpStatus.NOT_FOUND);
		
		Character character = characterService.getByName(name).get();		
		return new ResponseEntity<>(character, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody CharacterDto charDto) {
		if (StringUtils.isBlank(charDto.getName()) )
			return new ResponseEntity<>(new Message("Name is required!"), HttpStatus.BAD_REQUEST);
		
		if (characterService.existsByName(charDto.getName()))
			return new ResponseEntity<>(new Message("Sorry, the character name already exists!"), HttpStatus.BAD_REQUEST);
		
		Character character = new Character(charDto.getName(), charDto.getAge());
		characterService.save(character);
		return new ResponseEntity<>(new Message("Character created"), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CharacterDto charDto) {
		if (!characterService.existsById(id))
			return new ResponseEntity<>("The character does not exist!", HttpStatus.NOT_FOUND);
		
		if (characterService.existsByName(charDto.getName()) && characterService.getByName(charDto.getName()).get().getId() != id)
			return new ResponseEntity<>(new Message("Sorry, the character name already exists!"), HttpStatus.BAD_REQUEST);
		
		if (StringUtils.isBlank(charDto.getName()) )
			return new ResponseEntity<>(new Message("Name is required!"), HttpStatus.BAD_REQUEST);
		
		Character character = characterService.getOne(id).get();
		character.setName(charDto.getName());
		character.setAge(charDto.getAge());
		characterService.save(character);
		return new ResponseEntity<>(new Message("Updated character"), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (!characterService.existsById(id))
			return new ResponseEntity<>("The character does not exist!", HttpStatus.NOT_FOUND);
		characterService.delete(id);
		return new ResponseEntity<>(new Message("Character deleted"), HttpStatus.OK);
	}
	
	

}
