package org.rratan.leetcode;

import java.util.*;

public class _71_simplify_path {
    public static String simplifyPath(String path) {
        Stack<String> st = new Stack<>();
//        st.push("/");
        int i,j,n;
        String[] split = path.split("/");
        for(String s:split){
//            System.out.println(s);
            if(s.equals("..")){
                if(st.isEmpty()){
                    continue;
                }
                st.pop();
            }else if(s.equals(".")){
                continue;
            }else if(s.trim().length()>0){
//                System.out.println(s.trim().length());
                st.push(s);
            }
        }
        List<String> ans = new ArrayList<String>(st);
//        Collections.reverse(ans);
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        for(i=0;i<ans.size();i++){

                sb.append(ans.get(i));
            if(i!=ans.size()-1){
                sb.append("/");
            }


        }
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(simplifyPath("/home//foo/"));
    }
}
