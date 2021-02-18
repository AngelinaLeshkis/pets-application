package com.example.pets.service.serviceimpl;

import com.example.pets.dto.CatDTO;
import com.example.pets.entity.Cat;
import com.example.pets.exception.OwnerNotFoundException;
import com.example.pets.exception.PetNotFoundException;
import com.example.pets.mapper.PetMapper;
import com.example.pets.persistence.CatRepository;
import com.example.pets.persistence.OwnerRepository;
import com.example.pets.service.CatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.pets.mapper.PetMapper.toCatDto;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;

    @Override
    public CatDTO save(Cat cat, long ownerId) {
        cat.setOwner(ownerRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(ownerId)));

        return toCatDto(catRepository.save(cat));
    }

    @Override
    public CatDTO update(Cat cat, long ownerId, long id) {
        cat.setOwner(ownerRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(ownerId)));
        cat.setId(id);

        return toCatDto(catRepository.save(cat));
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
                .orElseThrow(() -> new PetNotFoundException(id));
    }

}
