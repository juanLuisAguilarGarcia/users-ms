package com.dvp.infra.api.router.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.pichincha.UsersApplication.class)
public class UsersApplicationTest {

    @Test
    public void contextLoads(){
        com.pichincha.UsersApplication.main(new String[]{
                "--spring.main.web-environment=false",
                "--spring.autoconfigure.exclude=blahblahblah"
        });
    }
}
