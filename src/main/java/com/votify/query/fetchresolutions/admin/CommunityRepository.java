package com.votify.query.fetchresolutions.admin;

import com.goodcode.online.result.Result;
import com.votify.shared.result.Failure;
import com.votify.shared.result.NotFoundFailure;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@Repository("queryFetchResolutionForAdminCommunityRepo")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class CommunityRepository {
    private static final String COMMUNITY_ID_BY_OWNER_ID_QUERY = """
            SELECT id FROM housing_communities WHERE owner_id = :ownerId
            """;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    Result<UUID, Failure> fetchAdminCommunityId(UUID ownerId) {
        try {
            return ofNullable(
                    jdbcTemplate.queryForObject(
                            COMMUNITY_ID_BY_OWNER_ID_QUERY,
                            Map.of("ownerId", ownerId),
                            UUID.class)
            )
                    .map(Result::<UUID, Failure>success)
                    .orElseGet(() -> Result.failure(ownerDoesNotExistFailure(ownerId)));
        } catch (EmptyResultDataAccessException e) {
            return Result.failure(ownerDoesNotExistFailure(ownerId));
        }
    }

    private static Failure ownerDoesNotExistFailure(UUID ownerId) {
        return (NotFoundFailure) () -> "Owner[%s] does not exist".formatted(ownerId);
    }
}
