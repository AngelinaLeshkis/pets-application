package com.example.pets.service.serviceimpl;

import com.example.pets.dto.PetDTO;
import com.example.pets.entity.Pet;
import com.example.pets.exception.PetNotFoundException;
import com.example.pets.mapper.PetMapper;
import com.example.pets.persistence.PetRepository;
import com.example.pets.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    @Transactional
    public void deletePet(long id) {
        petRepository.delete(getPetById(id));
    }

    @Override
    public Pet getPetById(long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException(id));
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public List<PetDTO> getPetsByOwnerId(long id) {
        return petRepository.findPetsByOwnerId(id).stream()
                .map(PetMapper::toDto)
                .collect(toList());
    }
}
