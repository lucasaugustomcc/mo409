/**
 * 
 */
package br.unicamp.ic.mo409.server;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Programmers
 *
 */
public class PasswordEncoder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new  BCryptPasswordEncoder(11);
		System.out.println(encoder.encode("password"));
		System.out.println(encoder.encode("password"));
		System.out.println(encoder.encode("password"));
		System.out.println(encoder.encode("password"));
	}

}
