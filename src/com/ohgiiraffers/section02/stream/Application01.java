package com.ohgiiraffers.section02.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Application01 {
    public static void main(String[] args) {
        /* FileInputStream을 사용할 수 있다. */
        // FileIntputStream bute 기반의 입력 스트림
        FileInputStream fin = null;
        try {
            /* 대상 파일이 존재하지 않는 경우 발생하는 FileNoFoundException 핸들링 필요 */
             fin = new FileInputStream("testInputStream.txt");//파일명이랑 똑같아야함 파일명 불러오는 코드

            int value;
            /* read() : 파일에 기록 된 값을 순차적으로 읽어오고 더이상 읽어올 데이터가 없는 경우 -1 반환 */
//            while ((value = fin.read()) != -1){
//                /* 값을 정수로 읽어온다.*/
//                System.out.println(value);
//                /* 문자로 출력하고 싶은 경우 형변환 */
//                System.out.println((char)value);
//            }

            /* 한글 값의 경우 1바이트를 초과하므로 1바이트씩 끊어서 읽어왔을때 깨지는 현상을 볼수 있다.
            *  ===      236 ì149136235ë133149* 출력문 ====       */


                /* 1바이트 씩 읽어오는 경우도 있지만 다소비효율적이다. byte 배여을 이용해서 한번에 데이터를 읽어올수 있다. */
            int fileSize = (int)new File("testInputStream.txt").length();
            byte[] bar = new byte[fileSize];
            /* read(0 메소드의 인자로 byte 배열을 전달하면 파일의 내용을 읽어서 byte 배열에 기록해준다. */
            fin.read(bar);
            /* 상단의 read() 메소드로 읽어오는 코드를 주석해야 확인 가능 */
            System.out.println(Arrays.toString(bar));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(fin != null) {
                try {
                    /* 자원 반납을 해야하는 이유 ?
                    *  1. 장기간 실행 중인 프로그램에서 스트림을 닫지 않으면 다양한 리소스에서 누수(leak0)가 발생한다.
                    *  2. 뒤에서 배우는 버퍼를 이용하는 경우 마지막에 flust() 메소드로 버퍼에 있는 데이터를 강제로 전송해야 하는데
                    *  만약 잔류 데이터가 남은 상황에서 추가로 스트림을 사용하면 데드락(deadlock) 상태가 발생할 수 있다.
                    * */
                    fin.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
