import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Scanner;
/**
 * @author Tatarski
 */
public class Main {
    static String ALGO="md5";
    public static void main(String[] args) throws NoSuchAlgorithmException {
        //init
        Boolean search=true;
        boolean correctNrOfChrs = false;
        boolean correctChoice = false;
        long c = 0;
        int numberOfChrs = 1;
        int numberOfAlg = 1;
        String hashtext = "";
        String outString = "";
        showBanner();
        do {
            System.out.println("select type of algorithm (1=md5 2=sha1) :");
            Scanner scanner3 = new Scanner(System.in);
            numberOfAlg = scanner3.nextInt();
            if(numberOfChrs<1||numberOfChrs>2){ correctChoice=true;}
            else {correctChoice=false;}
            switch (numberOfAlg){
                case 1:ALGO="md5";break;
                case 2:ALGO="sha1";break;
                default:ALGO="md5";
            }
        }while (correctChoice);
        do {
            System.out.println("number of characters (2-8 chars) :");
            Scanner scanner1 = new Scanner(System.in);
            numberOfChrs = scanner1.nextInt();
            if(numberOfChrs<2||numberOfChrs>8){ correctNrOfChrs=true;}
            else {correctNrOfChrs=false;}
        }
        while (correctNrOfChrs);
        System.out.println("Paste "+ALGO+"hashcode below:");
        Scanner scenner = new Scanner(System.in);
        hashtext = scenner.nextLine().toLowerCase();
        Random random = new Random();
        char [] word = new char[numberOfChrs];
        System.out.println("--------------------------------------------\n\nsearching for a match ...\n");
        Long ctm = System.currentTimeMillis();
        while (search){
                c++; // counter
            for (int j=0; j<numberOfChrs ; j++){
                 word[j] = (char)('a' + random.nextInt(26));
                  outString = String.valueOf(word);
                 if (getHashValue(outString).equals(hashtext)){
                     System.out.println("DONE ! searching completed :\n "+outString+" <= "+hashtext);
                     System.out.println("elapsed time :"+ ((System.currentTimeMillis()-ctm)/1000)+" seconds.");
                     System.out.println("number of candidates :"+c);
                     search=false;
                 }
            }
        }
    }
    private static String getHashValue(String result) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(ALGO);
        byte[] messageDigest = md.digest(result.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext;
        return  hashtext = number.toString(16);
    }
    private static void showBanner() {
        System.out.printf("" +
                "\n" +
                "  |                   |      |            |   \n" +
                "  __ \\    _` |   __|  __ \\   |  /   _ \\   __| \n" +
                "  | | |  (   | \\__ \\  | | |    <   (   |  |   \n" +
                " _| |_| \\__,_| ____/ _| |_| _|\\_\\ \\___/  \\__| \n" +
                "                                              \n" +
                "===== simple md5 & sha1 hash cracker =========\n" +
                "\n\n");
    }
}
