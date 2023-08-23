ALTER TABLE patients ADD active tinyint;
UPDATE patients set active = 1;
ALTER TABLE patients MODIFY COLUMN active tinyint not null;