package nl.novi.techiteasy1121.services;

import nl.novi.techiteasy1121.dtos.TelevisionDto;
import nl.novi.techiteasy1121.dtos.WallBracketDto;
import nl.novi.techiteasy1121.exceptions.RecordNotFoundException;
import nl.novi.techiteasy1121.models.Television;
import nl.novi.techiteasy1121.models.WallBracket;
import nl.novi.techiteasy1121.repositories.TelevisionRepository;
import nl.novi.techiteasy1121.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

// Deze klasse bevat de service methodes van TelevisionWallBracketController.
// Deze klasse wijkt af van de andere service-klassen, omdat deze in 3 verschillende controllers wordt ge-autowired.

@Service
public class TelevisionWallBracketService{

    private TelevisionRepository televisionRepository;

    private WallBracketRepository wallBracketRepository;

    public TelevisionWallBracketService(TelevisionRepository televisionRepository, WallBracketRepository wallBracketRepository) {
        this.televisionRepository = televisionRepository;
        this.wallBracketRepository = wallBracketRepository;
    }

    public Collection<TelevisionDto> getTelevisionsByWallBracketId(Long wallBracketId) {
        Collection<TelevisionDto> dtos = new HashSet<>();
        WallBracket wallbracket = wallBracketRepository.findById(wallBracketId).orElseThrow(()->{throw new RecordNotFoundException();});
        for (Television television : wallbracket.getTelevisions()) {
            TelevisionDto televisionDto = new TelevisionDto();

            televisionDto.setId(television.getId());
            televisionDto.setType(television.getType());
            televisionDto.setBrand(television.getBrand());
            televisionDto.setName(television.getName());
            televisionDto.setPrice(television.getPrice());
            televisionDto.setAvailableSize(television.getAvailableSize());
            televisionDto.setRefreshRate(television.getRefreshRate());
            televisionDto.setScreenType(television.getScreenType());
            televisionDto.setScreenQuality(television.getScreenQuality());
            televisionDto.setSmartTv(television.getSmartTv());
            televisionDto.setWifi(television.getWifi());
            televisionDto.setVoiceControl(television.getVoiceControl());
            televisionDto.setHdr(television.getHdr());
            televisionDto.setBluetooth(television.getBluetooth());
            televisionDto.setAmbiLight(television.getAmbiLight());
            televisionDto.setOriginalStock(television.getOriginalStock());
            televisionDto.setSold(television.getSold());

            dtos.add(televisionDto);
        }
        return dtos;
    }

    public Collection<WallBracketDto> getWallBracketsByTelevisionId(Long televisionId) {
        List<WallBracketDto> dtos = new ArrayList<>();
        Television television  = televisionRepository.findById(televisionId).orElseThrow(()->{throw new RecordNotFoundException();});
        for (WallBracket wallBracket : television.getWallBrackets()) {
            var dto = new WallBracketDto();

            dto.setId(wallBracket.getId());
            dto.setName(wallBracket.getName());
            dto.setSize(wallBracket.getSize());
            dto.setAdjustable(wallBracket.getAdjustable());
            dto.setPrice(wallBracket.getPrice());

            dtos.add(dto);
        }
        return dtos;
    }


    public Long addTelevisionWallBracket(Long televisionId, Long wallBracketId) {
        Television television = televisionRepository.findById(televisionId).orElseThrow(()->{throw new RecordNotFoundException();});
        WallBracket wallBracket = wallBracketRepository.findById(wallBracketId).orElseThrow(()->{throw new RecordNotFoundException();});
       wallBracket.getTelevisions().add(television);
       return wallBracketRepository.save(wallBracket).getId();
    }
}
