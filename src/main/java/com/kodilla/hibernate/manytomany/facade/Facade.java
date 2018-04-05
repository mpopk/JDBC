package com.kodilla.hibernate.manytomany.facade;


import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.manytomany.dao.EmployeeDao;
import com.kodilla.hibernate.manytomany.dao.CompanyDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Facade {

    @Autowired
    CompanyDao companyDao;
    @Autowired
    EmployeeDao employeeDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(Facade.class);


    public Company mapToCompany(final CompanyDto companyDto){
        return new Company(companyDto.getName());
    }

    public List<CompanyDto> mapToCompanyDtoList (List<Company> companyList){
        return companyList.stream()
                .map(t -> new CompanyDto(t.getName()))
                .collect(Collectors.toList());
    }

    public int getCompanies(String name) throws OrderProcessingException{
        boolean wasError = false;
        List<CompanyDto> companyByName = mapToCompanyDtoList(companyDao.retrieveCompanyByPartString(name));
        try {
            if(companyByName.size() == 0){
                LOGGER.error(OrderProcessingException.ERR_NOT_MATCH_RESULTS);
                wasError = true;
                throw new OrderProcessingException(OrderProcessingException.ERR_NOT_MATCH_RESULTS);
            } else {
                for (CompanyDto companyDto: companyByName){
                    LOGGER.info("Company "+ companyDto.getName());
                }
            }
        } finally {
            if(wasError){
                LOGGER.info("No results found");
            }
        }
        return companyByName.size();
    }


    public Employee mapToEmployee(final EmployeeDto employeeDto){
        return new Employee(employeeDto.getFirstname(), employeeDto.getLastname());
    }

    public List<com.kodilla.hibernate.manytomany.facade.EmployeeDto> mapToEmployeeDtoList() {
        return mapToEmployeeDtoList();
    }

    public List<com.kodilla.hibernate.manytomany.facade.EmployeeDto> mapToEmployeeDtoList (List<Employee> employeeList){
        return employeeList.stream()
                .map(t -> new com.kodilla.hibernate.manytomany.facade.EmployeeDto(t.getFirstname(), t.getLastname()))
                .collect(Collectors.toList());
    }

    public int getEmployees(String name) throws OrderProcessingException{
        boolean wasError = false;
        List<com.kodilla.hibernate.manytomany.facade.EmployeeDto> employeeByLastName = mapToEmployeeDtoList(employeeDao.retrieveEmployeeByPartLastName(name));
        try {
            if(employeeByLastName.size() == 0){
                LOGGER.error(OrderProcessingException.ERR_NOT_MATCH_RESULTS);
                wasError = true;
                throw new OrderProcessingException(OrderProcessingException.ERR_NOT_MATCH_RESULTS);
            } else {
                for (com.kodilla.hibernate.manytomany.facade.EmployeeDto employeeDto: employeeByLastName){
                    LOGGER.info("Employee "+ employeeDto.getFirstname() + " " + employeeDto.getLastname());
                }
            }
        } finally {
            if(wasError){
                LOGGER.info("No results found");
            }
        }
        return employeeByLastName.size();
    }
}
