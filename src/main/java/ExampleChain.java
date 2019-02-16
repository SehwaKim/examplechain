import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class ExampleChain {
    public static int difficulty = 5;
    public static ArrayList<Block> blockchain = new ArrayList<>();

    public static void main(String[] args) {
        blockchain.add(new Block("0", "it's the first bl" +
                "ock"));
        System.out.println("Trying to Mine block 1...");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block(blockchain.get(blockchain.size() - 1).hash, "the second block"));
        System.out.println("Trying to Mine block 2...");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block(blockchain.get(blockchain.size() - 1).hash, "the third block"));
        System.out.println("Trying to Mine block 3...");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe Blockchain");
        System.out.println(blockchainJson);
    }

    // Any change to the blockchain’s blocks will cause this method to return false.
    public static boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            String hashTarget = new String(new char[difficulty]).replace('\0', '0');

            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }

            //check if hash is solved (by mining)
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block has not been mined");
                return false;
            }
        }
        return true;
    }
}
