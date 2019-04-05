import java.awt.FileDialog;
import java.awt.Frame;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by wojcik.jaroslaw1 on 04.04.2019.
 */
public class TextFileDAO implements TextFileInterface {

    private static String FILENAME;
    private static BufferedReader br = null;
    private static int counterWord;
    private static StringBuilder allText = new StringBuilder();
    private static LinkedHashMap<String, List<String>> linkedHashMap = new LinkedHashMap<>();
    private static Map<String, Integer> mapWordsAndCount = new HashMap<>();

    @Override
    public String getFile() {
        Frame a = new Frame("Window init");
        a.setBounds(20, 20, 400, 500);
        a.setVisible(true);

        FileDialog fd = new FileDialog(a, "Select file", FileDialog.LOAD);
        // Ewentualnie: FileDialog fd =new FileDialog(a,"Zapisz",FileDialog.SAVE);
        fd.setVisible(true);
        a.setVisible(false);
        String katalog = fd.getDirectory();
        FILENAME = fd.getFile();
        System.out.println("\nSelected file: " + FILENAME);
        System.out.println("In folder: " + katalog);
        System.out.println("Path: " + katalog + FILENAME);
        fd.dispose();
        a.dispose();
        return FILENAME;
    }

    @Override
    public void readFile() {

        File file = new File(FILENAME);

        String row = "";
        try {
            br = new BufferedReader(new FileReader(file));
            while (true) {
                try {
                    if (((row = br.readLine()) == null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                allText.append(row).append(" ");

                System.out.println(row);

                StringTokenizer stringTokenizer = new StringTokenizer(row, " ");
                while(stringTokenizer.hasMoreTokens()) {
                    stringTokenizer.nextToken();
                    counterWord++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\nFile has " + counterWord + " words");
    }


    private  Map<String, Integer> getMapWordsAndCount() {

        String [] arrAllText = allText.toString().split(" ");

        for (String elem : arrAllText) {
            List<String> list = new ArrayList<>();
            list.add(elem);
            linkedHashMap.put(elem, list);
        }

        for (String elem : arrAllText) {
            linkedHashMap.forEach((k, v) -> {
                if (elem.equals(k)) {
                    v.add(elem);
                }
            });
        }

        linkedHashMap.forEach((k, v) -> mapWordsAndCount.put(k, v.size()-1));

        return mapWordsAndCount;
    }


    @Override
    public void getTop10WordsAndCount() {

        System.out.println("\nTop 10 words:");
        getMapWordsAndCount().entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(10)
                .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
    }

    @Override
    public void getTop10WordsLength() {

        System.out.println("\nTop 10 length words:");

        Map<String, Integer> map10top = getMapWordsAndCount().keySet()
                .stream()
                .collect(Collectors.toMap(word -> word, String::length));

        map10top.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(10)
                .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));

    }

}
