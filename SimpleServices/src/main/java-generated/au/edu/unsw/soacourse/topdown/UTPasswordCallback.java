package au.edu.unsw.soacourse.topdown;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.wss4j.common.ext.WSPasswordCallback;

public class UTPasswordCallback implements CallbackHandler {

	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		
		WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];

		if (pc.getIdentifier().equals("soa")) { //get the username from the callback
		 	pc.setPassword("soa"); //set the expected password for the username
		}
		
		if (pc.getIdentifier().equals("cse")) { //get the username from the callback
		 	pc.setPassword("cse"); //set the expected password for the username
		}
	}
}
