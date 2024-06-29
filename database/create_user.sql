CREATE TABLE social_user (
	user_id		int		PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	email		VARCHAR(100) NOT NULL,
	password	VARCHAR(100) NOT NULL,
	phone		VARCHAR(20),

	name		VARCHAR(100) NOT NULL,
	name_tag	VARCHAR(50) UNIQUE NOT NULL,
	bio			TEXT,
	registration_date DATE NOT NULL,
	avatar_url  VARCHAR(200),

	role				VARCHAR NOT NULL,
	follow_count		int DEFAULT 0, 
	followed_count		int DEFAULT 0,
	check (role = 'user' or role = 'admin')
);

CREATE TABLE follow (
	user_id			int REFERENCES social_user(user_id) ON DELETE CASCADE,
	follower_id		int REFERENCES social_user(user_id) ON DELETE CASCADE,
	PRIMARY KEY (user_id, follower_id)
);
