package com.disney.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.disney.api.rest.entity.Character;


@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
	
	Optional<Character> findByName(String name);
	boolean existsById(Long id);
	boolean existsByName(String name);
	Optional<Character> findByAge(Integer age);
	//Optional<Character> findByMovies(String movies);
	

}
