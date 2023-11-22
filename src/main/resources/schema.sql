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
-- Table `D_Nolja`.`Spot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`Spot` ;

CREATE TABLE IF NOT EXISTS `D_Nolja`.`Spot` (
                                                `spot_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                `name` VARCHAR(30) NOT NULL,
    `type1` VARCHAR(10) NOT NULL,
    `type2` VARCHAR(45) NOT NULL,
    `x` DOUBLE NOT NULL,
    `y` DOUBLE NOT NULL,
    `address` VARCHAR(300) NULL,
    `tel` VARCHAR(15) NULL,
    `open_time` VARCHAR(100) NULL,
    `parking` CHAR NULL,
    `info` VARCHAR(100) NULL,
    `img` VARCHAR(1000) NULL,
    PRIMARY KEY (`spot_id`))
    ENGINE = InnoDB;






-- -----------------------------------------------------
-- Table `D_Nolja`.`Email_Verification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`Email_Verification` ;

CREATE TABLE IF NOT EXISTS `D_Nolja`.`Email_Verifications` (
    `verification_id` VARCHAR(100) NOT NULL,
    `email` VARCHAR(50) NULL,
    PRIMARY KEY (`verification_id`))
    ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `D_Nolja`.`Plan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`Plan`;
CREATE TABLE IF NOT EXISTS `D_Nolja`.`Plan` (
                                                `plan_id` BIGINT NOT NULL  AUTO_INCREMENT,
                                                `user_id` BIGINT NOT NULL,
                                                `title` varchar(100) NOT NULL,
                                                `start` VARCHAR(30) NOT NULL,
                                                `end` VARCHAR(30) NOT NULL,
                                                `created_at` TIMESTAMP NOT NULL DEFAULT current_timestamp,
                                                `modified_at` TIMESTAMP NOT NULL DEFAULT current_timestamp,
                                                PRIMARY KEY (`plan_id`),
    INDEX `fk_Plan_Users1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_Plan_Users1` FOREIGN KEY (`user_id`) REFERENCES `D_Nolja`.`Users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
    ) ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `D_Nolja`.`Daily`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`Daily`;
CREATE TABLE IF NOT EXISTS `D_Nolja`.`Daily` (
                                                 `daily_id` BIGINT NOT NULL  AUTO_INCREMENT,
                                                 `plan_id` BIGINT NOT NULL,
                                                 `day_num` INT NOT NULL,
                                                 `created_at` TIMESTAMP NOT NULL DEFAULT current_timestamp,
                                                 `modified_at` TIMESTAMP NOT NULL DEFAULT current_timestamp,
                                                 PRIMARY KEY (`daily_id`),
    INDEX `fk_Daily_Plan_idx` (`plan_id` ASC) VISIBLE,
    CONSTRAINT `fk_Daily_Plan` FOREIGN KEY (`plan_id`) REFERENCES `D_Nolja`.`Plan` (`plan_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
    ) ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `D_Nolja`.`daily_to_spot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`daily_to_spot`;

-- -----------------------------------------------------
-- Table `D_Nolja`.`Review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`Review` ;

CREATE TABLE IF NOT EXISTS `D_Nolja`.`Review` (
                                                  `review_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                  `daily_id` BIGINT NOT NULL,
                                                  `spot_id` BIGINT NOT NULL,
                                                  `order` INT NULL,
                                                  `contents` VARCHAR(1000) NULL,
    `img` VARCHAR(1000) NULL,
    INDEX `fk_table4_Daily1_idx` (`daily_id` ASC) VISIBLE,
    INDEX `fk_table4_Spot1_idx` (`spot_id` ASC) VISIBLE,
    PRIMARY KEY (`review_id`),
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
-- Table `D_Nolja`.`Comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`Comment`;
CREATE TABLE IF NOT EXISTS `D_Nolja`.`Comment` (
                                                   `comment_id` BIGINT NOT NULL  AUTO_INCREMENT,
                                                   `review_id` BIGINT NOT NULL,
                                                   `user_id` BIGINT NOT NULL,
                                                   `content` VARCHAR(1000) NULL,
    `registered_at` TIMESTAMP NULL DEFAULT current_timestamp,
    `modified_at` TIMESTAMP NULL DEFAULT current_timestamp,
    `deleted_at` TIMESTAMP NULL,
    `Plan_plan_id` BIGINT NOT NULL,
    PRIMARY KEY (`comment_id`),
    INDEX `fk_Comment_Users1_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_Comment_Plan1_idx` (`Plan_plan_id` ASC) VISIBLE,
    CONSTRAINT `fk_Comment_Users1` FOREIGN KEY (`user_id`) REFERENCES `D_Nolja`.`Users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_Comment_Plan1` FOREIGN KEY (`Plan_plan_id`) REFERENCES `D_Nolja`.`Plan` (`plan_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
    ) ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `D_Nolja`.`Like`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`Like`;
CREATE TABLE IF NOT EXISTS `D_Nolja`.`Like` (
                                                `like_id` BIGINT NOT NULL  AUTO_INCREMENT,
                                                `review_id` BIGINT NOT NULL,
                                                `user_id` BIGINT NOT NULL,
                                                `registered_at` TIMESTAMP NULL DEFAULT current_timestamp,
                                                `Plan_plan_id` BIGINT NOT NULL,
                                                PRIMARY KEY (`like_id`),
    INDEX `fk_Like_Users1_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_Like_Plan1_idx` (`Plan_plan_id` ASC) VISIBLE,
    CONSTRAINT `fk_Like_Users1` FOREIGN KEY (`user_id`) REFERENCES `D_Nolja`.`Users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_Like_Plan1` FOREIGN KEY (`Plan_plan_id`) REFERENCES `D_Nolja`.`Plan` (`plan_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
    ) ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `D_Nolja`.`Alarm`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `D_Nolja`.`Alarm`;
CREATE TABLE IF NOT EXISTS `D_Nolja`.`Alarm` (
                                                 `alarm_id` BIGINT NOT NULL,
                                                 `user_id` BIGINT NOT NULL,
                                                 `content` VARCHAR(100) NULL,
    `type` VARCHAR(45) NULL,
    `registered_at` TIMESTAMP NULL DEFAULT current_timestamp,
    `updated_at` TIMESTAMP NULL DEFAULT current_timestamp,
    `target_id` BIGINT NULL,
    PRIMARY KEY (`alarm_id`),
    INDEX `fk_Alarm_Users1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_Alarm_Users1` FOREIGN KEY (`user_id`) REFERENCES `D_Nolja`.`Users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
    ) ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
