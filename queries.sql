-- Part 1
-- Types in 'job'
-- ID
-- Name
-- Skills
DESCRIBE job;
-- Part 2
SELECT name FROM employer WHERE location = "St. Louis City";
-- Part 3
DROP TABLE job;
-- Part 4
-- SELECT name
-- FROM skill
-- LEFT JOIN job_skill ON skill.id = job_skill.skill_id
-- ORDER BY name;
-- Part 4, this returns a green check within the workbench
SELECT * FROM skill
LEFT JOIN job_skills ON skill.id = job_skills.skills_id
WHERE job_skills.jobs_id IS NOT NULL
ORDER BY name ASC;