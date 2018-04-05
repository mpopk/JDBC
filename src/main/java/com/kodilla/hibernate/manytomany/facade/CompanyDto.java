
package com.kodilla.hibernate.manytomany.facade;


public class CompanyDto {

    private final String name;

    public CompanyDto(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

