INSERT INTO appliance (name, country, manufacturer, is_online_order, installment, appliance_attributes)
VALUES ('Телевизор', 'Япония', 'Sony Corporation', true, true, '{"technology":"string","category":"string"}'),
       ('Телевизор', 'Китай', 'TCL Corporation', true, false, '{"technology":"string","category":"string"}'),
       ('Телевизор', 'Япония', 'Panasonic Corporation', false, true, '{"technology":"string","category":"string"}'),
       ('Смартфон', 'Южная Корея', 'Samsung Electronics Co., Ltd.', true, true, '{"cameras_number":4,"memory":256}'),
       ('Смартфон', 'Китай', 'OnePlus Technology', true, false, '{"cameras_number":4,"memory":256}'),
       ('Смартфон', 'Китай', 'Huawei Technologies Co., Ltd.', false, true, '{"cameras_number":4,"memory":256}'),
       ('Пылесос', 'Япония', 'Panasonic Corporation', true, false, '{"modes_number":4,"dust_bag_volume":2.5}'),
       ('Пылесос', 'Германия', 'Miele & Cie. KG', true, true,'{"modes_number":4,"dust_bag_volume":2.5}' ),
       ('Пылесос', 'Великобритания', 'Dyson Ltd.', false, true, '{"modes_number":4,"dust_bag_volume":2.5}'),
       ('Холодильник', 'Южная Корея', 'LG Electronics Inc.', true, false, '{"doors_number":2,"compressor_type":"string"}'),
       ('Холодильник', 'США', 'Whirlpool Corporation', true, true, '{"doors_number":2,"compressor_type":"string"}'),
       ('Холодильник', 'Швеция', 'Electrolux AB', true, true, '{"doors_number":2,"compressor_type":"string"}'),
       ('Компьютер', 'США', 'Apple Inc.', true, true, '{"processor_type":"string","category":"string"}'),
       ('Компьютер', 'Китай', 'Lenovo Group Limited', true, true, '{"processor_type":"string","category":"string"}'),
       ('Компьютер', 'Тайвань', 'ASUS', true, true, '{"processor_type":"string","category":"string"}');

INSERT INTO model (name, serial_number, color, size, price, is_available, model_attributes , appliance_id)
VALUES ('QN65Q90TAFXZA', 'GHIJ-KLMN-11718-OPQR', 'Красный', 32, 99990, true,'{"technology":"FullHD","category":"Edge LED"}', 1),
       ('OLED65C1PUB', 'KJHGF-VBNM-LMVAA-LPOI', 'Черный', 55, 189990, true, '{"technology":"FullHD","category":"Direct LED"}', 1),
       ('XBR-55A8H', 'ABCD-EFGH-AKFAO-AIFK', 'Желтый', 43, 159990, false, '{"technology":"4K UltraHD","category":"Edge LED"}', 2),
       ('55R635', 'MNOP-QRST-VAVLA-UVWX', 'Желтый', 32, 59990, true, '{"technology":"HD Ready","category":"Direct LED"}', 2),
       ('55U7G', '1234-5678-AFAFAF-ABCD', 'Черный', 55, 38999, true, '{"technology":"4K UltraHD","category":"QLED"}', 3),
       ('OLED65C9PUA', 'EFGH-IJKL-KAFI-MNOP', 'Черный', 43, 39990, false, '{"technology":"FullHD","category":"QLED"}', 3);

INSERT INTO model (name, serial_number, color, size, price, is_available, model_attributes, appliance_id)
VALUES ('iPhone 13 Pro', 'QRST-UVWX-AFEWQ-1234', 'Черный', 6.1, 92990, true, '{"cameras_number":4,"memory":128}', 4),
       ('iPhone 14 Pro Max', '5678-ABCD-HOUJ-EFGH', 'Зеленый', 6.7, 99990, true, '{"cameras_number":2,"memory":512}', 4),
       ('Redmi Note 11S', 'IJKL-MNOP-NLJAA-QRST', 'Серый', 6.43, 69990, false, '{"cameras_number":3,"memory":128}', 5),
       ('Redmi Note 10 Pro', 'WSXZA-QWERT-LNJKV-POIU', 'Серый', 6.67, 94990, true, '{"cameras_number":4,"memory":256}', 5),
       ('Galaxy S21 Ultra', 'ZXCVD-FGHJK-LVAAQ-WQER', 'Синий', 6.8, 69990, true, '{"cameras_number":4,"memory":512}', 6),
       ('Galaxy S21 FE', 'TYUIO-PLMN-JPIAA-EDCB', 'Черный', 6.4, 79990, false, '{"cameras_number":3,"memory":512}', 6);


