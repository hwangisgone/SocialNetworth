CREATE TABLE customer (
    user_id SERIAL PRIMARY KEY,
    name_tag VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    bio TEXT,
    role varchar not null,
    phone VARCHAR(20),
    registration_date date not null,
	check (role = 'user' or role = 'admin')
);
