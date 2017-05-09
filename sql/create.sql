CREATE SCHEMA `xw_test` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `xw_test`.`leakinfo` (
  `id` VARCHAR(32) NOT NULL,
  `apk_version` VARCHAR(45) NULL COMMENT 'apk版本',
  `apk_packagename` VARCHAR(200) NULL COMMENT 'apk包名',
  `android_version` VARCHAR(45) NULL COMMENT 'android版本',
  `device_name` VARCHAR(200) NULL COMMENT '设备名称',
  `leakinfo` VARCHAR(2000) NULL COMMENT '内存泄露信息',
  PRIMARY KEY (`id`));
ALTER TABLE `xw_test`.`leakinfo` 
ADD COLUMN `hump_path` VARCHAR(100) NULL AFTER `leakinfo`;
ALTER TABLE `xw_test`.`leakinfo` 
ADD COLUMN `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP AFTER `hump_path`;
