package io.su0.test.soccer;

import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.persistence.GroupRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class Test {

    private final GroupRepository groupRepository;

    public Test(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @RequestMapping("/")
    String home() {
        Iterable<Group> all = groupRepository.findAll();
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Test.class, args);
    }
}
