-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema distancingdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `distancingdb` ;

-- -----------------------------------------------------
-- Schema distancingdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `distancingdb` DEFAULT CHARACTER SET utf8 ;
USE `distancingdb` ;

-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

CREATE TABLE IF NOT EXISTS `location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(100) NOT NULL,
  `city` VARCHAR(100) NOT NULL,
  `state` VARCHAR(50) NOT NULL,
  `country` VARCHAR(45) NULL,
  `postal_code` VARCHAR(10) NULL,
  `title` VARCHAR(100) NULL,
  `location_url` VARCHAR(3000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT '1',
  `location_id` INT NOT NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(3000) NULL,
  `role` VARCHAR(45) NULL DEFAULT 'user',
  `create_date` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_location_idx` (`location_id` ASC),
  CONSTRAINT `fk_user_location`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(100) NOT NULL,
  `short_description` VARCHAR(100) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT '1',
  `description` TEXT NULL,
  `image_url` VARCHAR(3000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `activity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activity` ;

CREATE TABLE IF NOT EXISTS `activity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `short_description` VARCHAR(300) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT '1',
  `creator_id` INT NOT NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(3000) NULL,
  `equipment_level` ENUM('Low', 'Medium', 'High') NULL,
  `equipment_description` TEXT NULL,
  `create_date` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_activity_user_idx` (`creator_id` ASC),
  CONSTRAINT `fk_activity_user`
    FOREIGN KEY (`creator_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resource` ;

CREATE TABLE IF NOT EXISTS `resource` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` TEXT NOT NULL,
  `link` VARCHAR(3000) NULL,
  `image_url` VARCHAR(3000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_group` ;

CREATE TABLE IF NOT EXISTS `user_group` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `short_description` VARCHAR(300) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT '1',
  `creator_id` INT NOT NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(3000) NULL,
  `create_date` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_group_user_creator_idx` (`creator_id` ASC),
  CONSTRAINT `fk_group_user_creator`
    FOREIGN KEY (`creator_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `group_member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `group_member` ;

CREATE TABLE IF NOT EXISTS `group_member` (
  `user_id` INT NOT NULL,
  `group_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `group_id`),
  INDEX `fk_user_has_group_group1_idx` (`group_id` ASC),
  INDEX `fk_user_has_group_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_group_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_group_group1`
    FOREIGN KEY (`group_id`)
    REFERENCES `user_group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `activity_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activity_location` ;

CREATE TABLE IF NOT EXISTS `activity_location` (
  `activity_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  PRIMARY KEY (`activity_id`, `location_id`),
  INDEX `fk_activity_has_location_location1_idx` (`location_id` ASC),
  INDEX `fk_activity_has_location_activity1_idx` (`activity_id` ASC),
  CONSTRAINT `fk_activity_has_location_activity1`
    FOREIGN KEY (`activity_id`)
    REFERENCES `activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_activity_has_location_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `activity_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activity_category` ;

CREATE TABLE IF NOT EXISTS `activity_category` (
  `activity_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`activity_id`, `category_id`),
  INDEX `fk_activity_has_category_category1_idx` (`category_id` ASC),
  INDEX `fk_activity_has_category_activity1_idx` (`activity_id` ASC),
  CONSTRAINT `fk_activity_has_category_activity1`
    FOREIGN KEY (`activity_id`)
    REFERENCES `activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_activity_has_category_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `activity_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activity_comment` ;

CREATE TABLE IF NOT EXISTS `activity_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NOT NULL,
  `create_date` DATETIME NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT '1',
  `user_id` INT NOT NULL,
  `activity_id` INT NOT NULL,
  `in_reply_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_activity_comment_user1_idx` (`user_id` ASC),
  INDEX `fk_activity_comment_activity1_idx` (`activity_id` ASC),
  INDEX `fk_activity_comment_reply_idx` (`in_reply_id` ASC),
  CONSTRAINT `fk_activity_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_activity_comment_activity1`
    FOREIGN KEY (`activity_id`)
    REFERENCES `activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_activity_comment_reply`
    FOREIGN KEY (`in_reply_id`)
    REFERENCES `activity_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `activity_resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activity_resource` ;

CREATE TABLE IF NOT EXISTS `activity_resource` (
  `activity_id` INT NOT NULL,
  `resource_id` INT NOT NULL,
  PRIMARY KEY (`activity_id`, `resource_id`),
  INDEX `fk_activity_has_resource_resource1_idx` (`resource_id` ASC),
  INDEX `fk_activity_has_resource_activity1_idx` (`activity_id` ASC),
  CONSTRAINT `fk_activity_has_resource_activity1`
    FOREIGN KEY (`activity_id`)
    REFERENCES `activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_activity_has_resource_resource1`
    FOREIGN KEY (`resource_id`)
    REFERENCES `resource` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event` ;

