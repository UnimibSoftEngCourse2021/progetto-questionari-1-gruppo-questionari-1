-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`utente` (
  `Email` VARCHAR(255) NOT NULL,
  `Nome` VARCHAR(255) NULL DEFAULT NULL,
  `Cognome` VARCHAR(255) NULL DEFAULT NULL,
  `Password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`Email`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`questionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`questionario` (
  `ID` VARCHAR(255) NOT NULL,
  `Nome` VARCHAR(255) NOT NULL,
  `Categoria` VARCHAR(255) NOT NULL,
  `Creatore` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Questionario_Utente1_idx` (`Creatore` ASC) VISIBLE,
  CONSTRAINT `fk_Questionario_Utente1`
    FOREIGN KEY (`Creatore`)
    REFERENCES `mydb`.`utente` (`Email`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`compilazione`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`compilazione` (
  `ID` INT NOT NULL,
  `Questionario_ID` VARCHAR(255) NOT NULL,
  `Compilatore` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Compilazione_Questionario1_idx` (`Questionario_ID` ASC) VISIBLE,
  INDEX `fk_Compilazione_Utente1_idx` (`Compilatore` ASC) VISIBLE,
  CONSTRAINT `fk_Compilazione_Questionario1`
    FOREIGN KEY (`Questionario_ID`)
    REFERENCES `mydb`.`questionario` (`ID`),
  CONSTRAINT `fk_Compilazione_Utente1`
    FOREIGN KEY (`Compilatore`)
    REFERENCES `mydb`.`utente` (`Email`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`domanda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`domanda` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Testo` VARCHAR(255) NULL DEFAULT NULL,
  `Immagine` VARBINARY(2000) NULL DEFAULT NULL,
  `Categoria` VARCHAR(255) NOT NULL,
  `DomandaChiusa` BIT(1) NOT NULL,
  `Creatore` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Domanda_Utente_idx` (`Creatore` ASC) VISIBLE,
  CONSTRAINT `fk_Domanda_Utente`
    FOREIGN KEY (`Creatore`)
    REFERENCES `mydb`.`utente` (`Email`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`compilazionedomanda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`compilazionedomanda` (
  `Risposta` VARCHAR(255) NOT NULL,
  `Domanda_ID` INT NOT NULL,
  `Compilazione_ID` INT NOT NULL,
  PRIMARY KEY (`Domanda_ID`, `Compilazione_ID`),
  INDEX `fk_CompilazioneDomanda_Domanda1_idx` (`Domanda_ID` ASC) VISIBLE,
  INDEX `fk_CompilazioneDomanda_Compilazione1_idx` (`Compilazione_ID` ASC) VISIBLE,
  CONSTRAINT `fk_CompilazioneDomanda_Compilazione1`
    FOREIGN KEY (`Compilazione_ID`)
    REFERENCES `mydb`.`compilazione` (`ID`),
  CONSTRAINT `fk_CompilazioneDomanda_Domanda1`
    FOREIGN KEY (`Domanda_ID`)
    REFERENCES `mydb`.`domanda` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`domandaquestionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`domandaquestionario` (
  `Questionario_ID` VARCHAR(255) NOT NULL,
  `Domanda_ID` INT NOT NULL,
  PRIMARY KEY (`Questionario_ID`, `Domanda_ID`),
  INDEX `fk_DomandaQuestionario_Questionario1_idx` (`Questionario_ID` ASC) VISIBLE,
  INDEX `fk_DomandaQuestionario_Domanda1_idx` (`Domanda_ID` ASC) VISIBLE,
  CONSTRAINT `fk_DomandaQuestionario_Domanda1`
    FOREIGN KEY (`Domanda_ID`)
    REFERENCES `mydb`.`domanda` (`ID`),
  CONSTRAINT `fk_DomandaQuestionario_Questionario1`
    FOREIGN KEY (`Questionario_ID`)
    REFERENCES `mydb`.`questionario` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`opzione`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`opzione` (
  `idOpzione` INT NOT NULL,
  `descrizioneOpzione` VARCHAR(255) NOT NULL,
  `Domanda_ID` INT NOT NULL,
  INDEX `fk_Opzione_Domanda1_idx` (`Domanda_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Opzione_Domanda1`
    FOREIGN KEY (`Domanda_ID`)
    REFERENCES `mydb`.`domanda` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
