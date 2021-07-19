package com.skyl.dianping.canal;

import com.alibaba.google.common.collect.Lists;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class CanalClient implements DisposableBean{

    private CanalConnector canalConnector;

    @Bean
    public CanalConnector getCanalConnector(){
        canalConnector = CanalConnectors.newClusterConnector(Lists.newArrayList(
           new InetSocketAddress("127.0.0.1", 11111)),
                "example","canal","canal"
           );
        try {
            canalConnector.connect();
            //指定filter，格式{database}.{table}
            canalConnector.subscribe();
            //回滚寻找上次中断的为止
            canalConnector.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return canalConnector;
    }


    @Override
    public void destroy() throws Exception {
        if(canalConnector != null){
            canalConnector.disconnect();
        }
    }
}
