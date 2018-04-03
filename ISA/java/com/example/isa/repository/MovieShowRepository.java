package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.MovieShow;

public interface MovieShowRepository extends JpaRepository<MovieShow, Long>{

	List<MovieShow> findByRepertoarId(long id);
	
	MovieShow findById(long id);
}
