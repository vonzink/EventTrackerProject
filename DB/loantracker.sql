-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema loantrackerdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `loantrackerdb` ;

-- -----------------------------------------------------
-- Schema loantrackerdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `loantrackerdb` DEFAULT CHARACTER SET utf8 ;
USE `loantrackerdb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(150) NULL DEFAULT NULL,
  `role` VARCHAR(50) NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `first_name` VARCHAR(50) NULL,
  `last_name` VARCHAR(50) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username` (`username` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `borrower`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `borrower` ;

CREATE TABLE IF NOT EXISTS `borrower` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NULL,
  `phone` VARCHAR(20) NULL,
  `created_at` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `application`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `application` ;

CREATE TABLE IF NOT EXISTS `application` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL DEFAULT NULL,
  `property_address` VARCHAR(255) NULL DEFAULT NULL,
  `loan_amount` DECIMAL(12,2) NULL DEFAULT NULL,
  `loan_type` VARCHAR(50) NULL DEFAULT NULL,
  `purpose` VARCHAR(50) NULL DEFAULT NULL,
  `submitted_date` DATE NULL DEFAULT NULL,
  `status` VARCHAR(50) NULL DEFAULT 'APPLICATION',
  `borrower_id` INT NOT NULL,
  `loan_number` INT NULL,
  `enable` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `fk_applications_borrower1_idx` (`borrower_id` ASC) VISIBLE,
  CONSTRAINT `applications_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`),
  CONSTRAINT `fk_applications_borrower1`
    FOREIGN KEY (`borrower_id`)
    REFERENCES `borrower` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `approved`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `approved` ;

CREATE TABLE IF NOT EXISTS `approved` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `approval_date` DATE NULL DEFAULT NULL,
  `approval_notes` TEXT NULL DEFAULT NULL,
  `interest_rate` DECIMAL(5,3) NULL DEFAULT NULL,
  `term_years` INT NULL DEFAULT NULL,
  `application_id` INT NOT NULL,
  PRIMARY KEY (`id`, `application_id`),
  INDEX `fk_approved_application1_idx` (`application_id` ASC) VISIBLE,
  CONSTRAINT `fk_approved_application1`
    FOREIGN KEY (`application_id`)
    REFERENCES `application` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `closed`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `closed` ;

CREATE TABLE IF NOT EXISTS `closed` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `closing_date` DATE NULL DEFAULT NULL,
  `settlement_agent` VARCHAR(100) NULL DEFAULT NULL,
  `notes` TEXT NULL DEFAULT NULL,
  `application_id` INT NOT NULL,
  PRIMARY KEY (`id`, `application_id`),
  INDEX `fk_closed_application1_idx` (`application_id` ASC) VISIBLE,
  CONSTRAINT `fk_closed_application1`
    FOREIGN KEY (`application_id`)
    REFERENCES `application` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `ctc`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ctc` ;

CREATE TABLE IF NOT EXISTS `ctc` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cleared_by` VARCHAR(100) NULL DEFAULT NULL,
  `ctc_date` DATE NULL DEFAULT NULL,
  `notes` TEXT NULL DEFAULT NULL,
  `application_id` INT NOT NULL,
  PRIMARY KEY (`id`, `application_id`),
  INDEX `fk_ctc_application1_idx` (`application_id` ASC) VISIBLE,
  CONSTRAINT `fk_ctc_application1`
    FOREIGN KEY (`application_id`)
    REFERENCES `application` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `declined`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `declined` ;

