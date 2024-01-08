package org.xliu.cs.algs_ds.algs.search;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;
import org.xliu.cs.projects.anno_for_doc.annotations.MethodNote;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 24点游戏，+、-、*、/四则运算能够计算成24点
 * 且为24点时候，输出解.
 */
@ClassNote("24点可行解搜索")
public class Card24Points {
    private final double threshold = 1e-6;
    int cardNums;
    int[] cards;

    public Card24Points(int[] cs) {
        cards = cs;
        cardNums = cs.length;
    }

    // f(1111) 表示 4 个数字的可能组合，每位表示一个数字
    // f(0001) = f(0010) = f(0100) = f(1000)，只有 1 个表达式，即数字本身
    // f(0011) = f(0001) X f(0010)，对于加减乘除四则运算，共有6个可能表达式: a+b, a-b, b-a, a*b, a/b, b/a
    // f(0111) = f(0001) X f(0110) + f(0010) X f(0101) + f(0100) X f(0011), 3 个 1只能拆分为 1+2的组合
    // f(1111) = f(0001) X f(1110) + f(0010) X f(1101) + f(0100) X f(1011) + f(1000) X f(0111)
    //         + f(0011) X f(1100) + f(0101) X f(1010) + f(1001) X f(0110)
    // 4 个 1可以拆分为 1+3 和 2+2 的组合
    @MethodNote("动态规划解决24点的问题")
    public String getResult() {
        int max = (int) Math.pow(2, cardNums);
        Expression[] expressions = new Expression[max];
        // 每个数字只有 1 个表达式，对应的位置上初始化
        for (int i = 0, pos = 1; i < cardNums; i++, pos *= 2) {
            expressions[pos] = new Expression(Collections.singleton(new NumberRule(cards[i], String.valueOf(cards[i]))));
        }

        // 获取 f(111...1) 的 集合
        getSet(max - 1, expressions);

        System.out.println("get rules size: " + expressions[max - 1].size());

        // f(111...1) 集合中等于 24 的表达式，因为涉及到除法，浮点数精度
        for (NumberRule val : expressions[max - 1]) {
            if (Math.abs(val.value - 24) < threshold) {
                return val.rule;
            }
        }
        return null;
    }

    private Set<NumberRule> getSet(int i, Expression[] expressions) {
        // 已经计算过的不再计算
        if (expressions[i] != null) {
            return expressions[i].rules;
        }
        Set<NumberRule> iSet = new HashSet<>();

        // (x, i-x) 跟 (i-x, x) 的组合是一样的，因此只需遍历到 i / 2 即可
        for (int x = 1; x <= i / 2; x++) {
            // x is the subset of i (即是 i 数字上为 1 的选择）
            if ((x & i) == x) {
                iSet.addAll(cartesian(getSet(x, expressions), getSet(i - x, expressions)));
            }
        }
        expressions[i] = new Expression(iSet);
        return iSet;
    }

    // 两个集合的表达式的所有可能组合
    private Set<NumberRule> cartesian(Set<NumberRule> setA, Set<NumberRule> setB) {
        Set<NumberRule> set = new HashSet<>();
        for (NumberRule a : setA) {
            for (NumberRule b : setB) {
                // +,* 满足交换律，-,/ 不满足除法
                set.add(new NumberRule(a.value + b.value, "(" + a.rule + " + " + b.rule + ")"));
                set.add(new NumberRule(a.value * b.value, "(" + a.rule + " * " + b.rule + ")"));
                set.add(new NumberRule(a.value - b.value, "(" + a.rule + " - " + b.rule + ")"));
                set.add(new NumberRule(b.value - a.value, "(" + b.rule + " - " + a.rule + ")"));
                if (a.value != 0) set.add(new NumberRule(b.value / a.value, "(" + b.rule + " / " + a.rule + ")"));
                if (b.value != 0) set.add(new NumberRule(a.value / b.value, "(" + a.rule + " / " + b.rule + ")"));
            }
        }
        return set;
    }

    private static class NumberRule {
        Double value;
        String rule;

        public NumberRule(double v, String r) {
            value = v;
            rule = r;
        }

        public int hashCode() {
            return value.hashCode() + rule.hashCode();
        }

        public boolean equals(Object o) {
            if (o == this) return true;
            if (o instanceof NumberRule) {
                NumberRule numberRule = (NumberRule) o;
                return value.equals(numberRule.value) && rule.equals(numberRule.rule);
            }
            return false;
        }
    }

    private static class Expression implements Iterable<NumberRule> {

        private final Set<NumberRule> rules;

        public Expression(Set<NumberRule> set) {
            this.rules = set;
        }

        public int size() {
            return this.rules.size();
        }

        @Override
        public Iterator<NumberRule> iterator() {
            return this.rules.iterator();
        }
    }
}
