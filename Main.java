import java.util.*;

public class Main {
    public static final Scanner in = new Scanner(System.in);
    private static final AccountManage accountManage = new AccountManage();
    private static final CatalogManage catalogManage = new CatalogManage();
    private static final ConfigManage configManage = new ConfigManage();
    private static final DataManage dataManage = new DataManage(configManage);
    private static final Control control = new Control(configManage);

    public static void main(String[] args) {
        String cmd = null;
        int menu = 0;
        do_Login();
        while (true) {
            if (menu == 0) {
                show_Main_Menu();
            }

            cmd = in.nextLine();
            switch (cmd) {
                case "1":
                    Show_a();
                    break;
                case "2":
                    Show_p();
                    break;
                case "3":
                    Show_by_c();
                    break;
                case "4":
                    Search();
                    break;
                case "5":
                    Mod();
                    break;
                case "6":
                    Del();
                    break;
                case "7":
                    Add_storage();
                    break;
                case "8":
                    Add_cat();
                    break;
                case "9":
                    Show_cat();
                    break;
                case "10":
                    Set_field();
                    break;
                case "11":
                    Set_page();
                    break;
                case "12":
                    Set_order();
                    break;
                case "13":
                    Set_sort();
                    break;
                case "14":
                    Show_r();
                    break;
                case "15":
                    Opt();
                    break;
                case "16":
                    Show_acc();
                    break;
                case "17":
                    Add_acc();
                    break;
                case "18":
                    Del_acc();
                    break;
                case "19":
                    Mod_acc();
                    break;
                case "20":
                    Logout();
                    break;
                case "99":
                    System.exit(0);
                default:
                    System.out.println("Error_wrong_command");
                    System.out.println("Please_enter_again:");
                    menu = 1;
                    break;
            }
        }
    }

    public static void show_Main_Menu() {
        System.out.println("****************************************");
        System.out.println("1.Show_a 2.Show_p 3.Show_by_c 4.Search 5.Mod 6.Del 7.Add_storage");
        System.out.println("8.Add_cat 9.Show_cat 10.Set_field 11.Set_page 12.Set_order 13.Set_sort");
        System.out.println("14.Show_r 15.Opt 16.Show_acc 17.Add_acc 18.Del_acc 19.Mod_acc 20.Logout 99.Exit");
        System.out.println("****************************************");
    }

    public static void do_Login() {
        String acc, pw, verify;
        int i;
        for (i = 0; i < 3; i++) {
            System.out.println("Account:");
            acc = in.nextLine();
            System.out.println("Password:");
            pw = in.nextLine();
            System.out.println("Verify_string:" + configManage.getVerify());
            System.out.println("Input_Verify_string:");
            verify = in.nextLine();
            if (accountManage.login(acc, pw, verify)) {
                System.out.println("Login_success");
                break;
            } else {
                System.out.println("Error_wrong_account_password_or_verify_string");
            }
        }
        if (i == 3) {
            System.exit(0);
        }
    }

    public static void Show_a() {
        control.display(dataManage.getData());
        control.check_Cmd(control.show_Sub_Menu(), in.nextLine());
    }

