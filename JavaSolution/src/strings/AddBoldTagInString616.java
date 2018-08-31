package strings;

import sun.jvm.hotspot.utilities.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b>
 * to wrap the substrings in s that exist in dict. If two such substrings overlap,
 * you need to wrap them together by only one pair of closed bold tag.
 * Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
 *
 Example 1:
 Input:
 s = "abcxyz123"
 dict = ["abc","123"]
 Output:
 "<b>abc</b>xyz<b>123</b>"
 Example 2:
 Input:
 s = "aaabbcc"
 dict = ["aaa","aab","bc"]
 Output:
 "<b>aaabbc</b>c"
 Note:
 The given dict won't contain duplicates, and its length won't exceed 100.
 All the strings in input have length in range [1, 1000].

 */

// 定义一个interval内部类来封装start end，
// 1.用while循环和string.indexOf来获取一个interval list，
// 2.对interval list按start排序，
// 3.合并list中重叠的区间，
// 4.用substring和StringBuilder来基于处理后的interval list得到最后的result

public class AddBoldTagInString616 {

    public class _interval{
        int start;
        int end;
        _interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public String addBoldTag(String s, String[] dict) {

        List<_interval> intervalList = new ArrayList<>();
        for(String str : dict)
        {
            int index = s.indexOf(str);
            while(index >= 0)
            {
                intervalList.add(new _interval(index, index+str.length()));
                index = s.indexOf(str, index+1);
            }
        }


        Collections.sort(intervalList,new Comparator<_interval>(){
            public int compare(_interval a,_interval b){
                return a.start-b.start;
            }
        });

        intervalList = mergeList(intervalList);


        StringBuilder sb = new StringBuilder();
        int start = 0;
        for(_interval interval : intervalList)
        {
            sb.append(s.substring(start, interval.start));
            sb.append("<b>");
            sb.append(s.substring(interval.start, interval.end));
            sb.append("</b>");
            start = interval.end;
        }

        if(start < s.length())
        {
            sb.append(s.substring(start));
        }

        return sb.toString();
    }

    public List<_interval> mergeList(List<_interval> intervalList)
    {

        List<_interval> res = new ArrayList<>();

        if(intervalList == null || intervalList.size() == 0)
        {
            return res;
        }

        for( _interval interval : intervalList)
        {

            if(res.size() == 0)
            {
                res.add(interval);
                continue;
            }

            _interval last = res.get(res.size()-1);
            if(last.end < interval.start)
            {
                res.add(interval);
            }
            else {
                // 要注意用max，不能直接last.end = interval.end
                last.end = Math.max(last.end, interval.end);
            }
        }

        return res;
    }

}
