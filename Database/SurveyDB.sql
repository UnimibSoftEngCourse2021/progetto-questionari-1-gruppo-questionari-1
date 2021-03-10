-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema survey
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema survey
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `survey` DEFAULT CHARACTER SET utf8 ;
USE `survey` ;

-- -----------------------------------------------------
-- Table `survey`.`utenti`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `survey`.`utenti` (
  `Email` VARCHAR(255) NOT NULL,
  `Nome` VARCHAR(255) NULL DEFAULT NULL,
  `Cognome` VARCHAR(255) NULL DEFAULT NULL,
  `Password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`Email`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `survey`.`questionari`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `survey`.`questionari` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(255) NOT NULL,
  `Categoria` VARCHAR(255) NOT NULL,
  `Creatore` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Questionario_Utente1_idx` (`Creatore` ASC) VISIBLE,
  CONSTRAINT `fk_Questionario_Utente1`
    FOREIGN KEY (`Creatore`)
    REFERENCES `survey`.`utenti` (`Email`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `survey`.`compilazioni`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `survey`.`compilazioni` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Questionario_ID` INT NOT NULL,
  `Compilatore` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Compilazione_Questionario1_idx` (`Questionario_ID` ASC) VISIBLE,
  INDEX `fk_Compilazione_Utente1_idx` (`Compilatore` ASC) VISIBLE,
  CONSTRAINT `fk_Compilazione_Questionario1`
    FOREIGN KEY (`Questionario_ID`)
    REFERENCES `survey`.`questionari` (`ID`),
  CONSTRAINT `fk_Compilazione_Utente1`
    FOREIGN KEY (`Compilatore`)
    REFERENCES `survey`.`utenti` (`Email`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `survey`.`domande`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `survey`.`domande` (
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
    REFERENCES `survey`.`utenti` (`Email`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `survey`.`compilazionidomande`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Survey`.`CompilazioniDomande` (
  `ID` VARCHAR(45) NOT NULL,
  `Risposta` VARCHAR(255) NOT NULL,
  `Domanda` INT NOT NULL,
  `Compilazione_ID` INT NOT NULL,
  INDEX `fk_CompilazioneDomanda_Domanda1_idx` (`Domanda` ASC) VISIBLE,
  INDEX `fk_CompilazioneDomanda_Compilazione1_idx` (`Compilazione_ID` ASC) VISIBLE,
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_CompilazioneDomanda_Compilazione1`
    FOREIGN KEY (`Compilazione_ID`)
    REFERENCES `Survey`.`Compilazioni` (`ID`),
  CONSTRAINT `fk_CompilazioneDomanda_Domanda1`
    FOREIGN KEY (`Domanda`)
    REFERENCES `Survey`.`Domande` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `survey`.`domandequestionari`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `survey`.`domandequestionari` (
  `Questionario_ID` INT NOT NULL,
  `Domanda_ID` INT NOT NULL,
  PRIMARY KEY (`Questionario_ID`, `Domanda_ID`),
  INDEX `fk_DomandaQuestionario_Questionario1_idx` (`Questionario_ID` ASC) VISIBLE,
  INDEX `fk_DomandaQuestionario_Domanda1_idx` (`Domanda_ID` ASC) VISIBLE,
  CONSTRAINT `fk_DomandaQuestionario_Domanda1`
    FOREIGN KEY (`Domanda_ID`)
    REFERENCES `survey`.`domande` (`ID`),
  CONSTRAINT `fk_DomandaQuestionario_Questionario1`
    FOREIGN KEY (`Questionario_ID`)
    REFERENCES `Survey`.`Questionari` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `survey`.`opzioni`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `survey`.`opzioni` (
  `idOpzione` INT NOT NULL AUTO_INCREMENT,
  `descrizioneOpzione` VARCHAR(255) NOT NULL,
  `Domanda_ID` INT NOT NULL,
  PRIMARY KEY (`idOpzione`),
  INDEX `fk_Opzione_Domanda1_idx` (`Domanda_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Opzione_Domanda1`
    FOREIGN KEY (`Domanda_ID`)
    REFERENCES `survey`.`domande` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
