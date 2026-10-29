package com.internship.system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.internship.system.domain.Booth;
import com.internship.system.repository.BoothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoothService {

    @Autowired
    private BoothRepository boothRepository;

    public String saveBooth(Booth booth) {
        boothRepository.save(booth);
        return "Booth saved successfully";
    }

    public List<Booth> getAllBooths() {
        return boothRepository.findAll();
    }

    public Optional<Booth> getBoothById(UUID id) {
        return boothRepository.findById(id);
    }

    public String updateBooth(UUID id, Booth booth) {

        Optional<Booth> existingBooth =
                boothRepository.findById(id);

        if (existingBooth.isEmpty()) {
            return "The booth with that id does not exist";
        }

        Booth dbBooth = existingBooth.get();

        dbBooth.setBoothNumber(
                booth.getBoothNumber());

        dbBooth.setName(
                booth.getName());

        boothRepository.save(dbBooth);

        return "Booth updated successfully";
    }

    public String deleteBooth(UUID id) {

        if (!boothRepository.existsById(id)) {
            return "The booth with that id does not exist";
        }

        boothRepository.deleteById(id);
        return "Booth deleted successfully";
    }

    public List<Booth> getFreeBooths(){
        return boothRepository.findFreeBooths();
    }
}