    public static void Show_p() {
        String cmd;
        int pages, pnum = 1, c = 0;
        ArrayList<ArrayList<OneData>> pdata = new ArrayList<>();
        ArrayList<OneData> data = dataManage.getData();

        String[] code = { "3", "5", "10", "d", "0", "99" };
        System.out.println("Choose_show_per_page:");
        String[] code1 = { "3", "5", "10" };
        String[] info1 = { "3_data_per_page ", "5_data_per_page ", "10_data_per_page" };
        control.cmdFormat(code1, info1);
        String[] code2 = { "d", "0", "99" };
        String[] info2 = { "default ", "Go_back_to_main_menu ", "Exit_system" };
        control.cmdFormat(code2, info2);

        cmd = control.check_Cmd(code, in.nextLine());
        if (cmd.equals("3")) {
            pnum = 3;
        } else if (cmd.equals("5")) {
            pnum = 5;
        } else if (cmd.equals("10")) {
            pnum = 10;
        } else if (cmd.equals("d")) {
            pnum = Integer.parseInt(configManage.getPerPage());
        }
        pages = (int) Math.ceil(data.size() / (pnum * 1.0));

        for (int i = 0; i < pages; i++) {
            ArrayList<OneData> perpage = new ArrayList<>();
            for (int j = 0; j < pnum; j++) {
                if (c == data.size())
                    break;
                perpage.add(data.get(c));
                c++;
            }
            pdata.add(perpage);
        }

        for (int i = 0; i < pages;) {
            for (OneData line : pdata.get(i)) {
                line.printData(configManage.getName(), configManage.getBrand(), configManage.getCat(),
                        configManage.getCount(), configManage.getWeight(), configManage.getPrice());
            }
            if (pages == 1) {
                control.check_Cmd(control.show_Sub_Menu(), in.nextLine());
            } else if (i == 0) {
                String[] codeString = { "2", "0", "99" };
                String[] infoString = { "Next_page ", "Go_back_to_main_menu ", "Exit_system" };
                control.cmdFormat(codeString, infoString);
                cmd = control.check_Cmd(codeString, in.nextLine());
            } else if (i == pages - 1) {
                String[] codeString = { "1", "0", "99" };
                String[] infoString = { "Last_page ", "Go_back_to_main_menu ", "Exit_system" };
                control.cmdFormat(codeString, infoString);
                cmd = control.check_Cmd(codeString, in.nextLine());
            } else {
                String[] codeString = { "1", "2", "0", "99" };
                String[] infoString = { "Last_page ", "Next_page ", "Go_back_to_main_menu ", "Exit_system" };
                control.cmdFormat(codeString, infoString);
                cmd = control.check_Cmd(codeString, in.nextLine());
            }
            switch (cmd) {
                case "1":
                    i--;
                    break;
                case "2":
                    i++;
                    break;
                case "0":
                    return;
                case "99":
                    System.exit(0);
            }

        }
    }

    public static void Show_by_c() {
        String[] code = new String[catalogManage.catSize() + 2];
        for (int i = 0; i < catalogManage.catSize(); i++) {
            code[i] = Integer.toString(i + 1);
        }
        code[catalogManage.catSize()] = "0";
        code[catalogManage.catSize() + 1] = "99";
        System.out.println("Catalogs:");
        catalogManage.printCat();
        control.show_Sub_Menu();
        String cmd = control.check_Cmd(code, in.nextLine());
        System.out.println("Input_catalog_to_show:");
        control.display(dataManage.getData(catalogManage.getACat(Integer.parseInt(cmd) - 1)));
        control.check_Cmd(control.show_Sub_Menu(), in.nextLine());
    }

    public static void Search() {
        while (true) {
            System.out.println("Search by:");
            String[] codes = { "1", "2", "3" };
            String[] info = { "ID ", "Name ", "Brand" };
            control.cmdFormat(codes, info);
            control.show_Sub_Menu();
            String[] code = { "1", "2", "3", "0", "99" };
            int cat = Integer.parseInt(control.check_Cmd(code, in.nextLine()));
            if (cat == 0) {
                return;
            }
            System.out.println("Input_target:");
            String val = in.nextLine();
            while (!control.isRight(info[cat - 1].trim(), val)) {
                System.out.println("Error_wrong_data");
                System.out.println("Please_input_again:");
                val = in.nextLine();
            }
            ArrayList<OneData> data = dataManage.getData(cat, val);
            if (data.size() == 0) {
                System.out.println("Error_no_result");
            } else {
                System.out.println("Search_result:");
                control.display(data);
            }
            String[] code1 = { "1", "0", "99" };
            String[] info1 = { "Restart_search ", "Go_back_to_main_menu ", "Exit_system" };
            control.cmdFormat(code1, info1);
            if (control.check_Cmd(code1, in.nextLine()).equals("0")) {
                return;
            }
        }
    }

