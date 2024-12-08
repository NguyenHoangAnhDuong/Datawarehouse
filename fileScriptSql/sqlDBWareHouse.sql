-- Tạo bảng date_dim
CREATE TABLE date_dim (
    id INTEGER PRIMARY KEY,
    full_date DATE NOT NULL,
    day_of_week VARCHAR(20),
    month VARCHAR(20),
    year VARCHAR(4)
);

-- Tạo bảng source_dim
CREATE TABLE source_dim (
    id INTEGER PRIMARY KEY,
    source VARCHAR(10)
);

-- Tạo bảng fact_gold_prices
CREATE TABLE fact_gold_prices (
    id INTEGER PRIMARY KEY,
    id_date INTEGER NOT NULL,
    id_source INTEGER NOT NULL,
    type_name VARCHAR(255),
    region VARCHAR(100),
    buy_value DECIMAL(10,2),
    sell_value DECIMAL(10,2),
    comparison_buy DECIMAL(10,2),
    comparison_sell DECIMAL(10,2),
    percentage_buy DECIMAL(5,2),
    percentage_sell DECIMAL(5,2),
    FOREIGN KEY (id_date) REFERENCES date_dim(id),
    FOREIGN KEY (id_source) REFERENCES source_dim(id)
);


