package com.example.pets.service.serviceimpl;

import com.example.pets.dto.PetDTO;
import com.example.pets.entity.Pet;
import com.example.pets.mapper.PetMapper;
import com.example.pets.persistence.PetRepository;
import com.example.pets.service.PetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    @Transactional
    public void delete(long id) {
        petRepository.delete(getById(id));
    }

    @Override
    public Pet getById(long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Pet.class +
                        " not found with " + id));
    }

    @Override
    public List<Pet> getAll() {
        return petRepository.findAll();
    }

    @Override
    public List<PetDTO> getAllByOwnerId(long id) {
        return petRepository.findPetsByOwnerId(id).stream()
                .map(PetMapper::toDto)
                .collect(toList());
    }
}
