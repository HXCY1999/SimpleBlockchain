import com.sun.deploy.util.StringUtils;

import java.util.Date;

public class Block {

    public String hash;

    public String previousHash;

    //current data
    private String data;
    //current time
    private long timeStamp;

    public int nonce;

    public Block(String hash, String previousHash, String data) {
        this.hash = hash;
        this.previousHash = previousHash;
        this.data = data;
    }

    public Block(String data, String previousHash){
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return StringUtil.applySha256(
                        previousHash + Long.toString(timeStamp)+
                                Integer.toString(nonce)+data);
    }

    public void mineBlock(int difficulty){
        String target = new String(new char[difficulty]).replace('\0','0');
        while(!hash.substring(0,difficulty).equals(target)){
            nonce++;
            hash = calculateHash();
        }

    }

}
