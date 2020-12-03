CREATE TABLE `class` (
    `id` int NOT NULL AUTO_INCREMENT,
    `className` varchar(255) NOT NULL,
    `description` varchar(255) NOT NULL,
    `hitDie` int NOT NULL,
    `spellcastingAbility` varchar(12),
   	`version` int UNSIGNED NOT NULL DEFAULT 0, 
	`createdBy` varchar(255) NOT NULL DEFAULT 'admin',
    `updatedBy` varchar(255) NOT NULL DEFAULT 'admin',
    `creationTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updatedTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `subclass` (
    `id` int NOT NULL AUTO_INCREMENT,
	`parentClassId` int NOT NULL,
    `name` varchar(255) NOT NULL,
    `description` varchar(255) NOT NULL,
   	`version` int UNSIGNED NOT NULL DEFAULT 0, 
	`createdBy` varchar(255) NOT NULL DEFAULT 'admin',
    `updatedBy` varchar(255) NOT NULL DEFAULT 'admin',
    `creationTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updatedTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
	CONSTRAINT `fk_subclass_parent_class_id` FOREIGN KEY (`parentClassId`) REFERENCES `class`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `spell` (
  `id` int NOT NULL AUTO_INCREMENT,
  `attackType` varchar(10) DEFAULT NULL,
  `castingTime` varchar(15) DEFAULT NULL,
  `damageType` varchar(15) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `duration` varchar(20) DEFAULT NULL,
  `higherLevel` varchar(500) DEFAULT NULL,
  `indexName` varchar(30) DEFAULT NULL,
  `isMaterial` bit(1) NOT NULL,
  `isRitual` bit(1) NOT NULL,
  `isSomatic` bit(1) NOT NULL,
  `isVerbal` bit(1) NOT NULL,
  `material` varchar(400) DEFAULT NULL,
  `requiresConcentration` bit(1) NOT NULL,
  `spellLevel` int NOT NULL,
  `spellName` varchar(255) DEFAULT NULL,
  `spellRange` varchar(255) DEFAULT NULL,
  `version` int unsigned NOT NULL DEFAULT '0',
  `createdBy` varchar(255) NOT NULL DEFAULT 'admin',
  `updatedBy` varchar(255) NOT NULL DEFAULT 'admin',
  `creationTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

CREATE TABLE `class_spell_list` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parentClassId` int DEFAULT NULL,
  `subclassId` int DEFAULT NULL,
  `version` int unsigned NOT NULL DEFAULT '0',
  `createdBy` varchar(255) NOT NULL DEFAULT 'admin',
  `updatedBy` varchar(255) NOT NULL DEFAULT 'admin',
  `creationTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `spellId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_class_spell_list_parent_class_id` (`parentClassId`),
  KEY `index_class_spell_list_subclass_id` (`subclassId`),
  KEY `index_class_spell_list_spell_id` (`spellId`),
  CONSTRAINT `fk_class_spell_list_spell_id` FOREIGN KEY (`spellId`) REFERENCES `spell` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_class_spell_list_parent_class_id` FOREIGN KEY (`parentClassId`) REFERENCES `class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_class_spell_list_subclass_id` FOREIGN KEY (`subclassId`) REFERENCES `subclass` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;