
    create table Address (
       id  bigserial not null,
        addr varchar(255),
        city varchar(255),
        country varchar(255),
        Zip varchar(4),
        primary key (id)
    )

    create table class (
       id  bigserial not null,
        ccLocation varchar(255),
        name varchar(255),
        primary key (id)
    )

    create table Student (
       id  bigserial not null,
        dateOfBirth date,
        email varchar(255) not null,
        name varchar(255),
        address_id int8,
        klass_id int8,
        primary key (id)
    )

    create table Student_phoneNumbers (
       Student_id int8 not null,
        phone varchar(255)
    )

    alter table if exists Student 
       add constraint UK_msrnvlmsye9t98fb3bvekffiq unique (email)

    alter table if exists Student 
       add constraint FKf12myy73nsf6soln9xli8th80 
       foreign key (address_id) 
       references Address

    alter table if exists Student 
       add constraint FKchk0xiwxbop4w0dkfu3u8h635 
       foreign key (klass_id) 
       references class

    alter table if exists Student_phoneNumbers 
       add constraint FKcw90n87pnmj6u2f5njkcg779l 
       foreign key (Student_id) 
       references Student
