package com.jdh.mcpToolSample.controller;

import com.jdh.mcpToolSample.dto.McpToolRequestDTO;
import com.jdh.mcpToolSample.dto.ToolChainRequestDTO;
import com.jdh.mcpToolSample.dto.ToolChainResponseDTO;
import com.jdh.mcpToolSample.dto.ToolChainStepDTO;
import com.jdh.mcpToolSample.infrastructure.McpToolExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mcp")
public class McpController {

    private final McpToolExecutor executor;

    @PostMapping("/run")
    public ResponseEntity<Object> runTool(@RequestBody McpToolRequestDTO request) {
        Object result = executor.execute(
                request.toolName(),
                request.args().toArray()
        );
        return ResponseEntity.ok(result);
    }

    @PostMapping("/chain-run")
    public ResponseEntity<ToolChainResponseDTO> runChainByJson(@RequestBody ToolChainRequestDTO request) {
        Map<String, Object> context = new LinkedHashMap<>();
        Object finalResult = null;

        for (ToolChainStepDTO step : request.plan()) {
            Object[] args = resolveArguments(step, context);
            Object result = executor.execute(step.tool(), args);
            context.put(step.tool(), result);
            finalResult = result;
        }

        return ResponseEntity.ok(new ToolChainResponseDTO(context, finalResult));
    }

    private Object[] resolveArguments(ToolChainStepDTO step, Map<String, Object> context) {
        if (step.args() != null) {
            return step.args().toArray();
        }

        Map<String, Object> resolved = new LinkedHashMap<>();

        if (step.staticArgs() != null) {
            resolved.putAll(step.staticArgs());
        }

        if (step.argsFrom() != null) {
            for (Map.Entry<String, String> entry : step.argsFrom().entrySet()) {
                resolved.put(entry.getKey(), context.get(entry.getValue()));
            }
        }

        return resolved.values().toArray();
    }
}
