package com.votify.housingcommunity;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class FetchHousingCommunity {
    private final HousingCommunityRepository housingCommunityRepository;

    List<HousingCommunity> fetchAll() {
        return housingCommunityRepository.findAll();
    }
}
