/**
 * 
 */
package main

/**
 * @author mike
 *
 */
class CMain {

	static main(args) {
		
		
		def var
		def gets = {
			print it
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
			String input = br.readLine()
			return input
		  }
		10.times {	println gets('input')}
	
	}

}
