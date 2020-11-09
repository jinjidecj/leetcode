package com.zcj.learnBili.greedy;

import com.zcj.learnBili.stackqm.heap.MaxHeap;

import java.util.*;

/**
 * 贪心算法
 */
public class MyGreedy {

    public static void main(String[] args) {
//        int[] money = new int[]{200,100,20,10,5,1};
//        System.out.println(new MyGreedy().money(628, money));

//        int[] candy = new int[]{6,1,20,3,8};
//        int[] children = new int[]{5,10,2,9,15,9};
//        System.out.println(new MyGreedy().findContentChildren(candy, children));

//        int[] wiggle = new int[]{1,17,5,10,13,15,10,5,16,8};
//        System.out.println(new MyGreedy().wiggleMaxLength(wiggle));

//        System.out.println(new MyGreedy().removeKDigits("1432219", 3));
//        System.out.println(new MyGreedy().removeKDigits("100200", 1));

//        System.out.println(new MyGreedy().jumpMinTime(new int[]{2, 3, 1, 1, 4}));
//        Balloon[] b = new Balloon[4];
//        b[0]= new Balloon(10,16);
//        b[1]= new Balloon(2,8);
//        b[2]= new Balloon(1,6);
//        b[3]= new Balloon(7,12);
//        System.out.println(new MyGreedy().findMinArrowShots(b));
        GasStation[]g = new GasStation[5];
        g[0] = new GasStation(0,0);
        g[1] = new GasStation(2,15);
        g[2] = new GasStation(3,10);
        g[3] = new GasStation(5,11);
        g[4] = new GasStation(4,4);
        System.out.println(new MyGreedy().gatMinStop(25, 16, g));

    }

    /**
     * 钞票支付问题
     * 有1,5,10,20,100,200元的钞票无穷多张，使用这些钞票支付x元，最少需要多少张
     */
    public int money(int sum,int[]rmb){
        int count = 0;
        for(int i = 0;i<rmb.length;i++){
            int use = sum/rmb[i];
            count+=use;
            sum = sum - rmb[i]*use;
            System.out.println("面额"+rmb[i]+"元--"+use+"张");
        }
        return count;
    }

    /**
     * 分糖果
     * 已知一些孩子和一些糖果，每个孩子有需求因子g，每个糖果有大小s，当某个糖果的大小s>=某个孩子的需求因子时，
     * 代表该糖果可以满足该孩子，求给出的糖果可以满座多少孩子。注意：一个孩子最多只能用1个糖果满足
     */
    public int findContentChildren(int[] candy,int[]children){
        Arrays.sort(candy,0,candy.length);
        Arrays.sort(children,0,children.length);
        int chi = 0;//代表已经满足了几个孩子，
        int can = 0;//代表尝试了几个糖果
        while (chi<children.length && can < candy.length){
            if(children[chi]<=candy[can]){
                chi++;
            }
            can++;
        }

        return chi;
    }
    /**
     * 摇摆序列
     * 如果相邻元素的差恰好正负（负正）交替出现，则该序列被称为摇摆序列，一个小于2个元素的序列直接为摇摆序列
     * 给一个随机序列，求这个序列满足摇摆序列定义的最长子序列长度
     * 如：1,7,4,9,2,5 结果为6；1，17,5,10,13,15,10,5,16,8 结果为7（1,17,10,13,10,16,8），
     * 1，2,3,4,5,6,7,8,9 结果为2.
     * 贪心规律：当序列有一段连续的递增(或递减)时，为形成摇摆子序列，我们只需要保留这段连续的递增或递减的首位元素，
     * 这样更可能使得尾部的后一个元素成为摇摆子序列的下一个元素
     */
    public int wiggleMaxLength(int[] nums){
        if(nums.length<=2){
            return nums.length;//序列个数小于等于2时直接为摇摆序列
        }
        final int BEGIN = 0, UP = 1, DOWN=2;//扫描时的三种状态
        int state = BEGIN;
        int maxLength = 1;//摇摆序列最长长度至少为1
        for(int i = 1; i<nums.length; i++) {
            switch (state) {
                case BEGIN:
                    if(nums[i-1]<nums[i]){
                        state = UP;
                        maxLength++;
                    }else if(nums[i-1]>nums[i]){
                        state = DOWN;
                        maxLength++;
                    }
                    break;
                case UP:
                    if(nums[i-1]>nums[i]){
                        state = DOWN;
                        maxLength++;
                    }
                    break;
                case DOWN:
                    if(nums[i-1]<nums[i]){
                        state = UP;
                        maxLength++;
                    }
                    break;
            }
        }
        return maxLength;
    }

