INSERT INTO social_user (email, password, phone, name, name_tag, bio, registration_date, role, follow_count, followed_count)
VALUES ('giant@example.com', 'troll', '123-456-7890', 'Giant', 'giant', 'Bio of Giant', '2024-05-15', 'user', 0, 0);

-- -- Insert mock data into the 'reply_post' table
-- INSERT INTO reply_post (post_id_parent, post_id_child) VALUES
--     (1, 2), -- Post 2 is a reply to post 1
--     (1, 3), -- Post 3 is a reply to post 1
--     (3, 5); -- Post 5 is a reply to post 3
	

-- Insert mock data into user table
INSERT INTO social_user (email, password, phone, name, name_tag, bio, registration_date, role) VALUES
    ('user1@example.com', 'password1', '1234567890', 'Trinh Diem Quynh', 'quynhtd', 'Hello.', '2024-03-29', 'user'),
    ('user2@example.com', 'password2', '9876543210', 'Jane Austen', 'janeausten', 'Nice to meet you!', '2024-03-29', 'user'),
    ('admin@example.com', 'adminpassword', NULL, 'Admin User', 'admin', 'I am the admin.', '2024-03-29', 'admin');
