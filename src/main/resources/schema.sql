-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema D_Nolja
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `D_Nolja` ;

-- -----------------------------------------------------
-- Schema D_Nolja
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `D_Nolja` DEFAULT CHARACTER SET utf8 ;
USE `D_Nolja` ;

-- -----------------------------------------------------
-- Table `D_Nolja`.`table1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`table1` ;



-- -----------------------------------------------------
-- Table `D_Nolja`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`Users` ;

CREATE TABLE IF NOT EXISTS `D_Nolja`.`Users` (
                                                 `user_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                 `provider` VARCHAR(10) null,
    `provider_id` BIGINT null,
    `nick_name` VARCHAR(45) NULL,
    `profile` VARCHAR(300) NULL,
    `status` VARCHAR(10) NOT NULL DEFAULT 'active',
    `created_at` TIMESTAMP NOT NULL DEFAULT current_timestamp,
    `modified_at` TIMESTAMP NOT NULL DEFAULT current_timestamp,
    `role` VARCHAR(45) NULL,
    `email` VARCHAR(100) NULL,
    `password` VARCHAR(100) NULL,  -- Changed 'password' to 'VARCHAR'
    `verified` SMALLINT NULL CHECK (verified IN (0, 1)),
    `refresh_token` VARCHAR(500) Null,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
    ) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `D_Nolja`.`Stay`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`Stay` ;

