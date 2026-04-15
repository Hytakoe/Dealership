CREATE DATABASE dealership;

\c dealership;

CREATE TABLE vehicles (
                          id SERIAL PRIMARY KEY,
                          brand VARCHAR(50) NOT NULL,
                          model VARCHAR(50) NOT NULL,
                          year INT NOT NULL,
                          price DECIMAL(10,2) NOT NULL,
                          color VARCHAR(30) NOT NULL,
                          sold BOOLEAN DEFAULT FALSE,
                          vehicle_type VARCHAR(20) NOT NULL,

                          body_type VARCHAR(30),
                          door_count INT,
                          passenger_capacity INT,
                          transmission VARCHAR(20),
                          engine_volume DECIMAL(3,1),
                          fuel_type VARCHAR(20),

                          truck_type VARCHAR(30),
                          load_capacity DECIMAL(8,2),
                          axle_count INT,
                          cargo_volume DECIMAL(8,2),
                          has_trailer_hitch BOOLEAN,
                          body_material VARCHAR(30)
);

CREATE TABLE customers (
                           id SERIAL PRIMARY KEY,
                           full_name VARCHAR(100) NOT NULL,
                           age INT NOT NULL,
                           gender VARCHAR(10) NOT NULL,
                           phone VARCHAR(20) NOT NULL,
                           email VARCHAR(100),
                           address VARCHAR(200)
);

CREATE TABLE sales (
                       id SERIAL PRIMARY KEY,
                       vehicle_id INT NOT NULL REFERENCES vehicles(id),
                       customer_id INT NOT NULL REFERENCES customers(id),
                       sale_date TIMESTAMP NOT NULL,
                       sale_price DECIMAL(10,2) NOT NULL
);