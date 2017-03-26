/*This file contains only the implementation of the function countSmallerToTheRight(int inputArray)
 * Example
 * 3 2 5 9 7 4 1 8 6

   // [2 1 2 5 3 1 1 0]
   //Logic : An arraylist stores elements that are smaller than previous element at the given point of execution
   //for current element, in arraylist see which elememnts are next to it..compare if they are smaller than the current element
   //if no one isnext to it in the list,then it does not have any small ones. 
   //if not at all there in arraylist , then check for new small ones.write to arraylist. if an element already exist, remove and add newly*/
*/

int[] countSmallerToTheRight(int[] nums) {
      int[] countArray=new int[]{};
if(nums == null || nums.length == 0)
   return countArray;
   ArrayList<Integer> compareList= new ArrayList<Integer>();
   countArray=new int[nums.length];
   int toCompare = nums[0];
   
   for(int i=1;i<nums.length;i++)
      if (nums[i] < toCompare)
         compareList.add(nums[i]);
   countArray[0] = compareList.size();
   
   for(int i=1;i<nums.length;i++){
      if(compareList.contains(nums[i])){ // the current element had been smaller than some previous element
         int pos = compareList.indexOf(nums[i]);
         int count=0;
         for(int j=pos+1;j<compareList.size();j++){
            if(compareList.get(j) < nums[i])
               count++;
         }
         compareList.remove(pos);
         countArray[i] = count;
         
      }
      else{ // current element larger than the elements encountered so far
         int count=0;
         for(int j=i+1;j<nums.length;j++){
            if(nums[j] < nums[i]){
               count++;
               if(compareList.contains(nums[j])){
                  compareList.remove(compareList.indexOf(nums[j]));
               }
               compareList.add(nums[j]);
            }
         }
         countArray[i]=count;
         
      }
   }
   return countArray;
   
}
/*Timecomplexity
 * w(n)=o(n^2) -> ascending order
 * t(n)=o(n*m) -> where 0 <= m < n 
*/
