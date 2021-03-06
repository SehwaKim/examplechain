import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class Wallet {
    public PrivateKey privateKey; // used to sign transactions
    public PublicKey publicKey; // act as address. used to verify the signature

    public Wallet() {
        generateKeyPair();
    }

    public void generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");

            // Initialize the key generator and generate a KeyPair
            keyGen.initialize(ecSpec, random);
            KeyPair keyPair = keyGen.generateKeyPair();

            // Set the public and private keys from the KeyPair
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
