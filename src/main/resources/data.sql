INSERT INTO appliance (name, country, manufacturer, is_online_order, installment)
VALUES ('Телевизор', 'Япония', 'Sony Corporation', true, true),
       ('Телевизор', 'Китай', 'TCL Corporation', true, false),
       ('Телевизор', 'Япония', 'Panasonic Corporation', false, true),
       ('Смартфон', 'Южная Корея', 'Samsung Electronics Co., Ltd.', true, true),
       ('Смартфон', 'Китай', 'OnePlus Technology', true, false),
       ('Смартфон', 'Китай', 'Huawei Technologies Co., Ltd.', false, true),
       ('Пылесос', 'Япония', 'Panasonic Corporation', true, false),
       ('Пылесос', 'Германия', 'Miele & Cie. KG', true, true),
       ('Пылесос', 'Великобритания', 'Dyson Ltd.', false, true),
       ('Холодильник', 'Южная Корея', 'LG Electronics Inc.', true, false),
       ('Холодильник', 'США', 'Whirlpool Corporation', true, true),
       ('Холодильник', 'Швеция', 'Electrolux AB', true, true),
       ('Компьютер', 'США', 'Apple Inc.', true, true),
       ('Компьютер', 'Китай', 'Lenovo Group Limited', true, true),
       ('Компьютер', 'Тайвань', 'ASUS', true, true);

INSERT INTO model (name, serial_number, color, size, price, is_available, tv_category, tv_technology, appliance_id)
VALUES ('QN65Q90TAFXZA', 'WXYZ-1234-11718-5678', 'Красный', 32, 99990, true, 'FullHD', 'Edge LED', 1),
       ('OLED65C1PUB', 'LMN-OPQR-11718-STUV', 'Черный', 55, 189990, true, '4K UltraHD', 'Edge LED', 1),
       ('XBR-55A8H', '9012-3456-11718-7890', 'Желтый', 43, 159990, false, '4K UltraHD', 'Direct LED', 2),
       ('55R635', 'PQRS-TUVW-11718-XYZA', 'Желтый', 32, 59990, true, 'HD Ready', 'Direct LED', 2),
       ('55U7G', 'BCDE-FGHI-11718-JKLM', 'Черный', 55, 38999, true, '4K UltraHD', 'QLED', 3),
       ('OLED65C9PUA', 'NOPQ-RSTU-11718-VWXY', 'Черный', 43, 39990, false, '4K UltraHD', 'QLED', 3);

INSERT INTO model (name, serial_number, color, size, price, is_available, p_memory, p_cameras_number, appliance_id)
VALUES ('iPhone 13 Pro', '1234-5678-11718-90AB', 'Черный', 6.1, 92990, true, 128, 2, 4),
       ('iPhone 14 Pro Max', 'CDEF-GHIJ-11718-KLMN', 'Зеленый', 6.7, 99990, true, 256, 3, 4),
       ('Redmi Note 11S', 'OPQR-STUV-11718-WXYZ', 'Серый', 6.43, 69990, false, 128, 4, 5),
       ('Redmi Note 10 Pro', '5678-9012-11718-3456', 'Серый', 6.67, 94990, true, 128, 4, 5),
       ('Galaxy S21 Ultra', 'UVWX-YZAB-11718-CDEF', 'Синий', 6.8, 69990, true, 512, 4, 6),
       ('Galaxy S21 FE', 'GHIJ-KLMN-11718-OPQR', 'Черный', 6.4, 79990, false, 256, 3, 6);

INSERT INTO model (name, serial_number, color, size, price, is_available, vc_dust_bag_volume, vc_modes_number,
                   appliance_id)
VALUES ('PVC 2004RI', 'STUV-WXYZ-11718-1234', 'Голубой', 5, 8999, true, 2.5, 1, 7),
       ('PVC 1834 Silent', '7890-5678-11718-9012', 'Белый', 6, 10999, false, 1.7, 1, 7),
       ('SC4570', 'YZAB-CDEF-11718-GHIJ', 'Черный', 6, 6999, true, 1.3, 1, 8),
       ('SC4520', 'KLMN-OPQR-11718-STUV', 'Синий', 6, 8399, true, 1.3, 1, 8),
       ('Twin Tiger', '3456-7890-11718-5678', 'Оранжевый', 6, 18999, false, 4, 2, 9),
       ('DRYBOX AMFIBIA FAMILY', 'DEFG-HIJK-11718-LMNO', 'Черный', 8, 33999, true, 2.6, 2, 9);

INSERT INTO model (name, serial_number, color, size, price, is_available, f_doors_number, f_compressor_type,
                   appliance_id)
VALUES ('RF28K9380SG', 'QRST-UVWX-11718-YZAB', 'Черный', 262, 159990, true, 2, 'Линейный', 10),
       ('LMXS30796S', '5678-9012-11718-3456', 'Коричневый', 351, 249990, false, 2, 'Инверторный', 10),
       ('WRX735SDHZ', 'LMNO-PQRS-11718-TUVW', 'Желтый', 364, 129990, true, 2, 'Линейный', 11),
       ('B36CL80SNS', '7890-1234-11718-5678', 'Голубой', 364, 219990, true, 2, 'Линейный', 11),
       ('FFSS2615TE', 'UVWX-YZAB-11718-CDEF', 'Белый', 332, 79990, false, 2, 'Инверторный', 12),
       ('HRF15N3AGS', '9012-3456-11718-7890', 'Красный', 535, 169990, true, 2, 'Инверторный', 12);

INSERT INTO model (name, serial_number, color, size, price, is_available, pc_category, pc_processor_type, appliance_id)
VALUES ('XPS 13', 'STUV-WXYZ-11718-1234', 'Красный', 512, 99990, false, 'Игровой', 'Core i5-11400F', 13),
       ('Spectre x360', 'GHIJ-KLMN-11718-OPQR', 'Черный', 1024, 89990, true, 'Офисный', 'Core i3-10105', 13),
       ('ROG Zephyrus G14', '5678-9012-11718-3456', 'Голубой', 512, 89990, false, 'Игровой', 'Core i5-12400F', 14),
       ('Surface Laptop 4', 'PQ-RSTU-67905-VWXY', 'Черный', 1024, 103999, true, 'Игровой', 'Core i5-12400F', 14),
       ('Predator Helios 300', 'FGH-IJKL-24010-MNOP', 'Фиолетовый', 512, 99990, true, 'Офисный', 'Core i3-10105', 15),
       ('MateBook X Pro', 'TUVW-XYZA-11718-BCDE', 'Черный', 512, 99990, false, 'Офисный', 'Core i5-10400', 15);