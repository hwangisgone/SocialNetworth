CREATE TABLE post (
	post_id			int		PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	content			text,
	time_posted		timestamp DEFAULT now(),
	time_edited		timestamp DEFAULT now(),

	share_count		int DEFAULT 0, 
	like_count		int DEFAULT 0,
	comment_count	int DEFAULT 0,

	user_id	int -- REFERENCES user(user_id),
);

CREATE TABLE reaction(
	reaction_type char NOT NULL DEFAULT 'H',

	user_id	int, -- REFERENCES user(user_id),
	post_id	int REFERENCES post(post_id),
	PRIMARY KEY (user_id, post_id)
);

CREATE TABLE reply_post(
	post_id_parent	int REFERENCES post(post_id),
	post_id_child	int REFERENCES post(post_id),
	PRIMARY KEY (post_id_parent, post_id_child),

	CHECK (post_id_parent < post_id_child)  -- Ensures child appears after parent, avoid circular reference
);



-- Maybe use later
CREATE TABLE share_post(
	user_id	int, -- REFERENCES user(user_id),
	post_id	int REFERENCES post(post_id)
);


