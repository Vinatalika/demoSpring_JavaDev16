CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(64) NOT NULL,
    role VARCHAR(45) NOT NULL,
    enabled BOOLEAN
);
CREATE TABLE note (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    user_id INT REFERENCES users(user_id) ON DELETE CASCADE,
    title VARCHAR(250) NOT NULL CHECK (LENGTH(title) >= 2 AND LENGTH(title) <= 250),
    content TEXT NOT NULL
);

ALTER TABLE note ADD CONSTRAINT note_fk FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE;
