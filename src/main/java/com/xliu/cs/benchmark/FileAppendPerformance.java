package com.xliu.cs.benchmark;

import com.xliu.cs.generate.ClassNote;
import com.xliu.cs.generate.MethodNote;

import java.io.FileOutputStream;
import java.io.IOException;

@ClassNote("文件追加写性能")
public class FileAppendPerformance {

    private static void testFileStream(String file, byte[] contents, int writeNums) throws IOException {
        try (FileOutputStream fout = new FileOutputStream(file)) {
            for (int i = 0; i < writeNums; i++) {
                fout.write(contents);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        writeBinData();
        writeStringData();
    }


    /**
     * Result:
     * Total time is :4.2052886
     * Average write speed is :0.23779580787867924 million records/s
     */
    @MethodNote("文本追加写测试")
    private static void writeStringData() throws IOException {
        String file = "./test.txt";

        int writeNums = 1000000;

        String contents =
                "2021-08-17 17:09:23 [main] [INFO] com.xliu.lifelearning.cs.ds.FileAppendPerformance : {\"uuid\":\"8da84b3c-e2e3-4b63-99f2-98e32f482df1\",\"create_time\":\"2021-08-14 00:00:00.000000\",\"qr_code_type\":\"1\",\"uId\":\"oN7UX5xBbN-vWkYwgKkr2dLZMPOo\",\"uName\":\"孙良\",\"uGender\":\"0\",\"uMobile\":\"13952067635\",\"uCardType\":\"居民身份证\",\"uCardId\":\"321027198505010012\",\"cId\":\"57be839afb3811eb8d92fa163eafc7da\",\"cName\":\"南京南大药业有限责任公司\",\"cLongitude\":null,\"cLatitude\":null,\"pId\":\"51035f12fb3811eb8938fa163edbf96f\"}";

        System.out.println("Average content size is: " + contents.getBytes().length);

        long timeStart = System.nanoTime();
        testFileStream(file, contents.getBytes(), writeNums);

        long timeEnd = System.nanoTime();
        System.out.println("Total time is :" + (timeEnd - timeStart) / 1000000000.0);
        System.out.println(
                "Average write speed is :" + writeNums / ((timeEnd - timeStart) / 1000000000.0) / 1000 / 1000 + " million records/s");

    }

    /**
     * Result:
     * Total time is :10.1053025
     * Average write speed is :193.27724231906961 mb/s
     */
    @MethodNote("二进制追加写测试")
    private static void writeBinData() throws IOException {
        String file = "./test.bin";

        int writeNums = 1000000;

        byte[] contents = new byte[2048];

        for (int i = 0; i < contents.length; i++) {
            contents[i] = (byte) (i & 0xff);
        }

        long timeStart = System.nanoTime();
        testFileStream(file, contents, writeNums);
        long timeEnd = System.nanoTime();
        System.out.println("Total time is :" + (timeEnd - timeStart) / 1000000000.0);
        System.out.println(
                "Average write speed is :" + writeNums * contents.length / ((timeEnd - timeStart) / 1000000000.0) / 1024 / 1024 + " mb/s");
    }

}
