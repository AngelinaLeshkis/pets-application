package com.example.pets.service.serviceimpl;

import com.example.pets.dto.OwnerDTO;
import com.example.pets.entity.Owner;
import com.example.pets.mapper.OwnerMapper;
import com.example.pets.persistence.OwnerRepository;
import com.example.pets.persistence.PetRepository;
import com.example.pets.service.OwnerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

import static com.example.pets.mapper.OwnerMapper.toOwnerDTO;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;

    @Override
    public OwnerDTO save(Owner owner) {
        ownerRepository.save(owner);
        return toOwnerDTO(owner);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Owner ownerFromDB = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Owner.class +
                        " not found with " + id));
        ownerRepository.delete(ownerFromDB);
    }

    @Override
    public OwnerDTO update(Owner owner, long id) {
        owner.setId(id);
        ownerRepository.save(owner);
        return toOwnerDTO(owner);
    }

    @Override
    public OwnerDTO getById(long id) {
        Owner ownerFromDB = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Owner.class +
                        " not found with " + id));
        return toOwnerDTO(ownerFromDB);
    }

    @Override
    public List<OwnerDTO> getAll() {
        List<Owner> owners = ownerRepository.findAll();
        return owners.stream()
                .map(OwnerMapper::toOwnerDTO)
                .collect(toList());
    }

    @Override
    @Transactional
    public OwnerDTO getByPetId(long id) {
        long ownerId = petRepository.findOwnerIdById(id);
        return getById(ownerId);
    }

}
