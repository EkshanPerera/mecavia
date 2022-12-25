package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mecavia.site.entity.Material;

public interface MaterialRepo extends JpaRepository<Material, Integer>{
}
