package com.example.habittrackr.services;

import com.example.habittrackr.dto.IdentityDTO;
import com.example.habittrackr.storage.identity.Identity;

import java.util.List;
import java.util.Optional;

public interface IdentityService {

    List<Identity> getAllIdentities();

    Optional<Identity> getIdentityById(long identityId);

    IdentityDTO createOrUpdateIdentity(Identity identity);

    void deleteIdentityById(long identityId);
}
