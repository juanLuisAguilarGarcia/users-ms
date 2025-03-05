package com.dvp.infra.api.router.config;

import com.dvp.UsersApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =  UsersApplication.class)
public class UsersApplicationTest {

    @Test
    public void contextLoads(){
        UsersApplication.main(new String[]{
                "--spring.main.web-environment=false",
                "--spring.autoconfigure.exclude=blahblahblah"
        });
    }
}
