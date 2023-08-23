ALTER TABLE doctors ADD active tinyint;
UPDATE doctors set active = 1;
ALTER TABLE doctors MODIFY COLUMN active tinyint not null;