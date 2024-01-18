package com.ohgiiraffers.section03.filterstream;

import java.io.*;

public class Application01 {
    public static void main(String[] args) {
        /* 필터 스트림(보조 스트림) : 기반 스트림에 추가 되어 성능 향상, 기능추가
        *  BufferedReader/Writer : 버퍼 공간을 이용하여 데이터를 쌓아두었다가 입/출력하므로써
        *  입출력 횟수를 줄이고 성능을 향상시킴 */

        BufferedWriter bw = null;

        try {
            /* 보조 스트림 객체를 생성할 떄에는 생성자의 인자로 기반 스트림을 전달한다. */
            bw = new BufferedWriter(new FileWriter("testBuffered.txt"));

            bw.write("안녕하세요\n");  // 버퍼에 보내는행동
            bw.write("반갑습니다\n");  // 같은 행동

            /* 버퍼를 이용하는 경우 버퍼가 가득차면 자동으로 내보내기를 하지만 버퍼가 가득차지 않은 상태에서는
            *  강제로 내보내기를 해야한다.
            *  flush()라는 메소드를 사용하면 내보내기 처리가된다. */
       //     bw.flush();   //내보다라는뜻 flush라는 사용하면 파일로 이동가능

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(bw != null){
                try {
                    /* close()를 호출하면 내부적으로 flush() 실행하고 나서 자원을 반납한다. */
                    bw.close();     // close 요거 쓰면 위에잇는 flush(플러시) 호출 안해도댐
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        try(BufferedReader br = new BufferedReader(new FileReader("testBuffered.txt"))){

//            System.out.println(br.readLine());
//            System.out.println(br.readLine());
//            System.out.println(br.readLine());

            /* readLine() 메소드 기능을 추가로 제공한다.
            *  버퍼의 한줄을 읽어와서 문자열로 반환하며 더이상 읽어올 값이 없을 경우 null이 반환 된다. */
            String temp;
            while ((temp = br.readLine())  != null){
                System.out.println(temp);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
