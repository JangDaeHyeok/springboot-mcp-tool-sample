package com.jdh.mcpToolSample.dto;

import java.util.List;
import java.util.Map;

public record ToolChainStepDTO(
        String tool,
        List<Object> args,                      // 직접 인자를 주는 경우
        Map<String, String> argsFrom,          // 이전 툴 결과에서 특정 인자를 끌어오는 경우
        Map<String, Object> staticArgs         // 고정값 인자
) {}
