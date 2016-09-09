package integration.com.otpp.annualstatement.service.integration;

import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

public class SftpOutboundTransferManager {

    private final CachingSessionFactory sftpSessionFactory;

    private final MessageChannel inputChannel;

    public SftpOutboundTransferManager(CachingSessionFactory cachingSessionFactory, MessageChannel messageChannel) {
        sftpSessionFactory = cachingSessionFactory;
        inputChannel = messageChannel;
    }

    public boolean send(byte[] fileContent, String filename,String fileDirectory) {
        Message<byte[]> message = MessageBuilder.withPayload(fileContent).setHeader("filename", filename).setHeader
                ("fileDirectory", fileDirectory).build();

        return inputChannel.send(message);
    }

}
