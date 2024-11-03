package dev.shash.bns;


import dev.shash.bns.Advisors.ReReadingAdvisor;
import dev.shash.bns.Advisors.SimpleLoggerAdvisor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ChatController {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    private ChatClient chatClient;

    public ChatController(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore,
                        SearchRequest.defaults()),
                new ReReadingAdvisor(),
                new SimpleLoggerAdvisor())
                .build();
    }

    @GetMapping("/chat")
    public LawObject chat(@RequestParam(defaultValue = "what is murder and its related law?") String question) {

        return chatClient.prompt()
                .system("Answer the user's question based on the provided Bhartiya Nyay Sahita provided in context, without using any prior knowledge. Provide a response in the specified format, and perform any necessary translations. Focus solely on the context and do not incorporate any external information.")
                .user(question)
                .call()
                .entity(LawObject.class);
    }
}
