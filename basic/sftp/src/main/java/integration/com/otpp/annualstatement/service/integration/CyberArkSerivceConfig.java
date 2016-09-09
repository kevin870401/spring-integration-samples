package integration.com.otpp.annualstatement.service.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.messaging.MessageChannel;

@Configuration
@ImportResource("classpath:/META-INF/spring/integration/SftpOutboundTransfer.xml")
public class CyberArkSerivceConfig {

    @Autowired
    CachingSessionFactory sftpSessionFactory;

    @Autowired
    MessageChannel inputChannel;

    @Bean
    public SftpOutboundTransferManager sftpOutboundTransferManager(){

        return new SftpOutboundTransferManager(sftpSessionFactory,inputChannel);
    }
}
