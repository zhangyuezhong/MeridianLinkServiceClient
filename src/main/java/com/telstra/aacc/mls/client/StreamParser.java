package com.telstra.aacc.mls.client;

import java.io.IOException;
import java.io.InputStream;

public interface StreamParser {

	public Object parse(InputStream stream) throws IOException;
	
	
}
