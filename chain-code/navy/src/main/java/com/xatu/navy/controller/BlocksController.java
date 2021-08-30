package com.xatu.navy.controller;


import com.xatu.common.service.BlockInfoService;
import io.github.ecsoya.fabric.bean.FabricTransactionRW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author hao
 * @create 2021/6/23 5:09 下午
 */
@RestController
public class BlocksController {
  @Autowired
  private BlockInfoService blockInfoService;
  
  @GetMapping("/blocks")
  public Map<String, Object> getBlockInfo() {
    return blockInfoService.blocksInfo();
  }
  
  @GetMapping("/txs")
  public Map<String, Object> getTxInfo(long blockNumber) {
    return blockInfoService.txInfo(blockNumber);
  }
}
