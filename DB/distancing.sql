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
  `role` ENUM('user', 'admin') NULL DEFAULT 'user',
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
  `short_description` VARCHAR(100) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT '1',
  `creator_id` INT NOT NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(3000) NULL,
  `equipment_level` ENUM('none', 'low', 'medium', 'high') NULL,
  `equipment_desciption` TEXT NULL,
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
  `short_description` VARCHAR(45) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT '1',
  `description` TEXT NULL,
  `image_url` VARCHAR(3000) NULL,
  `create_date` DATE NULL,
  PRIMARY KEY (`id`))
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
  `short_description` VARCHAR(100) NOT NULL,
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
  `id` INT NOT NULL,
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
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (2, '2710 S Colorado Blvd', 'Denver', 'Colorado', 'United States', '80222', NULL, NULL);
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (3, '430 S Colorado Blvd', 'Denver', 'Colorado', 'United States', '80246', NULL, NULL);
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (4, '5600 S Parker Rd', 'Aurora', 'Colorado', 'United States', '80015', NULL, NULL);
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (5, '7785 E Hampden Ave', 'Denver', 'Colorado', 'United States', '80231', NULL, NULL);
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (6, '9335 Crown Crest Blvd', 'Parker', 'Colorado', 'United States', '80138', NULL, NULL);
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (7, '391 Spectrum Loop', 'Colorado Springs ', 'Colorado', 'United States', '80921', NULL, NULL);
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (8, '14730 W Colfax Ave', 'Lakewood', 'Colorado', 'United States', '80401', NULL, NULL);
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (9, '7990 N Academy Blvd', 'Colorado Springs ', 'Colorado', 'United States', '80920', NULL, NULL);
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (10, '9091 Westview Rd', 'Lone Tree', 'Colorado', 'United States', '80124', NULL, NULL);
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (11, '14310 E Alameda Ave', 'Aurora', 'Colorado', 'United States', '80012', NULL, NULL);
INSERT INTO `location` (`id`, `street`, `city`, `state`, `country`, `postal_code`, `title`, `location_url`) VALUES (12, '1198 S Havana St', 'Aurora', 'Colorado', 'United States', '80012', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (1, 'kissmyaxe', 'bullseye', 'p.smith@sd.com', 'Peggy', 'Smith', 1, 2, ' Freelance internet buff. Amateur introvert. Writer. Web nerd. Travel aficionado.', 'https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/GettyImages-1158622531_thumb.jpg', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (2, 'shaquilleoatmeal', 'basketball', 'c.mcgregor@sd.com', 'Clayton', 'McGregor', 1, 3, 'Music geek. Social media enthusiast. Student. Professional twitter fanatic. Web advocate.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Outdoors-man-portrait_%28cropped%29.jpg/1200px-Outdoors-man-portrait_%28cropped%29.jpg', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (3, 'desperateenuf', 'crumbbum', 'b.bailey@sd.com', 'Brian', 'Bailey', 1, 4, 'Bacon enthusiast. Professional troublemaker. Lifelong music scholar. Unapologetic twitter advocate.', 'https://media.gettyimages.com/photos/what-more-can-a-girl-ask-for-picture-id692879918?b=1&k=6&m=692879918&s=612x612&w=0&h=uc4HrCahufEOMHYbRxjsESauxoJhzYkyzFPq0YZtxSo=', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (4, 'billnyetherussianspy', 'ilovebears', 'j.haley@sd.com', 'Julie', 'Haley', 1, 5, 'Analyst. Student. Tv nerd. Unapologetic music specialist. Freelance coffee maven.', 'https://secure.gravatar.com/avatar/81c8847e3d52f5cab58ce0a0b923f11f?s=400&d=mm&r=g', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (5, 'hoosierdaddy', 'indiana', 'a.adams@sd.com', 'Anna', 'Adams', 1, 6, 'Writer. Extreme zombieaholic. Problem solver. Tv guru. Communicator. Internet maven.', 'https://www.bmc.edu.sa/images/academia/tutors/dr-iklass.jpg', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (6, 'iwasamistake', 'surviving', 'r.gil@sd.com', 'Richard', 'Gil', 1, 7, ' Friend of animals everywhere. Explorer. Lifelong writer. Web trailblazer. Proud pop culture lover. Certified beer fan.', 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (7, 'hackedbychina', 'security', 'm.wood@sd.com', 'Mary', 'Wood', 1, 1, 'Hardcore communicator. Friendly student. Devoted problem solver. Amateur thinker. Alcoholaholic.', 'https://photoartinc.com/wp-content/uploads/2018/02/female-photos.jpg', 'admin', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (8, 'pricecharmin', 'toilets', 'k.johnson@sd.com', 'Kyle', 'Johnson', 1, 8, 'Friendly music scholar. Internet specialist. Extreme problem solver. Communicator. Freelance bacon buff', 'https://images.unsplash.com/photo-1530645298377-82c8416d3f90?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=80', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (9, 'tpqueen', 'bidet', 'k.landry@sd.com', 'Katherine', 'Landry', 1, 9, 'Total troublemaker. Coffee expert. Hipster-friendly internet evangelist. Organizer. Introvert. Alcohol scholar. Beer ninja. Avid music fanatic.', 'https://www.investmentexecutive.com/wp-content/uploads/sites/3/2019/11/100044137_m.jpg', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (10, 'behindyou', 'watchout', 'a.fletcher@sd.com', 'Andrew', 'Fletcher', 1, 10, ' Incurable communicator. Alcohol maven. Web buff. Zombieaholic. Professional pop culture specialist.', 'https://images-prod.healthline.com/hlcmsresource/images/imce/mens-health-symptoms_thumb.jpg', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (11, 'onemorepint', 'lastone', 'h.thomas@sd.com', 'Hosea', 'Thomas', 1, 11, 'Incurable bacon expert. Certified alcohol evangelist. Extreme internet ninja. Zombie geek. Freelance creator. Student.', 'https://media.wnyc.org/i/800/0/l/85/1/adult-business-businessman-428339.jpg', 'user', '2020-04-28');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `enabled`, `location_id`, `description`, `image_url`, `role`, `create_date`) VALUES (12, 'duckduckgrayduck', 'goose', 's.evans@sd.com', 'Selena', 'Evans', 1, 12, 'Coffee ninja. Certified web nerd. Future teen idol. Problem solver. Friendly pop culture fanatic.', 'https://findnicknames.com/wp-content/uploads/2017/05/Nicknames-for-girls.jpeg', 'user', '2020-04-28');

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `category` (`id`, `type`, `short_description`, `enabled`, `description`, `image_url`) VALUES (1, 'Mountain Activities', 'where dat mountain at', 1, 'these activities require a friggin mountain', 'https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Ftrevornace%2Ffiles%2F2015%2F11%2Fmt-everest-sunset-1200x675.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `activity`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `activity` (`id`, `title`, `short_description`, `enabled`, `creator_id`, `description`, `image_url`, `equipment_level`, `equipment_desciption`, `create_date`) VALUES (1, 'Hiking', 'Neature Walk Together', 1, 1, 'Let\'s go on a wonderful neature walk together', 'https://www.outsideonline.com/sites/default/files/styles/img_600x600/public/2020/04/17/thru-hike-vlog_s.jpg?itok=ZM4vc4JG', 'low', 'You will need a water source and good hiking boots', '2020-04-28');

COMMIT;


-- -----------------------------------------------------
-- Data for table `resource`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `resource` (`id`, `name`, `description`, `link`, `image_url`) VALUES (1, 'Hiking for Beginnners', 'Unlike walking on a treadmill or paved path, hiking involves more, sometimes unpredictable, variables. Of course, these variables are part of what makes it so enjoyable! Use the following hiking tips to make your first treks successful', 'https://blog.liftopia.com/10-essential-hiking-tips-beginner-hike/', 'https://10c9rz12yp4b46gr6a2oxgub-wpengine.netdna-ssl.com/wp-content/uploads/DSC_0879.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_group`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `user_group` (`id`, `name`, `short_description`, `enabled`, `description`, `image_url`, `create_date`) VALUES (1, 'Hikers United', 'For Hardcore Hiking', 1, 'This group is for people that want to go on long hikes', 'https://i1.wp.com/s3.amazonaws.com/passionpassport-1/wp-content/uploads/2020/02/12131422/oziel-gomez-IbLZjKcelpM-unsplash.jpg?fit=1068%2C638&ssl=1', '2020-04-28');

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

COMMIT;


-- -----------------------------------------------------
-- Data for table `activity_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `activity_comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `activity_id`, `in_reply_id`) VALUES (1, 'dude hiking is my passion', '2020-04-28', 1, 1, 1, NULL);
INSERT INTO `activity_comment` (`id`, `content`, `create_date`, `enabled`, `user_id`, `activity_id`, `in_reply_id`) VALUES (2, 'highly doubtful, prove it n00b', '2020-04-28', 1, 2, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `activity_resource`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `activity_resource` (`activity_id`, `resource_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `event`
-- -----------------------------------------------------
START TRANSACTION;
USE `distancingdb`;
INSERT INTO `event` (`id`, `creator_id`, `activity_id`, `title`, `event_time`, `event_date`, `short_description`, `enabled`, `location_id`, `description`, `image_url`, `create_date`) VALUES (1, 1, 1, 'Hiking at a Distance', '13:30:00', '2020-04-28', 'Hike in the nice weather', 1, 1, 'This is a chance to enjoy the nice weather but also respect distancing', 'https://res.cloudinary.com/fittco/image/upload/v1557513288/owpinvepmd0at6vjbmhx.jpg', '2020-04-28');

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

