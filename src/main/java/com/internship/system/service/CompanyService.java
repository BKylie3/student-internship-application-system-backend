package com.internship.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.internship.system.domain.Booth;
import com.internship.system.domain.Company;
import com.internship.system.domain.Field;
import com.internship.system.repository.BoothRepository;
import com.internship.system.repository.CompanyRepository;
import com.internship.system.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private BoothRepository boothRepository;

    @Autowired
    private FieldRepository fieldRepository;

    public String saveCompany(Company company) {

        if (company.getBooth() != null
                && company.getBooth().getId() != null) {

            Optional<Booth> booth =
                    boothRepository.findById(
                            company.getBooth().getId());

            if (booth.isEmpty()) {
                return "The booth with that id does not exist";
            }

            company.setBooth(booth.get());
        }

        if (company.getFields() != null) {

            List<Field> savedFields =
                    new ArrayList<>();

            for (Field field : company.getFields()) {

                Optional<Field> existingField =
                        fieldRepository.findById(
                                field.getId());

                if (existingField.isEmpty()) {
                    return "The field with that id does not exist";
                }

                savedFields.add(
                        existingField.get());
            }

            company.setFields(savedFields);
        }

        companyRepository.save(company);

        return "Company saved successfully";
    }

    public List<Company> getAllCompanies() {

        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(UUID id) {

        return companyRepository.findById(id);
    }

    public String updateCompany(
            UUID id,
            Company company) {

        Optional<Company> existingCompany =
                companyRepository.findById(id);

        if (existingCompany.isEmpty()) {
            return "The company with that id does not exist";
        }

        Company dbCompany =
                existingCompany.get();

        dbCompany.setCompanyName(
                company.getCompanyName());

        dbCompany.setContactPerson(
                company.getContactPerson());

        dbCompany.setEmail(
                company.getEmail());

        if (company.getBooth() != null
                && company.getBooth().getId() != null) {

            Optional<Booth> booth =
                    boothRepository.findById(
                            company.getBooth().getId());

            if (booth.isEmpty()) {
                return "The booth with that id does not exist";
            }

            dbCompany.setBooth(
                    booth.get());
        }

        if (company.getFields() != null) {

            List<Field> savedFields =
                    new ArrayList<>();

            for (Field field : company.getFields()) {

                Optional<Field> existingField =
                        fieldRepository.findById(
                                field.getId());

                if (existingField.isEmpty()) {
                    return "The field with that id does not exist";
                }

                savedFields.add(
                        existingField.get());
            }

            dbCompany.setFields(savedFields);
        }

        companyRepository.save(
                dbCompany);

        return "Company updated successfully";
    }

    public String deleteCompany(UUID id) {

        if (!companyRepository.existsById(id)) {

            return "The company with that id does not exist";
        }

        companyRepository.deleteById(id);

        return "Company deleted successfully";
    }
    public List<Company>getCompaniesByField(String name){
        return companyRepository.findCompaniesByFieldName(name);
    }
}