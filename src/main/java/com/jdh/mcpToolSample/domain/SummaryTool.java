package com.jdh.mcpToolSample.domain;

import com.jdh.mcpToolSample.annotation.McpTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SummaryTool {
    @McpTool(name = "summarize_products", description = "상품 목록 요약")
    public String summarize(String productSummaryText) {
        log.info("[summarize_products Tool] - productSummaryText: {}", productSummaryText);

        // 실제 로직
        return "[요약] " + productSummaryText;
    }
}
