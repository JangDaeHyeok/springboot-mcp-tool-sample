package com.jdh.mcpToolSample.domain;

import com.jdh.mcpToolSample.annotation.McpTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Product Tool 정의 클래스
 */
@Component
@Slf4j
public class ProductTool {

    @McpTool(name = "get_product_ids_by_keyword", description = "키워드로 상품 ID 목록 검색")
    public List<String> getProductIdsByKeyword(String keyword) {
        log.info("[get_product_ids_by_keyword Tool] - keyword: {}", keyword);

        // 실제 로직
        return List.of("product-1", "product-2", "product-3");
    }

    @McpTool(name = "get_products", description = "상품 ID 목록으로 상세 조회")
    public String getProducts(List<String> productIds, String storeId, String regionId) {
        log.info("[get_products Tool] - productIds: {}, storeId: {}, regionId: {}", productIds, storeId, regionId);

        // 실제 로직
        return "상품 개수: " + productIds.size() + ", 매장: " + storeId + ", 지역: " + regionId;
    }

}
