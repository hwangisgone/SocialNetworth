CREATE TABLE post (
	post_id			int		PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	content			text,
	time_posted		timestamp with time zone DEFAULT now(),		-- Equivalent type is OffsetDateTime
	time_edited		timestamp with time zone DEFAULT now(),		-- https://stackoverflow.com/questions/75492508/should-i-use-instant-or-datetime-or-localdatetime-in-java-entities/75498773#75498773

	like_count		int DEFAULT 0, 
	reply_count		int DEFAULT 0,
	share_count		int DEFAULT 0,

	user_id		int NOT NULL REFERENCES social_user(user_id) ON DELETE CASCADE
);

CREATE TABLE reaction (
	reaction_type char NOT NULL DEFAULT 'L',

	user_id	int REFERENCES social_user(user_id) ON DELETE CASCADE,
	post_id	int REFERENCES post(post_id) ON DELETE CASCADE,
	PRIMARY KEY (user_id, post_id)
);

CREATE TABLE reply_post(
	post_id_parent	int REFERENCES post(post_id) ON DELETE CASCADE,
	post_id_child	int REFERENCES post(post_id) ON DELETE CASCADE,
	PRIMARY KEY (post_id_parent, post_id_child),

	CHECK (post_id_parent < post_id_child)  -- Ensures child appears after parent, avoid circular reference
);



-- Maybe use later
CREATE TABLE share_post(
	user_id	int REFERENCES social_user(user_id) ON DELETE CASCADE,
	post_id	int REFERENCES post(post_id) ON DELETE CASCADE
);


