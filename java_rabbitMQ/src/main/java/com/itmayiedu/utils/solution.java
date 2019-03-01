package com.itmayiedu.utils;

/**
 * solution class
 *
 * @author zhaoyi
 * @date 19-2-20
 */
class Solution {
    public static int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        if (len==1){
            return len;
        }
        int flag;
        if(k<len/2){
            for(int i=0;i<k;i++){
                int max = nums[i];
                flag=i;
                for(int j=i+1;j<len;j++){
                    if(nums[j]>max){
                        max = nums[j];
                        flag=j;
                    }
                }
                nums[flag]=nums[i];
                nums[i]=max;
            }
            return nums[k-1];
        }else{
            for(int i=len-1;i>=k-1;i--){
                int min=nums[i];
                flag=i;
                for(int j=i;j>=0;j--){
                    if(nums[j]<min){
                        min=nums[j];
                        flag=j;
                    }
                }
                nums[flag]=nums[i];
                nums[i]=min;
            }
            return nums[k-1];
        }
    }

    public  static Obj swap(Obj a, Obj b){
       Obj temp ;
       temp =a;
       a=b;
       b=temp;
       return  b;
    }
    public static void main(String[] args) {
        Obj aa = new Obj() ;
        Obj bb = new Obj() ;
        aa.x=1;
        bb.x=2;
        System.out.println(aa+" "+bb);
        swap(aa,bb);
        System.out.println(aa+" "+bb);
    }
    static class Obj{
        public int x;

        @Override
        public String toString() {
            return "Obj{" +
                    "x=" + x +
                    '}';
        }
    }
}
