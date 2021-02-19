package com.example.pets.service.serviceimpl;

import com.example.pets.dto.CatDTO;
import com.example.pets.dto.CreateCatDTO;
import com.example.pets.entity.Cat;
import com.example.pets.mapper.PetMapper;
import com.example.pets.persistence.CatRepository;
import com.example.pets.service.CatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

import static com.example.pets.mapper.PetMapper.toCat;
import static com.example.pets.mapper.PetMapper.toCatDto;
import static com.example.pets.mapper.PetMapper.updateCat;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;

    @Override
    @Transactional
    public CatDTO saveCat(CreateCatDTO cat) {
        return toCatDto(catRepository.save(toCat(cat)));
    }

    @Override
    @Transactional
    public CatDTO update(CreateCatDTO cat, long id) {
        return toCatDto(catRepository.save(updateCat(cat, id)));
    }

    @Override
    public List<CatDTO> getAllCatsByOwnerId(long id) {
        return catRepository.getAllByOwnerId(id).stream()
                .map(PetMapper::toCatDto)
                .collect(toList());
    }

    @Override
    public Cat getCatById(long id) {
        return catRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

}
