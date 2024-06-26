package org.xliu.cs.algs_ds.algs.recursive;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;
import org.xliu.cs.projects.anno_for_doc.annotations.MethodNote;

import java.util.*;

/**
 * 使用栈替代递归函数。
 */
@ClassNote("递归变循环通用方法")
public class RecursiveToFor {

    private static class Frame {
        // 程序计数器，从0开始，遇到一次递归需要加一
        int pc;
        // 以下都表示为递归需要的参数
        int n;
        char from, to, via;

        public Frame(int pc, int n, char from, char via, char to) {
            this.pc = pc;
            this.n = n;
            this.from = from;
            this.via = via;
            this.to = to;
        }
    }

    public static List<String> hanio(int n, char from, char via, char to) {
        if (n == 1) {
            return Collections.singletonList(String.format("%c-%c", from, to));
        }
        ArrayList<String> result = new ArrayList<>();
        result.addAll(hanio(n - 1, from, to, via));
        result.addAll(hanio(1, from, via, to));
        result.addAll(hanio(n - 1, via, from, to));

        return result;
    }


    @MethodNote("汉诺塔非递归实现")
    public static List<String> hanioNonRecursive(int n, char from, char via, char to) {
        Deque<Frame> stack = new ArrayDeque<>();

        stack.addLast(new Frame(0, n, from, via, to));

        List<String> orders = new ArrayList<>();

        // stack is not empty
        while (!stack.isEmpty()) {
            // take the last stack
            Frame current = stack.getLast();

            switch (current.pc) {
                case 0:
                    if (current.n == 1) {
                        orders.add(String.format("%c-%c", current.from, current.to));
                        // simple return, or can goto(4)
                        // current.pc = 4
                        stack.removeLast();
                    }
                    break;
                case 1:
                    // call(n-1, from, to, via)
                    stack.addLast(new Frame(0, current.n - 1, current.from, current.to, current.via));
                    break;
                case 2:
                    // call(1, from, via, to);
                    stack.addLast(new Frame(0, 1, current.from, current.via, current.to));
                    break;
                case 3:
                    // call(n-1, via, from, to);
                    stack.addLast(new Frame(0, current.n - 1, current.via, current.from, current.to));
                    break;
                case 4:
                    stack.removeLast();
                    break;
                default:
                    throw new RuntimeException("wrong execute");
            }

            // pc increment 1
            current.pc += 1;
        }

        return orders;
    }

}
