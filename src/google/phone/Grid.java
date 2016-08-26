package google.phone;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/*
write a grid class

1. set the height and width
2. set value at arbitrary point
3. get max value of the grid

假设矩阵大小N＊M，那么（至少）有如下做法：

1. 维护一个正常的grid。set value O(1)，取最大值O(NM)，空间复杂度O(NM)。
2. 二维线段树维护最大值。set value O(logNlogM)，取最大值O(1)，空间复杂度O(NM)。
3. 自己建heap，然后建立一个从grid cell和heap index的双向映射，然后每次更新值的时候先从grid到heap的映射中找到heap对应的index，
然后根据改大还是改小决定shift up或者shift down并且维护所有变更的index。set value O(log(NM))，取最大值O(1)，空间复杂度O(NM)。
4. 记录最大值和最大值坐标，每次修改到最大值所在坐标的时候更新最大值。set value 最好情况O(1)，最差情况O(NM)，取最大值O(1)，空间复杂度O(NM)。

之后就是考虑如何tradeoff。如果set数量远远大于query数量，那么set的复杂度就要尽量低，于是方法1是最好的；
如果query数量远远大于set数量，或者说set数量远远低于矩阵大小，那么方法4是最好的，因为最大值被修改的可能性不高；
如果query和set数量基本相同，而且和矩阵大小是一个数量级，那么方法3应该是首选。如果这是一个实际问题，那么可能所有的情况都会出现，
所以可以根据现在query和set的比例情况自适应的选择最好的处理方式。
 */
public class Grid {
    int height;
    int width;
    Map<Integer, Integer> map;// index -> value
    TreeMap<Integer, Set<Integer>> max; // max -> index array
    public Grid(){
        map = new HashMap<>();
        max  = new TreeMap<>();
    }

    public void set(int height, int width) {
    	this.height = height;
    	this.width = width;
    }
    public void setValue(int height, int width, int value) {
        int index = setIndex(height, width);
        if (map.containsKey(index)){
            int oldValue = map.get(index); // get old value
            Set<Integer> oldValueSet = max.get(oldValue);
            oldValueSet.remove(index);
            if (oldValueSet.size()==0)
                max.remove(oldValue);
            if (max.containsKey(value))
                max.get(value).add(index);
            else
                max.computeIfAbsent(value, k->{
                    Set<Integer> set = new HashSet<Integer>();
                    set.add(index);
                    return set;
                });
        }
        else {
            max.computeIfAbsent(value, k->{
                Set<Integer> set = new HashSet<Integer>();
                set.add(index);
                return set;
            });
        }
        map.put(index,value);
    }

    public int setIndex(int height, int width){
        return  height * this.height + width;
    }
    public int getMax(){
        return max.lastKey();
    }
}
