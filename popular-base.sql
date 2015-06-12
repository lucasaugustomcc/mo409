-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: 11/06/2015 às 10:25
-- Versão do servidor: 5.6.19-0ubuntu0.14.04.1
-- Versão do PHP: 5.5.9-1ubuntu4.9

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de dados: `mo409`
--

--
-- Fazendo dump de dados para tabela `tb_aluno`
--

INSERT INTO `tb_aluno` (`ra_aluno`, `tb_usuario_id_usuario`) VALUES
(10798, 1),
(23060, 2),
(60490, 3),
(74606, 4),
(95584, 5),
(134067, 6),
(143128, 7),
(144406, 8),
(144690, 9),
(149867, 10),
(152472, 11),
(153621, 12),
(159573, 13),
(161248, 14),
(161251, 15),
(161253, 16),
(161255, 17),
(161789, 18),
(162639, 19),
(163125, 20),
(163139, 21),
(163140, 22),
(163144, 23),
(163145, 24),
(163149, 25),
(163152, 26),
(163153, 27),
(178993, 28),
(179412, 29),
(180128, 30),
(180259, 31),
(180260, 32),
(180261, 33),
(962334, 34),
(74219, 36),
(93887, 37),
(95746, 38),
(96952, 39),
(104852, 40),
(105740, 41),
(108330, 42),
(115913, 43),
(116125, 44),
(116245, 45),
(116935, 46),
(117760, 47),
(117856, 48),
(117964, 49),
(118457, 50),
(118787, 51),
(120761, 52),
(121295, 53),
(121405, 54),
(122241, 55),
(122285, 56),
(123198, 57),
(135334, 58),
(135494, 59),
(135551, 60),
(135723, 61),
(136004, 62),
(136323, 63),
(136576, 64),
(136700, 65),
(137748, 66),
(138450, 67),
(138466, 68),
(140604, 69);

--
-- Fazendo dump de dados para tabela `tb_disciplina`
--

INSERT INTO `tb_disciplina` (`id_disciplina`, `nome_disciplina`, `cod_disciplina`) VALUES
(1, 'Engenharia de Software I', 'MO409'),
(2, 'Análise e Projeto de Sistema de Informação', 'MC626');

--
-- Fazendo dump de dados para tabela `tb_professor`
--

INSERT INTO `tb_professor` (`ra_professor`, `tb_usuario_id_usuario`) VALUES
(1, 35);

--
-- Fazendo dump de dados para tabela `tb_turma`
--

INSERT INTO `tb_turma` (`id_turma`, `cod_turma`, `tb_disciplina_id_disciplina`, `ano`, `periodo`) VALUES
(1, 'A', 1, 2015, 1),
(2, 'A', 2, 2015, 1);

--
-- Fazendo dump de dados para tabela `tb_turma_aluno`
--

INSERT INTO `tb_turma_aluno` (`tb_turma_id_turma`, `tb_aluno_ra_aluno`) VALUES
(1, 10798),
(1, 23060),
(1, 60490),
(1, 74606),
(1, 95584),
(1, 134067),
(1, 143128),
(1, 144406),
(1, 144690),
(1, 149867),
(1, 152472),
(1, 153621),
(1, 159573),
(1, 161248),
(1, 161251),
(1, 161253),
(1, 161255),
(1, 161789),
(1, 162639),
(1, 163125),
(1, 163139),
(1, 163140),
(1, 163144),
(1, 163145),
(1, 163149),
(1, 163152),
(1, 163153),
(1, 178993),
(1, 179412),
(1, 180128),
(1, 180259),
(1, 180260),
(1, 180261),
(1, 962334),
(2, 74219),
(2, 93887),
(2, 95746),
(2, 96952),
(2, 104852),
(2, 105740),
(2, 108330),
(2, 115913),
(2, 116125),
(2, 116245),
(2, 116935),
(2, 117760),
(2, 117856),
(2, 117964),
(2, 118457),
(2, 118787),
(2, 120761),
(2, 121295),
(2, 121405),
(2, 122241),
(2, 122285),
(2, 123198),
(2, 135334),
(2, 135494),
(2, 135551),
(2, 135723),
(2, 136004),
(2, 136323),
(2, 136576),
(2, 136700),
(2, 137748),
(2, 138450),
(2, 138466),
(2, 140604);