INSERT INTO model (name, serial_number, color, size, price, is_available, model_attributes,
                   appliance_id)
VALUES ('PVC 2004RI', 'GHIJ-KLMN-LVMKL-OPQR', 'Голубой', 5, 8999, true, '{"modes_number":1,"dust_bag_volume":2.5}', 7),
       ('PVC 1834 Silent', 'UVWX-1234-HUOQK-CDEF', 'Белый', 6, 10999, false, '{"modes_number":1,"dust_bag_volume":1.7}', 7),
       ('SC4570', 'ABCD-EFGH-KAIQQ-IJKL', 'Черный', 6, 6999, true, '{"modes_number":1,"dust_bag_volume":1.3}', 8),
       ('SC4520', 'MNOP-QRST-11718-UVWX', 'Синий', 6, 8399, true, '{"modes_number":2,"dust_bag_volume":2.5}', 8),
       ('Twin Tiger', '5678-ABCD-QPOOV-EFGH', 'Оранжевый', 6, 18999, false, '{"modes_number":2,"dust_bag_volume":2}', 9),
       ('DRYBOX AMFIBIA FAMILY', 'QRST-UVWX-KNAVI-1234', 'Черный', 8, 33999, true, '{"modes_number":2,"dust_bag_volume":2.6}', 9);


INSERT INTO model (name, serial_number, color, size, price, is_available, model_attributes,
                   appliance_id)
VALUES ('RF28K9380SG', 'IJKL-MNOP-ANOKA-QRST', 'Черный', 262, 159990, true, '{"doors_number":2,"compressor_type":"Линейный"}', 10),
       ('LMXS30796S', 'EFGH-IJKL-VNAKL-MNOP', 'Коричневый', 351, 249990, false, '{"doors_number":2,"compressor_type":"Инверторный"}', 10),
       ('WRX735SDHZ', 'ZXCVD-FGHJK-67812-WQER', 'Желтый', 364, 129990, true, '{"doors_number":2,"compressor_type":"Линейный"}', 11),
       ('B36CL80SNS', 'ABCD-EFGH-AVMLA-IJKL', 'Голубой', 364, 219990, true, '{"doors_number":2,"compressor_type":"Линейный"}', 11),
       ('FFSS2615TE', 'MNOP-QRST-56711-UVWX', 'Белый', 332, 79990, false, '{"doors_number":1,"compressor_type":"Инверторный"}', 12),
       ('HRF15N3AGS', 'GHIJ-KLMN-AVKMM-OPQR', 'Красный', 535, 169990, true, '{"doors_number":1,"compressor_type":"Инверторный"}', 12);

INSERT INTO model (name, serial_number, color, size, price, is_available, model_attributes, appliance_id)
VALUES ('XPS 13', 'YUIOP-LKJH-87011-NBVC', 'Красный', 512, 99990, false, '{"processor_type":"Core i5-11400F","category":"Игровой"}', 13),
       ('Spectre x360', 'POIUY-TREW-98711-MNBV', 'Черный', 1024, 89990, true, '{"processor_type":"Core i3-10105","category":"Офисный"}', 13),
       ('ROG Zephyrus G14', 'ZXCVD-FGHJK-71913-WQER', 'Голубой', 512, 89990, false, '{"processor_type":"Core i5-12400F","category":"Игровой"}', 14),
       ('Surface Laptop 4', 'PQ-RSTU-67905-VWXY', 'Черный', 1024, 103999, true, '{"processor_type":"Core i5-12400F","category":"Игровой"}', 14),
       ('Predator Helios 300', 'FGH-IJKL-24010-MNOP', 'Фиолетовый', 512, 99990, true, '{"processor_type":"Core i3-10105","category":"Офисный"}', 15),
       ('MateBook X Pro', 'TUVW-XYZA-11718-BCDE', 'Черный', 512, 99990, false, '{"processor_type":"Core i5-10400","category":"Офисный"}', 15);