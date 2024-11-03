package dev.shash.bns;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public ChatClient openAIchatModel(OpenAiChatModel openAiChatModel) {
//		return ChatClient.create(openAiChatModel);
//	}

	@Bean
	public ChatClient chatClient(ZhiPuAiChatModel zhiPuAiChatModel) {
		return ChatClient.create(zhiPuAiChatModel);
	}

//	@Bean
//	public ChatClient chatClient(MistralAiChatModel mistralAiChatModel) {
//		return ChatClient.create(mistralAiChatModel);
//	}
//
//	@Bean
//	public ChatModel chatModel(@Qualifier("mistralAiChatModel") ChatModel chatModel) {
//		return chatModel;
//
//	}
}