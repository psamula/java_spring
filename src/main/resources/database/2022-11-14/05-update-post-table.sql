--liquibase formatted sql
--changeset psamula:7
ALTER TABLE POST
ADD author varchar(50) NULL;
