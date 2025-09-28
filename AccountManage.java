import java.util.*;

public class AccountManage {
    String verifycode;
    Map<String, String> account = new HashMap<>();
    ConfigManage config=new ConfigManage();
    FileManage file;

    public AccountManage() {
        file = new FileManage("account.txt");
        ArrayList<String> source = file.getData();
        for (String str : source) {
            String[] s = str.split(" ");
            account.put(s[0], s[1]);
        }
        verifycode = config.getVerify();
    }

    public boolean login(String acc, String pw, String verify) {
        if (!verify.equals(verifycode)) {
            return false;
        }
        if (!checkAcc(acc)) {
            return false;
        }
        return pw.equals(account.get(acc));
    }

    public boolean checkAcc(String acc) {
        if (account.containsKey(acc)) {
            return true;
        }
        return false;
    }

    public void showAcc() {
        System.out.printf("%-12s %-12s", "[Account]", "[Password]");
        System.out.println();
        for (String key : account.keySet()) {
            System.out.printf("%-12s %-12s\r\n", key, account.get(key));
        }
    }

    public void addAcc(String acc, String pw) {
        account.put(acc, pw);
        save();
    }

    public void delAcc(String acc) {
        account.remove(acc);
        save();
    }

    public void modAcc(String acc, String newAcc, String newPw) {
        account.remove(acc);
        account.put(newAcc, newPw);
        save();
    }

    public void save() {
        ArrayList<String> data = new ArrayList<>();
        for (String key : account.keySet()) {
            String s = String.format("%s %s", key, account.get(key));
            data.add(s);
        }
        file.setData(data);
    }
}
