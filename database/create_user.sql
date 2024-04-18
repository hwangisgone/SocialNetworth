CREATE TABLE "user" (
	user_id		int		PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	email		VARCHAR(100) NOT NULL,
	password	VARCHAR(100) NOT NULL,
	phone		VARCHAR(20),

	name		VARCHAR(100) NOT NULL,
	name_tag	VARCHAR(50) UNIQUE NOT NULL,
	bio			TEXT,
	registration_date DATE NOT NULL,

	role		VARCHAR NOT NULL,
	follow_count int DEFAULT 0, 
	followed_count int DEFAULT 0,
	check (role = 'user' or role = 'admin')
);

CREATE TABLE follow (
	user_id	int REFERENCES "user"(user_id) ON DELETE CASCADE,
	user_id_follow	int REFERENCES "user"(user_id) ON DELETE CASCADE,
	PRIMARY KEY (user_id, user_id_follow)
);
