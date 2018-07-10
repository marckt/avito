import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/*Парсит количество просмотров */
public class Avito {
    private static String[] mySites = {"https://www.avito.ru/novosibirsk/predlozheniya_uslug/maloetazhnoe_stroitelstvo._rekonstruktsiya_doma_799177412",
            "https://www.avito.ru/novosibirsk/posuda_i_tovary_dlya_kuhni/tarelka_1398355167",
            "https://www.avito.ru/novosibirsk/tovary_dlya_kompyutera/prodam_otlichnyy_bolshoy_korpus_powerman_pro_pm-003_1649809101",
            "https://www.avito.ru/novosibirsk/tovary_dlya_kompyutera/hp_g6-2000_zapchasti_1342778071",
            "https://www.avito.ru/novosibirsk/tovary_dlya_kompyutera/hp_g6_-_1000_serii_zapchasti_ot_noutbukov_1610580921"}; //ссыли на объявы(не мои)

    public static void main(String[] args) {
        Document doc = null;

        HashMap<String, Integer> sites = new HashMap<>();
        int count = 0;
        for (String s : mySites) {
            int count2 = 0;

            try {
                doc = Jsoup.connect(s).get();
                Elements links = doc.select("a[href=#]");
                count = Integer.parseInt(links.text().substring(links.text().indexOf("+"), links.text().lastIndexOf(")")));
                count2++;
            } catch (NumberFormatException e) {
                sites.put(s, 0);
            } catch (IOException e) {
                sites.put(s, -1);
            }
            sites.put(s, count - count2);
        }
        printValues(sites);
    }
    private static void printValues(Map<String, Integer> map)
    {
        for(Map.Entry<String, Integer> pair : map.entrySet())
        {
            int value = pair.getValue();
            String key = pair.getKey();
            System.out.println(key+"\n"+"Просмотров: "+value);
        }
    }
}