package com.swamy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swamy.exception.CompanyNotFoundException;
import com.swamy.model.Company;
import com.swamy.repo.CompanyRepository;
import com.swamy.service.ICompanyService;

@Service
@Transactional
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Integer saveCompany(Company company) {
		return companyRepository.save(company).getCmpId();
	}

	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public Company getOneCompany(Integer id) {
		Optional<Company> opt = companyRepository.findById(id);
		if(!opt.isPresent()) {
			throw new CompanyNotFoundException("Company Data '"+id+"' Not Exists");
		}
		return opt.get();
	}

	@Override
	public void deleteCompanyById(Integer id) {
		companyRepository.deleteById(id);
	}

	@Override
	public void updateCompanyById(Company company) {
		companyRepository.save(company);
	}

}