CREATE TABLE IF NOT EXISTS `declined` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `declined_date` DATE NULL DEFAULT NULL,
  `reason` TEXT NULL DEFAULT NULL,
  `application_id` INT NOT NULL,
  PRIMARY KEY (`id`, `application_id`),
  INDEX `fk_declined_application1_idx` (`application_id` ASC) VISIBLE,
  CONSTRAINT `fk_declined_application1`
    FOREIGN KEY (`application_id`)
    REFERENCES `application` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `documentation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `documentation` ;

CREATE TABLE IF NOT EXISTS `documentation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `application_id` INT NULL DEFAULT NULL,
  `doc_type` VARCHAR(100) NULL DEFAULT NULL,
  `file_path` VARCHAR(255) NULL DEFAULT NULL,
  `uploaded_by` INT NULL DEFAULT NULL,
  `uploaded_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `application_id` (`application_id` ASC) VISIBLE,
  INDEX `uploaded_by` (`uploaded_by` ASC) VISIBLE,
  CONSTRAINT `documentation_ibfk_1`
    FOREIGN KEY (`application_id`)
    REFERENCES `application` (`id`),
  CONSTRAINT `documentation_ibfk_2`
    FOREIGN KEY (`uploaded_by`)
    REFERENCES `user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `funded`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `funded` ;

CREATE TABLE IF NOT EXISTS `funded` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `funded_date` DATE NULL DEFAULT NULL,
  `wire_amount` DECIMAL(12,2) NULL DEFAULT NULL,
  `wire_confirmation` VARCHAR(100) NULL DEFAULT NULL,
  `application_id` INT NOT NULL,
  PRIMARY KEY (`id`, `application_id`),
  INDEX `fk_funded_application1_idx` (`application_id` ASC) VISIBLE,
  CONSTRAINT `fk_funded_application1`
    FOREIGN KEY (`application_id`)
    REFERENCES `application` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `status` ;

CREATE TABLE IF NOT EXISTS `status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `application_id` INT NULL DEFAULT NULL,
  `status` VARCHAR(50) NULL DEFAULT NULL,
  `changed_by` INT NULL DEFAULT NULL,
  `changed_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `notes` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `application_id` (`application_id` ASC) VISIBLE,
  INDEX `changed_by` (`changed_by` ASC) VISIBLE,
  CONSTRAINT `loan_statuses_ibfk_1`
    FOREIGN KEY (`application_id`)
    REFERENCES `application` (`id`),
  CONSTRAINT `loan_statuses_ibfk_2`
    FOREIGN KEY (`changed_by`)
    REFERENCES `user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `underwriting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `underwriting` ;

CREATE TABLE IF NOT EXISTS `underwriting` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `underwriter_name` VARCHAR(100) NULL DEFAULT NULL,
  `findings` TEXT NULL DEFAULT NULL,
  `reviewed_date` DATE NULL DEFAULT NULL,
  `decision` VARCHAR(50) NULL DEFAULT NULL,
  `application_id` INT NOT NULL,
  PRIMARY KEY (`id`, `application_id`),
  INDEX `fk_underwriting_application1_idx` (`application_id` ASC) VISIBLE,
  CONSTRAINT `fk_underwriting_application1`
    FOREIGN KEY (`application_id`)
    REFERENCES `application` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4;

SET SQL_MODE = '';
DROP USER IF EXISTS loantracker@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'loantracker'@'localhost' IDENTIFIED BY 'root';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'loantracker'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `user` (`id`, `username`, `password`, `email`, `role`, `created_at`, `first_name`, `last_name`) VALUES (1, 'vonzink', '1234', 'vonzink@gmail.com', 'LO', '2025-01-01', 'Zach', 'Zink');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `role`, `created_at`, `first_name`, `last_name`) VALUES (2, 'bzink', '1234', 'bzink@gmail.com', 'processor', '2025-01-01', 'Brook', 'Zink');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `role`, `created_at`, `first_name`, `last_name`) VALUES (3, 'b3', '1234', 'b3@gmail.com', 'LO', '2025-01-01', 'Tanya', 'Long');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `role`, `created_at`, `first_name`, `last_name`) VALUES (4, 'b4', '1234', 'b4', 'LO', '2025-01-01', 'Mike', 'Wilson');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `role`, `created_at`, `first_name`, `last_name`) VALUES (5, 'b5', '1234', 'b5', 'LO', '2025-01-01', 'Josh', 'Souel');

COMMIT;


