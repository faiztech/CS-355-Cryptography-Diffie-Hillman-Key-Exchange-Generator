
/* Test file for DiffieHellman key exchange */


/*
        * @Main Author Graciela Perera (gcperera@cis.ysu.edu)
        *
        * Modified by Mohammed Faizuddin (mfaizudd@neiu.edu) to generate 100 keys and analyze their frequency
        * Homework 4 Summer 2017
        */

import java.util.Random;

public class DHTest {
    public static void main(String[] arguments) {
        //Max size of random value for Alice and Bob
        //If it is greater than 50 the number is to Big need to move use BigInteger in DiffieHellman Class
        long MAXVAL = 50;

        //Start random number generator
        Random r = new Random();
        long numAlice = Math.abs(r.nextLong()) % MAXVAL;
        long numBob = Math.abs(r.nextLong()) % MAXVAL;
        //System.out.println ("alice number = " + numAlice);
        long key = 1;

        //Storing all the keys in this array
        long keyArray[] = new long[100];


        //Create class
        DiffieHellman testkey = new DiffieHellman(numAlice, numBob);
        //System.out.println ("alice in class = " + testkey.getAlice());


        for (int i = 0; i < 100; i++) {

            //Read primes from text file of 10000 primes pre-generated
            testkey.getPrimes();

            //Computing secret key
            key = testkey.setKey();
            System.out.println("Key " + (i + 1) + " = " + key);

            //Storing Keys in keyArray
            keyArray[i] = key;
        }

        //for testing if analyze works
        //keyArray[99] = keyArray[98];

        System.out.println("\nResults(frequency of keys): ");
        //calling analyze function created
        System.out.println(testkey.analyzeDuplicates(keyArray));

    }

}
