package Barclay;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by linyujie on 10/26/17.
 */
public class JosephusProblem {
    public List<Integer> OutPutSeq(int num, int rep) {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        int count = rep;

        for (int i = 0; i < num; i++) {

            if (set.contains(i)) {
                continue;
            }
            if (count == 1) {
                list.add(i);
                set.add(i);
                count = rep + 1;
            }
            if (i == num - 1 && set.size() != num) {
                i = -1;
            }
            count--;
        }
        return list;
    }
    public static void main(String[] args) {
        JosephusProblem JP = new JosephusProblem();
        List<Integer> list = JP.OutPutSeq(10, 3);
        for(int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + ",");
        }
    }
}
