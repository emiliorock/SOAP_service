package au.edu.unsw.soacourse.simple;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
    String sayHi(String text);
}

