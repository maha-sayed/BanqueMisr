CREATE TABLE FUSERS 
(
  USRNO NUMBER NOT NULL 
, USERNAME VARCHAR2(2000) NOT NULL 
, EMAIL VARCHAR2(2000) 
, PASSWORD VARCHAR2(200) 
, ACTIVE NUMBER 
, GENDER VARCHAR2(1) 
,BRCODE NUMBER NOT NULL
, CONSTRAINT FUSERS_PK PRIMARY KEY 
  (
    USRNO 
  )
  ENABLE 
);
--------------------------------------------
CREATE TABLE FROLES 
(
  ROLE_ID NUMBER NOT NULL 
, ROLE_NAME VARCHAR2(2000) 
, CONSTRAINT FROLES_PK PRIMARY KEY 
  (
    ROLE_ID 
  )
  ENABLE 
);
-------------------------------------------------
CREATE TABLE FUSERS_ROLES 
(
  USRNO NUMBER NOT NULL 
, ROLE_ID NUMBER NOT NULL 
, CONSTRAINT FUSERS_ROLES_PK PRIMARY KEY 
  (
    ROLE_ID 
  , USRNO 
  )
  ENABLE 
);

--------------------------------------------------
CREATE TABLE FTASKS 
(
  TASKNO NUMBER NOT NULL 
, TITLE VARCHAR2(2000) NOT NULL 
, DESCRIPTION VARCHAR2(2000) 
, PRIORITY VARCHAR2(2000) 
, STATUS VARCHAR2(200) DEFAULT 'TODO' 
, DUE_DATE DATE 
,BUSDATE DATE DEFAULT sysdate
, CONSTRAINT FTASKS_PK PRIMARY KEY 
  (
    TASKNO 
  )
  ENABLE 
);

COMMENT ON COLUMN FTASKS.PRIORITY IS 'high-medium-low';
------------------------------------------------------------
CREATE TABLE FTASKS_HIS
(
  TASKNO NUMBER NOT NULL 
, TITLE VARCHAR2(2000) NOT NULL 
, DESCRIPTION VARCHAR2(2000) 
, UPDATED_DATE DATE NOT NULL 
, PRIORITY VARCHAR2(2000) 
, OLD_STATUS VARCHAR2(200)
, NEW_STATUS VARCHAR2(200)
, DUE_DATE DATE 
, CONSTRAINT FTASKSHIS_PK PRIMARY KEY 
  (
    TASKNO ,UPDATED_DATE
  )
  ENABLE 
);


---------------------------------
CREATE TABLE NOTIFICAITON_EMAIL 
(
  EMAIL_ID NUMBER NOT NULL 
, SENDER_IP VARCHAR2(200) NOT NULL 
, RECEIVER_IP VARCHAR2(200) NOT NULL 
, SUBJECT VARCHAR2(200) 
, EMAIL_BODY VARCHAR2(4000) 
, BUSDATE DATE NOT NULL 
, CONSTRAINT NOTIFICAITON_EMAIL_PK PRIMARY KEY 
  (
    EMAIL_ID 
  )
  ENABLE 
);
--------------------------------
CREATE TABLE FBRANCH 
(
  BRCODE NUMBER NOT NULL 
, REGION_CODE NUMBER 
, CITY_CODE NUMBER 
, ANAME VARCHAR2(200) 
, ENAME VARCHAR2(200) 
, CONSTRAINT FBRANCH_PK PRIMARY KEY 
  (
    BRCODE 
  )
  ENABLE 
);
---------------------------------------------------------

INSERT INTO "HR"."FBRANCH" (BRCODE) VALUES ('9600');
UPDATE "HR"."FBRANCH" SET REGION_CODE = '55', CITY_CODE = '2', ANAME = 'الشهدلء', ENAME = 'shohdaa' WHERE BRCODE = 9600;
INSERT INTO "HR"."FBRANCH" (BRCODE, REGION_CODE, CITY_CODE, ANAME, ENAME) VALUES ('1', '1', '1', 'مركزية', 'central branch');
-----------------------------------------------------------

INSERT INTO "HR"."FUSERS" (USRNO, USERNAME, EMAIL, PASSWORD, ACTIVE, GENDER, BRCODE) VALUES ('301', 'LO1', 'lo1@banquemisr.com', 'dtdjlSnS7vwEisEAqx+yDQ==', '1', 'M', '9600')
INSERT INTO "HR"."FUSERS" (USRNO, USERNAME, EMAIL, PASSWORD, ACTIVE, GENDER, BRCODE) VALUES ('1', 'admin', 'admin@banquemisr.com', 'dtdjlSnS7vwEisEAqx+yDQ==', '1', 'M', '1')
------------------------------------------------------------
ALTER TABLE FUSERS
ADD CONSTRAINT FUSERS_FK1 FOREIGN KEY
(
  BRCODE 
)
REFERENCES FBRANCH
(
  BRCODE 
)
ENABLE;
---------------------------------------
ALTER TABLE FUSERS_ROLES
ADD CONSTRAINT FUSERS_ROLES_FK1 FOREIGN KEY
(
  ROLE_ID 
)
REFERENCES FROLES
(
  ROLE_ID 
)
ENABLE;

ALTER TABLE FUSERS_ROLES
ADD CONSTRAINT FUSERS_ROLES_FK2 FOREIGN KEY
(
  USRNO 
)
REFERENCES FUSERS
(
  USRNO 
)
ENABLE;

-------------------------------------------------
INSERT INTO "HR"."FROLES" (ROLE_ID, ROLE_NAME) VALUES ('1', 'Admin');
INSERT INTO "HR"."FROLES" (ROLE_ID, ROLE_NAME) VALUES ('2', 'LO Group');


INSERT INTO "HR"."FUSERS_ROLES" (USRNO, ROLE_ID) VALUES ('301', '2');
INSERT INTO "HR"."FUSERS_ROLES" (USRNO, ROLE_ID) VALUES ('1', '1');

---------------------------------------------
CREATE SEQUENCE FTASKSEQ INCREMENT BY 1 START WITH 1 MAXVALUE 99999999999999999 MINVALUE 1 NOCACHE ORDER;
-----------------------------------------------
