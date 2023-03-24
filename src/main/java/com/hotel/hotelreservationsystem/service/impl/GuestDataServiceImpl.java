package com.hotel.hotelreservationsystem.service.impl;

import com.hotel.hotelreservationsystem.model.GuestData;
import com.hotel.hotelreservationsystem.repository.GuestDataRepository;
import com.hotel.hotelreservationsystem.service.GuestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestDataServiceImpl implements GuestDataService {

    @Autowired
    private GuestDataRepository guestDataRepository;

    public GuestDataServiceImpl(GuestDataRepository guestDataRepository) {
        this.guestDataRepository = guestDataRepository;
    }

    @Override
    public GuestData createGuestData(GuestData guestData) {
        return guestDataRepository.save(guestData);
    }

    @Override
    public GuestData findGuestDataById(Long id) {
        return guestDataRepository.findById(id).orElse(null);
    }

    @Override
    public List<GuestData> getAllGuestData() {
        return (List<GuestData>) guestDataRepository.findAll();
    }

    @Override
    public GuestData updateGuestData(GuestData guestData) {
        return guestDataRepository.save(guestData);
    }

    @Override
    public GuestData deleteGuestData(GuestData guestData) {
        guestDataRepository.delete(guestData);
        return guestData;
    }

}
