import java.util.*;

public class CatalogManage {
    FileManage file;
    ArrayList<String> catalog = new ArrayList<>();

    public CatalogManage() {
        file = new FileManage("catalog.txt");
        catalog = file.getData();
    }

    public int catSize(){
        return catalog.size();
    }
    
    public ArrayList<String> getAllCat() {
        return new ArrayList<String>(catalog);
    }

    public String getACat(int index) {
        if (index >= catalog.size() || index < 0) {
            return null;
        }
        return catalog.get(index);
    }

    public void addCat(String c) {
        catalog.add(c);
        catalog.sort(Comparator.naturalOrder());
        save();
    }

    public void printCat() {
        for (int i = 0; i < catalog.size(); i++) {
            if (i != 0) {
                System.out.print(" ");
            }
            System.out.printf("[%d].%s", i + 1, catalog.get(i));
        }
        System.out.println();
    }

    public boolean checkCat(String cat){
        for(int i=0;i<catalog.size();i++){
            if(catalog.get(i).equals(cat))
            {
                return true;
            }
        }
        return false;
    }

    public void save() {
        file.setData(catalog);
    }


}
