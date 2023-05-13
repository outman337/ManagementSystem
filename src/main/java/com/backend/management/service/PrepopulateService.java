package com.backend.management.service;

import com.backend.management.model.Apartment;
import com.backend.management.model.ApartmentType;
import com.backend.management.model.User;
import com.backend.management.repository.ApartmentRepository;
import com.backend.management.repository.ApartmentTypeRepository;
import com.backend.management.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PrepopulateService {

    private UserRepository userRepository;
    private ApartmentRepository apartmentRepository;
    private ApartmentTypeRepository apartmentTypeRepository;



    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void populateDatabase() {

        ApartmentType studioType = new ApartmentType("Studio", 1, BigDecimal.valueOf(3000), BigDecimal.valueOf(500));
        apartmentTypeRepository.save(studioType);
        ApartmentType oneBedroomType = new ApartmentType("1-Bedroom", 2, BigDecimal.valueOf(3500), BigDecimal.valueOf(600));
        apartmentTypeRepository.save(oneBedroomType);
        ApartmentType twoBedroomType = new ApartmentType("2-Bedroom", 3, BigDecimal.valueOf(4000), BigDecimal.valueOf(700));
        apartmentTypeRepository.save(twoBedroomType);

        for (int floor = 1; floor < 6; floor++) {
            for (int room = 1; room < 21; room++) {

                ApartmentType apartmentType = null;
                if (room == 1 || room == 8 || room == 11 || room == 18) {
                    apartmentType = twoBedroomType;
                } else if (room == 2 || room == 9 || room == 10 || room == 12 || room == 19 || room == 20) {
                    apartmentType = oneBedroomType;
                } else {
                    apartmentType = studioType;
                }


                Apartment apartment = new Apartment(
                        String.valueOf(floor * 100 + room),
                        apartmentType,
                        null
                );
                apartmentRepository.save(apartment);
            }
        }
    }
}
