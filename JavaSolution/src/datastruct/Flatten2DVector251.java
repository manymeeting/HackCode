package datastruct;

import java.util.List;

/**
 * Implement an iterator to flatten a 2d vector.

 Example:

 Input: 2d vector =
 [
 [1,2],
 [3],
 [4,5,6]
 ]
 Output: [1,2,3,4,5,6]
 Explanation: By calling next repeatedly until hasNext returns false,
 the order of elements returned by next should be: [1,2,3,4,5,6].
 Follow up:
 As an added challenge, try to code it using only iterators in C++ or iterators in Java.


 */

// 注意处理空list的情况，比如[[1], [], [2,3]]

public class Flatten2DVector251 {
    int x, y;
    List<List<Integer>> vec;

    public Flatten2DVector251(List<List<Integer>> vec2d) {
        x = 0;
        y = 0;
        vec = vec2d;
        while( x < vec.size() && vec.get(x).size() == 0) x++;
    }

    public Integer next() {
        List<Integer> list = vec.get(x);
        int next = list.get(y++);
        if(y == list.size())
        {
            x++;
            while(x < vec.size() && vec.get(x).size() == 0) x++;
            y = 0;
        }
        return next;
    }


    public boolean hasNext() {

        if(x > vec.size() - 1) return false;
        return true;
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */