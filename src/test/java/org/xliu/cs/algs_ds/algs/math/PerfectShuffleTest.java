package org.xliu.cs.algs_ds.algs.math;

import org.junit.jupiter.api.Test;
import org.xliu.cs.algs_ds.algs.math.PerfectShuffle;

import java.util.Objects;

class PerfectShuffleTest {

    @Test
    void inplaceInShuffle() {
        Integer[] data = {1,2,3,4,5,6,7,8};
        PerfectShuffle.inplaceInShuffle(data);

        Objects.deepEquals(data, new Integer[]{1,5,2,6,3,7,4,8});
    }
}