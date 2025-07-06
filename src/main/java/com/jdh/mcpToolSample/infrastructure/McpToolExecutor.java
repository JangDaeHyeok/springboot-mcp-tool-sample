package com.jdh.mcpToolSample.infrastructure;

import com.jdh.mcpToolSample.config.McpToolRegistrar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class McpToolExecutor {

    private final McpToolRegistrar registrar;

    public Object execute(String toolName, Object... args) {
        try {
            return registrar.invoke(toolName, args);
        } catch (Exception e) {
            return "Tool 실행 오류: " + e.getMessage();
        }
    }

}
