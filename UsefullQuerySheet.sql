use landlord_database;

insert into user_types(user_type) values ('landlord'),('tenant');

insert into content_types(content_type) values ('image'), ('text');

select * from user_types;

select * from users;

select * from estates;

select * from addresses;

select * from bank_account;

select * from messages;

select * from message_content;
select * from content_types;

Select u.user_name, r.name, mc.text_message
from users u 
join messages m on m.sender_userid=u.userid
join message_content mc on m.contentid=mc.contentid;

update estates
set occupied=0
where estateid =1;

alter table estates
change occupied occupied boolean not null;

delete from bank_account where bank_accountid = 11;

delete from estates where estateid = 1;

delete from addresses where addressid =25;

select * from estates where estateid in(select estateid from users where user_name = 'galibber');


use landlord_bank;
select * from bank_account;
show processlist;
set global event_scheduler=on;
use landlord_database;
select * 
from estates 
where estateid in(select estateid 
					from user_estate 
                    where userid in(select userid
									from users 
                                    where user_name='galiber'));
                                    
select * from messages 
where sender_userid in(select userid from users where userid=1 or userid=2) and recipient_userid in(select userid from users where userid=2 or userid=1) order by time_stamp;
drop event if exists dayly_message_cleaner;
use landlord_database;
delimiter |
CREATE EVENT dayly_message_cleaner
ON SCHEDULE EVERY 1 day
STARTS '2018-11-07 23:00:00'
DO
BEGIN
delete from messages
where id in
		(select id from messages where time_stamp < adddate(current_timestamp(), INTERVAL -3 MONTH)
		and message_type like 'Estate message');
END;
|
delimiter ;