--
-- Fazendo dump de dados para tabela `tb_turma_professor`
--

INSERT INTO `tb_turma_professor` (`tb_turma_id_turma`, `tb_professor_ra_professor`) VALUES
(1, 1),
(2, 1);

--
-- Fazendo dump de dados para tabela `tb_usuario`
--

INSERT INTO `tb_usuario` (`id_usuario`, `nome`, `email`, `senha`, `papel`) VALUES
(1, 'Daniela Marques', NULL, 'senha', 'aluno'),
(2, 'Amaury Bosso André', NULL, 'senha', 'aluno'),
(3, 'Ewerton Martins de Menezes', 'E060490@dac.unicamp.br', 'senha', 'aluno'),
(4, 'Priscila Aparecida de Moraes', 'P074606@dac.unicamp.br', 'senha', 'aluno'),
(5, 'André Rodrigues Oliveira', 'A095584@dac.unicamp.br', 'senha', 'aluno'),
(6, 'Lise Rommel Romero Navarrete', 'L134067@dac.unicamp.br', 'senha', 'aluno'),
(7, 'Lucas Kannebley Tavares', NULL, 'senha', 'aluno'),
(8, 'Lorena Del Cisne León Quiñonez', 'L144406@dac.unicamp.br', 'senha', 'aluno'),
(9, 'John Edgar Vargas Muñoz', NULL, 'senha', 'aluno'),
(10, 'Iliézer Tamagno', NULL, 'senha', 'aluno'),
(11, 'Lúcia Satiko Nomiso', NULL, 'senha', 'aluno'),
(12, 'Jael Louis Zela Ruiz', NULL, 'senha', 'aluno'),
(13, 'João Luis Villar de Oliveira', NULL, 'senha', 'aluno'),
(14, 'Geise Kelly da Silva Santos', 'G161248@dac.unicamp.br', 'senha', 'aluno'),
(15, 'Leydi Rocio Erazo Paruma', 'L161251@dac.unicamp.br', 'senha', 'aluno'),
(16, 'Jeferson Rech Brunetta', 'J161253@dac.unicamp.br', 'senha', 'aluno'),
(17, 'Lucas Augusto Montalvão Costa Carvalho', 'L161255@dac.unicamp.br', 'senha', 'aluno'),
(18, 'Leandro Tacioli', 'L161789@dac.unicamp.br', 'senha', 'aluno'),
(19, 'Guilherme Henrique Caponetto', 'G162639@dac.unicamp.br', 'senha', 'aluno'),
(20, 'Acauan Cardoso Ribeiro', 'A163125@dac.unicamp.br', 'senha', 'aluno'),
(21, 'Pedro Paulo Libório Lima do Nascimento', 'P163139@dac.unicamp.br', 'senha', 'aluno'),
(22, 'Lucas Carvalho Leal', 'L163140@dac.unicamp.br', 'senha', 'aluno'),
(23, 'Luã Marcelo Muriana', 'L163144@dac.unicamp.br', 'senha', 'aluno'),
(24, 'Ray Dueñas Jiménez', 'R163145@dac.unicamp.br', 'senha', 'aluno'),
(25, 'Judy Carolina Guevara Amaya', 'J163149@dac.unicamp.br', 'senha', 'aluno'),
(26, 'Hernâni Delgado Chantre', 'H163152@dac.unicamp.br', 'senha', 'aluno'),
(27, 'Daniel Matte Freitas', 'D163153@dac.unicamp.br', 'senha', 'aluno'),
(28, 'Jaime Fabian Arias Aguilar', 'J178993@dac.unicamp.br', 'senha', 'aluno'),
(29, 'Bruna Tosi Rodrigues', NULL, 'senha', 'aluno'),
(30, 'Jose Eduardo Schwan Vianna', NULL, 'senha', 'aluno'),
(31, 'Ramos Eduardo Pedro', NULL, 'senha', 'aluno'),
(32, 'Luzizila Salambiaku', NULL, 'senha', 'aluno'),
(33, 'Joaquim Domingos Mussandi', NULL, 'senha', 'aluno'),
(34, 'Fabrício Matheus Gonçalves', 'F962334@dac.unicamp.br', 'senha', 'aluno'),
(35, 'Eliane Martins', NULL, 'senha', 'professor'),
(36, 'Marcelo Pereira Barretto', 'M074219@dac.unicamp.br', 'senha', 'aluno'),
(37, 'Ivan Sichmann Freitas					', 'I093887@dac.unicamp.br', 'senha', 'aluno'),
(38, 'Giovanni Ronnie Almeida					', 'G095746@dac.unicamp.br', 'senha', 'aluno'),
(39, 'Andréia Yukie Uratsuka					', 'A096952@dac.unicamp.br', 'senha', 'aluno'),
(40, 'Erik Iussuke Ebesui						', 'E104852@dac.unicamp.br', 'senha', 'aluno'),
(41, 'Tiago Bember Simeão						', 'T105740@dac.unicamp.br', 'senha', 'aluno'),
(42, 'Leonardo de Oliveira Silva				', 'L108330@dac.unicamp.br', 'senha', 'aluno'),
(43, 'Adalberto Alencar Albuquerque			', 'A115913@dac.unicamp.br', 'senha', 'aluno'),
(44, 'André Luís de Lannoy Coimbra Tavares	', 'A116125@dac.unicamp.br', 'senha', 'aluno'),
(45, 'Bernardo Vecchia Stein					', 'B116245@dac.unicamp.br', 'senha', 'aluno'),
(46, 'Gabriel Massaki Wakano Bezerra			', 'G116935@dac.unicamp.br', 'senha', 'aluno'),
(47, 'Lucas Tadeu Teixeira					', 'L117760@dac.unicamp.br', 'senha', 'aluno'),
(48, 'Lukas Antunes Lopes						', 'L117856@dac.unicamp.br', 'senha', 'aluno'),
(49, 'Maria Júlia Berriel de Sousa			', 'M117964@dac.unicamp.br', 'senha', 'aluno'),
(50, 'Rafael Matias Sacchi					', 'R118457@dac.unicamp.br', 'senha', 'aluno'),
(51, 'Tharine Cohen Macerau					', 'T118787@dac.unicamp.br', 'senha', 'aluno'),
(52, 'Arthur Espindola Ribeiro				', 'A120761@dac.unicamp.br', 'senha', 'aluno'),
(53, 'Rafael Matheus Garcia					', 'R121295@dac.unicamp.br', 'senha', 'aluno'),
(54, 'Vinicius Maciel Sant Ana				', 'V121405@dac.unicamp.br', 'senha', 'aluno'),
(55, 'Amadeu Bonfante							', 'A122241@dac.unicamp.br', 'senha', 'aluno'),
(56, 'Fabio Sartorato							', 'F122285@dac.unicamp.br', 'senha', 'aluno'),
(57, 'Pedro Teixeira Figueiredo				', 'P123198@dac.unicamp.br', 'senha', 'aluno'),
(58, 'Cibelle Begalli							', 'C135334@dac.unicamp.br', 'senha', 'aluno'),
(59, 'Diego Rocha								', 'D135494@dac.unicamp.br', 'senha', 'aluno'),
(60, 'Elisa DellArriva						', 'E135551@dac.unicamp.br', 'senha', 'aluno'),
(61, 'Fernando Henrique dos Santos Gonçalves	', 'F135723@dac.unicamp.br', 'senha', 'aluno'),
(62, 'Gustavo Cesar Nunes						', 'G136004@dac.unicamp.br', 'senha', 'aluno'),
(63, 'Jucélio Evangelista Fonseca				', 'J136323@dac.unicamp.br', 'senha', 'aluno'),
(64, 'Luan Gustavo Bicesto					', 'L136576@dac.unicamp.br', 'senha', 'aluno'),
(65, 'Luciano Pádua Sabença					', 'L136700@dac.unicamp.br', 'senha', 'aluno'),
(66, 'Tomás Silva Queiroga					', 'T137748@dac.unicamp.br', 'senha', 'aluno'),
(67, 'Guilherme da Rocha Alves Mendes			', 'G138450@dac.unicamp.br', 'senha', 'aluno'),
(68, 'Guilherme Sbrolini Mazzariol			', 'G138466@dac.unicamp.br', 'senha', 'aluno'),
(69, 'Felipe Caminada							', 'F140604@dac.unicamp.br', 'senha', 'aluno');
SET FOREIGN_KEY_CHECKS=1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
