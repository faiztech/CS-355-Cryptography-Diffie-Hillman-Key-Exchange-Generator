/* Test file for DiffieHellman key exchange */
// @author Graciela Perera (gcperera@cis.ysu.edu)


import java.util.Random;

public class DHTest {
    public static void main(String[] arguments) {
        //Max size of random value for Alice and Bob
        //If it is greater than 50 the number is to Big need to move use BigInteger in DiffieHellman Class
        long MAXVAL = 50;

        //Start random number generator
        Random r = new Random();
        long numAlice = (long) Math.abs(r.nextLong()) % MAXVAL;
        long numBob = Math.abs(r.nextLong()) % MAXVAL;
        //System.out.println ("alice number = " + numAlice);
        long key = 1;

        //Create class
        DiffieHellman testkey = new DiffieHellman(numAlice, numBob);
        //System.out.println ("alice in class = " + testkey.getAlice());

        //Read primes from text file of 10000 primes pre-generated
        testkey.getPrimes();

        //Compute secret key
        key = testkey.setKey();
        System.out.println("Key =  " + key);

    }
}
