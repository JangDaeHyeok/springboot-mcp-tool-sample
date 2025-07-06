package com.jdh.mcpToolSample.dto;

import java.util.List;

public record McpToolRequestDTO(String toolName, List<Object> args) {

}
