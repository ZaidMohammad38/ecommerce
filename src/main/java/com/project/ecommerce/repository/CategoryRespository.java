
package com.project.ecommerce.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entity.Category;

public interface CategoryRespository extends JpaRepository<Category, Integer>
{
//	List<Category> findByName(String name);
	Optional<Category> findByName(String name);

	void deleteById(Long id);

	Optional<Category> findById(Long id);

}
