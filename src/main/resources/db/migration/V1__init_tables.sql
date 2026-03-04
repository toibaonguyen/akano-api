CREATE TABLE
    roles (
        id UUID PRIMARY KEY,
        name VARCHAR(255) UNIQUE NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );

CREATE TABLE
    permissions (
        id UUID PRIMARY KEY,
        name VARCHAR(255) UNIQUE NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );

CREATE TABLE 
    accounts(
        id UUID PRIMARY KEY,
        email VARCHAR(255) UNIQUE NOT NULL,
        password VARCHAR(20) NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );

CREATE TABLE 
    role_permission(
        role_id UUID NOT NULL,
        permission_id UUID NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL,
        PRIMARY KEY (role_id, permission_id)
    );

CREATE TABLE 
    account_role(
        account_id UUID NOT NULL,
        role_id UUID NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL,
        PRIMARY KEY (account_id, role_id)
    );

CREATE TABLE
    user_profiles(
        id UUID PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        avatar_url TEXT,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );

CREATE TABLE 
    addresses (
        id              UUID PRIMARY KEY,
        account_id         UUID NOT NULL,
        recipient_name  VARCHAR(150) NOT NULL,
        phone           VARCHAR(20) NOT NULL,
        street          VARCHAR(255) NOT NULL,
        is_default      BOOLEAN DEFAULT FALSE,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );

CREATE TABLE
    authors (
        id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
        name VARCHAR(255),
        avatar_url TEXT,
        bio TEXT,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );

CREATE TABLE
    publishers (
        id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
        name VARCHAR(255) NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );

CREATE TABLE 
    genres(
        id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
        name VARCHAR(255) UNIQUE NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );

CREATE TABLE
    books (
        id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
        title VARCHAR(255) NOT NULL,
        isbn VARCHAR(20) UNIQUE,
        description TEXT NOT NULL,
        page_count INT,
        language VARCHAR(50),
        dimensions VARCHAR(100),
        weight NUMERIC(6, 2),
        cover_type VARCHAR(50), -- paperback / hardcover
        published_date DATE NOT NULL,
        publisher_id UUID NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );

CREATE TABLE
    book_images (
        book_id UUID NOT NULL,
        image_index SMALLINT NOT NULL,
        url TEXT NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL,
        PRIMARY KEY (book_id, image_index)
    );

CREATE TABLE
    author_book (
        author_id UUID NOT NULL,
        book_id UUID NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL,
        PRIMARY KEY (author_id, book_id)
    );

CREATE TABLE
    genre_book (
        genre_id UUID NOT NULL,
        book_id UUID NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL,
        PRIMARY KEY (genre_id, book_id)
    );

CREATE TABLE
    products (
        id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
        book_id UUID NOT NULL,
        in_stock INT NOT NULL,
        price NUMERIC(12,2) NOT NULL,
        is_active BOOLEAN NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );

CREATE TABLE 
    cart_items(
        id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
        account_id UUID NOT NULL,
        product_id UUID NOT NULL,
        quantity INT NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );

CREATE TABLE
    payment_method(
        id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
        code VARCHAR(4) UNIQUE NOT NULL,
        name VARCHAR(100) UNIQUE NOT NULL,
        is_active BOOLEAN NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );

CREATE TABLE 
    t_order_statuses (
        id          SMALLSERIAL PRIMARY KEY,
        code        VARCHAR(50) NOT NULL UNIQUE,
        name        VARCHAR(100) NOT NULL,
        description TEXT,
        is_active   BOOLEAN DEFAULT TRUE,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );

CREATE TABLE 
    m_t_order_statuses (
        id          SMALLSERIAL PRIMARY KEY,
        code        VARCHAR(50) NOT NULL UNIQUE,
        name        VARCHAR(100) NOT NULL,
        description TEXT,
        is_active   BOOLEAN DEFAULT TRUE,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );

CREATE TABLE 
    t_order(
        id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
        account_id UUID NOT NULL,
        payment_method_id UUID NOT NULL,
        status_id  UUID NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );

CREATE TABLE 
    t_order_details(
        id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
        t_order_id UUID NOT NULL,
        cart_item_id UUID NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );

CREATE TABLE 
    m_order_statuses (
        id          SMALLSERIAL PRIMARY KEY,
        code        VARCHAR(50) NOT NULL UNIQUE,
        name        VARCHAR(100) NOT NULL,
        description TEXT,
        is_active   BOOLEAN DEFAULT TRUE,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );

CREATE TABLE 
    orders(
        id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
        account_id UUID NOT NULL,
        payment_method_id UUID NOT NULL,
        status_id  UUID NOT NULL,
        total_amount NUMERIC(12,2) NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );

CREATE TABLE 
    order_details(
        id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
        order_id UUID NOT NULL,
        book_id UUID NOT NULL,
        quantity INT NOT NULL,
        price_at_purchase NUMERIC(12,2) NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        created_by UUID NOT NULL,
        updated_by UUID NOT NULL
    );
