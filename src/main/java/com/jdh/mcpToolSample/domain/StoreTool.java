package com.jdh.mcpToolSample.domain;

import com.jdh.mcpToolSample.annotation.McpTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Store Tool 정의 클래스
 */
@Component
@Slf4j
public class StoreTool {

    @McpTool(name = "get_store_id_by_name", description = "가게 이름으로 가게 ID를 조회")
    public String getStoreIdByName(String storeName) {
        log.info("[get_store_id_by_name Tool] - storeName: {}", storeName);

        // 실제 로직
        return "STORE_" + storeName.hashCode();
    }

}
