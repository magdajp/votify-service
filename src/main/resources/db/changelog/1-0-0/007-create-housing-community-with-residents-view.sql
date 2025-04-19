DROP VIEW IF EXISTS housing_communities_with_count;
CREATE VIEW housing_communities_with_count AS
SELECT communities.*,
       (SELECT COUNT(*) FROM residents resident WHERE resident.housing_community_id = communities.id) AS number_of_residents
FROM housing_communities communities;