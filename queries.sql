--Part 1
CREATE TABLE job (
	ID INT AUTO_INCREMENT PRIMARY KEY,
    Employer VARCHAR(255) NOT NULL,
	Name VARCHAR(255) NOT NULL,
    Skills TEXT
);
DESCRIBE job;
--Part 2
SELECT name FROM employer WHERE location = "St. Louis City";
--Part 3
DROP TABLE job;
--Part 4
SELECT DISTINCT skill.name
FROM skill
INNER JOIN job_skill ON skill.id = job_skill.skill_id
ORDER BY skill.name;