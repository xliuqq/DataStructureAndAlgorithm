package org.xliu.cs.algs_ds.algs.bit;

import org.xliu.cs.projects.anno_for_doc.annotations.ClassNote;
import org.xliu.cs.projects.anno_for_doc.annotations.MethodNote;

/**
 * 计算数字中 1 bit 的个数
 */
@ClassNote("计算数字中 1 bit 的个数")
public class CountBits {

    @MethodNote("右移法，循环次数等于最高位1的位置")
    public static int countOneBitsNumber(int number) {
        assert number > 0;
        int count = 0;

        while (number > 0 ) {
            count += number & 1;
            number = number >>> 1;
        }
        return count;
    }

    @MethodNote("减一相与法，循环次数等于1出现的次数")
    public static int countOneBitsNumberByAnd(int number) {
        assert number > 0;
        int count = 0;

        while (number > 0 ) {
            count += 1;
            number = number & (number - 1);
        }
        return count;
    }
}
