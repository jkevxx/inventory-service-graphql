package com.app.ecom.repository;

import com.app.ecom.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