-- -----------------------------------------------------
-- Data for table `borrower`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (1, 'Diane', 'Suelter', 'ds@gmail.com', '3033003030', '2025-01-01');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (2, 'tomas', 'Payne', 'aa@gmail.com', '3033333000', '2025-01-01');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (3, 'Amazon', 'Prime', 'bb@gmail.com', '3033033030', '2025-01-01');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (4, 'Anderson', 'Cooper', 'cc@gmail.com', '3330003030', '2025-01-01');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (5, 'Elon', 'Musk', 'dd@gmail.com', '3033333030', '2025-01-01');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (6, 'Matt', 'Murdock', 'mm@gmail.com', '3039008080', '2025-01-02');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (7, 'Austin', 'Powers', 'ap@gmail.com', '3039998078', '2025-01-02');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (8, 'Chad', 'Warner', 'chad@gmail.com', '3039008080', '2025-01-02');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (9, 'Carl', 'Weathers', 'cw@gmail.com', '3039008080', '2025-01-02');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (10, 'Marshal', 'Mathers', 'mm@gmail.com', '3039008080', '2025-01-02');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (11, 'Ben', 'Aflac', 'bf@gmail.com', '3039008080', '2025-01-02');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (12, 'Steven', 'Speilsberg', 'sb@gmail.com', '3039008080', '2025-01-02');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (13, 'john', 'adams', 'jm@gmail.com', '3039008080', '2025-01-02');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (14, 'Jamal', 'Murray', 'jm@gmail.com', '3039008080', '2025-01-02');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (15, 'Evan', 'Levy', 'ev@gmail.com', '3039008080', '2025-01-02');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (16, 'Brook', 'Zink', 'BZ@GMAIL.COM', '3039008080', '2025-01-02');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (17, 'Deanna', 'Hewey', 'dh@gmail.com', '3039008080', '2025-01-02');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (18, 'David', 'Charles', 'dc@gmail.com', '3039008080', '2025-01-02');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (19, 'Jeremy', 'Evans', 'je@gmail.com', '3039008080', '2025-01-02');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (20, 'Jeff', 'Brown', 'jb@gmail.com', '3039008080', '2025-01-02');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (21, 'David ', 'Bailey', 'db@gmail.com', '3039008080', '2024-01-01');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (22, 'Magnum', 'PI', 'pi@gmail.com', '3039008080', '2024-01-01');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (23, 'Robert', 'Hoff', 'rh@gmail.com', '3039008080', '2024-01-01');

COMMIT;


