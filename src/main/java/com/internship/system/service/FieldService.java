package com.internship.system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.internship.system.domain.Field;
import com.internship.system.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    public String saveField(Field field) {
        fieldRepository.save(field);
        return "Field saved successfully";
    }

    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    public Optional<Field> getFieldById(UUID id) {
        return fieldRepository.findById(id);
    }

    public String updateField(UUID id, Field field) {

        Optional<Field> existingField =
                fieldRepository.findById(id);

        if (existingField.isEmpty()) {
            return "The field with that id does not exist";
        }

        Field dbField = existingField.get();

        dbField.setName(field.getName());

        fieldRepository.save(dbField);

        return "Field updated successfully";
    }

    public String deleteField(UUID id) {

        if (!fieldRepository.existsById(id)) {
            return "The field with that id does not exist";
        }
    
        fieldRepository.deleteById(id);
        return "Field deleted successfully";
    }
}