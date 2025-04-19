DROP VIEW IF EXISTS community_resolutions_view;
CREATE OR REPLACE VIEW community_resolutions_view AS
SELECT resolutions.id                                AS id,
       community.id                                  AS community_id,
       resolutions.title,
       resolutions.content,
       resolutions.quorum,
       resolutions.deadline,
       (SELECT COUNT(*)
        FROM residents r
        WHERE r.housing_community_id = community.id) AS number_of_residents,
       COALESCE(votes_result.in_favor, 0)            AS in_favor,
       COALESCE(votes_result.against, 0)             AS against,
       votes_result.status                           AS status
FROM resolutions
         INNER JOIN housing_communities community ON resolutions.housing_community_id = community.id
         CROSS JOIN LATERAL resolution_voting_status(resolutions.id) as votes_result
ORDER BY community.timestamp DESC;