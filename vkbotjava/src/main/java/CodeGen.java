import java.util.Random;

public class CodeGen extends CodeGen2{
    private String code;
    Random rand = new Random();

    public CodeGen() {
        this.code = Integer.toString(rand.nextInt(9000) + 1000);
    }
}
