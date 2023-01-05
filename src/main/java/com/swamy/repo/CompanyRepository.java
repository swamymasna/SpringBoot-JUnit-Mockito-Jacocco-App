package com.swamy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swamy.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
