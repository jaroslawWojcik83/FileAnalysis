
import java.util.*;

/**
 * Created by wojcik.jaroslaw1 on 04.04.2019.
 */
public class Main2 {

        public static void main(String[] args) {


            String tekst1 = "bbb ala ma kota ala ma kota ala ma kota aaa bbb";
            String tekst2 = "bbb ala ma kota ala ma kota ala ma kota aaa bbb";

            LinkedHashMap<String, List<String>> lhm = new LinkedHashMap<>();
            Map<String, Integer> map = new HashMap<>();


            String [] arrText = tekst1.split(" ");


            for (String elem : arrText) {
                List<String> list = new ArrayList<>();
                list.add(elem);
                lhm.put(elem, list);
            }

            for (String elem : arrText) {
                lhm.forEach((k, v) -> {
                    if (elem.equals(k)) {
                        v.add(elem);
                    }
                });
            }

            lhm.forEach((k, v) -> {
                map.put(k, v.size());
            });


            lhm.forEach((k, v) -> {
                System.out.println(k + " " + v);
            });

            System.out.println("******************");
            map.forEach((k, v) -> {
                System.out.println(k + " " + v);
            });



        }
    }


