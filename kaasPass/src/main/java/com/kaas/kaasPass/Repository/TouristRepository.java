package com.kaas.kaasPass.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kaas.kaasPass.Model.Tourist;

@Repository
public interface TouristRepository extends JpaRepository<Tourist, Long>{
	
}
