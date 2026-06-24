package com.nit.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nit.entity.Quetions;

import org.springframework.data.repository.query.Param;

@Repository
public interface QuetionRepo extends JpaRepository<Quetions, Integer> {

	public List<Quetions>  findByCateogary(String cateogary);
	
	public List<Quetions> findByLevel(String level);

	@Query("SELECT q FROM Quetions q WHERE q.cateogary = :category AND q.level = :level")
	List<Quetions> findByCategoryAndLevel(@Param("category") String category, @Param("level") String level);

	

}
