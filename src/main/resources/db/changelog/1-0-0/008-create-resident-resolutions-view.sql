DROP FUNCTION IF EXISTS resolution_voting_result;
CREATE FUNCTION resolution_voting_result(resolution UUID)
    RETURNS TABLE
            (
                in_favour BIGINT,
                against   BIGINT
            )
AS
$$
BEGIN
    RETURN QUERY
        SELECT COUNT(*) FILTER (WHERE vote = 'IN_FAVOR') AS in_favour,
               COUNT(*) FILTER (WHERE vote = 'AGAINST')  AS against
        FROM votes
        WHERE votes.resolution_id = resolution;
END
$$ LANGUAGE plpgsql;

DROP VIEW IF EXISTS residents_resolutions_view;
CREATE VIEW residents_resolutions_view AS
SELECT resolutions.id as id,
       community.id   as community_id,
       resolutions.title,
       resolutions.content,
       resolutions.deadline,
       CASE
           WHEN resolutions.deleted IS TRUE THEN 'CANCELLED'
           WHEN resolutions.deadline > NOW() THEN 'UNDER_VOTING'
           WHEN (SELECT in_favour
                 FROM resolution_voting_result(resolutions.id)) >= resolutions.quorum THEN 'ACCEPTED'
           ELSE 'REJECTED'
           END        AS status
FROM resolutions
         INNER JOIN public.housing_communities community ON resolutions.housing_community_id = community.id
ORDER BY community.timestamp DESC;