    public static void Mod() {
        String id, n, b, cat, co, w, p;
        int index;
        while (true) {
            System.out.println("Input_ID_to_be_modified:");
            id = in.nextLine();
            index = dataManage.find(Integer.parseInt(id));
            if (index == -1) {
                System.out.println("Error_no_such_data");
                continue;
            } else {
                System.out.println("Search_result:");
                control.display(dataManage.getData(1, id));
            }

            System.out.println("New_name:");
            n = in.nextLine();
            if (n.equals("")) {
                n = dataManage.getName(index);
            } else {
                n = control.update("Name", n);
                if (n.equals("")) {
                    n = dataManage.getName(index);
                }
            }

            System.out.println("New_brand:");
            b = in.nextLine();
            if (b.equals("")) {
                b = dataManage.getBrand(index);
            } else {
                b = control.update("Brand", b);
                if (b.equals("")) {
                    b = dataManage.getBrand(index);
                }
            }
            System.out.print("Catalogs:");
            catalogManage.printCat();
            System.out.println("New_catalog:");
            cat = in.nextLine();
            if (cat.equals("")) {
                cat = dataManage.getCatalog(index);
            } else {
                int catnum = catalogManage.getAllCat().size();
                int catindex = Integer.parseInt(cat);
                while (catindex <= 0 || catindex > catnum) {
                    System.out.println("Error_wrong_data");
                    System.out.println("Please_input_again:");
                    cat = in.nextLine();
                    if (cat.equals("")) {
                        cat = catalogManage.getACat(index);
                        break;
                    }
                    catindex = Integer.parseInt(cat);
                    cat = catalogManage.getACat(catindex - 1);
                }
                cat = catalogManage.getACat(catindex - 1);
            }

            System.out.println("New_count:");
            co = in.nextLine();
            if (co.equals("")) {
                co = dataManage.getCount(index);
            } else {
                co = control.update("Count", co);
                if (co.equals("")) {
                    co = dataManage.getCount(index);
                }
            }

            System.out.println("New_weight:");
            w = in.nextLine();
            if (w.equals("")) {
                w = dataManage.getWeight(index);
            } else {
                w = control.update("Weight", w);
                if (w.equals("")) {
                    w = dataManage.getWeight(index);
                }
            }

            System.out.println("New_price:");
            p = in.nextLine();
            if (p.equals("")) {
                p = dataManage.getPrice(index);
            } else {
                p = control.update("Price", p);
                if (p.equals("")) {
                    p = dataManage.getPrice(index);
                }
            }
            break;
        }

        String f = String.format("%s %s %s %s %s %s %s", id, n, b, cat, co, w, p);
        OneData data = new OneData(f);
        dataManage.mod(index, data);
        System.out.println("Modify_data_success");

        control.check_Cmd(control.show_Sub_Menu(), in.nextLine());
    }

    public static void Del() {
        System.out.println("Input_ID_to_be_deleted:");
        String id = in.nextLine();
        int index;
        index = dataManage.find(Integer.parseInt(id));
        if (index == -1) {
            System.out.println("Error_no_such_data");
        } else {
            dataManage.del(index);
            System.out.println("Delete_data_success");
        }

        control.check_Cmd(control.show_Sub_Menu(), in.nextLine());
    }

    public static void Add_storage() {
        System.out.println("Name:");
        String n = in.nextLine();
        n = control.update("Name", n);

        System.out.println("Brand:");
        String b = in.nextLine();
        b = control.update("Brand", b);

        System.out.print("Catalogs:");
        catalogManage.printCat();
        System.out.println("Catalog:");
        String cat = in.nextLine();
        int catindex=Integer.parseInt(cat);
        
        while (catindex <= 0 || catindex > catalogManage.catSize()) {
            System.out.println("Error_wrong_data");
            System.out.println("Please_input_again:");
            cat = in.nextLine();
            catindex=Integer.parseInt(cat);
        }
        String catalog = catalogManage.getACat(catindex - 1);
        
        System.out.println("Count:");
        String co = in.nextLine();
        co = control.update("Count", co);

        System.out.println("Weight:");
        String w = in.nextLine();
        w = control.update("Weight", w);

        System.out.println("Price:");
        String p = in.nextLine();
        p = control.update("Price", p);

        configManage.setLastId();
        String f = String.format("%s %s %s %s %s %s %s", configManage.getLastId(), n, b, catalog, co, w, p);
        OneData d = new OneData(f);
        dataManage.add(d);
        System.out.println("Add_contact_success");
        control.check_Cmd(control.show_Sub_Menu(), in.nextLine());
    }

