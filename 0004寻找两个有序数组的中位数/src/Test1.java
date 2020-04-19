/**
 * @Classname 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 * @Description TODO
 * @Date 2020/4/19 22:39
 * @Created by luck
 */
public class Test1 {
    public double findMedianSortedArrays(int[] A,int[] B){
        int m = A.length;//数组 A 的长度
        int n = B.length;//数组 B 的长度
        if(m>n){//如果数组 A 比较长，则交换 A、B 数组
            int[] temp =A;A=B;B=temp;
            int tmp=m;m=n;n=tmp;
        }
        int iMin =0,iMax = m,halfLen = (m+n+1)/2;//A 数组折半查找左边界 //A 数组折半查找右边界
        // halfLen 的作用就是中点坐标，当 A 数组中折半查找向右移动时，B 数组以 halfLen 为相对点向左移动，以保持始终均分
        //二分查找

        //情况一: A 数组为空，中位数在 B 数组
        //情况二: A 数组较短
        //  1) A 数组元素都较小，中位数在B数组
        //  2) A 数组元素都较大，中位数在B数组
        //  3) A、B 元素大小分布基本相当，中位数为被分割的两数组左半部分较大的那一个和右半部分较小的那一个之和的一半
        //情况三: A、B 等长
        //  1) A 数组元素都比B数组元素小，中位数为 A 数组尾元素和B数组首元素之和的一半
        //  2) B 数组元素都比A数组元素小，中位数为 B 数组尾元素和A数组首元素之和的一半
        //  3) A、B 元素大小分布基本相当，中位数为被分割的两数组左半部分较大的那一个和右半部分较小的那一个之和的一半

        while (iMin <= iMax){
            int i =(iMin+iMax)/2;//A数组中分割点
            int j = halfLen - i; //B数组中分割点

            //数组 A 分割点相邻左边那个元素比数组 B 分割点相邻右边那个元素大，
            // 则应该将数组 A 分割点向右移，数组 B 分割点向左移
            //数组 A 分割点有向左移趋势，需检查左边界
            if(i<iMax && B[j-1]>A[i]){
                iMin =i+1;
            }
            else if(i>iMin && A[i-1]>B[i]){
                iMax = i-1;
            }else {
                int maxLeft =0;
                if(i==0){//A分成的leftA(空集) 和 rightA(A的全部)  所以leftPart = leftA(空集) + leftB,故maxLeft = B[j-1]。
                    maxLeft =B[j-1];
                }else if(j==0){//B分成的leftB(空集) 和 rightB(B的全部)  所以leftPart = leftA + leftB(空集),故maxLeft = A[i-1]。
                    maxLeft =A[i-1];
                }else { //排除上述两种特殊情况，正常比较
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if((m+n)%2== 1){//奇数，中位数正好是maxLeft
                    return maxLeft;
                }
                //偶数
                int minRight = 0;
                if(i == m){//A分成的leftA(A的全部) 和 rightA(空集)  所以rightPart = rightA(空集) + rightB,故minRight = B[j]。
                    minRight=B[j];
                }else if(j == n){//B分成的leftB(B的全部) 和 rightB(空集)  所以rightPart = rightA + rightB(空集),故minRight = A[i]。
                    minRight =A[i];
                }else {//排除上述两种特殊情况，正常比较
                    minRight = Math.min(B[j],A[i]);
                }
                return (maxLeft+minRight)/2.0;
            }
        }
        return 0.0;
    }

}
