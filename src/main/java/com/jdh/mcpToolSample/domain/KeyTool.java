package com.jdh.mcpToolSample.domain;

import com.jdh.mcpToolSample.annotation.McpTool;
import com.jdh.mcpToolSample.config.McpToolRegistrar;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class KeyTool {

    private final McpToolRegistrar mcpToolRegistrar;

    @McpTool(name = "get-tools", description = "등록되어있는 Tool 검색")
    public List<String> getTools() {
        log.info("[get-tools Tool]");

        // 실제 로직
        return mcpToolRegistrar.getRegisteredTools().stream().toList();
    }

}
