package com.example.studyaccesscounterredis;

import com.example.studyaccesscounterredis.cache.Message;
import com.example.studyaccesscounterredis.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RedisCacheTest {

    @Autowired
    MessageService messageService = new MessageService();

    StopWatch stopWatch = new StopWatch();

    @Test
    public void test() {
        //1回目　時間かかる
        stopWatch.start();
        Message m1 = messageService.build("Hello World!");
        stopWatch.stop();

        LocalDateTime now1 = m1.getNow();
        long elapsed1 = (long) stopWatch.getLastTaskInfo().getTimeSeconds();

        assertThat(m1.getValue()).isEqualTo("Hello World!");
        assertThat(elapsed1).isGreaterThanOrEqualTo(3L);


        //2回目　キャッシュされていて高速に　現在時刻は変化なし
        stopWatch.start();
        Message m2 = messageService.build("Hello World!");
        stopWatch.stop();

        LocalDateTime now2 = m2.getNow();
        long elapsed2 = (long) stopWatch.getLastTaskInfo().getTimeSeconds();

        assertThat(m2.getValue()).isEqualTo("Hello World!");
        assertThat(elapsed2).isEqualTo(0L);
        assertThat(now2).isEqualTo(now1); // cached


        //3回目　引数を変えている
        stopWatch.start();
        Message m3 = messageService.build("Hello Redis!");
        stopWatch.stop();

        LocalDateTime now3 = m3.getNow();
        long elapsed3 = (long) stopWatch.getLastTaskInfo().getTimeSeconds();

        assertThat(m3.getValue()).isEqualTo("Hello Redis!");
        assertThat(elapsed3).isEqualTo(3L);
        assertThat(now3).isNotEqualTo(now1);
    }

}
