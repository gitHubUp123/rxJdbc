package com.ramon.rxJavajdbc.operate;

import com.github.davidmoten.rx.jdbc.ConnectionProvider;
import com.github.davidmoten.rx.jdbc.Database;
import com.ramon.rxJavajdbc.db.Datasource;
import com.ramon.rxJavajdbc.entity.User;
import com.ramon.rxJavajdbc.entity.User2;
import org.junit.Test;
import rx.Observable;

import java.util.List;


public class Operate {

    Observable<Integer> create, insert1, insert2, insert3, update, delete = null;
    ConnectionProvider cp = Datasource.connectionProvider;
    Database db = Database.from(cp);

    @Test
    public void ddl() {
        create = db.update("create table if not exists user(id int primary key,name varchar(50))")
                .count();

        insert1 = db.update("insert into user(id,name) values(1,'leiming')")
                .dependsOn(create)
                .count();
        update = db.update("update user set name = 'leiming2' where id = 1")
                .dependsOn(create)
                .count();

        insert2 = db.update("insert into user(id,name) values(2,'minglei')")
                .dependsOn(create)
                .count();

        insert3 = db.update("insert into user(id,name) values(3,'lemi')")
                .dependsOn(create)
                .count();

        delete = db.update("delete from user where id=3")
                .dependsOn(create)
                .count();

        List<String> names = db.select("select name from user where id < ?")
                .parameter(4)
                .dependsOn(create)
                .dependsOn(insert1)
                .dependsOn(insert2)
                .dependsOn(insert3)
                .dependsOn(update)
                .dependsOn(delete)
                .getAs(String.class)
                .toList()
                .toBlocking()
                .single();
        System.out.println(names.toString());
    }

    @Test
    public void interfaceMapper(){
        create = db.update("create table if not exists user(id int primary key,name varchar(50))")
                .count();

        insert1 = db.update("insert into user(id,name) values(1,'leiming')")
                .dependsOn(create)
                .count();

        insert2 = db.update("insert into user(id,name) values(2,'minglei')")
                .dependsOn(create)
                .count();

        insert3 = db.update("insert into user(id,name) values(3,'lemi')")
                .dependsOn(create)
                .count();
        List<User> users = db.select("select id,name from user")
                .dependsOn(create)
                .dependsOn(insert1)
                .dependsOn(insert2)
                .dependsOn(insert3)
                .autoMap(User.class)
                .toList()
                .toBlocking()
                .single();
        for (User user:users) {
            System.out.println(user.id()+"===="+user.name());
        }
    }

    @Test
    public void entityMapper(){
        create = db.update("create table if not exists user(id int primary key,name varchar(50))")
                .count();

        insert1 = db.update("insert into user(id,name) values(1,'leiming')")
                .dependsOn(create)
                .count();

        insert2 = db.update("insert into user(id,name) values(2,'minglei')")
                .dependsOn(create)
                .count();

        insert3 = db.update("insert into user(id,name) values(3,'lemi')")
                .dependsOn(create)
                .count();
        List<User2> users2 = db.select("select id,name from user")
                .dependsOn(create)
                .dependsOn(insert1)
                .dependsOn(insert2)
                .dependsOn(insert3)
                .autoMap(User2.class)
                .toList()
                .toBlocking()
                .single();
        for (User2 user:users2) {
            System.out.println(user.getId()+"===="+user.getName());
        }
    }

}
