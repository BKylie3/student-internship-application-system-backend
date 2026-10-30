package com.internship.system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.internship.system.domain.Application;
import com.internship.system.domain.ApplicationStatus;
import com.internship.system.domain.Placement;
import com.internship.system.repository.ApplicationRepository;
import com.internship.system.repository.PlacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlacementService {

    @Autowired
    private PlacementRepository placementRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    public String savePlacement(
            UUID applicationId,
            Placement placement) {

        Optional<Application> application =
                applicationRepository.findById(applicationId);

        if (application.isEmpty()) {

            return "The application with that id does not exist";
        }

        if (application.get().getStatus()
                != ApplicationStatus.ACCEPTED) {

            return "Placements are only for ACCEPTED applications";
        }

        placement.setApplication(
                application.get());

        placement.setRecordedDate(
                LocalDate.now());

        placementRepository.save(
                placement);

        return "Placement saved successfully";
    }

    public List<Placement> getPlacementsByStudent(
            String regNumber) {

        return placementRepository
                .findPlacementsByStudentRegNumber(
                        regNumber);
    }

    public String deletePlacement(UUID id) {

        if (!placementRepository.existsById(id)) {

            return "The placement with that id does not exist";
        }

        placementRepository.deleteById(id);

        return "Placement deleted successfully";
    }
    
}
