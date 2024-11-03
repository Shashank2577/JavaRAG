package dev.shash.bns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.pdf.ParagraphPdfDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IngestionService implements CommandLineRunner {

    @Autowired
    private final EmbeddingModel embeddingModel;

    private static final Logger log = LoggerFactory.getLogger(IngestionService.class);

    private final VectorStore vectorStore;

    @Value("classpath:/docs/Bharatiya_Nyaya_Sanhita_2023.pdf")
    private Resource marketPDF;

    // ZHI PU AI
    @Autowired
    public IngestionService(@Qualifier("zhiPuAiEmbeddingModel") EmbeddingModel embeddingModel, VectorStore vectorStore) {
        this.embeddingModel = embeddingModel;
        this.vectorStore = vectorStore;
    }

// OPEN AI
//    @Autowired
//    public IngestionService(@Qualifier("openAiEmbeddingModel") EmbeddingModel embeddingModel, VectorStore vectorStore) {
//        this.embeddingModel = embeddingModel;
//        this.vectorStore = vectorStore;
//    }

    @Override
    public void run(String... args) throws Exception {

        if (false) {
            log.info("Loading VectorStore with data...");
            var pdfReader = new ParagraphPdfDocumentReader(marketPDF);
            TextSplitter textSplitter = new TokenTextSplitter();
            log.info("Reading PDF...of size {}", pdfReader.get().size());

            List<Document> documents = textSplitter.apply((pdfReader.get()));
            log.info("Documents split into {}", documents.size());
            vectorStore.accept(documents);
            log.info("VectorStore Loaded with data!");
        }
    }

}
