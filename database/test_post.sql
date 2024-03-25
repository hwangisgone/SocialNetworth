select create_post('2','This is a new post');
SELECT modify_post('4', '3', 'Updated content');

select delete_post('5','3');
select * from post;
select * from reply_post;
select * from reaction;
drop function retrieve_posts_reply(int);

select retrieve_posts_reply('1');

insert into reply_post values('1', '6');

drop function toggle_reaction(int, int);
select toggle_reaction(1,1);

select create_post()
drop function create_post( int, text);