package com.example.pets.service.serviceimpl;

import com.example.pets.dto.CatDTO;
import com.example.pets.dto.CreateCatDTO;
import com.example.pets.entity.Cat;
import com.example.pets.entity.Owner;
import com.example.pets.mapper.PetMapper;
import com.example.pets.persistence.CatRepository;
import com.example.pets.persistence.OwnerRepository;
import com.example.pets.service.CatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

import static com.example.pets.mapper.PetMapper.toCat;
import static com.example.pets.mapper.PetMapper.toCatDto;
import static com.example.pets.mapper.PetMapper.updateCat;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;

    @Override
    @Transactional
    public CatDTO save(CreateCatDTO cat) {
        Owner ownerFromDB = ownerRepository.findById(cat.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException(Owner.class +
                        " not found"));

        return toCatDto(catRepository.save(toCat(cat, ownerFromDB)));
    }

    @Override
    @Transactional
    public CatDTO update(CreateCatDTO cat, long id) {
        return toCatDto(catRepository.save(updateCat(cat, id)));
    }

    @Override
    public List<CatDTO> getAllByOwnerId(long id) {
        return catRepository.getAllByOwnerId(id).stream()
                .map(PetMapper::toCatDto)
                .collect(toList());
    }

    @Override
    public Cat getById(long id) {
        return catRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Cat.class +
                        " not found with " + id));
    }

}
