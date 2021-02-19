package com.example.pets.service.serviceimpl;

import com.example.pets.dto.CreateDogDTO;
import com.example.pets.dto.DogDTO;
import com.example.pets.entity.Dog;
import com.example.pets.mapper.PetMapper;
import com.example.pets.persistence.DogRepository;
import com.example.pets.service.DogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

import static com.example.pets.mapper.PetMapper.toDog;
import static com.example.pets.mapper.PetMapper.toDogDto;
import static com.example.pets.mapper.PetMapper.updateDog;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;

    @Override
    @Transactional
    public DogDTO save(CreateDogDTO dog) {
        return toDogDto(dogRepository.save(toDog(dog)));
    }

    @Override
    @Transactional
    public DogDTO update(CreateDogDTO dog, long id) {
        return toDogDto(dogRepository.save(updateDog(dog, id)));
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
                .orElseThrow(EntityNotFoundException::new);
    }
}
