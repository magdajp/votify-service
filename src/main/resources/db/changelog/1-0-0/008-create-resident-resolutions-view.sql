DROP FUNCTION IF EXISTS resolution_voting_status;
CREATE FUNCTION resolution_voting_status(resolution UUID)
    RETURNS TABLE
            (
                in_favor BIGINT,
                against  BIGINT,
                status   TEXT
            )
AS
$$
BEGIN
    SELECT COUNT(*) FILTER (WHERE vote = 'IN_FAVOR'),
           COUNT(*) FILTER (WHERE vote = 'AGAINST')
    INTO in_favor, against
    FROM votes
    WHERE resolution_id = resolution;

    SELECT CASE
               WHEN resolutions.deleted IS TRUE THEN 'CANCELLED'
               WHEN resolutions.deadline > NOW() THEN 'UNDER_VOTING'
               WHEN (in_favor >= resolutions.quorum) THEN 'ACCEPTED'
               ELSE 'REJECTED'
               END AS status
    INTO status
    FROM resolutions
    WHERE id = resolution;

    RETURN NEXT;
END;
$$ LANGUAGE plpgsql;

DROP VIEW IF EXISTS residents_resolutions_view;
CREATE VIEW residents_resolutions_view AS
SELECT resolutions.id AS id,
       community.id   AS community_id,
       resolutions.title,
       resolutions.content,
       resolutions.deadline,
       status         AS status
FROM resolutions
         INNER JOIN public.housing_communities community ON resolutions.housing_community_id = community.id
         CROSS JOIN LATERAL resolution_voting_status(resolutions.id)
ORDER BY community.timestamp DESC;