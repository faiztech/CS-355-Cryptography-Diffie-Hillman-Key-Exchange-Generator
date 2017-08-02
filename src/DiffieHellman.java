//Diffie-Hellman key exchange. Primes are read from file primes.txt


import java.io.*;
import java.lang.*;
import java.lang.Math;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/*
 * A simple way of generating secret keys using Diffie-Hellman.
 *
 * @author Graciela Perera (gcperera@cis.ysu.edu)
 *
 * Modified by Mohammed Faizuddin (mfaizudd@neiu.edu) to generate 100 keys and analyze their frequency
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
    public DiffieHellman(long randomValue1, long randomValue2) {
        this.randValAlice = randomValue1;
        this.randValBob = randomValue2;
    }

    /*
     * Read two primes from file primes.txt in a random position
	 */
    public void getPrimes() {

        try {
            //Read all primes
            FileReader readfile = new FileReader("primes.txt");
            BufferedReader in = new BufferedReader(readfile);
            String number;
            number = in.readLine();

            //Take all primes from string (number) and create array of primes
            String[] setPrimes = number.split("\\s");

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
        } catch (IOException e) {
        }
    }

    /*
     * Get Diffie-Hellman g parameter
     */
    public long setKey() {
        // Print out to test key operations
         /*
         System.out.println("g to power of a = " + Math.pow(g ,this.randValAlice) );
		 System.out.println("g to power of a mod p = " + (Math.pow(g ,this.randValAlice) % p) );
		 System.out.println("(g to power of a mod p) to power of b = " + (Math.pow( Math.pow(g ,this.randValAlice) % p, this.randValBob ) ) );
		 System.out.println("((g to power of a mod p) to power of b ) mod p= " + (Math.pow( Math.pow(g ,this.randValAlice) % p, this.randValBob ) ) % p);
		 */
        double dhKey = (Math.pow(Math.pow(g, this.randValAlice) % p, this.randValBob)) % p;
        long finaldhKey = (long) dhKey;
        return (finaldhKey);
    }


    //function which analyzes the keys generated
    public String analyzeDuplicates(long B[]) {
        String result = null;
        int counter = 1;
        ArrayList<Long> temp = new ArrayList<Long>();
        ArrayList<Integer> countingRepetitions = new ArrayList<Integer>();
        boolean flag = false;


        for (int i = 0; i < B.length; i++) {
            flag = false;
            counter = 1;
            for (int j = 0; j < B.length; j++) {
                if (!temp.contains(B[i]) && B[i] == B[j] && i != j) {
                    counter++;
                    flag = true;
                }
            }
            if (flag) {
                temp.add(B[i]);
                countingRepetitions.add(counter);
            }
        }


        if (temp.isEmpty())
            result = "There are no repetitions in the keys generated.";
        else {
            result = "There " + ((temp.size() == 1) ? "is " : "are ") + temp.size() + " keys that repeat.";


            for (int i = 0; i < temp.size(); i++) {
                result += "\n" + (i + 1) + " " + temp.get(i) + " repeats " + countingRepetitions.get(i) + " times.";
            }


        }

        return result;
    }


    /*
     * Get Diffie-Hellman g parameter
     */
    public long getg() {
        return (this.g);
    }

    /*
     * Get Diffie-Hellman p parameter
     */
    public long getp() {
        return (this.p);
    }

    /*
     * Get Diffie-Hellman Alice parameter
     */
    public long getAlice() {
        return (this.randValAlice);
    }

    /*
     * Get Diffie-Hellman Bob parameter
     */
    public long getBob() {
        return (this.randValBob);
    }


}//End Class DiffieHellman


