import java.util.*;

public class ConfigManage {
    Map<String, String> config = new HashMap<>();
    FileManage file;

    public ConfigManage() {
        file = new FileManage("config.txt");
        ArrayList<String> c = file.getData();

        for (String str : c) {
            String[] s = str.split(" *: *");
            config.put(s[0], s[1]);
        }
    }

    public String getVerify() {
        return config.get("verify_string");
    }

    public String getLastId() {
        return String.format("%04d", Integer.parseInt(config.get("used_last_id")));
    }

    public String getPerPage() {
        return config.get("show_defalt_perpage");
    }

    public String getCount() {
        return config.get("show_count");
    }

    public String getOrder() {
        return config.get("show_sort_order");
    }

    public String getField() {
        return config.get("show_sort_field");
    }

    public String getWeight() {
        return config.get("show_weight");
    }

    public String getPrice() {
        return config.get("show_price");
    }

    public String getBrand() {
        return config.get("show_brand");
    }

    public String getName() {
        return config.get("show_name");
    }

    public String getCat() {
        return config.get("show_catalog");
    }

    public void setVerify(String v) {
        config.put("verify_string", v);
        save();
    }

    public void setLastId() {
        int i = Integer.parseInt(getLastId()) + 1;
        config.put("used_last_id", Integer.toString(i));
        save();
    }

    public void setPerPage(String p) {
        config.put("show_defalt_perpage", p);
        save();
    }

    public void setCount(String c) {
        config.put("show_count", c);
        save();
    }

    public void setOrder(String o) {
        config.put("show_sort_order", o);
        save();
    }

    public void setField(String f) {
        config.put("show_sort_field", f);
        save();
    }

    public void setWeight(String w) {
        config.put("show_weight", w);
        save();
    }

    public void setPrice(String p) {
        config.put("show_price", p);
        save();
    }

    public void setBrand(String b) {
        config.put("show_brand", b);
        save();
    }

    public void setName(String n) {
        config.put("show_name", n);
        save();
    }

    public void setCat(String c) {
        config.put("show_catalog", c);
        save();
    }

    public void print_config() {
        System.out.print("[1].Show_name:" + (getName().equals("true") ? "1" : "0"));
        System.out.print(" [2].Show_brand:" + (getBrand().equals("true") ? "1" : "0"));
        System.out.print(" [3].Show_catalog:" + (getCat().equals("true") ? "1" : "0"));
        System.out.print(" [4].Show_count:" + (getCount().equals("true") ? "1" : "0"));
        System.out.print(" [5].Show_weight:" + (getWeight().equals("true") ? "1" : "0"));
        System.out.print(" [6].Show_price:" + (getPrice().equals("true") ? "1" : "0"));
        System.out.println("");
    }

    public void save() {
        ArrayList<String> temp = new ArrayList<>();
        for (String key : config.keySet()) {
            String s = String.format("%s: %s", key, config.get(key));
            temp.add(s);
        }
        file.setData(temp);

    }

}
