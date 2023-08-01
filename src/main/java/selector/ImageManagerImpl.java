package selector;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;


import utilities.Pair;
import utilities.Value;


public class ImageManagerImpl implements ImageManager{

    private File dir;
    private List<Pair<String, Value>> nameFiles;
    private int actInd = 0;
    private Predicate<Pair<String, Value>> isUnclassified = s -> s.getY().equals(Value.UNCLASSIFIED) || s.getY().equals(Value.ZERO);

    public ImageManagerImpl(File dir){
        this.dir = dir;
        this.nameFiles = Arrays.asList(dir.list()).stream().map(s -> new Pair<>(s, Value.UNCLASSIFIED)).toList();
        System.out.println("### " + nameFiles.size());
    }

    public void getActFile() {
        nameFiles.forEach(s -> System.out.println(s));
    }

    private void adaptIndex() {
        this.actInd = this.actInd < 0 ? nameFiles.size()-1 : this.actInd;
        this.actInd = this.actInd % nameFiles.size();
    }

    @Override
    public String next() {
        //do {
            this.actInd++;
            adaptIndex();
        //} while  (!isUnclassified.test(nameFiles.get(this.actInd)));
        return nameFiles.get(this.actInd).getX();
    }


    @Override
    public String prev() {
       // while (!isUnclassified.test(nameFiles.get(this.actInd))){
            this.actInd--;
            adaptIndex();
        //}
        return nameFiles.get(this.actInd).getX();
    }

    public String getActName() {
        return nameFiles.get(this.actInd).getX();
    }

    @Override
    public void classThisImage(Value toClass) {
        nameFiles.get(actInd).setY(toClass);
    }

    @Override
    public Pair<String, Value> getActImage() {
        return nameFiles.get(actInd);
    }

    @Override
    public void closeAll() {
        Map<Value, List<String>> classes = nameFiles.stream()
            .filter(s -> s.getY().isClass())
            .collect(Collectors.toMap(
                s -> s.getY(),
                a -> new LinkedList<>(List.of(a.getX())),
                (l, ll) -> {
                    ll.addAll(l);
                    return ll;
                },
                () -> new HashMap<Value, List<String>>()));
        
            // .map(p -> p.getY())
            // .distinct()
            // .filter(s -> s.isClass())
            // .map(s -> s.toString())
            // .collect(Collectors.toSet());
        
        String ROOT = System.getProperty("user.dir");
        String SEP = System.getProperty("file.separator");
        String PATH = ROOT + SEP + "src" + SEP + "main" + SEP + "resources" + SEP;
        String oldd = "TOSELECT" + SEP;
        String neww = "CLASSIFIED" + SEP;
        
        for (Entry<Value, List<String>> e : classes.entrySet()){
            String finPath = e.getKey().toString();
            (new File(PATH + neww + finPath)).mkdirs();
            finPath += SEP;
            for (String tmp : e.getValue()) {
                File oldPos = new File(PATH + oldd + tmp);
                File newPos = new File(PATH + neww + finPath + tmp);
                oldPos.renameTo(newPos);
            }            
        }

        System.exit(0);
    }

   

    
}
