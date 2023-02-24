import java.util.Random;

public class CodeGen2 {
    private String code;

    public CodeGen2() {
        this.code = String.valueOf(new Random().nextInt(9000)+1000);
    }

    public String getCode() {
        return code;
    }

    public boolean checkCode(String input) {
        return input.equals(code);
    }
}
