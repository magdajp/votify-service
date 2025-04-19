package com.votify.query.fetchresolutions.resident;

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

@Repository("queryFetchResolutionForResidentResidentRepo")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ResidentRepository {
    private static final String COMMUNITY_ID_BY_RESIDENT_ID_QUERY = """
            SELECT housing_community_id as communityId FROM residents WHERE user_id = :residentId
            """;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    Result<UUID, Failure> fetchResidentCommunityId(UUID resident) {
        try {
            return ofNullable(
                    jdbcTemplate.queryForObject(
                            COMMUNITY_ID_BY_RESIDENT_ID_QUERY,
                            Map.of("residentId", resident),
                            UUID.class)
            )
                    .map(Result::<UUID, Failure>success)
                    .orElseGet(() -> Result.failure(residentDoesNotExistFailure(resident)));
        } catch (EmptyResultDataAccessException e) {
            return Result.failure(residentDoesNotExistFailure(resident));
        }
    }

    private static Failure residentDoesNotExistFailure(UUID residentId) {
        return (NotFoundFailure) () -> "Resident of id[%s] does not exist".formatted(residentId);
    }
}
