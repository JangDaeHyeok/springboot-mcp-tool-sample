package com.jdh.mcpToolSample.config;

import com.jdh.mcpToolSample.annotation.McpTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class McpToolRegistrar implements ApplicationContextAware {

    private ApplicationContext context;

    private final Map<String, Method> toolMap = new HashMap<>();
    private final Map<String, Object> beanMap = new HashMap<>();
    private final Map<String, String> descriptionMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    // Spring Boot 애플리케이션의 초기화가 완료된 후에 실행 (ApplicationReadyEvent.onApplicationReady() 실행 이후에)
    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        Map<String, Object> beans = context.getBeansWithAnnotation(Component.class);
        for (Object bean : beans.values()) {
            for (Method method : bean.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(McpTool.class)) {
                    McpTool tool = method.getAnnotation(McpTool.class);
                    toolMap.put(tool.name(), method);
                    beanMap.put(tool.name(), bean);
                    descriptionMap.put(tool.name(), tool.description());
                }
            }
        }

        log.info("MCP Tools registered: {}", toolMap.keySet());
    }

    public Object invoke(String toolName, Object... args) throws Exception {
        Method method = toolMap.get(toolName);
        Object bean = beanMap.get(toolName);

        log.info("[Tool Invoke Info] - Tool: {}, Description: {}", toolName, descriptionMap.get(toolName));

        return method.invoke(bean, args);
    }

    public Set<String> getRegisteredTools() {
        return toolMap.keySet();
    }

}
