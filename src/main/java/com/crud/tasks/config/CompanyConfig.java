package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CompanyConfig {

    @Value("${info.app.company.name}")
    private String companyName;

    @Value("${info.app.owner.name}")
    private String companyOwnerName;

    @Value("${info.app.owner.surname}")
    private String companyOwnerSurname;

    @Value("${info.app.company.email}")
    private String companyMail;

    @Value("${info.app.company.phone}")
    private String companyPhone;
}