-- -----------------------------------------------------
-- Data for table `application`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (1, 1, '78 The Lane Road', 500000, 'FHA', 'Purchase', '2024-05-12', 'Application', 1, 1000, 1);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (2, 2, '123 main st', 250000, 'CONV', 'Refi', '2025-05-05', 'Underwriting', 2, 1201, 1);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (3, 3, '321 Broadway', 350000, 'VA', 'Purchase', '2025-05-05', 'closing', 3, 1351, 1);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (4, 4, '555 wolf street', 780000, 'Jumbo', 'Purchase', '2025-01-01', 'funding', 4, 6514, 1);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (5, 5, '7126 Joan St,', 800000, 'CONV', 'Purchase', '2025-01-01', 'Declined', 5, 9876, 1);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (6, 5, '2933 Central Park Blvd', 615000, 'conv', 'Purchase', '2025-05-05', 'approved', 6, 2345, 1);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (7, 4, '2426 Ames St', 534000, 'VA', 'Purchase', '2025-05-05', 'approved', 7, 5432, 1);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (8, 3, '2933 Central Park Blvd', 335544, 'CONV', 'Purchase', '2025-05-05', 'Declined', 8, 2435, 1);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (9, 2, '7126 Joan St', 658020, 'VA', 'Refi', '2025-05-05', 'approved', 9, 5432, 1);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (10, 1, '1120 N Clarkson St #6', 123700, 'CONV', 'Refi', '2025-05-05', 'Declined', 10, 5687, 1);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (11, 1, '2426 Ames St', 345455, 'CONV', 'Purchase', '2025-05-05', 'approved', 11, 8877, 1);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (12, 1, '633 S Galena St', 657733, 'VA', 'Purchase', '2025-05-05', 'funding', 12, 6856, 1);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (13, 2, '7474 E Arkansas Ave Unit 13-02', 664000, 'FHA', 'Refi', '2025-03-05', 'approved', 13, 5837, 1);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (14, 3, '5131 Andes St', 667700, 'VA', 'Refi', '2025-03-05', 'funding', 14, 9981, 1);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (15, 4, '8100 W Quincy Ave Unit A8', 758600, 'VA', 'Purchase', '2025-03-05', 'approved', 15, 81245, 1);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (16, 4, '1241 S Akron Way', 133000, 'CONV', 'Refi', '2025-03-05', 'ctc', 16, 9047, 1);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (17, 5, '6901 E 12th Ave', 225560, 'FHA', 'Purchase', '2025-03-05', 'approved', 17, 3874, 1);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (18, 5, '6223 W Flora Pl', 143500, 'VA', 'Purchase', '2025-03-05', 'approved', 18, 7765, 0);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (19, 1, '201 S Lafayette St', 668800, 'CONV', 'Refi', '2025-03-05', 'ctc', 19, 335645, 0);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (20, 1, '1885 S Quebec Way Unit A28', 76000, 'Jumbo', 'Refi', '2025-03-05', 'approved', 20, 7654, 0);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (21, 2, '2518 16th St', 76000, 'VA', 'Purchase', '2025-03-05', 'underwriting', 21, 77756, 0);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (22, 3, '7040 E Girard Ave #406', 100000, 'FHA', 'Purchase', '2025-03-05', 'underwriting', 22, 67555, 0);
INSERT INTO `application` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`, `enable`) VALUES (23, 2, '1340 W 68th Ave', 120000, 'VA', 'Purchase', '2025-03-05', 'approved', 23, 5675, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `approved`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `approved` (`id`, `approval_date`, `approval_notes`, `interest_rate`, `term_years`, `application_id`) VALUES (1, '2025-02-02', 'test', 6, 30, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `closed`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `closed` (`id`, `closing_date`, `settlement_agent`, `notes`, `application_id`) VALUES (1, '2025-03-03', 'title', 'test', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ctc`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `ctc` (`id`, `cleared_by`, `ctc_date`, `notes`, `application_id`) VALUES (1, '1', '2025-05-05', 'test', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `declined`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `declined` (`id`, `declined_date`, `reason`, `application_id`) VALUES (1, '2025-05-05', 'Credit', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `documentation`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `documentation` (`id`, `application_id`, `doc_type`, `file_path`, `uploaded_by`, `uploaded_at`) VALUES (1, 1, 'Income', 'http://', 1, '2025-01-01');

COMMIT;


-- -----------------------------------------------------
-- Data for table `funded`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `funded` (`id`, `funded_date`, `wire_amount`, `wire_confirmation`, `application_id`) VALUES (1, '2025-01-05', 250000, '10000', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `status`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `status` (`id`, `application_id`, `status`, `changed_by`, `changed_at`, `notes`) VALUES (1, 1, 'Application', 1, '2025-01-01', 'test');
INSERT INTO `status` (`id`, `application_id`, `status`, `changed_by`, `changed_at`, `notes`) VALUES (2, 2, 'Approved', 2, '2025-01-01', 'approved');
INSERT INTO `status` (`id`, `application_id`, `status`, `changed_by`, `changed_at`, `notes`) VALUES (3, 3, 'closed', 3, '2025-05-04', 'closed');

COMMIT;


-- -----------------------------------------------------
-- Data for table `underwriting`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `underwriting` (`id`, `underwriter_name`, `findings`, `reviewed_date`, `decision`, `application_id`) VALUES (1, 'seth', 'Approved Eligible', '2025-02-02', 'Approved', 1);

COMMIT;

