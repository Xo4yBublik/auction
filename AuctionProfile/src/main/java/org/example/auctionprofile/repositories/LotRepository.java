package org.example.auctionprofile.repositories;

import org.example.auctionprofile.dtos.LotRegisterDto;
import org.example.auctionprofile.entities.LotEntity;

import java.util.List;

public interface LotRepository {
    LotEntity getLotById(Long id);

    void saveLot(LotRegisterDto lot);

    void deleteLot(Long id);

    List<LotEntity> getAllLots();

    void updateLotById(LotRegisterDto updatedLot);
}
