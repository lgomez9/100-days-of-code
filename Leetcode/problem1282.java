class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
//         These map group sizes to indices
        HashMap<Integer, ArrayList<Integer>> buckets = new HashMap<Integer, ArrayList<Integer>>();
        
//         Put the indices in the actual bucket
        for(int i = 0; i < groupSizes.length; i++) {
            if(buckets.get(groupSizes[i]) == null) buckets.put(groupSizes[i], new ArrayList<Integer>());
            buckets.get(groupSizes[i]).add(i);
        }
        
//         Create the return array
        List<List<Integer>> groups = new ArrayList<List<Integer>>();
        
//         Split the buckets into groups
        for(Integer i : buckets.keySet()) {            
            while(!buckets.get(i).isEmpty()) {
                ArrayList<Integer> curr = new ArrayList<>();
                for(int j = i-1; j > -1; j--) {
                    curr.add(buckets.get(i).get(j));
                    buckets.get(i).remove(j);
                }
                groups.add(curr);
            }
        }
        
        return groups;
    }
}
