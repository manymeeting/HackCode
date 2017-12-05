import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryTreeLongestPathWithSameValue {

    public int ans = 0;
    public int LongestPathWithSameValue(int[] A, int[] E) {
        // write your code here
        int len = A.length;
        List<List<Integer>> ch = new ArrayList<>();
        for (int i = 0; i <= len; i++){
            ch.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < len - 1; i++){
            ch.get(E[i*2]).add(E[i*2+1]);
            ch.get(E[i*2+1]).add(E[i*2]);
        }
        int tmp = dfs(1,0,A,ch);
        ans = Math.max(ans,tmp);
        return ans - 1;
    }
    public int dfs(int index,int father,int[] A,List<List<Integer>> ch){
        List<Integer> v = new ArrayList<>();
        for (int son: ch.get(index)){
            if (son != father){
                if (A[son - 1] == A[index - 1]){
                    v.add(dfs(son,index,A,ch));
                }
                else{
                    dfs(son,index,A,ch);
                }
            }
        }
        v.add(0);
        v.add(0);
        Collections.sort(v);
        Collections.reverse(v);
        ans = Math.max(ans,v.get(0)+v.get(1)+1);
        return v.get(0)+1;
    }
}