CREATE TABLE IF NOT EXISTS `event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `creator_id` INT NOT NULL,
  `activity_id` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `event_time` TIME NOT NULL,
  `event_date` DATE NOT NULL,
  `short_description` VARCHAR(300) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT '1',
  `location_id` INT NOT NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(3000) NULL,
  `create_date` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_location_idx` (`location_id` ASC),
  INDEX `fk_event_activity_idx` (`activity_id` ASC),
  INDEX `fk_event_creator_idx` (`creator_id` ASC),
  CONSTRAINT `fk_event_creator`
    FOREIGN KEY (`creator_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_activity`
    FOREIGN KEY (`activity_id`)
    REFERENCES `activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_location`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event_comment` ;

CREATE TABLE IF NOT EXISTS `event_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NOT NULL,
  `create_date` DATETIME NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT '1',
  `user_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `in_reply_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_comment_user_idx` (`user_id` ASC),
  INDEX `fk_event_comment_event_idx` (`event_id` ASC),
  INDEX `fk_event_comment_reply_id_idx` (`in_reply_id` ASC),
  CONSTRAINT `fk_event_comment_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_comment_event`
    FOREIGN KEY (`event_id`)
    REFERENCES `event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_comment_reply_id`
    FOREIGN KEY (`in_reply_id`)
    REFERENCES `event_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event_photo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event_photo` ;

CREATE TABLE IF NOT EXISTS `event_photo` (
  `id` INT NOT NULL,
  `image_url` VARCHAR(500) NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_photos_event1_idx` (`event_id` ASC),
  CONSTRAINT `fk_event_photos_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_event` ;

CREATE TABLE IF NOT EXISTS `user_event` (
  `user_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `rating` INT NULL,
  `rating_comment` TEXT NULL,
  PRIMARY KEY (`user_id`, `event_id`),
  INDEX `fk_user_has_event_event1_idx` (`event_id` ASC),
  INDEX `fk_user_has_event_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_event_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_event_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `favorites_list`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `favorites_list` ;

CREATE TABLE IF NOT EXISTS `favorites_list` (
  `user_id` INT NOT NULL,
  `activity_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `activity_id`),
  INDEX `fk_user_has_activity_activity1_idx` (`activity_id` ASC),
  INDEX `fk_user_has_activity_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_activity_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_activity_activity1`
    FOREIGN KEY (`activity_id`)
    REFERENCES `activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS user@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'user'@'localhost' IDENTIFIED BY 'user';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'user'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `location`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (1, '7400 E Orchard Rd', 'Greenwood Village', 'Colorado', 'United States', '80111', 'Skill Distillery', 'https://skilldistillery.com/');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (2, '2710 S Colorado Blvd', 'Denver', 'Colorado', 'United States', '80222', '', '');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (3, '430 S Colorado Blvd', 'Denver', 'Colorado', 'United States', '80246', '', '');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (4, '5600 S Parker Rd', 'Aurora', 'Colorado', 'United States', '80015', '', '');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (5, '7785 E Hampden Ave', 'Denver', 'Colorado', 'United States', '80231', '', '');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (6, '9335 Crown Crest Blvd', 'Parker', 'Colorado', 'United States', '80138', '', '');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (7, '391 Spectrum Loop', 'Colorado Springs ', 'Colorado', 'United States', '80921', '', '');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (8, '14730 W Colfax Ave', 'Lakewood', 'Colorado', 'United States', '80401', '', '');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (9, '7990 N Academy Blvd', 'Colorado Springs ', 'Colorado', 'United States', '80920', '', '');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (10, '9091 Westview Rd', 'Lone Tree', 'Colorado', 'United States', '80124', '', '');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (11, '14310 E Alameda Ave', 'Aurora', 'Colorado', 'United States', '80012', '', '');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (12, '1198 S Havana St', 'Aurora', 'Colorado', 'United States', '80012', '', '');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (13, '13401 Picadilly Rd', 'Brighton', 'Colorado', 'United States', '80603', 'Barr Lake', 'https://cpw.state.co.us/placestogo/parks/barrlake');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (14, '1375 W. Plum Creek Parkway', 'Castle Rock', 'Colorado', 'United States', '80109', 'Philip S. Miller Park', 'https://www.crgov.com/2051/Philip-S-Miller-Park');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (15, '7101 W 38th Avenue', 'Wheat Ridge', 'Colorado', 'United States', '80033', 'Ridge at 38', 'https://ridgeat38.com/criterium/');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (16, '1855 Ski Time Square Dr', 'Steamboat Springs', 'Colorado', 'United States', '80487', 'Resort Lodging Company', 'https://resortlodgingcompany.com/about/');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (17, '1532 Bergen Parkway', 'Evergreen', 'Colorado', 'United States', '80439', 'Blue Quill Angler', 'https://bluequillangler.com');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (18, '18-2 Lory Park Rd', 'Bellvue', 'Colorado', 'United States', '80512', 'Lory Park', 'https://cpw.state.co.us/placestogo/parks/Lory');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (1, 'kissmyaxe', '$2a$10$7oiSLTSDtWVCx.v1S.rnouMsfYrQQnLP4tXwMQRRa.qN38gLjaovy', 'p.smith@sd.com', 'Peggy', 'Smith', 1, 2, ' Freelance internet buff. Amateur introvert. Writer. Web nerd. Travel aficionado.', 'https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/GettyImages-1158622531_thumb.jpg', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (2, 'shaquilleoatmeal', '$2a$10$tJr5y7Ouq5uMAbSTjkqmvO1ORbBE6v51a3pB2uUagqx57Eb4tKdpy', 'c.mcgregor@sd.com', 'Clayton', 'McGregor', 1, 3, 'Music geek. Social media enthusiast. Student. Professional twitter fanatic. Web advocate.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Outdoors-man-portrait_%28cropped%29.jpg/1200px-Outdoors-man-portrait_%28cropped%29.jpg', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (3, 'desperateenuf', '$2a$10$TUJRZqlvE0CIepACihr69uSNMsEiqV8YG1QejHb0SJnLuCR7sTwWm', 'b.bailey@sd.com', 'Brian', 'Bailey', 1, 4, 'Bacon enthusiast. Professional troublemaker. Lifelong music scholar. Unapologetic twitter advocate.', 'https://media.gettyimages.com/photos/what-more-can-a-girl-ask-for-picture-id692879918?b=1&k=6&m=692879918&s=612x612&w=0&h=uc4HrCahufEOMHYbRxjsESauxoJhzYkyzFPq0YZtxSo=', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (4, 'billnyetherussianspy', '$2a$10$YNQUag/CW9BzPFVoJzEg.eOWiLjjo3pqf3nNGrfgpkvVzsXyio4mm', 'j.haley@sd.com', 'Julie', 'Haley', 1, 5, 'Analyst. Student. Tv nerd. Unapologetic music specialist. Freelance coffee maven.', 'https://secure.gravatar.com/avatar/81c8847e3d52f5cab58ce0a0b923f11f?s=400&d=mm&r=g', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (5, 'hoosierdaddy', '$2a$10$pX5k47keymlboXxeA5yYFO.Sdbydr2BfVO2p6HQzMIld6AjggaKuy', 'a.adams@sd.com', 'Anna', 'Adams', 1, 6, 'Writer. Extreme zombieaholic. Problem solver. Tv guru. Communicator. Internet maven.', 'https://www.bmc.edu.sa/images/academia/tutors/dr-iklass.jpg', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (6, 'iwasamistake', '$2a$10$fw.zFiIcD1r29jhDsvGuaOCUPqPdt9JcVtTT51fA9VfaFJm/zhm9u', 'r.gil@sd.com', 'Richard', 'Gil', 1, 7, ' Friend of animals everywhere. Explorer. Lifelong writer. Web trailblazer. Proud pop culture lover. Certified beer fan.', 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (7, 'hackedbychina', '$2a$10$Oc9HuKhziL2nsFlLZzDPmOGvXx7aIWWKBVP88ypvKk0RwlpdgVRNi', 'm.wood@sd.com', 'Mary', 'Wood', 1, 1, 'Hardcore communicator. Friendly student. Devoted problem solver. Amateur thinker. Alcoholaholic.', 'https://photoartinc.com/wp-content/uploads/2018/02/female-photos.jpg', 'admin', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (8, 'pricecharmin', '$2a$10$7QkJWtH.uKBKFbb/sWHpWeLCSa6KizIfmpnC8NpetwmIzeFyMlxu6', 'k.johnson@sd.com', 'Kyle', 'Johnson', 1, 8, 'Friendly music scholar. Internet specialist. Extreme problem solver. Communicator. Freelance bacon buff', 'https://images.unsplash.com/photo-1530645298377-82c8416d3f90?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=80', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (9, 'tpqueen', '$2a$10$MT0LzEkxFCVIT3pp6n29wOgdQCt6g4nCW7aLKefTt8.OjlGh1jCiO', 'k.landry@sd.com', 'Katherine', 'Landry', 1, 9, 'Total troublemaker. Coffee expert. Hipster-friendly internet evangelist. Organizer. Introvert. Alcohol scholar. Beer ninja. Avid music fanatic.', 'https://www.investmentexecutive.com/wp-content/uploads/sites/3/2019/11/100044137_m.jpg', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (10, 'behindyou', '$2a$10$6lqH4PTjOLr9X/3X5jlYZO51SylON7I7DbXwSd0xw.eQpRf5FUP9q', 'a.fletcher@sd.com', 'Andrew', 'Fletcher', 1, 10, ' Incurable communicator. Alcohol maven. Web buff. Zombieaholic. Professional pop culture specialist.', 'https://images-prod.healthline.com/hlcmsresource/images/imce/mens-health-symptoms_thumb.jpg', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (11, 'onemorepint', '$2a$10$NkCR8OBhQuxby7yY7qtM4.tgsycOoYSD1kO/Cg94Hj2hrQNZPO5LS', 'h.thomas@sd.com', 'Hosea', 'Thomas', 1, 11, 'Incurable bacon expert. Certified alcohol evangelist. Extreme internet ninja. Zombie geek. Freelance creator. Student.', 'https://media.wnyc.org/i/800/0/l/85/1/adult-business-businessman-428339.jpg', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (12, 'duckduckgrayduck', '$2a$10$nhzEupLMq6QAn6R/sT2QVuGW40oAjkW/OgLWb3wNwwRervpCILI9.', 's.evans@sd.com', 'Selena', 'Evans', 1, 12, 'Coffee ninja. Certified web nerd. Future teen idol. Problem solver. Friendly pop culture fanatic.', 'https://findnicknames.com/wp-content/uploads/2017/05/Nicknames-for-girls.jpeg', 'user', '2020-04-28');

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `category` (`id`, `type`, `short_description`, `enabled`, `description`, `image_url`) VALUES (1, 'Mountain', 'Activites in the Mountain Air', 1, 'these activities require a friggin mountain', 'https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Ftrevornace%2Ffiles%2F2015%2F11%2Fmt-everest-sunset-1200x675.jpg');
INSERT INTO `category` (`id`, `type`, `short_description`, `enabled`, `description`, `image_url`) VALUES (2, 'Forest', 'Activities that can Take Place in the Forest', 1, NULL, NULL);
INSERT INTO `category` (`id`, `type`, `short_description`, `enabled`, `description`, `image_url`) VALUES (3, 'Water', 'Activities Requiring Water', 1, NULL, NULL);
INSERT INTO `category` (`id`, `type`, `short_description`, `enabled`, `description`, `image_url`) VALUES (4, 'Adventure', 'Adventure Seeking Activities', 1, NULL, NULL);
INSERT INTO `category` (`id`, `type`, `short_description`, `enabled`, `description`, `image_url`) VALUES (5, 'Scenic', 'Activities for the Scenery', 1, NULL, NULL);
INSERT INTO `category` (`id`, `type`, `short_description`, `enabled`, `description`, `image_url`) VALUES (6, 'Urban', 'Activities in an Urban Environment', 1, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `activity`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (1, 'Hiking', 'Long, vigorous walk, usually on trails or footpaths in the countryside', 1, 7, 'Hiking is an activity of moderate difficulty, which involves walking across long distances generally on trails or paths. The duration of the activity varies between short half-day programs and longer itineraries of over 20 days. It is usually an activity that allows groups of different sizes.\n\nHiking and trekking are two terms sometimes used indistinctly. The difference between each of them is usually the duration of the specific activity. Hiking is associated with shorter programs, while trekking is used to describe longer programs of a week or more.\n\nHiking is also a great way to immerse yourself in the culture and history of a country or area. Longer programs will take you to experience local communities and taste unique local food. Hiking is where the trail meets the people, and mountain sports meet cultural adventure.', 'https://www.outsideonline.com/sites/default/files/styles/img_600x600/public/2020/01/10/trey-thru-hiking_s.jpg?itok=xBFIYw6K', 'Low', 'Hiking Boots and Water Source', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (2, 'Birdwatching', 'Wildlife observation of birds', 1, 7, 'Birding is a shortened way of saying birdwatching, which means exactly what the name suggests.  Birdwatching is the practice of observing wild birds, which involves studying both their appearances and behaviors.   For the general public, birding is viewed as little more than a hobby, and some birders are just more enthusiastic than others.  However, for avid birders, birdwatching can be much more than just a hobby.  In fact, birding is the fastest growing sport in the United States.  Birding festivals dot the calendar, filling just about every weekend of the year with an event in different locations. \n\nOne of the advantages of birding is that it is educational in so many different ways.  An experienced birder can not only identify most species at the drop of a hat, but can also list information about the kinds of calls they make, the difference in plumage during different seasons, the kinds of areas they prefer, and the most common behaviors they exhibit.  In trying to gather all this knowledge, birders will develop other skills.  For example, birders often have a keen eye for the miniscule details that can identify one species from another similar one.  Some even develop their knowledge on ecosystems to better understand where certain birds can be most commonly found.', 'https://www.washingtonian.com/wp-content/uploads/2019/10/iStock-1002768220.jpg', 'Low', 'Binoculars, Scope, and Bird Guide ', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (3, 'Fishing', 'The activity of trying to catch fish', 1, 7, 'The most common form of recreational fishing is done with a rod, reel, line, hooks and any one of a wide range of baits. Other devices, commonly referred to as terminal tackle, are also used to affect or complement the presentation of the bait to the targeted fish. Some examples of terminal tackle include weights, floats, and swivels. Lures are frequently used in place of bait. Some hobbyists make handmade tackle themselves, including plastic lures and artificial flies. The practice of catching or attempting to catch fish with a hook is known as angling.\n\nBig-game fishing is conducted from boats to catch large open-water species such as tuna, sharks and marlin. Noodling and trout tickling are also recreational activities.', 'https://www.playwinterpark.com/sites/default/master/files/styles/hero_image_new_small/public/FlyFish2016.png?itok=DV7cRsry&timestamp=1479934863', 'Medium', 'Rod, Reel, Line, Hooks, and Bait', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (4, 'Running', 'The activity of moving rapidly on foot', 1, 7, 'Running is a type of gait characterized by an aerial phase in which all feet are above the ground (though there are exceptions). This is in contrast to walking, where one foot is always in contact with the ground, the legs are kept mostly straight and the center of gravity vaults over the stance leg or legs in an inverted pendulum fashion. A feature of a running body from the viewpoint of spring-mass mechanics is that changes in kinetic and potential energy within a stride occur simultaneously, with energy storage accomplished by springy tendons and passive muscle elasticity. The term running can refer to any of a variety of speeds ranging from jogging to sprinting.', 'https://www.outtherecolorado.com/wp-content/uploads/2017/06/2016_OOC_Summer_Favorites_12.jpg', 'Low', 'Running Shoes and Water Source', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (5, 'Walking', 'The activity of moving slowly on foot', 1, 7, 'Walking is generally distinguished from running in that only one foot at a time leaves contact with the ground and there is a period of double-support. In contrast, running begins when both feet are off the ground with each step. This distinction has the status of a formal requirement in competitive walking events.', 'https://images.ctfassets.net/cnu0m8re1exe/6ixFxAiav36EWRiKriEA7y/494d4e71a1773c28158714419b4f86cf/Walking-Diagnose-Dementia.jpg?w=650&h=433&fit=fill', 'Low', 'Shoes and Water Source', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (6, 'Roller Blading', 'The activity of traveling on surfaces with roller blades', 1, 7, 'Inline skating is a multi-disciplinary sport and can refer to a number of activities practiced using inline skates. Inline skates typically have two to five polyurethane wheels, arranged in a single line by a metal or plastic frame on the underside of a boot. The in-line design allows for greater speed and maneuverability than traditional (or quad) roller skates. Following this basic design principle, inline skates can be modified to varying degrees to accommodate niche disciplines.\n\nInline skating is commonly referred to by the proprietary eponym rollerblading, or just blading, due to the popular brand of inline skates, Rollerblade.', 'https://vtsports.com/wp-content/uploads/2018/07/052692887-attractive-girl-rollerblading--e1531166831149.jpeg', 'Low', 'Roller Blades, Helmet, and Pads', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (7, 'Roller Skating', 'The activity of traveling on surfaces with roller skates', 1, 7, 'It is a form of recreational activity as well as a sport, and can also be a form of transportation. Roller skating is performed on quad skates, four-wheeled roller skates that have two wheels in the front, and two in the back on each skate. Quad roller skates traditionally feature a rubber break, or toe stop, in the front that makes for an easy, quick stop.', 'https://media1.fdncms.com/bend/imager/u/original/6132059/chicks_in_bowls-13.jpg', 'Low', 'Roller Skates, Helmet, and Pads', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (8, 'Skateboarding', 'The activity involves riding and performing tricks using a skateboard', 1, 7, 'Simply put, skateboarding means riding on a wooden board with four wheels fastened to the bottom, propelled forward by the push of one\'s own foot or at the force of gravity on a slope. But skateboarding, transcends mere movement. Today\'s skateboarders take the simple action of riding a skateboard and turn it into both a sport and an art form. And despite its allure and the undoubted athleticism required to perform skateboarding tricks, the sport has fallen in and out of favor with the public and been driven underground several times.', 'https://i2.wp.com/skateboarding.transworld.net/wp-content/uploads/2008/12/07/picture-3.png?ssl=1', 'Low', 'Skateboard, Helmet, and Pads', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (9, 'Mountain Biking', 'The activity of riding bicycles off-road, often over rough terrain', 1, 7, 'Mountain bikes share similarities with other bikes but incorporate features designed to enhance durability and performance in rough terrain. Mountain biking can generally be broken down into multiple categories: cross country, trail riding, all mountain (also referred to as Enduro), downhill, freeride and dirt jumping.\n\nThis sport requires endurance, core strength and balance, bike handling skills, and self-reliance. Advanced riders pursue both steep technical descents and high incline climbs. In the case of freeride, downhill, and dirt jumping, aerial maneuvers are performed off both natural features and specially constructed jumps and ramps.', 'https://static01.nyt.com/images/2019/11/19/well/physed-bike/physed-bike-articleLarge.jpg?quality=75&auto=webp&disable=upscale', 'High', 'Mountain Bicycle and Helmet', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (10, 'Mushroom Hunting', 'The activity of gathering mushrooms in the wild', 1, 7, 'Many field guides on mushrooms are available and recommended to help safely distinguish edible from the many poisonous mushrooms.\n\nIdentification is not the only element of mushroom hunting that takes practice; knowing where and when to search does as well. Most mushroom species require very specific conditions. Some only grow at the base of a certain type of tree, for example. Finding a desired species that is known to grow in a certain region can be a challenge.', 'https://www.fccnn.com/incoming/2029614-96rgtt-060819.O.DR.MORELS1.jpg/alternates/BASE_LANDSCAPE/060819.O.DR.MORELS1.jpg', 'Low', 'Mushroom Hunting Knife, Mushroom Bag, and Mushroom Field Guide', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (11, 'Canoeing', 'The activity of paddling a canoe with a single-bladed paddle', 1, 7, 'Canoeing is a very popular water sport played extensively all around the world. Canoeing is a sport where one gets into a little boat made for the sport called a canoe and using a single-bladed paddle to steer the canoe, tries to travel down a stream of a river.', 'https://www.tutorialspoint.com/canoeing/images/paddle.jpg', 'Medium', 'Canoe, Paddles, and Lifevests', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (12, 'Swimming', 'Self-propulsion of a person through water, usually for recreation, sport, exercise, or survival', 1, 7, 'Swimming is the movement of the body through water using arms and legs. Most of the time equipment is not used. People swim for exercise, for fun,  and as a sport. People can swim in the sea, swimming pools, rivers and lakes. There are several styles of swimming, known as strokes, including: breaststroke, freestyle, butterfly, and backstroke are some of them. Many schools use swimming as a physical training exercise.', 'https://swimjim.com/wp-content/uploads/2019/05/iStock-966899080.jpg', 'Low', 'Bathing Suit', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (13, 'Horseback Riding', 'The activity includes the disciplines of riding, driving, or vaulting with horses', 1, 7, 'Horseback riding is one of the oldest sports and fulfills every definition of sport, yet it is often perceived as an easy activity, not a sport. This could be because the only exposure most people have had to equestrianism is television, where horseback riding is done by professionals so skilled they make it look effortless, or dude-string type horses who are saintly enough to carry unbalanced, unskilled human cargo. What the average non-rider doesn\'t see are the hours of practice, the sore muscles, bruising, and chafing not to mention the mental challenge that riders undergo to make it all look easy.', 'https://www.finishlinehorse.com/wp-content/uploads/2015/09/horseback-riding-is-a-great-exercise-for-humans-_1711_40070922_0_14103259_500.jpg', 'High', 'Horse and Saddle', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (14, 'Rock Climbing', 'Climb up, down or across natural rock formations or artificial rock walls', 1, 7, 'Rock climbing is an activity in which participants climb up, down or across natural rock formations or artificial rock walls. The goal is to reach the summit of a formation or the endpoint of a pre-defined route without falling. Rock climbing is a physically and mentally demanding sport, one that often tests a climbers strength, endurance, agility and balance along with mental control. It can be a dangerous sport and knowledge of proper climbing techniques and usage of specialized climbing equipment is crucial for the safe completion of routes. Because of the wide range and variety of rock formations around the world, rock climbing has been separated into several different styles and sub-disciplines.', 'https://www.rei.com/events/images/csi/subProgram/41/course/45196', 'High', 'Climbing Shoes, Climbing Helmet, Chalk, Climbing Rope, Harnesses, Carabiners, and Belay Devices', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (15, 'Bouldering', 'form of rock climbing that is performed without the use of ropes or harnesses', 1, 7, 'Bouldering is rock climbing stripped down to its raw essentials. Leaving behind ropes and harnesses and just using climbing shoes and a bag of chalk over safety mats, your challenge is to climb short but tricky bouldering problems (a route, or sequence of moves) using balance, technique, strength, and your brain.\n\nYou don\'t need experience or lots of expensive kit to have a go making it really easy to get into if you\'ve never tried it before.\n\nThe climbs are high enough to be exciting, but not so high that they\'re hugely intimidating. Using safety mats means that the risks of falling off can be managed, and leaving the ropes behind means that you are free to concentrate on the climbing, not the equipment. It\'s just you, the wall, and your friends on the ground egging you on.', 'https://res.cloudinary.com/fittco/image/upload/v1557507984/mv9jok3cqyhp50dauo2o.jpg', 'Medium', 'Climbing Shoes, Climbing Helmet, Chalk, and Bouldering Mat', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (16, 'Archery', 'The art, sport, practice, or skill of using a bow to shoot arrows', 1, 7, 'A person who participates in archery is typically called an archer. Someone who is fond of or an expert at archery is called a toxophilite or lover of the bow, from Ancient Greek tokson bow and philos friend.\n\nAs a sport, archery requires skills of precision, control, focus, repetition and determination. It is available to be practised by all, no matter age, gender or ability, and is a widespread pastime in both developed and developing countries.', 'https://worldarchery.org/sites/default/files/styles/manual_crop_16_9/public/page/image/archery_web.jpg?itok=EP-1kTyE&c=42aab2c8019a0badf60dfe63e9e50f7f', 'Medium', 'Bow and Arrows', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (17, 'Hunting', 'The practice of seeking, pursuing and capturing or killing wild animals', 1, 7, 'Hunting wildlife or feral animals is most commonly done by humans for meat, recreation, to remove predators that can be dangerous to humans or domestic animals, to remove pests that destroy crops or kill livestock, or for trade.\n\nRegulations distinguish lawful hunting from poaching, which involves the illegal killing, trapping or capture of the hunted species. The species that are hunted are referred to as game or prey and are usually mammals and birds. Economists classify hunting as part of primary production - alongside forestry, agriculture and fishing.', 'https://www.colorado.com/sites/default/files/styles/1000x685/public/Muzzleloader_DOW_DennisMcKinney.jpg?itok=M9v-HD4Q', 'High', 'Weapon and Safety Equipment', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (18, 'Kayaking', 'The use of a kayak for moving across water', 1, 7, 'Kayaks is distinguished from canoeing by the sitting position of the paddler and the number of blades on the paddle. A kayak is a low-to-the-water, canoe-like boat in which the paddler sits facing forward, legs in front, using a double-bladed paddle to pull front-to-back on one side and then the other in rotation. Most kayaks have closed decks, although sit-on-top and inflatable kayaks are growing in popularity as well.', 'https://www.nps.gov/glba/planyourvisit/images/KayakinginGLBA.jpg?maxwidth=1200&maxheight=1200&autorotate=false', 'Medium', 'Kayak, Paddles, and Lifevests', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (19, 'Tubing', 'The activity where an individual rides on top of an inner tube, either on water or snow', 1, 7, 'Tubing on water generally consists of two forms: towed and free-floating, also known as river tubing. There is also water skiing. According to Time Magazine, tubing was purportedly invented in Thailand by Princess Chumbhot of Nagar Svarga sometime in the middle of the 20th century, but examples of the practice were published as early as 1916, when the popularization of the automobile meant a large supply of rubber inner tubes was available to the general public.\n\nTowed tubing usually takes place on a large body of water such as a lake or river. One or more tube riders (often called tubers) tether their tubes to a powered watercraft such as a motor boat or a personal watercraft. The riders are then towed through the water by the watercraft.', 'https://www.colorado.com/sites/default/files/styles/media-player-large/public/Rivertubing1_BoulderCreek_WhitewaterTubeCo.jpeg?itok=vw0nS5Ic', 'Low', 'Tube', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (20, 'Paddleboarding', 'The activity of lying, kneeling, or standing on a paddleboard or surfboard', 1, 7, 'Stand up paddle boarding (SUP) offers a fun way to play on the water, with the added benefit of a full-body workout. And, since you stand at full height on your board, it gives you a unique vantage point for viewing what\'s down under the water and out on the horizon.', 'https://cpw.state.co.us/PublishingImages/Outdoor-Recreation/SUP-Steamboat01.png', 'Medium', 'Paddleboard and Paddle', '2020-04-28');
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_description`, `create_date`) VALUES (21, 'Stargazing', 'The activity of gazing and observing the stars ', 1, 7, 'Every to-be observer often wonders, Where do I begin?. Astronomy often is the hobby of looking at very distant object with a telescope... Unfortunately, for so many people, it ends with the skinny unused telescope offered as a kid for a birthday.\n\nTruth is, people rarely know what to look for. Yet, it can be so much simpler if you know the following: Don\'t start with a telescope, start with your own eyes.\n\nEvery dedicated astronomer is first and foremost a stargazer. Stargazing is the act of seeing subtle details, comparing and contrasting what you see from what you know.', 'https://media.fromthegrapevine.com/assets/images/2017/2/star-gazing-0208.jpg.824x0_q71_crop-scale.jpg', 'Low', 'Eyes', '2020-04-28');

COMMIT;


-- -----------------------------------------------------
-- Data for table `resource`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (1, 'Hiking for Beginners', 'Unlike walking on a treadmill or paved path, hiking involves more, sometimes unpredictable, variables. Of course, these variables are part of what makes it so enjoyable! Use the following hiking tips to make your first treks successful', 'https://blog.liftopia.com/10-essential-hiking-tips-beginner-hike/', 'https://10c9rz12yp4b46gr6a2oxgub-wpengine.netdna-ssl.com/wp-content/uploads/DSC_0879.jpg');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (2, 'How to Start Birding', 'If you\'ve been considering joining the ranks of the 47 million birders in the United States, there\'s no better time than the present to take the plunge or at least dip your toes in. But wait. Which bins should you choose? Where should you go? How do you even find a bird? If you\'re a novice, this handy primer will give you the tools you need to venture into the field with confidence. (First tip: Always casually refer to binoculars as bins)', 'https://www.audubon.org/birding/how-to-start-birding', 'https://www.audubon.org/sites/default/files/styles/hero_image/public/web_ohio_camillacerea-9266.jpg?itok=P1WlqHn-');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (3, 'Learn to Fish', 'Go fishing by yourself, with friends, or with the whole family. Make some great memories, or even start a fun summer tradition! Spending time in nature is not only good for your health and your soul. Relaxing times by a gurgling brook or whispering stream or placid lake can also create wonderful and lasting bonds with children. Best of all, taking kids fishing need not be expensive, nor require a lot of time or a long drive. ', 'https://cpw.state.co.us/learn/Pages/Fishing.aspx', 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTaFtA-BXRNgt6EGcHepmOtdb5M3AfOxygHsVBPEsUt4lJwg3Bu&usqp=CAU');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (4, 'How to Start Running', 'Running is a great way to get fit, feel better and even form new relationships with other runners. Starting a new running habit doesn\'t have to be hard all it takes is a comfortable pair of shoes and a willingness to move a little or a lot, all at your own pace. The Well Guide makes it easy to get started, get inspired and stay on track. ', 'https://www.nytimes.com/guides/well/how-to-start-running', 'https://rptutah.com/wp-content/uploads/2019/01/iStock-518323734.jpg');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (5, 'How to Start Walking', 'Walking literally transforms your body and mind. In fact, research shows it can add almost two years to your life. Of course, there\'s the major perk that sneaking in those steps helps you shed unwanted weight. But going for a walk can benefit your body in other significant ways too.', 'https://www.prevention.com/fitness/a20494108/how-to-start-walking-for-weight-loss/', 'https://www.verywellfit.com/thmb/Ua84a1D--86Avsj9Q-BU-HWF5LM=/2667x2000/smart/filters:no_upscale()/brisk-walking-greenway-path-Steve-Debenport-Eplus-GettyImages-154961050-572369203df78c5640e99637.jpg');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (6, 'Rollerblading for Beginners', 'Rollerblading is a great way to keep fit and a fast route into performing big-air tricks on the skatepark. Skates are plentiful, relatively cheap and can be used pretty much anywhere there\'s a hard surface. All this makes rollerblading a perfect gateway sport into the action sports world.', 'https://www.fise.fr/en/fise-world-series-2020/news/rollerblading-beginners', 'https://www.skimag.com/.image/t_share/MTY2NDcxOTA2NDMyNjU2NTQ1/uphillhanddown.jpg');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (7, 'Skating 101', 'This guide is intended to be a basic introduction to the world of roller and inline skating. We\'ve been in the skate business for more than 30 years - And we know it\'s a fun and great way to stay in shape. We hope to be able to share our extensive experience and product knowledge in skating with you through our website.', 'https://www.skates.com/how-to-skate-s/84838.htm', 'https://www.outsidepursuits.com/wp-content/uploads/2018/01/Best-Roller-Skates.jpg');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (8, 'Learning to Skateboard', 'Ready to try skateboarding?? This guide takes you through all our beginner tips & tricks to help you learn how to skateboard.', 'https://www.tactics.com/info/learning-to-skateboard', 'https://www.tactics.com/a/bn2n/r/how-to-skateboard-learn-to-skate.jpg');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (9, 'Mountain Biking for Beginners', 'Mountain bikes are a fun way to exercise and connect with nature. Compared to road bikes, they have the following characteristics: fatter tires with rugged tread for stability and durability on off-road terrain, a more upright cycling position that lets you enjoy the view\nsuspension systems on some bikes absorb shock for a more comfortable ride. There are many ways to enjoy mountain biking, and you don\'t even have to be in the mountains. Trails vary from pleasant rides on wide, flowing logging roads to high-adrenaline challenges on technical singletrack.', 'https://www.rei.com/learn/expert-advice/mountain-biking-beginners.html', 'https://www.rei.com/dam/content_team_080317_61569_mountain_biking_beginners_lg.jpg');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (10, 'Beginner\'s Guide to Mushroom Hunting', 'Campers, have you tried mushroom hunting? The Dyrt writer Meghan O\'Dea recently traveled to Ohio to learn all about this thrilling way to connect with nature.  Disclaimer: Consuming foraged mushrooms can be risky (poisonous mushrooms). Only those who are well-educated on foraging and mushroom varieties should attempt to do so. Please note that this is one writer\'s experience and should not be taken as expert advice. ', 'https://thedyrt.com/magazine/lifestyle/a-beginners-guide-to-foraging/', 'https://bloximages.newyork1.vip.townnews.com/nvdaily.com/content/tncms/assets/v3/editorial/d/38/d388dac0-750c-5b93-9362-81613453a47f/5cd064197eaba.image.jpg?resize=1200%2C808');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (11, 'How to Canoe if you\'ve Never Paddled Before', 'Do you fantasize about canoeing peacefully across an alpine lake, but lack any actual canoeing experience? Do you dream about posting very Instagram-y photos of yourself paddling into the sunset, but don\'t know how to paddle a canoe in the first place? Worry no more here\'s how to get started. ', 'https://www.outdoorresearch.com/blog/article/how-to-canoe-if-youve-never-paddled-before', 'https://www.outdoorresearch.com/blog/images/articles/how_to_canoe_1.jpg');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (12, 'How to Swim', 'There\'s nothing like swimming on a hot summer day. However, swimming is also a skill that can save your life. When you know how to swim, you can safely enjoy water activities like kayaking and surfing. Swimming is a great workout, too. It forces your body to work against resistance, which strengthens your muscles, heart, and lungs. The best way to learn how to swim is to take lessons. Let\'s look at the most commonly taught strokes and how to improve your technique.', 'https://www.healthline.com/health/exercise-fitness/how-to-swim#freestyle', 'https://lh3.googleusercontent.com/proxy/imdhL-L1cD3BXvZyW9w_9bmY75yNviZstZQpc6i4tR7T-v2jULqoT36S7yhxqaD7deKZ_MZu3zgmc0Pwb6TE-9Lkki1yixoKRgdq9cd3GpszwXxPvPEWzx0jsUG2mS_z3KaMRPM');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (13, '10 Tips for Beginner Riders', 'Are you thinking of going horse riding for the first time? Its only natural to feel a bit nervous about it; after all, you\'re about to ride a relatively large, living animal. This is why it\'s very important to be guided by an expert or a trained professional when you take up horse riding. Not only will they provide the support and reassurance that you need to feel safe and comfortable, but they can also share useful riding tips for beginners. After all, every expert was once a beginner too, right? They will be able to relate to what you are going through and provide personalized feedback.', 'https://www.bookhorseridingholidays.com/news/tips-beginner-horse-riders', 'https://images.tpn.to/nr/rs/qq/ln/content.jpg');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (14, 'The Noob\'s Guide to Rock Climbing', 'Always wanted to go climbing, but don\'t know where to start? We\'re here to help, with this basic guide to climbing styles, techniques, and jargon. Hopefully it helps remove some of the mystery, helps you figure out which disciplines you\'d like to try, and makes it easier for you to get started.', 'https://www.outsideonline.com/2062326/beginners-guide-rock-climbing', 'https://www.outsideonline.com/sites/default/files/styles/width_1200/public/2016/03/14/n00b-climbing_h.jpg?itok=_Efqntf4');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (15, '10 Bouldering Tips for Beginners', 'Just starting out in bouldering and keen to progress fast? Here are ten tips from a trio of bouldering experts, including GB boulderer and Red Bull athlete Shauna Coxsey.', 'https://www.redbull.com/my-en/10-bouldering-tips-for-beginners', 'https://www.instagram.com/p/BcP0wsRgCmN/?utm_source=ig_embed');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (16, 'Archery for Beginners', 'If you\'d like to get into archery as a hobby but you have no idea where to begin, we absolutely understand how you feel. A lot of people are really excited to grab a bow and start shooting, but get confused by all the terms and equipment and measurements. You want to dive in, but it all seems a little overwhelming. \nWith that in mind, we put together this post to help you get started. It\'s a way of presenting the \"big ideas\" of archery, and introducing you to the sport, little by little.\nOn this page, we\'ll provide all the basic info you\'ll need as a new archer. There\'s a lot here, but by the end of the post, you\'ll have a very clear idea of how to begin your journey with the equipment and knowledge that you\'ll need.', 'https://www.completeguidetoarchery.com/archery-for-beginners-how-to-get-started/', 'https://www.completeguidetoarchery.com/wp-content/uploads/2017/01/Recurve-Bow-Image.png');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (17, 'How to Start Hunting', 'If you aren\'t a hunter, and none of your family has encouraged you into it, you might be wondering how to how to get into hunting. Before you start worrying about the must-have hunting gear and all the accessories you need, take a look at what you need to do to get started. It can be difficult to know where to begin if none of your family or friends is into hunting, and it can be a bit overwhelming at the start. Here are our top tips on how to start hunting.', 'https://naturesportcentral.com/hunting/', 'https://s3.amazonaws.com/images.gearjunkie.com/uploads/2018/10/1102_deercamp.jpg');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (18, 'Getting Started Kayaking', 'It\'s an iconic image of outdoor exploration: a kayak glides across a glassy stretch of water, its bow knifing through the mist and its wake shimmering in reflected light. If that sort of thing calls to you, we\'re here to help. With some thoughtful preparation, you can slip into the cockpit and put paddle to pond.', 'https://www.rei.com/learn/expert-advice/getting-started-kayaking.html', 'https://web.uri.edu/inside-rec/files/Leah-kayak.jpg');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (19, 'Beginner\'s Guide to Floating', 'Let me preface this by saying I went floating down a river in Colorado in August, when water levels are low and temperatures are semi-tolerable. Your experience may vary (I hope you can find a warmer river than I did) but the clothes and gear you need won\'t.', 'https://outdoorbeginner.com/2017/08/20/beginners-guide-to-floating-what-to-wear-and-expect-your-first-time-tubing/', 'https://theknow.denverpost.com/wp-content/uploads/2018/07/TUBING-FEATURE-06212017-JL-62x.jpg');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (20, '7 Fun Places to Stand Up Paddle Board in Colorado', 'Stand up paddle boarding (SUP) is becoming an increasingly popular water based activity, especially in coastal areas. But you needn\'t move to California, bleach your teeth and boast a bikini tan-line to enjoy paddle boarding. In fact, wherever there\'s a state park with a safe body of water, there\'s probably an opportunity for paddling!', 'https://www.uncovercolorado.com/stand-up-paddle-board-colorado/', 'https://www.liveabout.com/thmb/TlMOWUVWEyLkgdb-p02wH59YEP8=/768x0/filters:no_upscale():max_bytes(150000):strip_icc()/GettyImages-530053707-591c90353df78cf5fad757a4.jpg');
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (21, 'Astronomy for Beginners', 'Did you know you can see a galaxy 2_ million light-years away with your unaided eyes? Craters on the Moon with binoculars? Countless wonders await you any clear night. The first step in astronomy for beginners is simply to look up and ask, \"What\'s that?\" Begin gazing at the stars from your backyard, and you\'ll be taking the first step toward a lifetime of cosmic exploration and enjoyment.', 'https://skyandtelescope.org/astronomy-resources/stargazing-basics/how-to-start-right-in-astronomy/', 'https://skyandtelescope.org/wp-content/uploads/woman_Horizon_Comet_m1.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_group`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `user_group` (`id`, `name`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `create_date`) VALUES (1, 'Hikers United', 'For Hardcore Hiking', 1, 7, 'This group is for people that want to go on long hikes', 'https://i1.wp.com/s3.amazonaws.com/passionpassport-1/wp-content/uploads/2020/02/12131422/oziel-gomez-IbLZjKcelpM-unsplash.jpg?fit=1068%2C638&ssl=1', '2020-04-28');

COMMIT;


-- -----------------------------------------------------
-- Data for table `group_member`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `group_member` (`user_id`, `group_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `activity_location`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `activity_location` (`activity_id`, `location_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `activity_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (1, 1);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (1, 2);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (1, 4);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (1, 5);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (2, 1);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (2, 2);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (2, 5);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (2, 6);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (3, 3);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (4, 1);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (4, 2);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (4, 6);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (5, 2);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (5, 5);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (5, 6);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (6, 4);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (6, 6);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (7, 4);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (7, 6);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (8, 4);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (8, 6);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (9, 1);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (9, 2);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (9, 4);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (10, 1);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (10, 2);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (10, 5);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (11, 3);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (12, 3);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (13, 1);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (13, 2);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (13, 5);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (14, 1);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (14, 4);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (14, 6);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (15, 1);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (15, 4);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (15, 6);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (16, 2);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (16, 6);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (17, 2);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (18, 3);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (19, 3);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (19, 5);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (20, 3);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (20, 4);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (21, 1);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (21, 2);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (21, 5);
INSERT INTO `activity_category` (`activity_id`, `category_id`) VALUES (21, 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `activity_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `activity_comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `activity_id`, `in_reply_id`) VALUES (1, 'dude hiking is my passion', '2020-04-28', 1, 1, 1, NULL);
INSERT INTO `activity_comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `activity_id`, `in_reply_id`) VALUES (2, 'highly doubtful, prove it n00b', '2020-04-28', 1, 2, 1, 1);
INSERT INTO `activity_comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `activity_id`, `in_reply_id`) VALUES (3, 'why you so mad', '2020-04-28', 1, 3, 1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `activity_resource`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (1, 1);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (2, 2);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (3, 3);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (4, 4);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (5, 5);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (6, 6);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (7, 7);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (8, 8);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (9, 9);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (10, 10);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (11, 11);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (12, 12);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (13, 13);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (14, 14);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (15, 15);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (16, 16);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (17, 17);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (18, 18);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (19, 19);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (20, 20);
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (21, 21);

COMMIT;


-- -----------------------------------------------------
-- Data for table `event`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `event` (`id`, `creator_id`, `activity_id`, `title`, `event_time`, `event_date`, `short_description`, `enabled`, `location_id`, `description`, `image_url`, `create_date`) VALUES (1, 7, 9, 'Bicycle Colorado', '9:00:00', '2020-06-07', 'Biking and beers!', 1, 14, 'This exciting and fun event has something for the entire family to enjoy. This year\'s bike race will once again act as the state championships, and the Brewfest will include local breweries showing off their best summer beers. In addition to racing and beer, this free, family-friendly day features a kids zone, food trucks, a beer garden, an outdoors expo and a community fun ride!', 'https://www.bicyclecolorado.org/wp-content/uploads/2017/12/ridge-at-38-269x137.png', '2020-04-28');
INSERT INTO `event` (`id`, `creator_id`, `activity_id`, `title`, `event_time`, `event_date`, `short_description`, `enabled`, `location_id`, `description`, `image_url`, `create_date`) VALUES (2, 7, 2, 'Crane Festival', '9:00:00', '2020-09-03', 'The 9th annual Yampa Valley Crane Festival is scheduled for September 3-6, 2020. It will once again feature all of your favorite crane, bird and nature activities, plus new events, films and expert speakers.', 1, 16, 'The 9th annual Yampa Valley Crane Festival is scheduled for September 3-6, 2020. It will once again feature all of your favorite crane, bird and nature activities, plus new events, films and expert speakers. We are thrilled to announce that our 2020 keynote speaker will be Dr. Richard Beilfuss, President and CEO of the International Crane Foundation. Other featured speakers will include Steve Burrows, award-winning Canadian mystery writer, journalist, and past recipient of a Nature Writer of the Year award from BBC Wildlife, and Arvind Panjabi, avian conservation scientist for Bird Conservancy of the Rockies and coauthor of the recent study published in Science magazine on the decline of North American birds. Paul Tebbel will lead guided crane viewings and presents a \"Cranes 101\" talk. Special guest, Yu Qian, director of the China program for the International Crane Foundation, will give a presentation on \"2020: The Year of the Cranes\" in Asia. Ted Floyd, editor of Birding magazine, will lead the guided bird walks and offer a special presentation at The Nature Conservancy\'s historic Carpenter Ranch.', 'https://coloradocranes.org//wp-content/uploads/2014/02/CraneFestLogoWEB-150x150.jpg', '2020-04-28');
INSERT INTO `event` (`id`, `creator_id`, `activity_id`, `title`, `event_time`, `event_date`, `short_description`, `enabled`, `location_id`, `description`, `image_url`, `create_date`) VALUES (3, 7, 9, 'EROCK Mountain Bike Race', '7:00:00', '2020-06-06', 'The Sunrise to Sunset Elephant Rock is designed to offer recreational and competitive cyclists the opportunity to compete in a fun and rewarding 12 hour endurance race on a moderately technical off-road course.', 1, 15, 'Register as an individual or form a team that loves to shred for a memorable day of racing, camaraderie and laughs.\nTaking place again at the freshly cut trails of the Philip S. Miller Park in Castle Rock, this amazing $30M facility offers the perfect endurance mountain bike venue less than a mile from the Colorado Bike Expo and Elephant Rock Cycling Festival. The playful and flowy 6.5 mile course is cut into to the hills and drainages surrounding the park and offers ample viewing from the staging area for team members and spectators.Register as an individual or form a team that loves to shred for a memorable day of racing, camaraderie and laughs.\nTaking place again at the freshly cut trails of the Philip S. Miller Park in Castle Rock, this amazing $30M facility offers the perfect endurance mountain bike venue less than a mile from the Colorado Bike Expo and Elephant Rock Cycling Festival.The playful and flowy 6.5 mile course is cut into to the hills and drainages surrounding the park and offers ample viewing from the staging area for team members and spectators.', 'https://www.bicyclecolorado.org/wp-content/uploads/2017/12/ridge-at-38-269x137.png', '2020-04-28');
INSERT INTO `event` (`id`, `creator_id`, `activity_id`, `title`, `event_time`, `event_date`, `short_description`, `enabled`, `location_id`, `description`, `image_url`, `create_date`) VALUES (4, 7, 3, 'Free 101 Fly Fishing', '10:00:00', '2020-05-09', 'We host a free fly fishing clinic at our shop in Evergreen every Saturday from May to September.', 1, 17, 'Learn how to cast, tie knots, rig your rod, choose the right fly, and more. Discounts offered on Orvis products and other items for those who come. Our free Saturday seminars are taught by Blue Quill Angler Guides and other professional anglers. Classes are from 10:00 am until 12:00 noon. Registration is not necessary, just show up and learn!', 'https://bluequillangler.com/wp-content/uploads/2017/10/orvis_101-sidebar.jpg', '2020-04-28');
INSERT INTO `event` (`id`, `creator_id`, `activity_id`, `title`, `event_time`, `event_date`, `short_description`, `enabled`, `location_id`, `description`, `image_url`, `create_date`) VALUES (5, 7, 4, 'Timberview Trail Run', '8:00:00', '2020-08-08', 'Crisp morning air with a summer sunrise in Colorado. YES PLEASE! Join us on the foothills at Lory State Park for a fun morning trail race', 1, 18, 'This run-only race features two distances (a 2.5 mile and a 5 mile) that are perfect for just about any level athlete at any age. Run on a beautiful & scenic course among the treetops with views of Horsetooth reservoir, Fort Collins & the foothills. Bring the kiddos too as the 2.5 mile route is very beginner friendly.', 'https://breakawayathleticevents.com/wp-content/uploads/2019/11/timberview-trail-run-logo-2-300x296.jpg', '2020-04-28');
INSERT INTO `event` (`id`, `creator_id`, `activity_id`, `title`, `event_time`, `event_date`, `short_description`, `enabled`, `location_id`, `description`, `image_url`, `create_date`) VALUES (6, 7, 10, 'Mushroom Foraging Seminar', '7:00:00', '2020-05-11', 'We\'re proud to announce that Dr. Brian Perry will give his talk - previously scheduled for April\'s meeting - on the scientific wonders of bioluminesecent fungi.', 1, 1, 'Fungal bioluminescence was first described by Aristotle (384-322 B.C.E.), and continues to fascinate and puzzle scientists today. While over 100 species of fungi are known to produce luminescent mushrooms or mycelium, the chemical and genetic basis of the light-producing reaction was only recently discovered, and the reason these fungi glow remains somewhat of a mystery. Come learn about our current understanding of the evolution, ecology and biochemistry of this phenomenon. And yes, there will be lots of pictures of glowing mushrooms!', 'https://cmsweb.org/wp-content/uploads/2018/01/cms-logo-redraw-1.png', '2020-04-28');
INSERT INTO `event` (`id`, `creator_id`, `activity_id`, `title`, `event_time`, `event_date`, `short_description`, `enabled`, `location_id`, `description`, `image_url`, `create_date`) VALUES (7, 7, 3, 'Spring Fishing Clinic', '8:00:00', '2020-05-02', 'Free fishing classes. Requires a valid Colorado fishing license.', 1, 13, '100 FREE poles for the first kids to sign up in advance for a day filled with fishing tips and giveaways!\nThe kids\' fishing classes are at 8AM, 9AM, 10AM. Participants must be present to win door prizes. Adult fishing workshops held throughout the event .This event is free however each vehicle is required to obtain a $9 daily or annual parks pass.', 'https://cpw.state.co.us/Style%20Library/Custom/Images/CPW_SiteLogo.png', '2020-04-28');

COMMIT;


-- -----------------------------------------------------
-- Data for table `event_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `event_comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `event_id`, `in_reply_id`) VALUES (1, 'yo brosephs what time we meeting up I cant read good', '2020-04-28', 1, 1, 1, NULL);
INSERT INTO `event_comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `event_id`, `in_reply_id`) VALUES (2, 'dude its on the details page, read', '2020-04-28', 1, 2, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `event_photo`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `event_photo` (`id`, `image_url`, `event_id`) VALUES (1, 'https://www.gohikeit.com/wp-content/uploads/2015/04/hiker-selfie-saddleback-mountain-hiking-trail.jpg', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_event`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `user_event` (`user_id`, `event_id`, `rating`, `rating_comment`) VALUES (1, 1, 5, 'once I figured out what time the event was I had a great time hiking with the brotatoes');

COMMIT;


-- -----------------------------------------------------
-- Data for table `favorites_list`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `favorites_list` (`user_id`, `activity_id`) VALUES (1, 1);

COMMIT;

