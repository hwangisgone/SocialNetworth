---1. Funciton retrieve all the post of some user

CREATE OR REPLACE FUNCTION retrieve_posts_by_user_id(
    user_id_param int
)
RETURNS JSONB
AS '
DECLARE
    posts_json JSONB;
BEGIN
    SELECT jsonb_agg(to_jsonb(posts.*))
    INTO posts_json
    FROM (
        SELECT
            * 
        FROM
            post
        WHERE
            user_id = user_id_param
    ) posts;

    RETURN posts_json;
END;
' LANGUAGE plpgsql;


---2. Trigger for share_post table
CREATE OR REPLACE FUNCTION update_share_count()
RETURNS TRIGGER AS '
BEGIN
    IF TG_OP = ''INSERT'' THEN
        UPDATE post
        SET share_count = share_count + 1
        WHERE post_id = NEW.post_id;
    ELSIF TG_OP = ''DELETE'' THEN
        UPDATE post
        SET share_count = share_count - 1
        WHERE post_id = OLD.post_id;
    END IF;
    RETURN NULL;
END;
' LANGUAGE plpgsql;

CREATE TRIGGER share_post_trigger
AFTER INSERT OR DELETE ON share_post
FOR EACH ROW
EXECUTE FUNCTION update_share_count();


--3. Trigger for reaction table
CREATE OR REPLACE FUNCTION update_reaction_count()
RETURNS TRIGGER AS '
BEGIN
    IF TG_OP = ''INSERT'' THEN
        UPDATE post
        SET like_count = like_count + 1
        WHERE post_id = NEW.post_id;
    ELSIF TG_OP = ''DELETE'' THEN
        UPDATE post
        SET like_count = like_count - 1
        WHERE post_id = OLD.post_id;
    END IF;
    RETURN NULL;
END;
' LANGUAGE plpgsql;

CREATE TRIGGER reaction_trigger
AFTER INSERT OR DELETE ON reaction
FOR EACH ROW
EXECUTE FUNCTION update_reaction_count();


--4. Trigger for reply_post table
CREATE OR REPLACE FUNCTION update_reply_count()
RETURNS TRIGGER AS '
BEGIN
    IF TG_OP = ''INSERT'' THEN
        UPDATE post
        SET reply_count = reply_count + 1
        WHERE post_id = NEW.post_id_parent;
    ELSIF TG_OP = ''DELETE'' THEN
        UPDATE post
        SET reply_count = reply_count - 1
        WHERE post_id = OLD.post_id_parent;
    END IF;
    RETURN NULL;
END;
' LANGUAGE plpgsql;

CREATE TRIGGER reply_post_trigger
AFTER INSERT OR DELETE ON reply_post
FOR EACH ROW
EXECUTE FUNCTION update_reply_count();

--5. Trigger follow for user table
CREATE OR REPLACE FUNCTION update_follow_count()
RETURNS TRIGGER AS '
BEGIN
    IF TG_OP = ''INSERT'' THEN
        UPDATE "user"
        SET follow_count = follow_count + 1
        WHERE user_id = NEW.user_id_follow;
		
		UPDATE "user"
        SET followed_count = followed_count + 1
        WHERE user_id = NEW.user_id;
		
    ELSIF TG_OP = ''DELETE'' THEN
        UPDATE "user"
        SET follow_count = follow_count - 1
        WHERE user_id = OLD.user_id_follow;
		
		UPDATE "user"
        SET followed_count = followed_count - 1
        WHERE user_id = OLD.user_id;
    END IF;
    RETURN NULL;
END;
' LANGUAGE plpgsql;

CREATE TRIGGER follow_trigger
AFTER INSERT OR DELETE ON follow
FOR EACH ROW
EXECUTE FUNCTION update_follow_count();



-- --5. Create post function 
-- CREATE OR REPLACE FUNCTION create_post(
--     user_id_param int,
--     content_param text
-- )
-- RETURNS JSONB
-- AS '
-- DECLARE
--     new_post_id int;
--     new_post_json JSONB;
-- BEGIN
--     INSERT INTO post (content, user_id)
--     VALUES (content_param, user_id_param)
--     RETURNING post_id INTO new_post_id;

--     SELECT to_jsonb(p.*)
--     INTO new_post_json
--     FROM post p
--     WHERE p.post_id = new_post_id;

