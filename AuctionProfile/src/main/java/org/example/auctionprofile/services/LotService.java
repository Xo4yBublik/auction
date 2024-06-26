package org.example.auctionprofile.services;

import org.example.auctionprofile.dtos.LotRegisterDto;
import org.example.auctionprofile.entities.LotEntity;
import org.example.auctionprofile.repositories.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotService {
    @Autowired
    private LotRepository lotRepository;
    public void save(LotRegisterDto lotRegisterDto) {
        lotRepository.saveLot(lotRegisterDto);
    }

    public List<LotEntity> getAllLots() {
        return lotRepository.getAllLots();
    }

    public LotEntity getLot(Long id) {
        return lotRepository.getLotById(id);
    }

    public void deleteLotById(Long id) {
        lotRepository.deleteLot(id);
    }

    public void updateLot(LotRegisterDto updatedLot) {
        lotRepository.updateLotById(updatedLot);
    }
}