CREATE TABLE IF NOT EXISTS `D_Nolja`.`Stay` (
                                                `stay_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                `name` VARCHAR(30) NOT NULL,
    `x` DOUBLE NOT NULL,
    `y` DOUBLE NOT NULL,
    `address` VARCHAR(100) NULL,
    `tel` VARCHAR(15) NULL,
    `open_time` VARCHAR(100) NULL,
    `info` VARCHAR(100) NULL,
    PRIMARY KEY (`stay_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `D_Nolja`.`FCL`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`FCL` ;

CREATE TABLE IF NOT EXISTS `D_Nolja`.`FCL` (
                                               `fcl_id` BIGINT NOT NULL AUTO_INCREMENT,
                                               `name` VARCHAR(30) NOT NULL,
    `type` VARCHAR(10) NOT NULL,
    `x` DOUBLE NOT NULL,
    `y` DOUBLE NOT NULL,
    `address` VARCHAR(100) NULL,
    `tel` VARCHAR(15) NULL,
    `open_time` VARCHAR(100) NULL,
    `parking` CHAR NULL,
    `info` VARCHAR(100) NULL,
    PRIMARY KEY (`fcl_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `D_Nolja`.`Spot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`Spot` ;

CREATE TABLE IF NOT EXISTS `D_Nolja`.`Spot` (
                                                `spot_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                `name` VARCHAR(30) NOT NULL,
    `type` VARCHAR(10) NOT NULL,
    `x` DOUBLE NOT NULL,
    `y` DOUBLE NOT NULL,
    `address` VARCHAR(100) NULL,
    `tel` VARCHAR(15) NULL,
    `open_time` VARCHAR(100) NULL,
    `parking` CHAR NULL,
    `info` VARCHAR(100) NULL,
    PRIMARY KEY (`spot_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `D_Nolja`.`Plan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`Plan` ;

CREATE TABLE IF NOT EXISTS `D_Nolja`.`Plan` (
                                                `plan_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                `user_id` BIGINT NOT NULL,
                                                `start` DATE NOT NULL,
                                                `end` DATE NOT NULL,
                                                `created_at` VARCHAR(45) NOT NULL DEFAULT 'current_timestamp',
    `modified_at` VARCHAR(45) NOT NULL DEFAULT 'current_timestamp',
    PRIMARY KEY (`plan_id`),
    INDEX `fk_Plan_Users1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_Plan_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `D_Nolja`.`Users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `D_Nolja`.`Daily`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`Daily` ;

CREATE TABLE IF NOT EXISTS `D_Nolja`.`Daily` (
                                                 `daily_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                 `plan_id` BIGINT NOT NULL,
                                                 `order` INT NOT NULL,
                                                 `day_num` INT NOT NULL,
                                                 `created_at` VARCHAR(45) NOT NULL DEFAULT 'current_timestamp',
    `modified_at` TIMESTAMP NOT NULL DEFAULT current_timestamp,
    PRIMARY KEY (`daily_id`),
    INDEX `fk_Daily_Plan_idx` (`plan_id` ASC) VISIBLE,
    CONSTRAINT `fk_Daily_Plan`
    FOREIGN KEY (`plan_id`)
    REFERENCES `D_Nolja`.`Plan` (`plan_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `D_Nolja`.`Companion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`Companion` ;

CREATE TABLE IF NOT EXISTS `D_Nolja`.`Companion` (
                                                     `companion_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                     `plan_id` BIGINT NOT NULL,
                                                     `user_id` BIGINT NOT NULL,
                                                     `role` VARCHAR(45) NOT NULL DEFAULT 'member',
    `status` VARCHAR(45) NOT NULL DEFAULT 'join',
    `join_date` TIMESTAMP NOT NULL DEFAULT current_timestamp,
    `cancel_date` TIMESTAMP NULL,
    PRIMARY KEY (`companion_id`),
    INDEX `fk_Companion_Plan1_idx` (`plan_id` ASC) VISIBLE,
    INDEX `fk_Companion_Users1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_Companion_Plan1`
    FOREIGN KEY (`plan_id`)
    REFERENCES `D_Nolja`.`Plan` (`plan_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_Companion_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `D_Nolja`.`Users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `D_Nolja`.`Review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`Review` ;

CREATE TABLE IF NOT EXISTS `D_Nolja`.`Review` (
                                                  `review_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                  `plan_id` BIGINT NOT NULL,
                                                  `user_id` BIGINT NOT NULL,
                                                  `hit` INT NULL DEFAULT 0,
                                                  `good` INT NULL DEFAULT 0,
                                                  `created_at` VARCHAR(45) NULL DEFAULT 'current_timestamp',
    `modified_at` VARCHAR(45) NULL DEFAULT 'current_timestamp',
    INDEX `fk_Review_Plan1_idx` (`plan_id` ASC) VISIBLE,
    INDEX `fk_Review_Users1_idx` (`user_id` ASC) VISIBLE,
    PRIMARY KEY (`review_id`),
    CONSTRAINT `fk_Review_Plan1`
    FOREIGN KEY (`plan_id`)
    REFERENCES `D_Nolja`.`Plan` (`plan_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_Review_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `D_Nolja`.`Users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `D_Nolja`.`Daily_Review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`Daily_Review` ;

CREATE TABLE IF NOT EXISTS `D_Nolja`.`Daily_Review` (
                                                        `daily_review_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                        `daily_id` BIGINT NOT NULL,
                                                        `review_id` BIGINT NOT NULL,
                                                        `content` VARCHAR(500) NULL,
    `img` VARCHAR(300) NULL,
    `modified_at` TIMESTAMP NULL DEFAULT current_timestamp,
    `created_at` TIMESTAMP NULL DEFAULT current_timestamp,
    PRIMARY KEY (`daily_review_id`),
    INDEX `fk_Daily_Review_Daily1_idx` (`daily_id` ASC) VISIBLE,
    INDEX `fk_Daily_Review_Review1_idx` (`review_id` ASC) VISIBLE,
    CONSTRAINT `fk_Daily_Review_Daily1`
    FOREIGN KEY (`daily_id`)
    REFERENCES `D_Nolja`.`Daily` (`daily_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_Daily_Review_Review1`
    FOREIGN KEY (`review_id`)
    REFERENCES `D_Nolja`.`Review` (`review_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `D_Nolja`.`daily_to_stay`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`daily_to_stay` ;

CREATE TABLE IF NOT EXISTS `D_Nolja`.`daily_to_stay` (
                                                         `daily_id` BIGINT NOT NULL,
                                                         `stay_id` BIGINT NOT NULL,
                                                         INDEX `fk_table3_Daily1_idx` (`daily_id` ASC) VISIBLE,
    INDEX `fk_table3_Stay1_idx` (`stay_id` ASC) VISIBLE,
    CONSTRAINT `fk_table3_Daily1`
    FOREIGN KEY (`daily_id`)
    REFERENCES `D_Nolja`.`Daily` (`daily_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_table3_Stay1`
    FOREIGN KEY (`stay_id`)
    REFERENCES `D_Nolja`.`Stay` (`stay_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `D_Nolja`.`daily_to_spot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`daily_to_spot` ;

CREATE TABLE IF NOT EXISTS `D_Nolja`.`daily_to_spot` (
                                                         `daily_id` BIGINT NOT NULL,
                                                         `spot_id` BIGINT NOT NULL,
                                                         INDEX `fk_table4_Daily1_idx` (`daily_id` ASC) VISIBLE,
    INDEX `fk_table4_Spot1_idx` (`spot_id` ASC) VISIBLE,
    CONSTRAINT `fk_table4_Daily1`
    FOREIGN KEY (`daily_id`)
    REFERENCES `D_Nolja`.`Daily` (`daily_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_table4_Spot1`
    FOREIGN KEY (`spot_id`)
    REFERENCES `D_Nolja`.`Spot` (`spot_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `D_Nolja`.`daily_to_fcl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`daily_to_fcl` ;

CREATE TABLE IF NOT EXISTS `D_Nolja`.`daily_to_fcl` (
                                                        `daily_id` BIGINT NOT NULL,
                                                        `fcl_id` BIGINT NOT NULL,
                                                        INDEX `fk_table5_Daily1_idx` (`daily_id` ASC) VISIBLE,
    INDEX `fk_table5_FCL1_idx` (`fcl_id` ASC) VISIBLE,
    CONSTRAINT `fk_table5_Daily1`
    FOREIGN KEY (`daily_id`)
    REFERENCES `D_Nolja`.`Daily` (`daily_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_table5_FCL1`
    FOREIGN KEY (`fcl_id`)
    REFERENCES `D_Nolja`.`FCL` (`fcl_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `D_Nolja`.`Email_Verification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`Email_Verification` ;
DROP TABLE IF EXISTS `D_Nolja`.`Email_Verifications` ;

CREATE TABLE IF NOT EXISTS `D_Nolja`.`Email_Verifications` (
    `verification_id` VARCHAR(100) NOT NULL,
    `email` VARCHAR(50) NULL,
    PRIMARY KEY (`verification_id`))
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;