--     RETURN new_post_json;
-- END;
-- ' LANGUAGE plpgsql;

-- --6. Function modify post 
-- CREATE OR REPLACE FUNCTION modify_post(
--     post_id_param int,
--     user_id_param int,
--     new_content_param text
-- )
-- RETURNS JSONB
-- AS '
-- DECLARE
--     updated_post_json JSONB;
-- BEGIN
--     -- Check if the post exists and belongs to the specified user
--     IF EXISTS (
--         SELECT 1
--         FROM post
--         WHERE post_id = post_id_param AND user_id = user_id_param
--     ) THEN
--         -- Update the content of the post
--         UPDATE post
--         SET content = new_content_param,
--             time_edited = now()
--         WHERE post_id = post_id_param;
        
--         -- Retrieve the updated post as JSONB
--         SELECT to_jsonb(p.*)
--         INTO updated_post_json
--         FROM post p
--         WHERE p.post_id = post_id_param;
        
--         RETURN updated_post_json;
--     ELSE
--         -- Return NULL if the post does not exist or does not belong to the specified user
--         RETURN NULL;
--     END IF;
-- END;
-- ' LANGUAGE plpgsql;

-- --7. Delete post 
-- CREATE OR REPLACE FUNCTION delete_post(
--     post_id_param int,
--     user_id_param int
-- )
-- RETURNS BOOLEAN
-- AS '
-- DECLARE
--     deletion_successful BOOLEAN := false;
-- BEGIN
--     -- Check if the post exists and belongs to the specified user
--     IF EXISTS (
--         SELECT 1
--         FROM post
--         WHERE post_id = post_id_param AND user_id = user_id_param
--     ) THEN
--         -- Delete the post
--         DELETE FROM post
--         WHERE post_id = post_id_param;
        
--         deletion_successful := true;
--     END IF;
    
--     RETURN deletion_successful;
-- END;
-- ' LANGUAGE plpgsql;




-- --8. toggle reaction
-- CREATE OR REPLACE FUNCTION toggle_reaction(
--     post_id_param int,
--     user_id_param int
-- )
-- RETURNS VOID
-- AS '
-- BEGIN
--     -- Check if the record exists in the reaction table
--     IF EXISTS (
--         SELECT 1
--         FROM reaction
--         WHERE post_id = post_id_param AND user_id = user_id_param
--     ) THEN
--         -- If exists, delete the record from the reaction table
--         DELETE FROM reaction
--         WHERE post_id = post_id_param AND user_id = user_id_param;
--     ELSE
--         -- If not exists, insert a new record into the reaction table
--         INSERT INTO reaction (user_id, post_id)
--         VALUES (user_id_param, post_id_param);
--     END IF;
-- END;
-- ' LANGUAGE plpgsql;

-- --9. toggle share
-- CREATE OR REPLACE FUNCTION toggle_share(
--     post_id_param int,
--     user_id_param int
-- )
-- RETURNS VOID
-- AS '
-- BEGIN
--     -- Check if the record exists in the share_post table
--     IF EXISTS (
--         SELECT 1
--         FROM share_post
--         WHERE post_id = post_id_param AND user_id = user_id_param
--     ) THEN
--         -- If exists, delete the record from the share_post table
--         DELETE FROM share_post
--         WHERE post_id = post_id_param AND user_id = user_id_param;
--     ELSE
--         -- If not exists, insert a new record into the share_post table
--         INSERT INTO share_post (user_id, post_id)
--         VALUES (user_id_param, post_id_param);
--     END IF;

-- END;
-- ' LANGUAGE plpgsql;


-- --10. Retrieve all post reply to post_id
-- CREATE OR REPLACE FUNCTION retrieve_posts_reply(
--     post_id_param int
-- )
-- RETURNS JSONB
-- AS '
-- DECLARE
--     posts_json JSONB;
-- BEGIN
--     SELECT jsonb_agg(to_jsonb(posts.*))
--     INTO posts_json
--     FROM (
--         SELECT
--             p.*
--         FROM
--             post p, reply_post rp
--         WHERE
--             p.post_id = rp.post_id_child
-- 		AND post_id_param = rp.post_id_parent
--     ) posts;

--     RETURN posts_json;
-- END;
-- ' LANGUAGE plpgsql;



