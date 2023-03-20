-------------------------------------------------------------------------------

----관리자 테이블생성-----

Create table manager (
    ID varchar2(50) ,
    PW varchar2(50) ,
    JOB_ID varchar(50)
);

 

----트레이너테이블 삭제------------
drop table trainer 

----트레이너 테이블생성 ---------
create table Trainer
(
    gym_id number ,
    name varchar2(50) ,
    career varchar2(1000)        
);

---- 트레이너 테이블의 제약조건 ( 참조의 무결성 ) 생성 --------
alter table trainer 
add constraint gym_id_fk foreign key (gym_id)
references gym ( gym_id );
---------참조 키 삭제 ---------------------------------
ALTER TABLE trainer DROP CONSTRAINT GYM_ID_FK;


-----------트레이너 테이블 테이터 삽입 -------------------
insert into trainer values (  1  , '김찬민' , '경력 3년' ) ;
insert into trainer values (  1  , '이연우' , '경력 5년' ) ;
insert into trainer values (  1  , '김찬민' , '경력 7년' ) ;
insert into trainer values (  1  , '도란이' , '경력 9년' ) ;

insert into trainer values (  2  , '사영철' , '경력 3년,망그러진 곰,카카오프렌즈,IFBB 스포츠 모델 3연연속 우승,핫둘셋넷둘둘셋넷,
저에게 운동을 배운다면 3개월안에 대회에서 우승할수 있는 몸을 만들어 드리겠습니다 후후후 방법은 간단합니다 남성호르몬을 강제로 몇십배이상 증가시켜주는 
경구제 약품을 투입후 매일매일 하루에 3시간씩 쉬지않고 운동을 할겁니다 우하하하하하 자세한 사항은 개인연락으로 알려드리겠습니다
위험한 약물을 투입하니 고민 많이하고 등록하세요 껄껄껄껄' );
insert into trainer values (  2  , '이상현' , '경력 5년' ) ;
insert into trainer values (  2  , '감경도' , '경력 7년' ) ;
insert into trainer values (  2  , '정백창' , '경력 9년' ) ;

insert into trainer values (  3  , '이현' , '경력 3년' ) ;
insert into trainer values (  3  , '김환희' , '경력 5년' ) ;
insert into trainer values (  3  , '강창묵' , '경력 7년' ) ;
insert into trainer values (  3  , '이현지' , '경력 9년' ) ;



select * 
from trainer;

----------체육관 테이블 삭제 ------------
drop table gym;
-----------기본키삭제 -------------------
ALTER TABLE gym DROP CONSTRAINT SYS_C008453;

----------체육관 테이블 생성 ---------------

create table GYM
(
    gym_id  number primary key,
    gym_name varchar2(50),
    address varchar2(50), 
    telephone_number  varchar2(50),
    gym_picture varchar2(50)
);



---------------체육관 테이블 데이터 삽입 ---------------------

insert into gym values ( 1 , '한울핏' , '광주광역시 서구 경열로 3' , '010-3557-9353' , 'gym1.png' );
insert into gym values ( 2 , '울한짐' , '광주광역시 서구 경열로 13' , '010-2345-9353' , 'gym2.png');
insert into gym values ( 3 , '한글짐' , '광주광역시 서구 경열로 23' , '010-3456-9353' ,'gym3.png');
insert into gym values ( 4 , '근육맨짐' , '광주광역시 서구 경열로 33' , '010-4567-9353' , 'gym4.png');
 


commit;

-------------------------------------------------------------------------

select * 
from gym

select * 
from trainer

select g.gym_name , t.*
from gym g , trainer t
where g.gym_id = t.gym_id
and gym_name = '한울짐'

select * 
from member
commit;
-----------------------------------------------