    public static void Add_cat() {

        System.out.println("Please_input_new_catalog:");
        String cat = in.nextLine();
        String catSave = cat.substring(0, 1).toUpperCase() + cat.substring(1).toLowerCase();
        while (catSave.length() > 12) {
            System.out.println("Error_catalog_too_long");
            System.out.println("Please_input_new_catalog:");
            cat = in.nextLine();
            catSave = cat.substring(0, 1).toUpperCase() + cat.substring(1).toLowerCase();
        }
        while (catalogManage.checkCat(catSave)) {
            System.out.println("Error_catalog_existed");
            System.out.println("Please_input_new_catalog:");
            cat = in.nextLine();
            catSave = cat.substring(0, 1).toUpperCase() + cat.substring(1).toLowerCase();
        }
        catalogManage.addCat(catSave);
        System.out.println("Add_catalog_" + cat + "_success");

        control.check_Cmd(control.show_Sub_Menu(), in.nextLine());
    }

    public static void Show_cat() {
        ArrayList<String> cat = catalogManage.getAllCat();
        System.out.println("[Catalog]");
        for (String d : cat) {
            System.out.println(d);
        }
        control.check_Cmd(control.show_Sub_Menu(), in.nextLine());
    }

    public static void Set_field() {
        configManage.print_config();
        System.out.println();

        System.out.println("New_show_name(0/1):");
        String n = in.nextLine();
        while (!n.equals("0") && !n.equals("1")) {
            System.out.println("Input_error_plaese_input_0_or_1:");
            n = in.nextLine();
        }
        if (n.equals("0")) {
            configManage.setName("false");
        } else {
            configManage.setName("true");
        }

        System.out.println("New_show_brand(0/1):");
        String b = in.nextLine();
        while (!b.equals("0") && !b.equals("1")) {
            System.out.println("Input_error_plaese_input_0_or_1:");
            b = in.nextLine();
        }
        if (b.equals("0")) {
            configManage.setBrand("false");
        } else {
            configManage.setBrand("true");
        }

        System.out.println("New_show_catalog(0/1):");
        String cat = in.nextLine();
        while (!cat.equals("0") && !cat.equals("1")) {
            System.out.println("Input_error_plaese_input_0_or_1:");
            cat = in.nextLine();
        }
        if (cat.equals("0")) {
            configManage.setCat("false");
        } else {
            configManage.setCat("true");
        }

        System.out.println("New_show_count(0/1):");
        String c = in.nextLine();
        while (!c.equals("0") && !c.equals("1")) {
            System.out.println("Input_error_plaese_input_0_or_1:");
            c = in.nextLine();
        }
        if (c.equals("0")) {
            configManage.setCount("false");
        } else {
            configManage.setCount("true");
        }

        System.out.println("New_show_weight(0/1):");
        String w = in.nextLine();
        while (!w.equals("0") && !w.equals("1")) {
            System.out.println("Input_error_plaese_input_0_or_1:");
            w = in.nextLine();
        }
        if (w.equals("0")) {
            configManage.setWeight("false");
        } else {
            configManage.setWeight("true");
        }

        System.out.println("New_show_price(0/1):");
        String p = in.nextLine();
        while (!p.equals("0") && !p.equals("1")) {
            System.out.println("Input_error_plaese_input_0_or_1:");
            p = in.nextLine();
        }
        if (p.equals("0")) {
            configManage.setPrice("false");
        } else {
            configManage.setPrice("true");
        }

        System.out.println();
        configManage.print_config();
        System.out.println();

        control.check_Cmd(control.show_Sub_Menu(), in.nextLine());
    }

    public static void Set_page() {
        System.out.println("show_defalt_perpage:" + configManage.getPerPage());
        System.out.println("new_show_defalt_perpage:");
        String p = in.nextLine();
        if (p.equals("")) {
            p = configManage.getPerPage();
        } else {
            while (!p.matches("[0-9]+")) {
                System.out.println("Error_wrong_data");
                System.out.println("Please_input_again:");
                p = in.nextLine();
                if (p.equals("")) {
                    p = configManage.getPerPage();
                    break;
                }
            }
        }

        configManage.setPerPage(p);
        System.out.println("show_defalt_perpage:" + configManage.getPerPage());

        control.check_Cmd(control.show_Sub_Menu(), in.nextLine());
    }

