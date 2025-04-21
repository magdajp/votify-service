package com.votify.housingcommunity.addresident;

import com.goodcode.online.result.Result;
import com.votify.housingcommunity.addresident.HousingCommunity.UserAddedToHousingCommunity;
import com.votify.shared.Email;
import com.votify.shared.result.Failure;
import com.votify.shared.result.NotFoundFailure;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Repository("addResidentToCommunityHousingCommunityRepo")
class HousingCommunityRepository {
    private static final String FETCH_COMMUNITY_BY_ID_QUERY = """
            SELECT id, owner_id as ownerId
            FROM housing_communities
            WHERE id = :id
            """;
    private static final String FETCH_RESIDENTS_BY_COMMUNITY_ID_QUERY = """
            SELECT users.email
            FROM residents
            INNER JOIN users ON residents.user_id = users.id
            WHERE housing_community_id = :communityId
            """;

    private static final String ADD_USER_QUERY = """
            INSERT INTO users (id, first_name, last_name, email, password) VALUES (:id, :firstName, :lastName, :email, :password)
            """;
    private static final String UPDATE_COMMUNITY_QUERY = """
            INSERT INTO residents (user_id, housing_community_id) VALUES (:userId, :housingCommunityId)
            """;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Transactional
    public Result<UserAddedToHousingCommunity, Failure> persist(UserAddedToHousingCommunity event) {
        try {
            return createUser(event)
                    .flatSuccess(this::addToCommunity);
        } catch (Exception e) {
            return Result.failure(userOfEmailAddressAlreadyExists(event.user().email()));
        }
    }

    private Result<UserAddedToHousingCommunity, Failure> addToCommunity(UserAddedToHousingCommunity event) {
        try {
            jdbcTemplate.update(UPDATE_COMMUNITY_QUERY,
                    Map.of(
                            "userId", event.user().id(),
                            "housingCommunityId", event.housingCommunityId()
                    )
            );
            return Result.success(event);
        } catch (Exception e) {
            return Result.failure(userOfEmailAddressAlreadyPartOfCommunity(event.user().email()));
        }
    }

    private Result<UserAddedToHousingCommunity, Failure> createUser(UserAddedToHousingCommunity event) {
        try {
            jdbcTemplate.update(ADD_USER_QUERY,
                    Map.of(
                            "id", event.user().id(),
                            "firstName", event.user().firstName(),
                            "lastName", event.user().lastName(),
                            "email", event.user().email().value(),
                            "password", event.user().password()
                    )
            );
            return Result.success(event);
        } catch (Exception e) {
            return Result.failure(userOfEmailAddressAlreadyExists(event.user().email()));
        }
    }

    Result<HousingCommunity, Failure> fetchById(UUID communityId) {
        try {
            return ofNullable(
                    jdbcTemplate.queryForObject(
                            FETCH_COMMUNITY_BY_ID_QUERY,
                            Map.of("id", communityId),
                            new BeanPropertyRowMapper<>(CommunityDto.class)
                    )
            )
                    .map(dto -> new HousingCommunity(dto.id, dto.ownerId, fetchResidents(communityId)))
                    .map(Result::<HousingCommunity, Failure>success)
                    .orElseGet(() -> Result.failure(communityDoesNotExistFailure(communityId)));
        } catch (EmptyResultDataAccessException e) {
            return Result.failure(communityDoesNotExistFailure(communityId));
        }
    }

    private Set<Email> fetchResidents(UUID communityId) {
        return jdbcTemplate.queryForStream(
                        FETCH_RESIDENTS_BY_COMMUNITY_ID_QUERY,
                        Map.of("communityId", communityId),
                        new BeanPropertyRowMapper<>(UserDto.class)
                )
                .map(UserDto::email)
                .collect(Collectors.toSet());
    }

    @Data
    @Accessors(fluent = false, chain = true)
    static class CommunityDto {
        private UUID id;
        private UUID ownerId;
    }

    @Data
    @Accessors(fluent = false, chain = true)
    static class UserDto {
        private String email;

        private Email email() {
            return new Email(email);
        }
    }

    private static Failure communityDoesNotExistFailure(UUID communityId) {
        return (NotFoundFailure) () -> "Housing community[%s] does not exist".formatted(communityId);
    }

    private static Failure userOfEmailAddressAlreadyExists(Email email) {
        return () -> "User[%s] already exist".formatted(email);
    }

    private static Failure userOfEmailAddressAlreadyPartOfCommunity(Email email) {
        return () -> "User[%s] already exists in community".formatted(email);
    }
}
