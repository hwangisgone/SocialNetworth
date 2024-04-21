CREATE TABLE social_user (
	user_id		int		PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	email		VARCHAR(100) NOT NULL,
	password	VARCHAR(100) NOT NULL,
	phone		VARCHAR(20),

	name		VARCHAR(100) NOT NULL,
	name_tag	VARCHAR(50) UNIQUE NOT NULL,
	bio			TEXT,
	registration_date DATE NOT NULL,

	role		VARCHAR NOT NULL,
	check (role = 'user' or role = 'admin')
);

CREATE TABLE follow (
	user_id	int REFERENCES social_user(user_id),
	follower_id	int REFERENCES social_user(user_id),
	PRIMARY KEY (user_id, follower_id),
	
	CHECK (user_id < follower_id)
);