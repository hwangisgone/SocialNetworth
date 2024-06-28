INSERT INTO social_user (email, password, phone, name, name_tag, bio, registration_date, avatar_url, role)
VALUES ('giant@example.com', 'troll', '123-456-7890', 'Giant', 'giant', 'Bio of Giant', '2024-05-15', 'https://i.imgur.com/GCBVgXD.jpeg', 'user');

-- -- Insert mock data into the 'reply_post' table
-- INSERT INTO reply_post (post_id_parent, post_id_child) VALUES
--     (1, 2), -- Post 2 is a reply to post 1
--     (1, 3), -- Post 3 is a reply to post 1
--     (3, 5); -- Post 5 is a reply to post 3
	

-- Insert mock data into user table
INSERT INTO social_user (email, password, phone, name, name_tag, bio, registration_date, avatar_url, role) VALUES
    ('user1@example.com', 'password1', '1234567890', 'Trinh Diem Quynh', 'quynhtd', 'Hello.', '2024-03-29', 'https://i.imgur.com/ng36sqK.jpeg', 'user'),
    ('user2@example.com', 'password2', '9876543210', 'Jane Austen', 'janeausten', 'Nice to meet you!', '2024-03-29', 'https://i.imgur.com/ng36sqK.jpeg', 'user'),
    ('admin@example.com', 'admin', NULL, 'Admin User', 'admin', 'I am the admin.', '2024-03-29', 'https://i.imgur.com/0VGAy.png', 'admin');


INSERT INTO post (content, time_posted, time_edited, user_id) VALUES
    ('Hello World 2', '2024-06-18 03:43:20.5398+08', NULL, 1),
    ('Hello World 3', '2024-06-18 03:43:24.193798+08', NULL, 1),
    ('Hello World 4', '2024-06-18 03:43:25.970034+08', NULL, 1),
    ('Hello World 5', '2024-06-18 03:43:27.508246+08', NULL, 1),
    ('World 5 hello', '2024-06-18 03:43:35.177305+08', NULL, 1),
    ('more', '2024-06-21 13:59:52.293401+08', NULL, 1),
    ('New post', '2024-06-28 22:28:31.73447+08', NULL, 1);
    
INSERT INTO post (content, time_posted, time_edited, user_id) VALUES
    ('Lovely day here', '2024-06-18 11:31:43.879375+08', NULL, 2),
    ('Kitty', '2024-06-18 11:32:41.435514+08', NULL, 2),
    ('Today I learned that in Finland they use to have about 4,000 reindeer/ car accidents a year so they paint their antlers with reflective paint and this is vaguely terrifying', '2024-06-18 11:32:45.722099+08', NULL, 2);

INSERT INTO reaction (reaction_type, user_id, post_id) VALUES
    ('L', 2, 1),
    ('L', 2, 2),
    ('L', 3, 1),
    ('L', 1, 1);