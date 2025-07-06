package com.jdh.mcpToolSample.domain;

import com.jdh.mcpToolSample.annotation.McpTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RegionTool {
    @McpTool(name = "get_region_id_by_name", description = "지역 이름으로 지역 ID 조회")
    public String getRegionIdByName(String regionName) {
        log.info("[get_region_id_by_name Tool] - regionName: {}", regionName);

        // 실제 로직
        return "REGION_" + regionName.hashCode();
    }
}
