package com.jdh.mcpToolSample.dto;

import java.util.Map;

public record ToolChainResponseDTO(
        Map<String, Object> results,
        Object finalResult
) {
}
