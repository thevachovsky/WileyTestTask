package TestFrameWork.ApiTests.Commons;

import java.util.ArrayList;
import java.util.List;

public class Commons {
    public static List<Integer[]> getParametersIndexes(Integer max){
        List<Integer[]> range = new ArrayList<Integer[]>();
        for(int i=0; i<max; i++){
            Integer[] nextArray = {i};
            range.add(nextArray);
        }
        return range;
    }
}