    public static void Set_order() {
        System.out.println("show_sort_order:" + configManage.getOrder());

        System.out.println("Please_input_new_sort_order:");
        String o = in.nextLine();
        while (!o.equals("asc") && !o.equals("des")) {
            System.out.println("Input_error_plaese_input_asc_or_des:");
            o = in.nextLine();
        }
        if (o.equals("asc")) {
            configManage.setOrder("asc");
        } else {
            configManage.setOrder("des");
        }

        System.out.println("show_sort_order:" + configManage.getOrder());

        control.check_Cmd(control.show_Sub_Menu(), in.nextLine());
    }

    public static void Set_sort() {
        String[] codes = { "1", "2", "3", "4", "5", "6", "7" };
        String[] info = { "ID ", "Name ", "Brand ", "Catalog ", "Count ", "Weight ", "Price" };
        control.cmdFormat(codes, info);
        control.show_Sub_Menu();

        String sort = in.nextLine();
        String[] code = { "1", "2", "3", "4", "5", "6", "7", "0", "99" };
        sort = control.check_Cmd(code, sort);

        configManage.setField(info[Integer.parseInt(sort) - 1].trim());
        System.out.println("Sorted_by:" + configManage.getField());

        control.check_Cmd(control.show_Sub_Menu(), in.nextLine());
    }

    public static void Show_r() {
        ArrayList<OneData> raw = new ArrayList<>();

        for (String d : dataManage.getRawData()) {
            raw.add(new OneData(d));
        }

        control.display(raw);

        control.check_Cmd(control.show_Sub_Menu(), in.nextLine());
    }

    public static void Opt() {
        System.out.println("Please_confirm_data_optimize_y_or_n:");
        String cmd = in.nextLine();
        if (cmd.equals("y")) {
            dataManage.opt();
            System.out.println("Data_optimize_success");
        } else {
            System.out.println("Data_optimize_denied");
        }

        control.check_Cmd(control.show_Sub_Menu(), in.nextLine());
    }

    public static void Show_acc() {
        accountManage.showAcc();

        control.check_Cmd(control.show_Sub_Menu(), in.nextLine());
    }

    public static void Add_acc() {
        System.out.println("New_account:");
        String acc = in.nextLine();
        System.out.println("New_password:");
        String pw = in.nextLine();

        accountManage.addAcc(acc, pw);

        control.check_Cmd(control.show_Sub_Menu(), in.nextLine());
    }

    public static void Del_acc() {
        System.out.println("Delete_account:");
        String acc = in.nextLine();
        while (!accountManage.checkAcc(acc)) {
            System.out.println("No_account_please_try_again:");
            acc = in.nextLine();
        }
        accountManage.delAcc(acc);
        System.out.println("Delete_account_success");

        control.check_Cmd(control.show_Sub_Menu(), in.nextLine());
    }

    public static void Mod_acc() {
        System.out.println("Modify_account:");
        String acc = in.nextLine();
        while (!accountManage.checkAcc(acc)) {
            System.out.println("No_account_please_try_again:");
            acc = in.nextLine();
        }

        System.out.println("New_account:");
        String newAcc = in.nextLine();

        System.out.println("New_password:");
        String newPw = in.nextLine();

        accountManage.modAcc(acc, newAcc, newPw);
        System.out.println("Modify_account_success");

        control.check_Cmd(control.show_Sub_Menu(), in.nextLine());
    }

    public static void Logout() {
        System.out.println("Please_confirm_to_logout_y_or_n:");
        String cmd = in.nextLine();
        while (!cmd.equals("y") && !cmd.equals("n")) {
            System.out.println("Error_input");
            System.out.println("Please_input_again:");
            cmd = in.nextLine();
        }
        if (cmd.equals("y")) {
            do_Login();
        }
    }

}
