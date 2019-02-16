import org.junit.Test;

public class BlockTest {
    @Test
    public void createBlockTest() {
        Block genesisBlock = new Block("0", "first block");
        System.out.println("Hash for block 1: " + genesisBlock.hash);

        Block secondBlock = new Block(genesisBlock.hash, "second block here");
        System.out.println("Hash for block 2: " + secondBlock.hash);

        Block thirdBlock = new Block(secondBlock.hash, "third block here");
        System.out.println("Hash for block 3: " + thirdBlock.hash);
    }
}
