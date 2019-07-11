import java.util.LinkedList;

/**
 * 155. 最小栈
 *
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 */
public class LeetCode155 {
    class MinStack {
        private LinkedList<Integer> stack;
        private LinkedList<Integer> stackMin;

        /** initialize your data structure here. */
        public MinStack() {
            stack = new LinkedList<Integer>();
            stackMin = new LinkedList<Integer>();
        }

        public void push(int x) {
            stack.push(x);
            if(stackMin.isEmpty()){
                stackMin.push(x);
            }else {
                int curMin = stackMin.peek();
                stackMin.push(curMin < x ? curMin : x);
            }
        }

        public void pop() {
            stack.pop();
            stackMin.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return stackMin.peek();
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
}
