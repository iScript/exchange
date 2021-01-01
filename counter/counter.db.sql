-- -------------------------------------------------------------
-- TablePlus 3.12.0(354)
--
-- https://tableplus.com/
--
-- Database: counter.db
-- Generation Time: 2021-01-01 17:02:33.2630
-- -------------------------------------------------------------


CREATE TABLE "t_order" ("id" integer,"uid" bigint,"code" integer,"direction" integer,"type" integer,"price" bigint,"ocount" bigint,"status" integer,"date" varchar,"time" varchar, PRIMARY KEY (id));

CREATE TABLE "t_posi" ("id" integer,"uid" bigint,"code" integer,"cost" bigint,"count" bigint, PRIMARY KEY (id));

CREATE TABLE "t_trade" ("id" integer,"uid" bigint,"code" integer,"direction" integer,"price" bigint,"tcount" bigint,"oid" integer,"date" varchar,"time" varchar, PRIMARY KEY (id));

CREATE TABLE "t_user" ("id" integer NOT NULL DEFAULT '',"uid" bigint NOT NULL,"password" varchar,"balance" bigint,"createDate" char(8),"createTime" char(8),"modifyDate" char(8),"modifyTime" char(8), PRIMARY KEY (id));

