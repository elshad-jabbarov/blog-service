CREATE TABLE IF NOT EXISTS permission (
                                          id BIGSERIAL PRIMARY KEY,
                                          name VARCHAR(500) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    status BOOLEAN DEFAULT true,
    created_by BIGINT,
    updated_by BIGINT
    );

CREATE TABLE IF NOT EXISTS user_type (
                                         id BIGSERIAL PRIMARY KEY,
                                         name VARCHAR(500) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    status BOOLEAN DEFAULT true,
    created_by BIGINT,
    updated_by BIGINT
    );

CREATE TABLE IF NOT EXISTS "user" (
                                      id BIGSERIAL PRIMARY KEY,
                                      username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    surname VARCHAR(255),
    phone_number VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    user_type_id BIGINT NOT NULL REFERENCES user_type(id),
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    status BOOLEAN DEFAULT true,
    created_by BIGINT,
    updated_by BIGINT
    );

CREATE TABLE IF NOT EXISTS user_permission (
                                               user_id BIGINT NOT NULL REFERENCES "user"(id),
    permission_id BIGINT NOT NULL REFERENCES permission(id),
    PRIMARY KEY (user_id, permission_id)
    );

CREATE TABLE IF NOT EXISTS user_type_permission (
                                                    user_type_id BIGINT NOT NULL REFERENCES user_type(id),
    permission_id BIGINT NOT NULL REFERENCES permission(id),
    PRIMARY KEY (user_type_id, permission_id)
    );

CREATE TABLE IF NOT EXISTS blog (
                                    id BIGSERIAL PRIMARY KEY,
                                    name VARCHAR(500) NOT NULL,
    title VARCHAR(500) NOT NULL,
    content TEXT NOT NULL,
    cover VARCHAR(500) NOT NULL,
    viewed_count BIGINT DEFAULT 0,
    published BOOLEAN DEFAULT true,
    published_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    status BOOLEAN DEFAULT true,
    created_by BIGINT,
    updated_by BIGINT,
    user_id BIGINT REFERENCES "user"(id)
    );

CREATE TABLE IF NOT EXISTS blog_image (
                                          id BIGSERIAL PRIMARY KEY,
                                          file_path VARCHAR(500),
    blog_id BIGINT NOT NULL REFERENCES blog(id),
    alt_text VARCHAR(500),
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    status BOOLEAN DEFAULT true,
    created_by BIGINT,
    updated_by BIGINT
    );

CREATE TABLE IF NOT EXISTS comment (
                         id BIGSERIAL PRIMARY KEY,
                         text TEXT NOT NULL,
                         created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
                         blog_id BIGINT NOT NULL,
                         FOREIGN KEY (blog_id) REFERENCES blog(id)
);