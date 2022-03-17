-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           8.0.13 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para ensino
DROP DATABASE IF EXISTS `ensino`;
CREATE DATABASE IF NOT EXISTS `ensino` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `ensino`;

-- Copiando estrutura para tabela ensino.aluno
DROP TABLE IF EXISTS `aluno`;
CREATE TABLE IF NOT EXISTS `aluno` (
  `idAluno` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `dataNasc` date NOT NULL,
  PRIMARY KEY (`idAluno`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela ensino.aluno: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` (`idAluno`, `nome`, `dataNasc`) VALUES
	(1, 'teste', '2002-03-16'),
	(3, 'vsvsv', '2022-03-03');
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;

-- Copiando estrutura para tabela ensino.escola
DROP TABLE IF EXISTS `escola`;
CREATE TABLE IF NOT EXISTS `escola` (
  `idEscola` int(11) NOT NULL AUTO_INCREMENT,
  `nomeEscola` varchar(50) NOT NULL,
  `logradouro` varchar(50) NOT NULL,
  `complemento` varchar(50) NOT NULL,
  `bairro` varchar(50) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `estado` varchar(50) NOT NULL,
  PRIMARY KEY (`idEscola`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela ensino.escola: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `escola` DISABLE KEYS */;
INSERT INTO `escola` (`idEscola`, `nomeEscola`, `logradouro`, `complemento`, `bairro`, `cidade`, `estado`) VALUES
	(1, 'Escola A', 'Rua 20 de maio', 'predio', 'centro', 'sap', 'pr'),
	(3, 'Escola B', 'ffsv', 'vd', 'svds', 'vdsvd', 'vsdv');
/*!40000 ALTER TABLE `escola` ENABLE KEYS */;

-- Copiando estrutura para tabela ensino.turma
DROP TABLE IF EXISTS `turma`;
CREATE TABLE IF NOT EXISTS `turma` (
  `idTurma` int(11) NOT NULL AUTO_INCREMENT,
  `nomeTurma` varchar(50) NOT NULL,
  `capacidade` int(100) NOT NULL,
  `escolaFK` int(11) NOT NULL,
  `alunoFK` int(11) NOT NULL,
  PRIMARY KEY (`idTurma`),
  KEY `FK_turma_escola` (`escolaFK`),
  KEY `FK_turma_aluno` (`alunoFK`),
  CONSTRAINT `FK_turma_aluno` FOREIGN KEY (`alunoFK`) REFERENCES `aluno` (`idaluno`) ON UPDATE CASCADE,
  CONSTRAINT `FK_turma_escola` FOREIGN KEY (`escolaFK`) REFERENCES `escola` (`idescola`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela ensino.turma: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `turma` DISABLE KEYS */;
INSERT INTO `turma` (`idTurma`, `nomeTurma`, `capacidade`, `escolaFK`, `alunoFK`) VALUES
	(1, 'MANHÃ B', 80, 1, 1);
/*!40000 ALTER TABLE `turma` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
