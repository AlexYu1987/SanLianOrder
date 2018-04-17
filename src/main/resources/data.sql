/*代理等级插入*/
INSERT INTO t_dealer_level (id, name, amount, comment) VALUES (100, 'Province Dealer', 10000, 'Level 1');
INSERT INTO t_dealer_level (id, name, amount, comment) VALUES (200,'City Dealer', 5000, 'Level 2');

/*商品信息插入*/
INSERT INTO t_commodity (id, name) VALUES (100, 'danhuangsu');
INSERT INTO t_commodity (id, name) VALUES (101, 'niuzhatang');
INSERT INTO t_commodity (id, name) VALUES (102, 'qingtuan');

/*代理地址信息插入*/
INSERT INTO t_contact_info (id, city, female, name, phone, province, street) VALUES
  (101,'NJ', FALSE ,'Little Rabbit', '13913900234', 'JS', 'Andemen Street NO.101');

/*代理信息插入*/
INSERT INTO t_dealer (id, nick_name, create_time, wechat_open_id,level_id, contact_id, balance) VALUES
(101, 'Little Rabbit',CURRENT_TIMESTAMP(), 'ealsd23fekcat', 100, 101, 10000);

/*价格数据*/
INSERT INTO t_commodity_Price (dealer_level_id, commodity_id, price) VALUES (100, 100, 100);
INSERT INTO t_commodity_Price (dealer_level_id, commodity_id, price) VALUES (100, 101, 200);
INSERT INTO t_commodity_Price (dealer_level_id, commodity_id, price) VALUES (100, 102, 300);
INSERT INTO t_commodity_Price (dealer_level_id, commodity_id, price) VALUES (200, 100, 150);
INSERT INTO t_commodity_Price (dealer_level_id, commodity_id, price) VALUES (200, 101, 250);
INSERT INTO t_commodity_Price (dealer_level_id, commodity_id, price) VALUES (200, 102, 350);