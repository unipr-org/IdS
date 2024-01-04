package it.unipr.informatica.esercizi.esercizio_131.abstractions;

import java.nio.charset.StandardCharsets;
import java.util.logging.Handler;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public abstract class Stream {
	protected byte[] buffer;
	protected int pos;
	
	static final int BUFFER_DEFAULT_LENGTH = 4096;
	static final int BUFFER_MAX_LENGTH = 4096*16;
	
	public Stream() {
		buffer = new byte[BUFFER_DEFAULT_LENGTH];
		pos=0;
	}
	
	public void PutInt(int integer) {
		String s = Integer.toString(integer);
		PutString(s);
	}
	
	public void PutString(String s) {
		if (s == null)
			throw new IllegalArgumentException("s == null");
		
		byte[] res = s.getBytes(StandardCharsets.UTF_8);
		
		for(int i=0; i<res.length; ++i, ++pos) {
			if (pos == buffer.length) {
				HandleBufferFull();
				pos = 0;
			}
			
			buffer[pos] = res[i];
		}
	}
	
	
	public abstract void HandleBufferFull();
	public abstract void close();
	
	public static void main(String[] args) {
		Stream s = new Stream() {
			
			@Override
			public void HandleBufferFull() {
				for (int i=0; i<Stream.BUFFER_DEFAULT_LENGTH; ++i)
					System.out.print(new String(buffer, StandardCharsets.UTF_8));
			}

			@Override
			public void close() {
			}
		};
		
		for (int i=0; i<Stream.BUFFER_DEFAULT_LENGTH+1; ++i)
			if (i%16 == 0)
				s.PutString("\n");
			else if (i%16 == 2)
				s.PutInt(2);
			else
				s.PutString("İ");
	}
}