    /**
     * 移除k个数字
     * 已知一个使用字符串表示的非负整数num，将num的k个数字移走，求移除k个数字后，
     * 可以获得的最小的可能的新数字
     * 分析：若去除某一位数字，为了使得到的新数字最小，需要尽可能让得到的新数字优先最高位最小
     *      其次次高位最小，再其次第三位最小
     * 贪心思想：从高位向低位遍历，如果对应的数字大于下一位，则把数字去掉，得到的数字最小
     * 最暴力的解法：去掉k个数字，从头到尾遍历k次
     * 算法思路：使用栈存储最终结果或删除工作，从高位向低位遍历num，
     *          如果遍历的数字大于栈顶元素，则将该数字push入栈，
     *          如果小于栈顶元素，则进行pop弹出栈顶元素，
     *          直到栈为空或者不能再删除数字或者栈顶小于当前元素为止
     */
    public String removeKDigits(String num,int k){
        Stack<Integer> s = new Stack<>();
        String result = "";
        for(int i = 0;i<num.length();i++){
            int number = num.charAt(i)-'0';
            while (s.size()!=0 && s.peek()>number && k>0){
                s.pop();
                k--;
            }
            if(s.size()!=0 || number!=0){
                s.push(number);
            }
        }
        while (s.size()!=0 && k>0){
            s.pop();
            k--;
        }
        for (int i = 0;i<s.size();i++){
            result = result+ s.get(i);
        }
        if(result==""){
            return "0";
        }
        return result;
    }

    /**
     * 跳跃游戏
     * 一个数组存储了非负整型数据，数组中的第i个元素nums[i]，代表了可以从数组的第i个位置最多向前跳跃nums[i]步，
     * 已知数组各元素的情况下，求是否可以从数组的第0个位置，跳跃到数组的最后一个元素的位置？
     * 如：nums=[2,3,1,1,4]可以从nums[0]=2跳跃至nums[4]=4;
     *    nums=[3,2,1,0,4]不可以从nums[0]=3跳跃至nums[4]=4
     */
    public boolean canJump(int[]nums){
        List<Integer> index = new LinkedList<>();//最远可跳至的位置
        for(int i = 0; i<nums.length;i++){
            //计算index数组
            index.add(i+nums[i]);
        }
        int jump = 0;
        int max_index = index.get(0);
        //直到jump跳至数组尾部或者jump超越了当前可以跳的最远的位置
        while (max_index>= jump && jump < nums.length ){
            if(max_index<index.get(jump)){
                max_index = index.get(jump);//如果当前可以跳的更远，则更新max_index
            }
            jump++;//扫描jump
        }
        if(jump==nums.length){//若jump到达数组尾部
            return true;
        }
        return false;
    }

    /**
     * 跳跃游戏2
     * 一个数组存储了非负整型数据，数组中的第i个元素nums[i]，代表了可以从数组的第i个位置最多向前跳跃nums[i]步，
     * 已知数组各元素的情况下，确认可以从第0个位置跳跃到数组的最后一个位置，求最少需要跳跃几次？
     * 如：nums=[2,3,1,1,4]可以：nums[0]->nums[1]->nums[4]
     * 贪心规律：到达某点前，若一直不跳跃，发现从该点不能跳到更远的地方了，在这之前肯定有次必要的跳跃
     *          结论：在无法到达更远的地方时，在这之前应该跳到一个可以达到更远位置的位置。
     *          如：index[0]=i,而index[i-2]是index[0,..i]中最大的，则选择index[i-2]进行跳跃
     * 算法思路：1.设置current_max_index为当前可达到的最远位置
     *         2.设置pre_max_max_index为在遍历各个位置的过程中，各个位置可以到达的最远位置
     *         3.设置jump_min为最少跳跃的次数
     *         4.利用i遍历nums数组，如果i超过current_max_index,则jum_min加1,current_max_index=pre_max_max_index
     *         5.遍历过程中，如果nums[i]+i(index[i])更大，则更新pre_max_max_index = nums[i]+i
     */
    public int jumpMinTime(int[]nums){
        if(nums.length<2){
            return 0;
        }
        int current_max_index = nums[0];//当前可以到达的最远位置
        int pre_max_max_index = nums[0];//遍历各个位置过程中，可到达的最远位置
        int jump_min = 1;
        for(int i = 1; i < nums.length; i++){
            if(i>current_max_index){//若无法向前移动了，才进行跳跃
                jump_min ++;
                current_max_index = pre_max_max_index;  //更新当前可达的最远位置
            }
            if(pre_max_max_index<nums[i]+i){
                pre_max_max_index = nums[i]+i;
            }
        }
        return jump_min;
    }

