package com.example.habittrackr.services;

import com.example.habittrackr.dto.IdentityDTO;
import com.example.habittrackr.mapper.Mapper;
import com.example.habittrackr.storage.identity.Identity;
import com.example.habittrackr.storage.identity.IdentityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdentityServiceImpl implements IdentityService {

    private final IdentityRepository identityRepository;
    private final Mapper mapper;

    public IdentityServiceImpl(IdentityRepository identityRepository, Mapper mapper) {
        this.identityRepository = identityRepository;

        this.mapper = mapper;
    }

    @Override
    public IdentityDTO createOrUpdateIdentity(Identity identity) {
        Identity save = identityRepository.save(identity);
        return mapper.toIdentityDTO(save);
    }

    @Override
    public List<Identity> getAllIdentities() {
        return identityRepository.findAll();
    }

    @Override
    public Optional<Identity> getIdentityById(long identityId) {
        return identityRepository.findById(identityId);
    }

    @Override
    public void deleteIdentityById(long identityId) {
        identityRepository.deleteById(identityId);
    }
}
