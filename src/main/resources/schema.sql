DROP TABLE IF EXISTS EMPLOYEE;

create table EMPLOYEE
(
    EMPLOYEE_ID       INT auto_increment
        primary key,
    FIRST_NAME        VARCHAR(250) not null,
    LAST_NAME         VARCHAR(250) not null,
    EMAIL_ADDRESS     VARCHAR(100) default NULL,
    PHONE             VARCHAR(250) default NULL,
    BIRTH_DATE        DATE         not null,
    JOB_TITLE         VARCHAR(50)  default NULL,
    DEPARTMENT        VARCHAR(50)  default NULL,
    LOCATION          VARCHAR(250) default NULL,
    START_DATE        DATE         not null,
    MANAGER_REPORTING VARCHAR(250) default NULL,
    CREATION_DATE     DATE         not null,
    LAST_UPDATE_DATE  DATE         not null
);