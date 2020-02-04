-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema movies
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema movies
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `movies` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `movies` ;

-- -----------------------------------------------------
-- Table `movies`.`directors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movies`.`directors` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `about` TEXT NULL DEFAULT NULL,
  `image_URL` VARCHAR(255) NULL DEFAULT NULL,
  `year` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `movies`.`genres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movies`.`genres` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `image_URL` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `movies`.`movies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movies`.`movies` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `year` INT(11) NOT NULL,
  `image_url` VARCHAR(255) NOT NULL,
  `certificate` VARCHAR(45) NULL DEFAULT NULL,
  `runtime` INT(11) NULL DEFAULT NULL,
  `imdb_rating` FLOAT NULL DEFAULT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `metascore` INT(11) NULL DEFAULT NULL,
  `votes` INT(11) NULL DEFAULT NULL,
  `gross` INT(11) NULL DEFAULT NULL,
  `moviescol` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `movies`.`movies_directors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movies`.`movies_directors` (
  `movies_id` INT(11) NOT NULL,
  `directors_id` INT(11) NOT NULL,
  PRIMARY KEY (`directors_id`, `movies_id`),
  INDEX `fk_movies_has_directors_directors1_idx` (`directors_id` ASC) VISIBLE,
  INDEX `fk_movies_has_directors_movies_idx` (`movies_id` ASC) VISIBLE,
  CONSTRAINT `fk_movies_has_directors_directors1`
    FOREIGN KEY (`directors_id`)
    REFERENCES `movies`.`directors` (`id`),
  CONSTRAINT `fk_movies_has_directors_movies`
    FOREIGN KEY (`movies_id`)
    REFERENCES `movies`.`movies` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `movies`.`movies_genres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movies`.`movies_genres` (
  `movies_id` INT(11) NOT NULL,
  `genres_id` INT(11) NOT NULL,
  PRIMARY KEY (`movies_id`, `genres_id`),
  INDEX `fk_movies_has_genres_genres1_idx` (`genres_id` ASC) VISIBLE,
  INDEX `fk_movies_has_genres_movies1_idx` (`movies_id` ASC) VISIBLE,
  CONSTRAINT `fk_movies_has_genres_genres1`
    FOREIGN KEY (`genres_id`)
    REFERENCES `movies`.`genres` (`id`),
  CONSTRAINT `fk_movies_has_genres_movies1`
    FOREIGN KEY (`movies_id`)
    REFERENCES `movies`.`movies` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `movies`.`stars`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movies`.`stars` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `about` TEXT NULL DEFAULT NULL,
  `image_URL` VARCHAR(255) NULL DEFAULT NULL,
  `year` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `movies`.`movies_stars`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movies`.`movies_stars` (
  `movies_id` INT(11) NOT NULL,
  `stars_id` INT(11) NOT NULL,
  PRIMARY KEY (`movies_id`, `stars_id`),
  INDEX `fk_movies_has_stars_stars1_idx` (`stars_id` ASC) VISIBLE,
  INDEX `fk_movies_has_stars_movies1_idx` (`movies_id` ASC) VISIBLE,
  CONSTRAINT `fk_movies_has_stars_movies1`
    FOREIGN KEY (`movies_id`)
    REFERENCES `movies`.`movies` (`id`),
  CONSTRAINT `fk_movies_has_stars_stars1`
    FOREIGN KEY (`stars_id`)
    REFERENCES `movies`.`stars` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(32) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
