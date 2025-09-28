import java.util.Comparator;

public class OneData {
    public String id, name, brand, catalog, count, weight, price;

    public OneData(String s) {
        String str[] = s.split(" ");
        try {
            id = String.format("%04d", Integer.parseInt(str[0]));
        } catch (Exception e) {
            id = str[0];
        }
        name = str[1];
        brand = str[2];
        catalog = str[3];
        count = str[4];
        weight = str[5];
        price = str[6];
    }

    public String toString() {
        String s = String.format("%s %s %s %s %s %s %s", id, name, brand, catalog, count, weight, price);
        return s;
    }

    public void printData(String n, String b, String cat, String co, String w, String p) {
        System.out.printf("%-4s ", id);
        if (n.equals("true")) {
            System.out.printf("%-12s ", name);
        }
        if (b.equals("true")) {
            System.out.printf("%-12s ", brand);
        }
        if (cat.equals("true")) {
            System.out.printf("%-12s ", catalog);
        }
        if (co.equals("true")) {
            System.out.printf("%-7s ", count);
        }
        if (w.equals("true")) {
            System.out.printf("%-8s ", weight);
        }
        if (p.equals("true")) {
            System.out.printf("%-7s ", price);
        }
        System.out.println();
    }

    public boolean isTarget(int field, String val) {
        switch (field) {
            case 1:
                val = String.format("%04d", Integer.parseInt(val));
                return id.equals(val);
            case 2:
                return name.equals(val);
            case 3:
                return brand.equals(val);
            case 4:
                return catalog.equals(val);
            case 5:
                return count.equals(val);
            case 6:
                return weight.equals(val);
            case 7:
                return price.equals(val);
            default:
                return false;
        }
    }

    public static Comparator<OneData> getComparator(String field, String order) {
        field = field.toLowerCase();
        order = order.toLowerCase();
        //System.out.print(field+order);
        if (field.equals("id")) {
            if (order.equals("asc")) {
                return ID_ASC;
            }
            return ID_DES;
        }
        if (field.equals("name")) {
            if (order.equals("asc")) {
                return NAME_ASC;
            }
            return NAME_DES;
        }
        if (field.equals("brand")) {
            if (order.equals("asc")) {
                return BRAND_ASC;
            }
            return BRAND_DES;
        }
        if (field.equals("catalog")) {
            if (order.equals("asc")) {
                return CATALOG_ASC;
            }
            return CATALOG_DES;
        }
        if (field.equals("count")) {
            if (order.equals("asc")) {
                return COUNT_ASC;
            }
            return COUNT_DES;
        }
        if (field.equals("weight")) {
            if (order.equals("asc")) {
                return WEIGHT_ASC;
            }
            return WEIGHT_DES;
        }
        if (field.equals("price")) {
            if (order.equals("asc")) {
                return PRICE_ASC;
            }
            return PRICE_DES;
        }
        if (order.equals("asc")) {
            return ID_ASC;
        }
        return ID_DES;
    }

    public static Comparator<OneData> ID_ASC = new Comparator<OneData>() {
        public int compare(OneData c1, OneData c2) {
            return c1.id.compareTo(c2.id);
        }
    };

    public static Comparator<OneData> ID_DES = new Comparator<OneData>() {
        public int compare(OneData c1, OneData c2) {
            return c2.id.compareTo(c1.id);
        }
    };

    public static Comparator<OneData> NAME_ASC = new Comparator<OneData>() {
        public int compare(OneData c1, OneData c2) {
            return c1.name.compareTo(c2.name);
        }
    };

    public static Comparator<OneData> NAME_DES = new Comparator<OneData>() {
        public int compare(OneData c1, OneData c2) {
            return c2.name.compareTo(c1.name);
        }
    };

    public static Comparator<OneData> BRAND_ASC = new Comparator<OneData>() {
        public int compare(OneData c1, OneData c2) {
            return c1.brand.compareTo(c2.brand);
        }
    };

    public static Comparator<OneData> BRAND_DES = new Comparator<OneData>() {
        public int compare(OneData c1, OneData c2) {
            return c2.brand.compareTo(c1.brand);
        }
    };

    public static Comparator<OneData> CATALOG_ASC = new Comparator<OneData>() {
        public int compare(OneData c1, OneData c2) {
            return c1.catalog.compareTo(c2.catalog);
        }
    };

    public static Comparator<OneData> CATALOG_DES = new Comparator<OneData>() {
        public int compare(OneData c1, OneData c2) {
            return c2.catalog.compareTo(c1.catalog);
        }
    };

    public static Comparator<OneData> COUNT_ASC = new Comparator<OneData>() {
        public int compare(OneData c1, OneData c2) {
            return c1.count.compareTo(c2.count);
        }
    };

    public static Comparator<OneData> COUNT_DES = new Comparator<OneData>() {
        public int compare(OneData c1, OneData c2) {
            return c2.count.compareTo(c1.count);
        }
    };

    public static Comparator<OneData> WEIGHT_ASC = new Comparator<OneData>() {
        public int compare(OneData c1, OneData c2) {
            return c1.weight.compareTo(c2.weight);
        }
    };

    public static Comparator<OneData> WEIGHT_DES = new Comparator<OneData>() {
        public int compare(OneData c1, OneData c2) {
            return c2.weight.compareTo(c1.weight);
        }
    };

    public static Comparator<OneData> PRICE_ASC = new Comparator<OneData>() {
        public int compare(OneData c1, OneData c2) {
            return c1.price.compareTo(c2.price);
        }
    };

    public static Comparator<OneData> PRICE_DES = new Comparator<OneData>() {
        public int compare(OneData c1, OneData c2) {
            return c2.price.compareTo(c1.price);
        }
    };

}
