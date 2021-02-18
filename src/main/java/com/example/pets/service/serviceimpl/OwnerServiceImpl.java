package com.example.pets.service.serviceimpl;

import com.example.pets.entity.Owner;
import com.example.pets.persistence.OwnerRepository;
import com.example.pets.persistence.PetRepository;
import com.example.pets.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;

    @Override
    public Owner saveOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void deleteOwner(long id) {
        ownerRepository.delete(getOwnerById(id));
    }

    @Override
    public Owner updateOwner(Owner owner, long id) {
        owner.setId(id);
        return ownerRepository.save(owner);
    }

    @Override
    public Owner getOwnerById(long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The owner with such id not found"));
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner getOwnerByPetId(long id) {
        return getOwnerById(petRepository.findOwnerIdById(id));
    }
}
