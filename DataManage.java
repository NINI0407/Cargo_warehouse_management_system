import java.util.*;

public class DataManage {
    ArrayList<OneData> datas = new ArrayList<>();
    ArrayList<String> rawdata = new ArrayList<>();
    Comparator<OneData> compare;
    ConfigManage config ;
    FileManage file;

    public DataManage(ConfigManage configManage) {
        file = new FileManage("data.txt");
        rawdata = file.getData();

        for (String d : rawdata) {
            datas.add(new OneData(d));
        }
        this.config=configManage;
    }

    public ArrayList<String> toString(ArrayList<OneData> s) {
        ArrayList<String> temp = new ArrayList<>();

        for (OneData d : s) {
            String str = d.toString();
            temp.add(str);
        }
        return temp;
    }

    public ArrayList<String> getRawData() {
        return rawdata;
    }

    public int find(int id) {
        String ID = String.format("%04d", id);

        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).isTarget(1, ID)) {
                return i;
            }
        }
        return -1;
    }

    public void del(int index) {
        datas.remove(index);
        save();
    }

    public void mod(int index, OneData n) {
        datas.remove(index);
        datas.add(n);
        save();
    }

    public void add(OneData n) {
        datas.add(n);
        save();
    }

    public void opt() {
        String field = config.getField();
        String order = config.getOrder();
        Comparator<OneData> compare = OneData.getComparator(field, order);
        Collections.sort(datas, compare);
        rawdata = toString(datas);
        save();
    }

    public ArrayList<OneData> getData() {
        ArrayList<OneData> temp = new ArrayList<>(datas);
        String field = config.getField();
        String order = config.getOrder();
        //System.out.print(field+order);
        Comparator<OneData> compare = OneData.getComparator(field, order);
        Collections.sort(temp, compare);
        return temp;
    }

    public ArrayList<OneData> getData(String cat) {
        ArrayList<OneData> temp = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).isTarget(4, cat)) {
                temp.add(datas.get(i));
            }
        }
        String field = config.getField();
        String order = config.getOrder();
        Comparator<OneData> compare = OneData.getComparator(field, order);
        Collections.sort(temp, compare);
        return temp;
    }

    public ArrayList<OneData> getData(int field, String val) {
        ArrayList<OneData> temp = new ArrayList<>();
        for (OneData d : datas) {
            if (d.isTarget(field, val)) {
                temp.add(d);
            }
        }
        return temp;
    }

    public String getName(int index) {
        return datas.get(index).name;
    }

    public String getBrand(int index) {
        return datas.get(index).brand;
    }

    public String getCatalog(int index) {
        return datas.get(index).catalog;
    }

    public String getCount(int index) {
        return datas.get(index).count;
    }

    public String getWeight(int index) {
        return datas.get(index).weight;
    }

    public String getPrice(int index) {
        return datas.get(index).price;
    }

    public void save() {
        ArrayList<String> temp = new ArrayList<>();
        temp = toString(datas);
        file.setData(temp);
    }
}
