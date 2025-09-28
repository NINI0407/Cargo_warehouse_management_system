import java.util.*;

public class Control {
    public ConfigManage configManage=new ConfigManage();

    public Control(ConfigManage config){
        this.configManage=config;
    }

    public void cmdFormat(String[] code, String[] info) {
        for (int i = 0; i < code.length; i++) {
            System.out.print("[" + code[i] + "]." + info[i]);
        }
        System.out.println();

    }

    public String[] show_Sub_Menu() {
        System.out.println("[0].Go_back_to_main_menu [99].Exit_system");
        String code[] = { "0", "99" };
        return code;
    }

    public String check_Cmd(String[] code, String cmd) {
        while (true) {
            for (int i = 0; i < code.length; i++) {
                if (code[i].equals(cmd)) {
                    switch (cmd) {
                        case "0":
                            return "0";
                        case "99":
                            System.exit(0);
                    }
                    return cmd;
                }
            }
            System.out.println("Error_wrong_command");
            System.out.println("Please_enter_again:");
            cmd = Main.in.nextLine();
        }
    }

    public void display(ArrayList<OneData> data) {
        OneData d = new OneData("[ID] [Name] [Brand] [Catalog] [Count] [Weight] [Price]");
        d.printData(configManage.getName(), configManage.getBrand(), configManage.getCat(), configManage.getCount(),
                configManage.getWeight(), configManage.getPrice());
        
        for (OneData t : data) {
            t.printData(configManage.getName(), configManage.getBrand(), configManage.getCat(), configManage.getCount(),
                    configManage.getWeight(), configManage.getPrice());
        }
    }

    public boolean isRight(String field, String val) {
        switch (field) {
            case "ID":
                return val.matches("[0-9]+") && val.length() <= 4;
            case "Name":
                return val.matches("[a-zA-Z]+") && val.length() <= 12;
            case "Brand":
                return val.matches("[a-zA-Z'.]+") && val.length() <= 12;
            case "Cat":
                return val.matches("[a-zA-Z]+") && val.length() <= 12;
            case "Count":
                return val.matches("[0-9]+") && val.length() <= 7 && Integer.parseInt(val) >= 0;
            case "Weight":
                return val.matches("[0-9]+") && val.length() <= 6 && Integer.parseInt(val) >= 0;
            case "Price":
                return val.matches("[0-9]+") && val.length() <= 7 && Integer.parseInt(val) >= 0;
        }
        return true;
    }

    public String update(String field, String val) {
        while(!isRight(field, val)) {
            System.out.println("Error_wrong_data");
            System.out.println("Please_input_again:");
            val = Main.in.nextLine();
            if(val.equals(""))
            {
                return "";
            }
        }
        return val;
    }
}
