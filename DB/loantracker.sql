-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema loantrackerdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `loantrackerdb` ;

-- -----------------------------------------------------
-- Schema loantrackerdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `loantrackerdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
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
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `borrower`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `borrower` ;

CREATE TABLE IF NOT EXISTS `borrower` (
  `id` INT NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NULL,
  `phone` VARCHAR(20) NULL,
  `created_at` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `applications`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `applications` ;

CREATE TABLE IF NOT EXISTS `applications` (
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
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `approved`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `approved` ;

CREATE TABLE IF NOT EXISTS `approved` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `application_id` INT NULL DEFAULT NULL,
  `approval_date` DATE NULL DEFAULT NULL,
  `approval_notes` TEXT NULL DEFAULT NULL,
  `interest_rate` DECIMAL(5,3) NULL DEFAULT NULL,
  `term_years` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `application_id` (`application_id` ASC) VISIBLE,
  CONSTRAINT `approved_ibfk_1`
    FOREIGN KEY (`application_id`)
    REFERENCES `applications` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `closed`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `closed` ;

CREATE TABLE IF NOT EXISTS `closed` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `application_id` INT NULL DEFAULT NULL,
  `closing_date` DATE NULL DEFAULT NULL,
  `settlement_agent` VARCHAR(100) NULL DEFAULT NULL,
  `notes` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `application_id` (`application_id` ASC) VISIBLE,
  CONSTRAINT `closed_ibfk_1`
    FOREIGN KEY (`application_id`)
    REFERENCES `applications` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ctc`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ctc` ;

CREATE TABLE IF NOT EXISTS `ctc` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `application_id` INT NULL DEFAULT NULL,
  `cleared_by` VARCHAR(100) NULL DEFAULT NULL,
  `ctc_date` DATE NULL DEFAULT NULL,
  `notes` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `application_id` (`application_id` ASC) VISIBLE,
  CONSTRAINT `ctc_ibfk_1`
    FOREIGN KEY (`application_id`)
    REFERENCES `applications` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `declined`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `declined` ;

CREATE TABLE IF NOT EXISTS `declined` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `application_id` INT NULL DEFAULT NULL,
  `declined_date` DATE NULL DEFAULT NULL,
  `reason` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `application_id` (`application_id` ASC) VISIBLE,
  CONSTRAINT `declined_ibfk_1`
    FOREIGN KEY (`application_id`)
    REFERENCES `applications` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


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
    REFERENCES `applications` (`id`),
  CONSTRAINT `documentation_ibfk_2`
    FOREIGN KEY (`uploaded_by`)
    REFERENCES `user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `funded`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `funded` ;

CREATE TABLE IF NOT EXISTS `funded` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `application_id` INT NULL DEFAULT NULL,
  `funded_date` DATE NULL DEFAULT NULL,
  `wire_amount` DECIMAL(12,2) NULL DEFAULT NULL,
  `wire_confirmation` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `application_id` (`application_id` ASC) VISIBLE,
  CONSTRAINT `funded_ibfk_1`
    FOREIGN KEY (`application_id`)
    REFERENCES `applications` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


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
    REFERENCES `applications` (`id`),
  CONSTRAINT `loan_statuses_ibfk_2`
    FOREIGN KEY (`changed_by`)
    REFERENCES `user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `underwriting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `underwriting` ;

CREATE TABLE IF NOT EXISTS `underwriting` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `application_id` INT NULL DEFAULT NULL,
  `underwriter_name` VARCHAR(100) NULL DEFAULT NULL,
  `findings` TEXT NULL DEFAULT NULL,
  `reviewed_date` DATE NULL DEFAULT NULL,
  `decision` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `application_id` (`application_id` ASC) VISIBLE,
  CONSTRAINT `underwriting_ibfk_1`
    FOREIGN KEY (`application_id`)
    REFERENCES `applications` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


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

COMMIT;


-- -----------------------------------------------------
-- Data for table `borrower`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (1, 'Diane', 'Suelter', 'ds@gmail.com', '3033003030', '2025-01-01');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (2, 'a', 'a', 'aa@gmail.com', '3033333000', '2025-01-01');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (3, 'b', 'b', 'bb@gmail.com', '3033033030', '2025-01-01');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (4, 'c', 'c', 'cc@gmail.com', '3330003030', '2025-01-01');
INSERT INTO `borrower` (`id`, `first_name`, `last_name`, `email`, `phone`, `created_at`) VALUES (5, 'd', 'd', 'dd@gmail.com', '3033333030', '2025-01-01');

COMMIT;


-- -----------------------------------------------------
-- Data for table `applications`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `applications` (`id`, `user_id`, `property_address`, `loan_amount`, `loan_type`, `purpose`, `submitted_date`, `status`, `borrower_id`, `loan_number`) VALUES (1, 1, '78 The Lane Road', 500000, 'FHA', 'Purchase', '2024-05-12', 'Application', 1, 1000);

COMMIT;


-- -----------------------------------------------------
-- Data for table `approved`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `approved` (`id`, `application_id`, `approval_date`, `approval_notes`, `interest_rate`, `term_years`) VALUES (1, 1, '2025-02-02', 'test', 6, 30);

COMMIT;


-- -----------------------------------------------------
-- Data for table `closed`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `closed` (`id`, `application_id`, `closing_date`, `settlement_agent`, `notes`) VALUES (1, 1, '2025-03-03', 'title', 'test');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ctc`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `ctc` (`id`, `application_id`, `cleared_by`, `ctc_date`, `notes`) VALUES (1, 1, '1', '2025-05-05', 'test');

COMMIT;


-- -----------------------------------------------------
-- Data for table `declined`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `declined` (`id`, `application_id`, `declined_date`, `reason`) VALUES (1, 1, '2025-05-05', 'Credit');

COMMIT;


-- -----------------------------------------------------
-- Data for table `documentation`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `documentation` (`id`, `application_id`, `doc_type`, `file_path`, `uploaded_by`, `uploaded_at`) VALUES (1, 1, 'Income', 'http://', 1, '2025-01-01');

COMMIT;


-- -----------------------------------------------------
-- Data for table `status`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `status` (`id`, `application_id`, `status`, `changed_by`, `changed_at`, `notes`) VALUES (1, 1, 'Application', 1, '2025-01-01', 'test');

COMMIT;


-- -----------------------------------------------------
-- Data for table `underwriting`
-- -----------------------------------------------------
START TRANSACTION;
USE `loantrackerdb`;
INSERT INTO `underwriting` (`id`, `application_id`, `underwriter_name`, `findings`, `reviewed_date`, `decision`) VALUES (1, 1, 'seth', 'Approved Eligible', '2025-02-02', 'Approved');

COMMIT;

