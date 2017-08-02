//Diffie-Hellman key exchange. Primes are read from file primes.txt

import java.io.*;
import java.lang.*;
import java.lang.Math;
import java.lang.String;
import java.util.Random;

/*
 * A simple way of generating secret keys using Diffie-Hellman.
 *
 * @author Graciela Perera (gcperera@cis.ysu.edu)
 *
 */
public class DiffieHellman {
    long randValAlice = -1;
    long randValBob = -1;
    long p = -1;
    long g = -1;
    long key = -1;

    /*
     * Construct a key. You must specify a random number
     */
    public DiffieHellman( long randomValue1,long randomValue2) {
        this.randValAlice = randomValue1;
        this.randValBob = randomValue2;
    }

    /*
	 * Read two primes from file primes.txt in a random position
	 */
    public void getPrimes( ) {

        try{
            //Read all primes
            FileReader readfile = new FileReader( "primes.txt");
            BufferedReader in = new BufferedReader ( readfile );
            String number;
            number = in.readLine( );

            //Take all primes from string (number) and create array of primes
            String [] setPrimes = number.split("\\s");

            //Start random number generator
            Random r = new Random();

            //Random pick a position to extract prime  (p) from array
            long pos1 = Math.abs(r.nextLong()) % setPrimes.length;
            int pos = (int) pos1;
            p = Long.parseLong(setPrimes[pos]);
            // Test value p = 10;
            //System.out.println(" number 1 = " + p);

            //Random pick a position to extract prime  (g) from array
            long pos2 = Math.abs(r.nextLong()) % setPrimes.length;
            pos = (int) pos2;
            g = Long.parseLong(setPrimes[pos]);
            //Test value g = 4;
            //System.out.println(" number 2 = " + g);
        }catch (IOException e){};
    }

    /*
     * Get Diffie-Hellman g parameter
     */
    public long setKey( ) {
        // Print out to test ket operations
		 /*
		 System.out.println("g to power of a = " + Math.pow(g ,this.randValAlice) );
		 System.out.println("g to power of a mod p = " + (Math.pow(g ,this.randValAlice) % p) );
		 System.out.println("(g to power of a mod p) to power of b = " + (Math.pow( Math.pow(g ,this.randValAlice) % p, this.randValBob ) ) );
		 System.out.println("((g to power of a mod p) to power of b ) mod p= " + (Math.pow( Math.pow(g ,this.randValAlice) % p, this.randValBob ) ) % p);
		 */
        double dhKey = (Math.pow( Math.pow(g ,this.randValAlice) % p, this.randValBob ) ) % p;
        long finaldhKey = (long) dhKey;
        return(finaldhKey);
    }

    /*
     * Get Diffie-Hellman g parameter
     */
    public long getg( ) {
        return(this.g);
    }

    /*
     * Get Diffie-Hellman p parameter
     */
    public long getp( ) {
        return(this.p);
    }

    /*
     * Get Diffie-Hellman Alice parameter
     */
    public long getAlice( ) {
        return(this.randValAlice);
    }

    /*
     * Get Diffie-Hellman Bob parameter
     */
    public long getBob( ) {
        return(this.randValBob);
    }

}//End Class DiffieHellman
	