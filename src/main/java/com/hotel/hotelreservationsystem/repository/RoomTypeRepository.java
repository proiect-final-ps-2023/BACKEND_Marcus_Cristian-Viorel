package com.hotel.hotelreservationsystem.repository;

import com.hotel.hotelreservationsystem.model.RoomType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomTypeRepository extends CrudRepository<RoomType, Long> {

    Optional<RoomType> findByCost(Double cost);
    Optional<RoomType> findByName(String name);
    List<RoomType> findByCostGreaterThan(Double cost);
    List<RoomType> findByCostLessThan(Double cost);
    List<RoomType> findByCostBetween(Double cost1, Double cost2);

}
