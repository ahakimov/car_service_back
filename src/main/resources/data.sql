insert into car_service.clients (name, phone, email)
values ('Client A', 'Phone A', 'client_a@example.com'),
       ('Client B', 'Phone B', 'client_b@example.com'),
       ('Admin', 'Phone A', 'admin@example.com');

insert into car_service.users (email, password, role)
values ('client_a@example.com', 'password_a', 'USER'),
       ('client_b@example.com', 'password_b', 'USER'),
       ('admin@example.com', 'password_admin', 'ADMIN'),
       ('mechanic_a@example.com', 'password_mechanic_a', 'MECHANIC'),
       ('mechanic_b@example.com', 'password_mechanic_b', 'MECHANIC');

insert into car_service.cars (model, make, produced, license_plate, owner_id)
values ('Model A', 'Make A', 2000, 'Car A', 1),
       ('Model B', 'Make B', 2001, 'Car B', 2);

insert into car_service.mechanics (name, phone, email, specialty, experience)
values ('Mechanic A', 'Phone A', 'mechanic_a@example.com', 'Specialty A', 1),
       ('Mechanic B', 'Phone B', 'mechanic_b@example.com', 'Specialty B', 2);

insert into car_service.services (service_name, description, price, estimated_duration)
values ('Service A', 'Description A', 12345.00, 1),
       ('Service B', 'Description B', 54321.00, 2);

insert into car_service.reservations (date_added, visit_date_time, status, client_id, car_id, mechanic_id, service_id)
values ('2025-05-05T12:00:00', '2025-05-05T12:00:00', 'in progress', 1, 1, 1, 1),
       ('2025-06-06T12:00:00', '2025-06-06T12:00:00', 'in progress', 2, 2, 2, 2);

insert into car_service.repair_jobs (client_id, mechanic_id, start_date_time, end_date_time, service_id, status, additional_details)
values (1, 1, '2025-05-05T12:00:00', '2025-05-05T12:00:00', 1, 'in progress', 'additional details'),
       (2, 2, '2025-06-06T12:00:00', '2025-06-06T12:00:00', 2, 'in progress', 'additional details');