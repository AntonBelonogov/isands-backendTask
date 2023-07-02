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
VALUES ('QN65Q90TAFXZA', 'GHIJ-KLMN-11718-OPQR', 'Красный', 32, 99990, true, 'FullHD', 'Edge LED', 1),
       ('OLED65C1PUB', 'KJHGF-VBNM-LMVAA-LPOI', 'Черный', 55, 189990, true, '4K UltraHD', 'Edge LED', 1),
       ('XBR-55A8H', 'ABCD-EFGH-AKFAO-AIFK', 'Желтый', 43, 159990, false, '4K UltraHD', 'Direct LED', 2),
       ('55R635', 'MNOP-QRST-VAVLA-UVWX', 'Желтый', 32, 59990, true, 'HD Ready', 'Direct LED', 2),
       ('55U7G', '1234-5678-AFAFAF-ABCD', 'Черный', 55, 38999, true, '4K UltraHD', 'QLED', 3),
       ('OLED65C9PUA', 'EFGH-IJKL-KAFI-MNOP', 'Черный', 43, 39990, false, '4K UltraHD', 'QLED', 3);

INSERT INTO model (name, serial_number, color, size, price, is_available, p_memory, p_cameras_number, appliance_id)
VALUES ('iPhone 13 Pro', 'QRST-UVWX-AFEWQ-1234', 'Черный', 6.1, 92990, true, 128, 2, 4),
       ('iPhone 14 Pro Max', '5678-ABCD-HOUJ-EFGH', 'Зеленый', 6.7, 99990, true, 256, 3, 4),
       ('Redmi Note 11S', 'IJKL-MNOP-NLJAA-QRST', 'Серый', 6.43, 69990, false, 128, 4, 5),
       ('Redmi Note 10 Pro', 'WSXZA-QWERT-LNJKV-POIU', 'Серый', 6.67, 94990, true, 128, 4, 5),
       ('Galaxy S21 Ultra', 'ZXCVD-FGHJK-LVAAQ-WQER', 'Синий', 6.8, 69990, true, 512, 4, 6),
       ('Galaxy S21 FE', 'TYUIO-PLMN-JPIAA-EDCB', 'Черный', 6.4, 79990, false, 256, 3, 6);

INSERT INTO model (name, serial_number, color, size, price, is_available, vc_dust_bag_volume, vc_modes_number,
                   appliance_id)
VALUES ('PVC 2004RI', 'GHIJ-KLMN-LVMKL-OPQR', 'Голубой', 5, 8999, true, 2.5, 1, 7),
       ('PVC 1834 Silent', 'UVWX-1234-HUOQK-CDEF', 'Белый', 6, 10999, false, 1.7, 1, 7),
       ('SC4570', 'ABCD-EFGH-KAIQQ-IJKL', 'Черный', 6, 6999, true, 1.3, 1, 8),
       ('SC4520', 'MNOP-QRST-11718-UVWX', 'Синий', 6, 8399, true, 1.3, 1, 8),
       ('Twin Tiger', '5678-ABCD-QPOOV-EFGH', 'Оранжевый', 6, 18999, false, 4, 2, 9),
       ('DRYBOX AMFIBIA FAMILY', 'QRST-UVWX-KNAVI-1234', 'Черный', 8, 33999, true, 2.6, 2, 9);

INSERT INTO model (name, serial_number, color, size, price, is_available, f_doors_number, f_compressor_type,
                   appliance_id)
VALUES ('RF28K9380SG', 'IJKL-MNOP-ANOKA-QRST', 'Черный', 262, 159990, true, 2, 'Линейный', 10),
       ('LMXS30796S', 'EFGH-IJKL-VNAKL-MNOP', 'Коричневый', 351, 249990, false, 2, 'Инверторный', 10),
       ('WRX735SDHZ', 'ZXCVD-FGHJK-67812-WQER', 'Желтый', 364, 129990, true, 2, 'Линейный', 11),
       ('B36CL80SNS', 'ABCD-EFGH-AVMLA-IJKL', 'Голубой', 364, 219990, true, 2, 'Линейный', 11),
       ('FFSS2615TE', 'MNOP-QRST-56711-UVWX', 'Белый', 332, 79990, false, 2, 'Инверторный', 12),
       ('HRF15N3AGS', 'GHIJ-KLMN-AVKMM-OPQR', 'Красный', 535, 169990, true, 2, 'Инверторный', 12);

INSERT INTO model (name, serial_number, color, size, price, is_available, pc_category, pc_processor_type, appliance_id)
VALUES ('XPS 13', 'YUIOP-LKJH-87011-NBVC', 'Красный', 512, 99990, false, 'Игровой', 'Core i5-11400F', 13),
       ('Spectre x360', 'POIUY-TREW-98711-MNBV', 'Черный', 1024, 89990, true, 'Офисный', 'Core i3-10105', 13),
       ('ROG Zephyrus G14', 'ZXCVD-FGHJK-71913-WQER', 'Голубой', 512, 89990, false, 'Игровой', 'Core i5-12400F', 14),
       ('Surface Laptop 4', 'PQ-RSTU-67905-VWXY', 'Черный', 1024, 103999, true, 'Игровой', 'Core i5-12400F', 14),
       ('Predator Helios 300', 'FGH-IJKL-24010-MNOP', 'Фиолетовый', 512, 99990, true, 'Офисный', 'Core i3-10105', 15),
       ('MateBook X Pro', 'TUVW-XYZA-11718-BCDE', 'Черный', 512, 99990, false, 'Офисный', 'Core i5-10400', 15);