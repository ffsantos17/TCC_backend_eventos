-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 28/08/2024 às 02:52
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `projeto_ifs`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `alerta`
--

CREATE TABLE `alerta` (
  `alerta_id` int(11) NOT NULL,
  `lista_item_tipoAlerta_id` int(11) NOT NULL,
  `alerta_descricao` varchar(500) NOT NULL,
  `alerta_usuarioCriacao_id` int(11) NOT NULL,
  `alerta_dataCriacao` datetime NOT NULL,
  `alerta_tabela` varchar(100) NOT NULL,
  `alerta_tabelaRegistro_id` int(11) NOT NULL,
  `alerta_lido` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `alerta`
--

INSERT INTO `alerta` (`alerta_id`, `lista_item_tipoAlerta_id`, `alerta_descricao`, `alerta_usuarioCriacao_id`, `alerta_dataCriacao`, `alerta_tabela`, `alerta_tabelaRegistro_id`, `alerta_lido`) VALUES
(1, 7, 'Documento incorreto, favor anexar o correto', 32, '2024-08-22 10:33:52', 'usuario_r_documento', 21, 1),
(2, 7, 'teste', 32, '2024-08-22 15:21:24', 'usuario_r_documento', 21, 1),
(3, 7, 'TextEditingController#de382(TextEditingValue(text: ┤Alerta teste 2├, selection: TextSelection.collapsed(offset: 14, affinity: TextAffinity.downstream, isDirectional: false), composing: TextRange(start: -1, end: -1)))', 32, '2024-08-22 15:28:00', 'usuario_r_documento', 21, 1),
(4, 7, 'TextEditingController#51eec(TextEditingValue(text: ┤Alerta teste 3├, selection: TextSelection.collapsed(offset: 14, affinity: TextAffinity.downstream, isDirectional: false), composing: TextRange(start: -1, end: -1)))', 32, '2024-08-22 15:28:29', 'usuario_r_documento', 21, 1),
(5, 7, 'Alerta teste 6', 32, '2024-08-22 15:28:55', 'usuario_r_documento', 21, 1),
(6, 7, 'Teste 6', 32, '2024-08-22 17:08:18', 'usuario_r_documento', 9, 1),
(7, 7, 'Teste 7', 32, '2024-08-22 17:08:21', 'usuario_r_documento', 10, 1),
(8, 7, 'Teste 8', 32, '2024-08-22 17:08:24', 'usuario_r_documento', 11, 1),
(9, 7, '1', 32, '2024-08-22 17:57:33', 'usuario_r_documento', 17, 1),
(10, 7, '2', 32, '2024-08-22 17:57:35', 'usuario_r_documento', 18, 1),
(11, 7, '3', 32, '2024-08-22 17:57:39', 'usuario_r_documento', 19, 1),
(12, 7, '5', 32, '2024-08-22 17:57:42', 'usuario_r_documento', 20, 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `documento`
--

CREATE TABLE `documento` (
  `documento_id` int(11) NOT NULL,
  `documento_modelo` varchar(255) DEFAULT NULL,
  `documento_nome` varchar(255) NOT NULL,
  `documento_possui_modelo` bit(1) NOT NULL,
  `documento_data_criacao` datetime(6) NOT NULL,
  `documento_excluido` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `documento`
--

INSERT INTO `documento` (`documento_id`, `documento_modelo`, `documento_nome`, `documento_possui_modelo`, `documento_data_criacao`, `documento_excluido`) VALUES
(3, '', 'Copia RG/CPF', b'0', '2024-05-21 22:40:53.000000', b'0'),
(4, NULL, 'Cartão Vacina', b'0', '2024-07-01 11:14:57.000000', b'0'),
(5, '', 'Autorização assinada por responsavel', b'0', '2024-07-01 11:15:01.000000', b'0'),
(6, NULL, 'Comprovante de matricula', b'0', '2024-07-25 11:15:07.000000', b'0'),
(18, '', 'Testeee', b'0', '2024-08-13 16:04:47.000000', b'0'),
(19, '', 'teste', b'0', '2024-08-13 16:05:18.000000', b'0'),
(20, '', 'teste', b'0', '2024-08-13 16:06:15.000000', b'0'),
(21, '', 'test5', b'0', '2024-08-13 16:06:20.000000', b'0'),
(22, '', 'Teste66', b'0', '2024-08-13 16:07:30.000000', b'0'),
(23, '', 'Teste777', b'0', '2024-08-13 16:08:21.000000', b'0'),
(24, '145e2932-3d98-4624-a057-7ea6b69e5bd9_2024-08-14 104327392.pdf', 'TReste', b'1', '2024-08-14 10:45:51.000000', b'0'),
(25, '33c7ca0c-b321-420c-a1f8-7670510997d4_2024-08-14 104636009.pdf', 'TesteUploa', b'1', '2024-08-14 10:46:43.000000', b'0'),
(26, '6ee2f65c-65f4-435b-9086-dd6fe3e553c5_2024-08-14 122120540.docx', 'Doc Word', b'1', '2024-08-14 12:21:20.000000', b'0'),
(27, '84f78250-692a-4cbc-a8b4-969853e40f0d_2024-08-14 141919369.pdf', '55555', b'1', '2024-08-14 14:19:19.000000', b'0'),
(28, 'd1c77990-0b03-4e96-8c15-14e0308969ee_2024-08-14 142000789.png', 'IMG', b'1', '2024-08-14 14:20:00.000000', b'0'),
(48, NULL, 'Tetesssssss', b'0', '2024-08-15 23:56:30.000000', b'0');

-- --------------------------------------------------------

--
-- Estrutura para tabela `evento`
--

CREATE TABLE `evento` (
  `evento_id` int(11) NOT NULL,
  `evento_link` varchar(255) DEFAULT NULL,
  `evento_data` date DEFAULT NULL,
  `evento_dataFim` date DEFAULT NULL,
  `evento_data_criacao` datetime(2) DEFAULT NULL,
  `evento_id_usuario_criacao` int(11) DEFAULT NULL,
  `evento_linkepublico` bit(1) DEFAULT NULL,
  `evento_nome` varchar(255) DEFAULT NULL,
  `evento_vagas` int(11) DEFAULT NULL,
  `evento_imagem` varchar(255) NOT NULL,
  `evento_descricao` longtext DEFAULT NULL,
  `evento_visitas` int(200) NOT NULL DEFAULT 0,
  `evento_local` mediumtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `evento`
--

INSERT INTO `evento` (`evento_id`, `evento_link`, `evento_data`, `evento_dataFim`, `evento_data_criacao`, `evento_id_usuario_criacao`, `evento_linkepublico`, `evento_nome`, `evento_vagas`, `evento_imagem`, `evento_descricao`, `evento_visitas`, `evento_local`) VALUES
(2, 'www.teste.com', '2024-09-18', '2024-09-25', '2023-12-31 00:00:00.00', 1, b'0', 'Evento', 2, 'u6f0l95c-e594-4cef-abfb-5d4629d5f73d_2024-08-14 171211803.jpg', NULL, 11, 'Lagarto-SE'),
(3, 'www.teste.com', '2024-08-24', '2024-08-29', '2023-12-31 21:00:00.00', 1, b'0', 'Evento Teste', 100, 'i6f0u95c-e594-4cef-abfb-5d4629d5f73d_2024-08-14 171211803.jpg', NULL, 78, 'Recife-PE'),
(4, 'www.teste.com', '2024-06-24', '2024-06-29', '2023-12-31 21:00:00.00', 1, b'0', 'Evento Teste 4', 100, 'c6f0g95c-e594-4cef-abfb-5d4629d5f73d_2024-08-14 171211803.jpg', NULL, 0, 'Rio de Janeiro-RJ'),
(5, 'www.teste.com', '2024-08-24', '2024-08-29', '2023-12-31 21:00:00.00', 1, b'0', 'Evento Teste 5', 100, 'c6f0g95c-e594-4cef-abfb-5d4629d5f73d_2024-08-14 171211803.jpg', 'Evento de Teste para a criação do sistema', 81, 'Auditorio IFS- Campus Lagarto'),
(6, 'www.teste.com', '2024-08-24', '2024-08-29', '2023-12-31 21:00:00.00', 1, b'0', 'Evento Teste 6', 100, 'c6f0g95c-e594-4cef-abfb-5d4629d5f73d_2024-08-14 171211803.jpg', NULL, 8, 'IFS - Campus Aracaju'),
(7, 'www.teste.com', '2024-08-24', '2024-08-29', '2023-12-31 21:00:00.00', 1, b'0', 'Evento Teste 7', 100, 'c6f0g95c-e594-4cef-abfb-5d4629d5f73d_2024-08-14 171211803.jpg', NULL, 21, 'Instituto Federal da Bahia'),
(8, NULL, '2024-08-20', '2024-08-30', '2024-08-14 16:43:00.00', 32, b'1', 'Teste', 11, 'c6f0g95c-e594-4cef-abfb-5d4629d5f73d_2024-08-14 171211803.jpg', NULL, 13, 'teste'),
(9, NULL, '2024-08-15', '2024-08-29', '2024-08-14 16:58:54.00', 32, b'1', 'Evento Teste', 100, 'u6f0l95c-e594-4cef-abfb-5d4629d5f73d_2024-08-14 171211803.jpg', NULL, 7, 'Local teste'),
(10, NULL, '2024-08-15', '2024-08-29', '2024-08-14 17:02:01.00', 32, b'1', 'Evento Teste', 100, 'a9cde88f-00cf-4d80-82a1-c6aeb12c7276_2024-08-14 170157346.png', NULL, 0, 'Local teste'),
(11, NULL, '2024-08-15', '2024-08-29', '2024-08-14 17:12:26.00', 32, b'1', 'Evento teste 2', 22, 'c6f0g95c-e594-4cef-abfb-5d4629d5f73d_2024-08-14 171211803.jpg', NULL, 2, 'Evento teste 2');

-- --------------------------------------------------------

--
-- Estrutura para tabela `evento_r_documento`
--

CREATE TABLE `evento_r_documento` (
  `evento_r_documento_id` int(11) NOT NULL,
  `evento_id` int(11) NOT NULL,
  `documento_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `evento_r_documento`
--

INSERT INTO `evento_r_documento` (`evento_r_documento_id`, `evento_id`, `documento_id`) VALUES
(1, 5, 3),
(2, 5, 4),
(3, 5, 5),
(4, 5, 6),
(5, 3, 3),
(6, 9, 22),
(7, 9, 23),
(8, 9, 24),
(9, 9, 25),
(10, 9, 26),
(11, 10, 22),
(12, 10, 23),
(13, 10, 24),
(14, 10, 25),
(15, 10, 26),
(16, 11, 24),
(17, 11, 25),
(18, 11, 26);

-- --------------------------------------------------------

--
-- Estrutura para tabela `evento_r_usuario`
--

CREATE TABLE `evento_r_usuario` (
  `evento_r_usuario_id` int(11) NOT NULL,
  `evento_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `lista_item_tipoInscricao_id` int(11) NOT NULL,
  `lista_item_statusInscricao_id` int(11) NOT NULL,
  `status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `evento_r_usuario`
--

INSERT INTO `evento_r_usuario` (`evento_r_usuario_id`, `evento_id`, `usuario_id`, `lista_item_tipoInscricao_id`, `lista_item_statusInscricao_id`, `status`) VALUES
(1, 2, 41, 3, 4, 'aprovado'),
(2, 3, 48, 3, 4, 'aprovado'),
(3, 4, 41, 3, 5, 'pendente'),
(4, 5, 41, 3, 6, 'pendente'),
(5, 2, 42, 3, 4, 'aprovado'),
(19, 5, 48, 3, 4, 'pendente'),
(24, 9, 48, 3, 5, 'pendente'),
(25, 2, 32, 1, 4, 'aprovado'),
(26, 3, 32, 1, 4, 'aprovado'),
(27, 4, 32, 1, 4, 'aprovado'),
(28, 5, 32, 1, 4, 'aprovado'),
(29, 6, 32, 1, 4, 'aprovado'),
(30, 7, 32, 1, 4, 'aprovado'),
(31, 8, 32, 1, 4, 'aprovado'),
(32, 9, 32, 1, 4, 'aprovado'),
(33, 10, 32, 1, 4, 'aprovado'),
(34, 11, 32, 1, 4, 'aprovado'),
(35, 5, 41, 3, 4, 'aprovado'),
(36, 5, 42, 3, 4, 'aprovado'),
(37, 5, 33, 3, 4, 'aprovado'),
(38, 5, 34, 3, 4, 'aprovado');

-- --------------------------------------------------------

--
-- Estrutura para tabela `lista`
--

CREATE TABLE `lista` (
  `lista_id` int(11) NOT NULL,
  `lista_nome` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `lista`
--

INSERT INTO `lista` (`lista_id`, `lista_nome`) VALUES
(1, 'Tipo Inscrição'),
(2, 'Status Inscrição'),
(3, 'Tipo Alerta');

-- --------------------------------------------------------

--
-- Estrutura para tabela `lista_item`
--

CREATE TABLE `lista_item` (
  `lista_item_id` int(11) NOT NULL,
  `lista_id` int(11) NOT NULL,
  `lista_item_nome` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `lista_item`
--

INSERT INTO `lista_item` (`lista_item_id`, `lista_id`, `lista_item_nome`) VALUES
(1, 1, 'Criador'),
(2, 1, 'Colaborador'),
(3, 1, 'Participante'),
(4, 2, 'Aprovado'),
(5, 2, 'Pendente'),
(6, 2, 'Cancelado'),
(7, 3, 'Documento com pendência');

-- --------------------------------------------------------

--
-- Estrutura para tabela `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `tipo_usuario_id` int(11) NOT NULL,
  `tipo_usuario_nome` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`tipo_usuario_id`, `tipo_usuario_nome`) VALUES
(1, 'admin'),
(2, 'aluno'),
(3, 'professor');

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE `usuario` (
  `usuario_id` int(11) NOT NULL,
  `usuario_cpf` varchar(255) DEFAULT NULL,
  `usuario_email` varchar(255) DEFAULT NULL,
  `usuario_matricula` varchar(255) DEFAULT NULL,
  `usuario_nome` varchar(255) DEFAULT NULL,
  `usuario_Senha` varchar(100) NOT NULL,
  `tipo_usuario_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuario`
--

INSERT INTO `usuario` (`usuario_id`, `usuario_cpf`, `usuario_email`, `usuario_matricula`, `usuario_nome`, `usuario_Senha`, `tipo_usuario_id`) VALUES
(30, '111111111111', 'felipe@teste.com', '2016186631203771', 'Felipe', '123', 1),
(32, '111111111111', 'professor@teste.com', '2016186631203771', 'Felipe', '$2a$10$LPWCvdzVy2eZ00aNUIIaU.A2fTTlLmvX5k6VWrJDUhJYDpefdG3Ku', 3),
(33, '111111111111', 'felipe3@teste.com', '2016186631203771', 'Felipe', '123', 2),
(34, '111111111111', 'felipe6@teste.com', '2016186631203771', 'Felipe', '$2a$10$WiuPE0WYU1/kdCaibUWewugtBEpJ/P12yDjjfvY/xuhp9Plua/5/6', 2),
(41, '111111111111', 'felipe7@teste.com', '2016186631203771', 'Felipe', '$2a$10$UJHNQyszaDn/VztRZkAa3.bOmohbYLDXRgRnebosryQVplACCpZ..', 2),
(42, '111111111111', 'felipe8@teste.com', '2016186631203771', 'Felipe', '$2a$10$GBKMV/2HZp3DzMqGgkrgoerNwVnH68znAzNaRcgVky7aHJzbgAlBa', 1),
(48, '05956867531', 'ffsantos17@gmail.com', '20161863120377', 'Felipe Fontes Santos', '$2a$10$LPWCvdzVy2eZ00aNUIIaU.A2fTTlLmvX5k6VWrJDUhJYDpefdG3Ku', 2);

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario_r_documento`
--

CREATE TABLE `usuario_r_documento` (
  `id` int(11) NOT NULL,
  `evento_r_usuario_id` int(11) NOT NULL,
  `evento_r_documento_id` int(11) NOT NULL,
  `entregue` bit(1) NOT NULL,
  `anexo_Nome` varchar(110) DEFAULT NULL,
  `anexo_visualizado` tinyint(1) NOT NULL DEFAULT 0,
  `anexo_Data` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuario_r_documento`
--

INSERT INTO `usuario_r_documento` (`id`, `evento_r_usuario_id`, `evento_r_documento_id`, `entregue`, `anexo_Nome`, `anexo_visualizado`, `anexo_Data`) VALUES
(5, 17, 1, b'1', 'Avaliação 1º Bimestre - Computação Inteligente.pdf', 0, '2024-08-07 09:39:57'),
(6, 17, 2, b'0', '0', 0, NULL),
(7, 17, 3, b'0', '0', 0, NULL),
(8, 17, 4, b'0', '0', 0, NULL),
(9, 19, 1, b'1', '9_681665798_2024-08-14 093818324.pdf', 1, '2024-08-14 09:38:18'),
(10, 19, 2, b'1', '10_38490058_2024-08-15 000746902.pdf', 1, '2024-08-15 00:07:47'),
(11, 19, 3, b'1', '11_125569335_2024-08-15 000937714.pdf', 1, '2024-08-15 00:09:37'),
(12, 19, 4, b'1', '12_651931337_2024-08-21 233336024.pdf', 1, '2024-08-21 23:33:36'),
(13, 21, 1, b'0', '0', 0, NULL),
(14, 21, 2, b'0', '0', 0, NULL),
(15, 21, 3, b'0', '0', 0, NULL),
(16, 21, 4, b'0', '0', 0, NULL),
(17, 24, 6, b'1', '17_155799413_2024-08-15 001217317.pdf', 1, '2024-08-15 00:12:17'),
(18, 24, 7, b'1', '18_370335800_2024-08-22 094010478.pdf', 1, '2024-08-22 09:40:10'),
(19, 24, 8, b'1', '19_333800410_2024-08-22 094013888.pdf', 1, '2024-08-22 09:40:13'),
(20, 24, 9, b'1', '20_655058796_2024-08-22 094017060.pdf', 1, '2024-08-22 09:40:17'),
(21, 24, 10, b'1', '21_409786935_2024-08-22 094019944.pdf', 1, '2024-08-22 09:40:19');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `alerta`
--
ALTER TABLE `alerta`
  ADD PRIMARY KEY (`alerta_id`);

--
-- Índices de tabela `documento`
--
ALTER TABLE `documento`
  ADD PRIMARY KEY (`documento_id`);

--
-- Índices de tabela `evento`
--
ALTER TABLE `evento`
  ADD PRIMARY KEY (`evento_id`);

--
-- Índices de tabela `evento_r_documento`
--
ALTER TABLE `evento_r_documento`
  ADD PRIMARY KEY (`evento_r_documento_id`);

--
-- Índices de tabela `evento_r_usuario`
--
ALTER TABLE `evento_r_usuario`
  ADD PRIMARY KEY (`evento_r_usuario_id`);

--
-- Índices de tabela `lista`
--
ALTER TABLE `lista`
  ADD PRIMARY KEY (`lista_id`);

--
-- Índices de tabela `lista_item`
--
ALTER TABLE `lista_item`
  ADD PRIMARY KEY (`lista_item_id`);

--
-- Índices de tabela `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  ADD PRIMARY KEY (`tipo_usuario_id`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`usuario_id`);

--
-- Índices de tabela `usuario_r_documento`
--
ALTER TABLE `usuario_r_documento`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `alerta`
--
ALTER TABLE `alerta`
  MODIFY `alerta_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de tabela `documento`
--
ALTER TABLE `documento`
  MODIFY `documento_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT de tabela `evento`
--
ALTER TABLE `evento`
  MODIFY `evento_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de tabela `evento_r_documento`
--
ALTER TABLE `evento_r_documento`
  MODIFY `evento_r_documento_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de tabela `evento_r_usuario`
--
ALTER TABLE `evento_r_usuario`
  MODIFY `evento_r_usuario_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT de tabela `lista`
--
ALTER TABLE `lista`
  MODIFY `lista_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `lista_item`
--
ALTER TABLE `lista_item`
  MODIFY `lista_item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  MODIFY `tipo_usuario_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `usuario_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT de tabela `usuario_r_documento`
--
ALTER TABLE `usuario_r_documento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FKe581tp719p3d7o5u2w9sre10b` FOREIGN KEY (`tipo_usuario_id`) REFERENCES `tipo_usuario` (`tipo_usuario_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
