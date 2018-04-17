SET FOREIGN_KEY_CHECKS=0;
INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('909719474314252289', '纹绣快约', 'studio.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('909719474314252290', '909719474314252289', '添加店铺', 'studio.create'),
('909719474314252291', '909719474314252289', '修改店铺', 'studio.edit'),
('909719474314252292', '909719474314252289', '删除店铺', 'studio.delete'),
('909719474314252293', '909719474314252289', '查询订单', 'appointment.query');


insert into t_ad_group (id,name) values (1, 'carousel'),(2, 'banner');
