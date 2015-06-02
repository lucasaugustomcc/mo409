SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mo` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mo` ;

-- -----------------------------------------------------
-- Table `mo`.`tb_usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mo`.`tb_usuario` ;

CREATE TABLE IF NOT EXISTS `mo`.`tb_usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(40) NOT NULL,
  `email` VARCHAR(30) NULL,
  `senha` VARCHAR(20) NOT NULL,
  `papel` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mo`.`tb_professor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mo`.`tb_professor` ;

CREATE TABLE IF NOT EXISTS `mo`.`tb_professor` (
  `ra_professor` INT NOT NULL,
  `tb_usuario_id_usuario` INT NOT NULL,
  PRIMARY KEY (`ra_professor`),
  CONSTRAINT `fk_tb_Professor_tb_usuario1`
    FOREIGN KEY (`tb_usuario_id_usuario`)
    REFERENCES `mo`.`tb_usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_tb_Professor_tb_usuario1_idx` ON `mo`.`tb_professor` (`tb_usuario_id_usuario` ASC);

CREATE UNIQUE INDEX `tb_usuario_id_usuario_UNIQUE` ON `mo`.`tb_professor` (`tb_usuario_id_usuario` ASC);


-- -----------------------------------------------------
-- Table `mo`.`tb_aluno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mo`.`tb_aluno` ;

CREATE TABLE IF NOT EXISTS `mo`.`tb_aluno` (
  `ra_aluno` INT NOT NULL,
  `tb_usuario_id_usuario` INT NOT NULL,
  PRIMARY KEY (`ra_aluno`),
  CONSTRAINT `fk_tb_Aluno_tb_Usuario`
    FOREIGN KEY (`tb_usuario_id_usuario`)
    REFERENCES `mo`.`tb_usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_tb_Aluno_tb_Usuario_idx` ON `mo`.`tb_aluno` (`tb_usuario_id_usuario` ASC);

CREATE UNIQUE INDEX `tb_usuario_id_usuario_UNIQUE` ON `mo`.`tb_aluno` (`tb_usuario_id_usuario` ASC);


-- -----------------------------------------------------
-- Table `mo`.`tb_disciplina`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mo`.`tb_disciplina` ;

CREATE TABLE IF NOT EXISTS `mo`.`tb_disciplina` (
  `id_disciplina` INT NOT NULL,
  `nome_disciplina` VARCHAR(45) NULL,
  PRIMARY KEY (`id_disciplina`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mo`.`tb_turma`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mo`.`tb_turma` ;

CREATE TABLE IF NOT EXISTS `mo`.`tb_turma` (
  `id_turma` INT NOT NULL AUTO_INCREMENT,
  `cod_turma` CHAR(2) NULL,
  `tb_disciplina_id_disciplina` INT NOT NULL,
  `ano` INT(11) NOT NULL,
  `periodo` TINYINT(4) NOT NULL,
  PRIMARY KEY (`id_turma`),
  CONSTRAINT `fk_tb_turma_tb_disciplina1`
    FOREIGN KEY (`tb_disciplina_id_disciplina`)
    REFERENCES `mo`.`tb_disciplina` (`id_disciplina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_tb_turma_tb_disciplina1_idx` ON `mo`.`tb_turma` (`tb_disciplina_id_disciplina` ASC);

CREATE UNIQUE INDEX `oferta_UNIQUE` ON `mo`.`tb_turma` (`periodo` ASC, `ano` ASC, `tb_disciplina_id_disciplina` ASC, `cod_turma` ASC);


-- -----------------------------------------------------
-- Table `mo`.`tb_aula`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mo`.`tb_aula` ;

CREATE TABLE IF NOT EXISTS `mo`.`tb_aula` (
  `id_aula` INT NOT NULL AUTO_INCREMENT,
  `hora_inicio` TIME NULL,
  `hora_fim` TIME NULL,
  `data_aula` DATE NULL,
  `tb_turma_id_turma` INT NOT NULL,
  `tb_professor_ra_professor` INT NOT NULL,
  PRIMARY KEY (`id_aula`),
  CONSTRAINT `fk_tb_Aula_tb_Turma1`
    FOREIGN KEY (`tb_turma_id_turma`)
    REFERENCES `mo`.`tb_turma` (`id_turma`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_aula_tb_professor1`
    FOREIGN KEY (`tb_professor_ra_professor`)
    REFERENCES `mo`.`tb_professor` (`ra_professor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_tb_Aula_tb_Turma1_idx` ON `mo`.`tb_aula` (`tb_turma_id_turma` ASC);

CREATE INDEX `fk_tb_aula_tb_professor1_idx` ON `mo`.`tb_aula` (`tb_professor_ra_professor` ASC);


-- -----------------------------------------------------
-- Table `mo`.`tb_presenca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mo`.`tb_presenca` ;

CREATE TABLE IF NOT EXISTS `mo`.`tb_presenca` (
  `id_presenca` INT NOT NULL AUTO_INCREMENT,
  `num_ticks` INT NOT NULL,
  `is_presente` TINYINT(1) NULL,
  `tb_aluno_ra_aluno` INT NOT NULL,
  `tb_aula_id_aula` INT NOT NULL,
  PRIMARY KEY (`id_presenca`),
  CONSTRAINT `fk_tb_presenca_tb_aluno1`
    FOREIGN KEY (`tb_aluno_ra_aluno`)
    REFERENCES `mo`.`tb_aluno` (`ra_aluno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_presenca_tb_aula1`
    FOREIGN KEY (`tb_aula_id_aula`)
    REFERENCES `mo`.`tb_aula` (`id_aula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_tb_presenca_tb_aluno1_idx` ON `mo`.`tb_presenca` (`tb_aluno_ra_aluno` ASC);

CREATE INDEX `fk_tb_presenca_tb_aula1_idx` ON `mo`.`tb_presenca` (`tb_aula_id_aula` ASC);

CREATE UNIQUE INDEX `ra_aluno_id_aula_UNIQUE` ON `mo`.`tb_presenca` (`tb_aluno_ra_aluno` ASC, `tb_aula_id_aula` ASC);


-- -----------------------------------------------------
-- Table `mo`.`tb_tick`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mo`.`tb_tick` ;

CREATE TABLE IF NOT EXISTS `mo`.`tb_tick` (
  `id_tick` INT NOT NULL AUTO_INCREMENT,
  `data_hora` DATETIME NULL,
  `distancia` INT NULL,
  `tb_aluno_ra_aluno` INT NOT NULL,
  `tb_aula_id_aula` INT NOT NULL,
  PRIMARY KEY (`id_tick`),
  CONSTRAINT `fk_tb_Ticks_tb_Aluno1`
    FOREIGN KEY (`tb_aluno_ra_aluno`)
    REFERENCES `mo`.`tb_aluno` (`ra_aluno`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_tick_tb_aula1`
    FOREIGN KEY (`tb_aula_id_aula`)
    REFERENCES `mo`.`tb_aula` (`id_aula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_tb_Ticks_tb_Aluno1_idx` ON `mo`.`tb_tick` (`tb_aluno_ra_aluno` ASC);

CREATE INDEX `fk_tb_tick_tb_aula1_idx` ON `mo`.`tb_tick` (`tb_aula_id_aula` ASC);


-- -----------------------------------------------------
-- Table `mo`.`tb_aluno_turma`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mo`.`tb_aluno_turma` ;

CREATE TABLE IF NOT EXISTS `mo`.`tb_aluno_turma` (
  `tb_aluno_ra_aluno` INT NOT NULL,
  `tb_turma_id_turma` INT NOT NULL,
  PRIMARY KEY (`tb_aluno_ra_aluno`, `tb_turma_id_turma`),
  CONSTRAINT `fk_tb_Aluno_has_tb_Turma_tb_Aluno1`
    FOREIGN KEY (`tb_aluno_ra_aluno`)
    REFERENCES `mo`.`tb_aluno` (`ra_aluno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_Aluno_has_tb_Turma_tb_Turma1`
    FOREIGN KEY (`tb_turma_id_turma`)
    REFERENCES `mo`.`tb_turma` (`id_turma`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_tb_Aluno_has_tb_Turma_tb_Turma1_idx` ON `mo`.`tb_aluno_turma` (`tb_turma_id_turma` ASC);

CREATE INDEX `fk_tb_Aluno_has_tb_Turma_tb_Aluno1_idx` ON `mo`.`tb_aluno_turma` (`tb_aluno_ra_aluno` ASC);


-- -----------------------------------------------------
-- Table `mo`.`tb_turma_professor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mo`.`tb_turma_professor` ;

CREATE TABLE IF NOT EXISTS `mo`.`tb_turma_professor` (
  `tb_turma_id_turma` INT NOT NULL,
  `tb_professor_ra_professor` INT NOT NULL,
  PRIMARY KEY (`tb_turma_id_turma`, `tb_professor_ra_professor`),
  CONSTRAINT `fk_tb_Turma_has_tb_Professor_tb_Turma1`
    FOREIGN KEY (`tb_turma_id_turma`)
    REFERENCES `mo`.`tb_turma` (`id_turma`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_Turma_has_tb_Professor_tb_Professor1`
    FOREIGN KEY (`tb_professor_ra_professor`)
    REFERENCES `mo`.`tb_professor` (`ra_professor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_tb_Turma_has_tb_Professor_tb_Professor1_idx` ON `mo`.`tb_turma_professor` (`tb_professor_ra_professor` ASC);

CREATE INDEX `fk_tb_Turma_has_tb_Professor_tb_Turma1_idx` ON `mo`.`tb_turma_professor` (`tb_turma_id_turma` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
