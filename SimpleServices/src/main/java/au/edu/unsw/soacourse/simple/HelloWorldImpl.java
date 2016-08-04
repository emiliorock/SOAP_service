
package au.edu.unsw.soacourse.simple;

import javax.jws.WebService;

@WebService(endpointInterface = "au.edu.unsw.soacourse.simple.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        return "Hello " + text;
    }
}

