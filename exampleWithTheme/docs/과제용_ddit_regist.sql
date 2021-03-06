CREATE TABLE GRADE(
  CODE CHAR(4),
  TEXT VARCHAR2(10),
  CONSTRAINTS PK_GRADE PRIMARY KEY(CODE)
);
INSERT INTO grade (code, text) VALUES ('G001', '고졸');
INSERT INTO grade (code, text) VALUES ('G002', '초대졸');
INSERT INTO grade (code, text) VALUES ('G003', '대졸');
INSERT INTO grade (code, text) VALUES ('G004', '석사');
INSERT INTO grade (code, text) VALUES ('G005', '박사');

CREATE TABLE LICENSE(
  CODE CHAR(4),
  TEXT VARCHAR2(40),
  CONSTRAINTS PK_LICENSE PRIMARY KEY(CODE)
);
INSERT INTO license ( code,text) VALUES ('L001', '정보처리기사');
INSERT INTO license ( code,text) VALUES ('L002', '정보보안기사');
INSERT INTO license ( code,text) VALUES ('L003', 'SQLD');
INSERT INTO license ( code,text) VALUES ('L004', 'SQLP');
CREATE TABLE DDITSTUDENT(
  CODE CHAR(4),
  NAME VARCHAR2(20),
  BIRTHDAY DATE,
  AGE NUMBER(3),
  GR_CODE CHAR(4),
  GEN CHAR(1),
  CAREER VARCHAR2(100),
  CONSTRAINTS PK_DDITSTUDENT PRIMARY KEY (CODE),
  CONSTRAINTS FK_DDITSTUDENT_GRADE FOREIGN KEY(GR_CODE) REFERENCES GRADE(CODE)
);
CREATE TABLE STD_LICENSE(
  STD_CODE CHAR(4), LIC_CODE CHAR(4),
  CONSTRAINTS FK_STD_CODE_DDITSTUDENT FOREIGN KEY(STD_CODE) REFERENCES DDITSTUDENT(CODE),
  CONSTRAINTS FK_LIC_CODE_LICENSE FOREIGN KEY(LIC_CODE) REFERENCES LICENSE(CODE)
);
INSERT INTO dditstudent (
    code,    name,    birthday,    age,
    gr_code,    gen,    career
) 
SELECT 'S'|| LPAD(ROWNUM, 3, '0'), MEM_NAME, MEM_BIR, 23,
        'G003', 'M', '대충 경력'
FROM MEMBER;        

INSERT INTO STD_LICENSE (STD_CODE, LIC_CODE)
VALUES ('S001', 'L001');
INSERT INTO STD_LICENSE (STD_CODE, LIC_CODE)
VALUES ('S001', 'L002');
COMMIT;