    /**
     * 射击气球
     * 在一个平面有一定数量的气球，平面可以看成一个坐标系，在平面的x轴的不同位置安排弓箭手向y轴射箭，无穷远
     * 给定气球的宽度 xstart<=x<=xend，问至少需要多个少个弓箭手，可将全部气球打爆
     * 如：四个气球：[[10,16],[2.8],[1,6],[7,12]]，至少需要两个弓箭手
     * 贪心规律：1.对于某个气球，至少需要使用1只弓箭将它击穿
     *         2.在这只气球将其击穿的同时，尽可能击穿其他更多的气球
     * 解决思路：1.对各个气球排序，按照气球的左断点从小到大排序
     *         2.遍历气球数组，同时维护一个射击区间，在满足可以将当前气球射穿的情况下，尽可能多的击穿更多的气球
     *           每击穿一个新的气球，更新一次射击区间
     *         3.如果新的气球没办法被击穿了，则需要增加新的弓箭手，即维护一个新的射击区间，然后继续遍历数组
     */
    static class Balloon implements Comparable{
        int start;
        int end;
        public Balloon(int s,int e){
            this.start = s;
            this.end = e;
        }

        @Override
        public int compareTo(Object o) {
            if(o instanceof Balloon){
                Balloon b = (Balloon) o;
                if(this.start>b.start) return 1;
                else if(this.start==b.start) return 0;
                else return -1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "Balloon{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
    public int findMinArrowShots(Balloon[] points){
        if(points.length==0){
            return 0;
        }
        Arrays.sort(points);
        System.out.println(Arrays.toString(points));
        int shootNum = 1;
        //初始化射击区间
        int shootBegin = points[0].start;
        int shootEnd = points[0].end;
        for(int i = 1;i<points.length;i++){
            if(points[i].start<=shootEnd){
                //满足条件的情况下更新射击区间
                shootBegin = points[i].start;
                if(shootEnd>points[i].end){
                    shootEnd = points[i].end;
                }
            }else{
                shootNum++;
                shootBegin = points[i].start;
                shootEnd = points[i].end;
            }
        }
        return shootNum;
    }

    /**
     * 最优加油方法
     * 已知一条公路上，有一个起点和一个终点，这之间有n个加油站；已知从这n个加油站到终点的距离d与各个
     * 加油站可以加油的量l，起点位置至终点的距离L与起始时刻油箱中汽油量p，假设使用1个单位的汽油即走
     * 1个单位的距离，邮箱没有上限，最少加几次油，可以从起点开至终点？(如果无法到达终点，返回-1)
     * 贪心规律：
     *        何时加油?油用光的时候加油最合适
     *        在哪个加油站加油最合适？在油量最多的加油站加油最合适
     * 算法思路：
     * 1.设置一个最大堆，存储经过的加油站的汽油量
     * 2.从起点到终点遍历各个加油站的距离
     * 3.每次需要走两个加油站的距离d，如果发现汽油不够走距离d时，从最大堆中取出一个油量添加，直到可以走玩d
     * 4.如果把最大堆的汽油添加任然不够走d，则无法到达终点
     * 5.当前油量p减少d
     * 6.将当前加油站油量添加到最大堆中
     */
    static class GasStation implements Comparable{
        int gas;
        int dist;
        public GasStation(int g,int d){
            this.gas = g;
            this.dist = d;
        }

        @Override
        public int compareTo(Object o) {
            if(o instanceof GasStation){
                GasStation b = (GasStation) o;
                if(this.dist>b.dist) return -1;
                else if(this.dist==b.dist) return 0;
                else return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "GasStation{" +
                    "gas=" + gas +
                    ", dist=" + dist +
                    '}';
        }
    }
    public int gatMinStop(int L,int P,GasStation[] gasStations){
        MaxHeap<Integer> gasHeap = new MaxHeap<>();
        int result = 0;
        Arrays.sort(gasStations);
        System.out.println(Arrays.toString(gasStations));
        for(int i = 0;i<gasStations.length;i++){
            int dis = L - gasStations[i].dist;
            while (!gasHeap.empty()&&P<dis ){//油不够了
                P+=gasHeap.top();
                gasHeap.pop();
                result++;
            }
            if(P<dis && gasHeap.empty()){ //油不够且加油站的油都加过了
                return -1;
            }
            P-=dis; //前进，减油
            gasHeap.insert(gasStations[i].gas);
            L = gasStations[i].dist;
        }
        return result;
    }
}
