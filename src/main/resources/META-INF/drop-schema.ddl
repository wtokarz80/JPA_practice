
    alter table if exists Student 
       drop constraint if exists FKf12myy73nsf6soln9xli8th80

    alter table if exists Student 
       drop constraint if exists FKchk0xiwxbop4w0dkfu3u8h635

    alter table if exists Student_phoneNumbers 
       drop constraint if exists FKcw90n87pnmj6u2f5njkcg779l

    drop table if exists Address cascade

    drop table if exists class cascade

    drop table if exists Student cascade

    drop table if exists Student_phoneNumbers cascade
