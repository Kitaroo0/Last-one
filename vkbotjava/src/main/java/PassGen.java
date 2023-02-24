public class PassGen extends CodeGen{
    private String code;

    public PassGen() {
        int length = 8;
        String s = rand.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97)).mapToObj(i -> (char) i).limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
         this.code = s;
    }

    public String getCode() {
        return code;
    }

    public boolean checkCode(String input) {
        return input.equals(code);
    }
}
