import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class ExampleChain {
    public static ArrayList<Block> blockchain = new ArrayList<>();

    public static void main(String[] args) {
        blockchain.add(new Block("0", "it's the first bl" +
                "ock"));
        blockchain.add(new Block(blockchain.get(blockchain.size() - 1).hash, "the second block"));
        blockchain.add(new Block(blockchain.get(blockchain.size() - 1).hash, "the third block"));

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
    }

    // Any change to the blockchain’s blocks will cause this method to return false.
    public static boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }

        return true;
    }
}
