CREATE TABLE post (
	post_id		int		PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	content		text,
	time_posted timestamp DEFAULT now(),
	time_edited timestamp DEFAULT now(),

	-- user_id			int,
	-- FOREIGN KEY (user_id) REFERENCES user(user_id),
);

CREATE TABLE like{
	
}

CREATE TABLE comment (
	id				int				PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	reply_to	int				REFERENCES comment,
	content		text,
	time_entered timestamp DEFAULT now(),

	post_id		int,
	FOREIGN KEY (post_id) REFERENCES post(post_id),

	-- user_id			int,
	-- FOREIGN KEY (user_id) REFERENCES user(user_id),
	
	CHECK (reply_to <> id)
);