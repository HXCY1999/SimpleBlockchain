import java.util.ArrayList;
import java.util.List;

public class BlockchainTest {
    public  static List<Block> blockchain = new ArrayList<>();

    public static int difficulty = 5;

    public static void main(String[] args) {

        blockchain.add(new Block("first block","0"));
        blockchain.get(0).mineBlock(difficulty);
        blockchain.add(new Block("second block",blockchain.get(blockchain.size()-1).hash));
        blockchain.get(1).mineBlock(difficulty);
        blockchain.add(new Block("second block",blockchain.get(blockchain.size()-1).hash));
        blockchain.get(2).mineBlock(difficulty);
        System.out.println("If blockchain valid : " + isChainValid());

    }

    private static boolean isChainValid() {

        Block currentBlock;
        Block previousBlock;
        boolean flag = true;
        String hashTarget = new String(new char[difficulty]).replace('\0','0');

        for(int i = 1; i < blockchain.size(); i++){
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);

            if(!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println("current hash is not equal");
                flag = false;
            }

            if(!previousBlock.hash.equals(currentBlock.previousHash)){
                System.out.println("previous hash not equal");
                flag = false;
            }

            if(!currentBlock.hash.substring(0,difficulty).equals(hashTarget)){
                System.out.println("this block has not been mined, not valid ");
                flag = false;
            }
        }
        return flag;

    }
}
