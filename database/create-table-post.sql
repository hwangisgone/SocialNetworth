
CREATE TABLE post (
	post_id		int		PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	content		text,
	time_posted timestamp DEFAULT now(),
	time_edited timestamp DEFAULT now(),
	share_count int default 0, 
	like_count int default 0,
	comment_count int default 0,
	user_id			int
	-- FOREIGN KEY (user_id) REFERENCES user(user_id),
);

create table share_post(
	user_id int,
	post_id int,
	foreign key (post_id) references post(post_id)
);

create table reaction(
	user_id int, 
	post_id int,
	time_react timestamp DEFAULT now(),
	reaction_type char not null default 'H',
	foreign key (post_id) references post(post_id)
);

create table reply_post(
	post_id_parent int, 
	post_id_child int, 
	foreign key(post_id_parent) references post(post_id),
	foreign key(post_id_child) references post(post_id)
);
