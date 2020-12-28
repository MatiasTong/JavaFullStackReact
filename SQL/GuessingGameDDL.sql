-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema GuessingGame
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema GuessingGame
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `GuessingGame` DEFAULT CHARACTER SET utf8 ;
USE `GuessingGame` ;

-- -----------------------------------------------------
-- Table `GuessingGame`.`Game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GuessingGame`.`Game` (
  `GameId` INT NOT NULL,
  `Answer` CHAR(4) NOT NULL,
  `Status` TINYINT NOT NULL,
  PRIMARY KEY (`GameId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GuessingGame`.`Guess`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GuessingGame`.`Guess` (
  `GuessId` INT NOT NULL,
  `Time` DATETIME GENERATED ALWAYS AS (),
  `Result` CHAR(7) NOT NULL,
  `GameId` INT NOT NULL,
  PRIMARY KEY (`GuessId`),
  INDEX `FK_Guess_GameId_idx` (`GameId` ASC) VISIBLE,
  CONSTRAINT `FK_Guess_GameId`
    FOREIGN KEY (`GameId`)
    REFERENCES `GuessingGame`.`Game` (`GameId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
