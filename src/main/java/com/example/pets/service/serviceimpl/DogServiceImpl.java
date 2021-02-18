package com.example.pets.service.serviceimpl;

import com.example.pets.dto.DogDTO;
import com.example.pets.entity.Dog;
import com.example.pets.exception.OwnerNotFoundException;
import com.example.pets.exception.PetNotFoundException;
import com.example.pets.mapper.PetMapper;
import com.example.pets.persistence.DogRepository;
import com.example.pets.persistence.OwnerRepository;
import com.example.pets.service.DogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.pets.mapper.PetMapper.toDogDto;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;
    private final OwnerRepository ownerRepository;

    @Override
    public DogDTO save(Dog dog, long ownerId) {
        dog.setOwner(ownerRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(ownerId)));

        return toDogDto(dogRepository.save(dog));
    }

    @Override
    public DogDTO update(Dog dog, long ownerId, long id) {
        dog.setOwner(ownerRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(ownerId)));
        dog.setId(id);

        return toDogDto(dogRepository.save(dog));
    }

    @Override
    public List<DogDTO> getAllByOwnerId(long id) {
        return dogRepository.getAllByOwnerId(id).stream()
                .map(PetMapper::toDogDto)
                .collect(toList());
    }

    @Override
    public Dog getById(long id) {
        return dogRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException(id));
    }
}
