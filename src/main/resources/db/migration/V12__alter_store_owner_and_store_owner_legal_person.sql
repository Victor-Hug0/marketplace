ALTER TABLE store_owner DROP COLUMN gender;

ALTER TABLE natural_person_owner ADD COLUMN gender VARCHAR(20);

ALTER TABLE natural_person_owner ALTER COLUMN gender SET NOT NULL;