--Part 1
--Types in 'job'
-- ID
-- Employer
-- Name
-- Skills
--DESCRIBE job;
--Part 2
SELECT name FROM employer WHERE location = "St. Louis City";
--Part 3
DROP TABLE job;
--Part 4
SELECT name
FROM skill
LEFT JOIN job_skill ON skill.id = job_skill.skill_id
ORDER BY name;