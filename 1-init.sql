create user mcuser with superuser;
alter user mcuser with password 'mcuser';
create database mc_authentication with owner mcuser;
create extension "uuid-ossp";
