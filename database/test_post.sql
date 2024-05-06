insert into "user"(email, password, name, name_tag, registration_date, role) 
values('125@emgkjl', 'lhn1', 'lhn1', 'hn1', '01-01-2023', 'user'),
('122@emgkjl', 'lhn2', 'lhn2', 'hn2', '01-01-2023', 'user'),
('128@emgkjl', 'lhn3', 'lhn3', 'hn3', '01-01-2023', 'user');


select * from "user";
delete from "user";

select * from post;
select * from reaction;
DROP TRIGGER IF EXISTS follow_trigger ON follow;
drop trigger if exists update_follow_count_on_delete_trigger on follow;

delete from post;
insert into post(content, user_id) values('New post two', 1);

insert into reaction(user_id, post_id) values(1,1);
select * from follow;

delete from follow where 
user_id_follow = 8;

insert into follow values(7,8);
insert into follow values(7,9);

