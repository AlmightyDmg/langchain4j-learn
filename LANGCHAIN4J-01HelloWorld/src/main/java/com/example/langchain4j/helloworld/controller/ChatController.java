package com.example.langchain4j.helloworld.controller;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
public class ChatController {

    @Value("${langchain4j.openai.api-key:demo-key}")
    private String apiKey;

    @Value("${langchain4j.openai.base-url:http://localhost:11434/v1}")
    private String baseUrl;

    @Value("${langchain4j.openai.model-name:qwen2.5:latest}")
    private String modelName;

    /**
     * 最简单的对话接口 - 阻塞式
     * GET /hello/chat?msg=你好
     */
    @GetMapping("/hello/chat")
    public String chat(@RequestParam(defaultValue = "你好，请简单介绍一下你自己") String msg) {
        var model = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .baseUrl(baseUrl)
                .modelName(modelName)
                .timeout(Duration.ofSeconds(60))
                .build();

        return model.chat(msg);
    }

    /**
     * 测试接口 - 检查服务是否正常运行
     * GET /hello/test
     */
    @GetMapping("/hello/test")
    public String test() {
        return "LangChain4j HelloWorld 服务运行正常！当前时间：" + LocalDateTime.now();
    }
}
