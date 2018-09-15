package datastruct;

import java.util.*;

/**
 * You are given several logs that each log contains a unique id and timestamp. Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.

 Design a log storage system to implement the following functions:

 void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.


 int[] Retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the range from start to end. Start and end all have the same format as timestamp. However, granularity means the time level for consideration. For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.

 Example 1:
 put(1, "2017:01:01:23:59:59");
 put(2, "2017:01:01:22:59:59");
 put(3, "2016:01:01:00:00:00");
 retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
 retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
 Note:
 There will be at most 300 operations of Put or Retrieve.
 Year ranges from [2000,2017]. Hour ranges from [00,23].
 Output for Retrieve has no order required.

 */

// TreeMap的subMap可以返回一个区间的entries，注意通过参数控制区间的开闭

public class DesignLogStorageSystem635 {

    private String minDate, maxDate;
    private Map<String, Integer> graMap;
    private TreeMap<String, List<Integer>> treeMap;

    public DesignLogStorageSystem635() {
        minDate = "2000:01:01:00:00:00";
        maxDate = "2017:12:31:23:59:59";

        graMap = new HashMap<>();
        graMap.put("Year", 4);
        graMap.put("Month", 7);
        graMap.put("Day", 10);
        graMap.put("Hour", 13);
        graMap.put("Minute", 16);
        graMap.put("Second", 19);


        treeMap = new TreeMap<>();
    }

    public void put(int id, String timestamp) {
        if(!treeMap.containsKey(timestamp))
        {
            treeMap.put(timestamp, new ArrayList<>());
        }
        treeMap.get(timestamp).add(id);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        int graIndex = graMap.get(gra);
        String start = s.substring(0, graIndex) + minDate.substring(graIndex);
        String end = e.substring(0, graIndex) + maxDate.substring(graIndex);

        // subMap() default [start, end) 用两个true来保证两端闭区间
        SortedMap<String, List<Integer>> resMap = treeMap.subMap(start, true, end, true);
        List<Integer> res = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : resMap.entrySet())
        {
            res.addAll(entry.getValue());
        }
        return res;
    }
}


/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */