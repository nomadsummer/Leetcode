package google.phone;

import java.util.List;
import java.util.Random;

/*
 * 给一个blacklist 含有一些整数[0, N) 中，写一个function 返回 [0, N)的随机数，
 * 但是不要返回任何包含在blacklist的数，要求uniform distribution, 且尽可能减少call 系统Math.random() 的次数。
 */
public class BlackList {
	/*
	 * 开个辅助数组，size 为 N - blacklist.size, 
	 * 然后把不在blacklist的数依次放到辅助数组中，然后每次call 的话 调用一次 Math.random() * (辅助数组的size), 
	 * 生成的随机数用作辅助数组的index,然后返回对应的值
	 * 
	 * 
这题应该可以用binary search和binary search tree。比如N为1~8，black list为{2, 3, 5}，
这时候剩下的数字是{1, 4, 6, 7 ,8}。随机数生成的是{1, 2, 3, 4, 5}，和前述数值之间有个对应关系 rand = x - L(x)，其中L(x)是候选数x在black list
里面小于(没有等于)它的数的个数。比如候选是6，L(6)=3，因为2, 3和5都小于6，这时候rand = 3。但是这里我们不知道候选数，而是知道rand，
这时候我们可以用binary search。比如rand是4，我们猜候选数是6，6-L(6)=3<4，表面6是猜小了，再猜8，那么8 - L(8)=5 > 4，又太大，所以最后候选数是7。. 
这里对N进行binary search，而L函数需要用binary search tree。复杂度是O(log(N) * log(blacklist.size))，但是随机数生成只需要一次。
	 */
	public int getRandom(List<Integer> blackList, int N) {
		//
		int[] num = new int[N - blackList.size()];
		
		for(int i = 0, j = 0; i < N && j < N-blackList.size(); i++) {
			if(!blackList.contains(i)) {
				num[j++] = i;
			}
		}
		Random random = new Random();
		int next = random.nextInt(N-blackList.size());
		return num[next];
	}
}
/*
 * Reservoir Sampling

	int res = 0, int count = 0;
	for(int i = 0; i < N; i++) {
		if(!blackList.contains(i)) {
			if(count == 0){
				res = i;
				count = 1;
			} else {
				count++;
				Random random = new Random();
				int NextInt = random.nextInt(N) + 1;
				if(nextInt % count + 1 == 1)
					res = i;
			}
		}
	}


*/