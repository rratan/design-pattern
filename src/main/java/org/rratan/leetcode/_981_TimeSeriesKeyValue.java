package org.rratan.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _981_TimeSeriesKeyValue {
    class TimeValue implements Comparable<TimeValue>{
        int timestamp;
        String value;

        public TimeValue(String value,int timestamp){
            this.timestamp = timestamp;
            this.value = value;
        }

        @Override
        public int compareTo(TimeValue o) {
            if(o.timestamp == this.timestamp){
                return this.value.compareTo(o.value);
            }
            return o.timestamp-this.timestamp;
        }
    }

    class TimeMap {
        private HashMap<String, List<TimeValue>> mp;
        public TimeMap() {
            this.mp = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            mp.putIfAbsent(key,new ArrayList<TimeValue>());
            mp.get(key).add(new TimeValue(value,timestamp));
        }

        public String get(String key, int timestamp) {
            String ans ="";
            List<TimeValue> lis = mp.getOrDefault(key,null);
            if(lis == null){
                return ans;
            }
            int l=0,h = lis.size()-1;
            while(l<=h){
                int mid = (l+h)/2;
                if(lis.get(mid).timestamp> timestamp){
                    h = mid-1;
                }else{
                    ans = lis.get(mid).value;
                    l = mid+1;
                }
            }
            return ans;

        }


    }
}
