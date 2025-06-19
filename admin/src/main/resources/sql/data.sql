-- Insert systems
INSERT INTO error_systems (system_name, description) VALUES
                                                         ('authentication', 'Authentication related errors'),
                                                         ('database', 'Database related errors');

-- Insert categories
INSERT INTO error_categories (system_id, category_name, description) VALUES
                                                                         (1, 'login', 'User login errors'),
                                                                         (2, 'connection', 'Database connection errors');

-- Insert error types
INSERT INTO error_types (category_id, type_name, base_code, http_status) VALUES
                                                                             (1, 'invalid_credentials', 'AUTH-001', 401),
                                                                             (2, 'connection_failed', 'DB-001', 500);

-- Insert messages
INSERT INTO error_messages (error_type_id, language_code, message_text) VALUES
                                                                            (1, 'en', 'Invalid credentials'),
                                                                            (1, 'es', 'Credenciales no válidas'),
                                                                            (2, 'en', 'Database connection failed');

-- Insert details
INSERT INTO error_details (error_type_id, detail_key, detail_description, is_required) VALUES
                                                                                           (1, 'username', 'Username that failed authentication', TRUE),
                                                                                           (1, 'retry_after', 'Seconds until next allowed attempt', FALSE);