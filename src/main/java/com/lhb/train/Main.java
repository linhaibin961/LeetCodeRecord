import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int countLine = Integer.valueOf(in.nextLine());
        // 注意 hasNext 和 hasNextLine 的区别
        Map<Integer, Integer> map = new TreeMap();
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            String[] strArr = str.split(" ");
            Integer key = Integer.valueOf(strArr[0]);
            Integer value = Integer.valueOf(strArr[1]);
            if (map.containsKey(key)) {
                value = map.get(key) + value;
            }
            map.put(key, value);
            if (--countLine == 0)
                break;
        }
        Set<Integer> set = map.keySet();
//        set.forEach(e -> {
//            System.out.println(e);
//        });
        for (Integer e : set) {
            System.out.println(e + " " + map.get(e));
        }
    }
}