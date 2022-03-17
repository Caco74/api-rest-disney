package com.disney.api.rest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disney.api.rest.entity.Character;
import com.disney.api.rest.repository.CharacterRepository;

@Service
@Transactional
public class CharacterService {
	
	@Autowired
	CharacterRepository characterRepository;
	
	public List<Character> list() {
		return characterRepository.findAll();
	}
	
	public Optional<Character> getOne(Long id) {
		return characterRepository.findById(id);
	}
	
	public Optional<Character> getByName(String name) {
		return characterRepository.findByName(name);
	}
	
	public void save(Character character) {
		characterRepository.save(character);
	}
	
	public void delete(Long id) {
		characterRepository.deleteById(id);
	}
	
	public boolean existsByName(String name) {
		return characterRepository.existsByName(name);
	}
	
	public boolean existsById(Long id) {
		return characterRepository.existsById(id);
	}
}
