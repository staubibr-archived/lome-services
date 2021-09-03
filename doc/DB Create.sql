CREATE DATABASE `LoM`

CREATE TABLE `contributors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `affiliation` varchar(255) DEFAULT NULL,
  `creation_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

CREATE TABLE `model_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` enum('Atomic','Coupled','Top') NOT NULL,
  `formalism` enum('DEVS','Cell-DEVS','GIS-DEVS') NOT NULL,
  `simulator` enum('Cadmium') NOT NULL,
  `description` varchar(4096) NOT NULL,
  `date_created` date NOT NULL,
  `author` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_model_type_author` (`author`),
  CONSTRAINT `fk_model_type_author` FOREIGN KEY (`author`) REFERENCES `contributors` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

CREATE TABLE `experiments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(4096) NOT NULL,
  `date_created` date NOT NULL,
  `author` bigint(20) DEFAULT NULL,
  `top_model_type` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_experiment_author` (`author`),
  KEY `fk_experiment_top_model_type` (`top_model_type`),
  CONSTRAINT `fk_experiment_author` FOREIGN KEY (`author`) REFERENCES `contributors` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_experiment_top_model_type` FOREIGN KEY (`top_model_type`) REFERENCES `model_types` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE `file_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `extension` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

CREATE TABLE `tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

CREATE TABLE `files` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `file_type_id` bigint(20) NOT NULL,
  `last_modification` date NOT NULL,
  `last_author` bigint(20) DEFAULT NULL,
  `path` varchar(255) NOT NULL,
  `model_type_id` bigint(20) DEFAULT NULL,
  `document_id` bigint(20) DEFAULT NULL,
  `experiment_id` bigint(20) DEFAULT NULL,
  `raw_result_id` bigint(20) DEFAULT NULL,
  `converted_result_id` bigint(20) DEFAULT NULL,
  `visualization_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_files_file_type` (`file_type_id`),
  KEY `fk_files_model_type` (`model_type_id`),
  KEY `fk_files_document` (`document_id`),
  KEY `fk_files_experiment` (`experiment_id`),
  KEY `fk_files_raw_result` (`raw_result_id`),
  KEY `fk_files_converted_result` (`converted_result_id`),
  KEY `fk_files_visualization` (`visualization_id`),
  CONSTRAINT `fk_files_converted_result` FOREIGN KEY (`converted_result_id`) REFERENCES `experiments` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_files_document` FOREIGN KEY (`document_id`) REFERENCES `experiments` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_files_experiment` FOREIGN KEY (`experiment_id`) REFERENCES `experiments` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_files_file_type` FOREIGN KEY (`file_type_id`) REFERENCES `file_types` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_files_model_type` FOREIGN KEY (`model_type_id`) REFERENCES `model_types` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_files_raw_result` FOREIGN KEY (`raw_result_id`) REFERENCES `experiments` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_files_visualization` FOREIGN KEY (`visualization_id`) REFERENCES `experiments` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `nn_experiment_v_visualization` (
  `experiment_id` bigint(20) NOT NULL,
  `visualization_id` bigint(20) NOT NULL,
  KEY `fk_nn_experiment_v_visualization_experiment` (`experiment_id`),
  KEY `fk_nn_experiment_v_visualization_visualization` (`visualization_id`),
  CONSTRAINT `fk_nn_experiment_v_visualization_experiment` FOREIGN KEY (`experiment_id`) REFERENCES `experiments` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_nn_experiment_v_visualization_visualization` FOREIGN KEY (`visualization_id`) REFERENCES `files` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `nn_model_types_v_tags` (
  `model_type_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  KEY `fk_nn_model_types_v_tags_model_type` (`model_type_id`),
  KEY `fk_nn_model_types_v_tags_tag` (`tag_id`),
  CONSTRAINT `fk_nn_model_types_v_tags_model_type` FOREIGN KEY (`model_type_id`) REFERENCES `model_types` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_nn_model_types_v_tags_tag` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;